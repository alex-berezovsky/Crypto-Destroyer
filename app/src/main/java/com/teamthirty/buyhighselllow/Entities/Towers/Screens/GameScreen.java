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
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {
    private final GameController gameController = new GameController(this);
    private ArrayList<Pair<Integer, Integer>> path;
    private static Button[][] mapArray;
    private TowerType towerType;
    private PlayerSystem playerSystem;
    private int roundCounter = 1;
    private ArrayList<Enemy> unspawnedList = new ArrayList<>();
    private static ArrayList<Enemy> spawnedList = new ArrayList<>();
    private int cash = 0;
    private int monumentHealth = 0;
    private TextView monumentHealthText;
    private TextView roundCounterText;
    private Boolean hasNotFinished = true;

    public static ArrayList<Tower> getTowerList() {
        return towerList;
    }

    private static ArrayList<Tower> towerList;


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
        redditDude.setOnClickListener(view -> gameController.setTowerType(TowerType.RedditDude));
        tradingChad.setOnClickListener(view -> gameController.setTowerType(TowerType.TradingChad));
        cryptoWhale.setOnClickListener(view -> gameController.setTowerType(TowerType.CryptoWhale));

        setCash(difficulty.getCash());
        setMonumentHealth(difficulty.getMonumentHealth());
        playerSystem.setMoney(cash);

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
        TextView playerCashText = findViewById(R.id.playerCash);

        Timer textTimer = new Timer();
        TimerTask updateCashTextTask = new TimerTask() {
            @Override
            public void run() {
                Util.setText(GameScreen.this, playerCashText, "Player Cash: "
                    + playerSystem.getMoney());

            }
        };

        textTimer.scheduleAtFixedRate(updateCashTextTask, 0, 1000);

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

        Tower tower = null;
        if (path.contains(towerLocation)) {
            Util.displayError(this, "Cannot place tower on path!");
        } else {
            if (towerType == null) {
                Util.displayError(this, "Select a tower type to place!");
            } else {
                if (((ColorDrawable) mapArray[row][column].getBackground()).getColor()
                    != (Color.GREEN)) {
                    Util.displayError(this, "Cannot place tower on top of another tower!");
                } else {
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


    public static ArrayList<Enemy> getSpawnedList() {
        return spawnedList;
    }

    public TextView getRoundCounterText() {
        return roundCounterText;
    }

    public Boolean getHasNotFinished() {
        return hasNotFinished;
    }

    public int getMonumentHealth() {
        return monumentHealth;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public ArrayList<Pair<Integer, Integer>> getPath() {
        return path;
    }

    public Button[][] getMapArray() {
        return mapArray;
    }

    public TextView getMonumentHealthText() {
        return monumentHealthText;
    }

    public ArrayList<Enemy> getUnspawnedList() {
        return unspawnedList;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    public void setHasNotFinished(Boolean hasNotFinished) {
        this.hasNotFinished = hasNotFinished;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setMonumentHealth(int monumentHealth) {
        this.monumentHealth = monumentHealth;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public void setPath(
        ArrayList<Pair<Integer, Integer>> path) {
        this.path = path;
    }

    public void setMapArray(Button[][] mapArray) {
        this.mapArray = mapArray;
    }

    public PlayerSystem getPlayerSystem() {
        return playerSystem;
    }

    public void setPlayerSystem(PlayerSystem playerSystem) {
        this.playerSystem = playerSystem;
    }
}