package com.teamthirty.buyhighselllow.Entities.Towers.Screens;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.teamthirty.buyhighselllow.Entities.Enemies.Enemy;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Systems.GameController;
import com.teamthirty.buyhighselllow.Systems.PlayerSystem;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import com.teamthirty.buyhighselllow.Utilities.Util;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {
    private static Button[][] mapArray;
    private static ArrayList<Enemy> spawnedList = new ArrayList<>();
    private static int cash = 0;
    private static ArrayList<Tower> towerList;
    private final GameController gameController = new GameController(this);
    private ArrayList<Pair<Integer, Integer>> path;
    private TowerType towerType;
    private PlayerSystem playerSystem;
    private int roundCounter = 1;
    private ArrayList<Enemy> unspawnedList = new ArrayList<>();
    private int monumentHealth = 0;
    private TextView monumentHealthText;
    private TextView roundCounterText;
    private TextView playerCashText;
    private Boolean hasNotFinished = true;
    private int enemiesKilled = 0;

    public static ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public static ArrayList<Enemy> getSpawnedList() {
        return spawnedList;
    }

    public static int getCash() {
        return cash;
    }

    public static void setCash(int cashnew) {
        cash = cashnew;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle extras = getIntent().getExtras();

        String playerName = extras.getString("name");
        Difficulty difficulty = (Difficulty) extras.get("difficulty");
        playerSystem = new PlayerSystem(difficulty);

        // User interface buttons
        Button playButton = findViewById(R.id.playButton);
        Button redditDude = findViewById(R.id.RedditDude);
        Button tradingChad = findViewById(R.id.TradingChad);
        Button cryptoWhale = findViewById(R.id.CryptoWhale);
        Button clearSelection = findViewById(R.id.clearSelection);
        redditDude.setOnClickListener(view -> gameController.setTowerType(TowerType.RedditDude));
        tradingChad.setOnClickListener(view -> gameController.setTowerType(TowerType.TradingChad));
        cryptoWhale.setOnClickListener(view -> gameController.setTowerType(TowerType.CryptoWhale));
        clearSelection.setOnClickListener(view -> gameController.setTowerType(null));

        setCash(difficulty.getCash());
        setMonumentHealth(difficulty.getMonumentHealth());


        // create path for enemies to follow
        gameController.generatePath();

        // color in map tiles
        gameController.generateMap();

        // set player name text
        TextView playerNameText = findViewById(R.id.playerName);
        playerNameText.setText(playerName);

        // set monument health text
        monumentHealthText = findViewById(R.id.monumentHealth);
        Util.setText(GameScreen.this, monumentHealthText, "Monument HP: " + monumentHealth);

        // set round text
        roundCounterText = findViewById(R.id.roundText);
        Util.setText(GameScreen.this, roundCounterText, "Round: " + roundCounter);

        // set balance text
        playerCashText = findViewById(R.id.playerCash);
        Util.setText(GameScreen.this, playerCashText, "Player Cash: " + cash);

        towerList = new ArrayList<>();
        // set onClick listener for all buttons
        for (Button[] buttonArray : mapArray) {
            for (Button button : buttonArray) {
                button.setOnClickListener(this);
            }
        }

        playButton.setOnClickListener(view -> gameController.startCombat());
    }

    @Override
    public void onClick(View v) {
        Pair<Integer, Integer> towerLocation = Util.towerLocation(v.getId());
        int row = towerLocation.first;
        int column = towerLocation.second;

        if (path.contains(towerLocation)) {
            Util.displayError(this, "Cannot place tower on path!");
        } else {
            if (towerType == null && ((ColorDrawable) mapArray[row][column].getBackground())
                    .getColor() == (Color.GREEN)) {
                Util.displayError(this, "Select a tower type to place!");
            } else if (towerType == null && ((ColorDrawable) mapArray[row][column].getBackground())
                    .getColor() != (Color.GREEN)) {
                for (Tower tower : towerList) {
                    if (tower.getPosition().first == row && tower.getPosition().second == column) {
                        if (tower.getUpgradeCost() <= cash) {
                            System.out.println(tower.getUpgradeCost());
                            cash -= tower.getUpgradeCost();
                            System.out.println(cash);
                            Util.setText(GameScreen.this, playerCashText, "Player Cash: "
                                    + cash);
                            tower.levelUp();
                            Util.displayError(this, "Tower leveled up!");

                            float[] hsv = new float[3];
                            Color.colorToHSV(((ColorDrawable) mapArray[row][column].getBackground())
                                    .getColor(), hsv);
                            hsv[2] = (float) (hsv[2] * .9);
                            mapArray[row][column].setBackgroundColor(Color.HSVToColor(hsv));
                        } else {
                            Util.displayError(this, "Not enough money to level tower up!");
                        }
                    }
                }
            } else {
                if (((ColorDrawable) mapArray[row][column].getBackground()).getColor()
                        != (Color.GREEN)) {
                    Util.displayError(this, "Cannot place tower on top of another tower!");
                } else {
                    Tower tower = null;
                    if (towerType.equals(TowerType.RedditDude)) {
                        tower = new RedditDude(towerLocation);
                    } else if (towerType.equals(TowerType.TradingChad)) {
                        tower = new TradingChad(towerLocation);
                    } else if (towerType.equals(TowerType.CryptoWhale)) {
                        tower = new CryptoWhale(towerLocation);
                    }

                    //playerSystem.addEntity(tower);
                    if (playerSystem.buyTower(towerType, this, mapArray, row, column, this)) {
                        towerList.add(tower);
                    }

                }
            }
        }
    }

    public TextView getRoundCounterText() {
        return roundCounterText;
    }

    public Boolean getHasNotFinished() {
        return hasNotFinished;
    }

    public void setHasNotFinished(Boolean hasNotFinished) {
        this.hasNotFinished = hasNotFinished;
    }

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public void setMonumentHealth(int monumentHealth) {
        this.monumentHealth = monumentHealth;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public ArrayList<Pair<Integer, Integer>> getPath() {
        return path;
    }

    public void setPath(
            ArrayList<Pair<Integer, Integer>> path) {
        this.path = path;
    }

    public Button[][] getMapArray() {
        return mapArray;
    }

    public void setMapArray(Button[][] mapArray) {
        this.mapArray = mapArray;
    }

    public TextView getMonumentHealthText() {
        return monumentHealthText;
    }

    public TextView getCashText() {
        return playerCashText;
    }

    public ArrayList<Enemy> getUnspawnedList() {
        return unspawnedList;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    public PlayerSystem getPlayerSystem() {
        return playerSystem;
    }

    public void setPlayerSystem(PlayerSystem playerSystem) {
        this.playerSystem = playerSystem;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
}