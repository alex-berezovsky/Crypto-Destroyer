package com.teamthirty.buyhighselllow.Screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.gridlayout.widget.GridLayout;
import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.Enemy;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Systems.PlayerSystem;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import com.teamthirty.buyhighselllow.Utilities.Util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {
    private GridLayout mapLayout;
    private ArrayList<Pair<Integer, Integer>> path;
    private Button[][] mapArray;
    private TowerType towerType;
    private PlayerSystem playerSystem;
    private int roundCounter = 1;
    private ArrayList<Enemy> unspawnedList = new ArrayList<>();
    private ArrayList<Enemy> spawnedList = new ArrayList<>();
    private int cash = 0;
    private int monumentHealth = 0;
    private TextView monumentHealthText;
    private TextView roundCounterText;
    private Boolean hasNotFinished = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("name");
        Difficulty difficulty = (Difficulty) extras.get("difficulty");
        playerSystem = new PlayerSystem(difficulty);

        // User interface buttons
        Button redditDude = findViewById(R.id.RedditDude);
        Button tradingChad = findViewById(R.id.TradingChad);
        Button cryptoWhale = findViewById(R.id.CryptoWhale);
        Button playButton = findViewById(R.id.playButton);
        // THIS IS HARD-CODED AND NEEDS TO GO
        path = new ArrayList<>();
        path.add(new Pair<>(3, 0));
        path.add(new Pair<>(3, 1));
        path.add(new Pair<>(3, 2));
        path.add(new Pair<>(3, 3));
        path.add(new Pair<>(3, 4));
        path.add(new Pair<>(3, 5));
        path.add(new Pair<>(3, 6));
        path.add(new Pair<>(3, 7));
        path.add(new Pair<>(3, 8));
        makeMap();

        redditDude.setOnClickListener(view -> setTowerType(TowerType.RedditDude));
        tradingChad.setOnClickListener(view -> setTowerType(TowerType.TradingChad));
        cryptoWhale.setOnClickListener(view -> setTowerType(TowerType.CryptoWhale));

        TextView playerNameText = findViewById(R.id.playerName);
        playerNameText.setText(playerName);
        monumentHealthText = findViewById(R.id.monumentHealth);
        roundCounterText = findViewById(R.id.roundText);

        TextView playerCashText = findViewById(R.id.playerCash);
        switch (difficulty) {
        case HARD: // hard difficulty
            cash = 600;
            monumentHealth = 60;
            break;
        case STANDARD: // medium difficulty
            cash = 800;
            monumentHealth = 80;
            break;
        case EASY: // easy difficulty
        default:
            // default is easy mode
            cash = 1000;
            monumentHealth = 100;
            break;
        }
        playerSystem.setMoney(cash);

        monumentHealthText.setText("Monument HP: " + monumentHealth);
        roundCounterText.setText("Round: " + roundCounter);
        playerCashText.setText("Player Cash: " + cash);
        playerCashText.setTextSize(15);

        for (Button[] buttonArray : mapArray) {
            for (Button button : buttonArray) {
                button.setOnClickListener(this);
            }
        }

        playButton.setOnClickListener(view -> startCombat());
    }

    private void setTowerType(TowerType newType) {
        towerType = newType;
    }

    private void makeMap() {
        // Layout object representing the map
        GridLayout mapLayout = findViewById(R.id.map);
        mapArray = new Button[mapLayout.getRowCount()][mapLayout.getColumnCount()];
        for (int i = 0; i < mapLayout.getRowCount(); i++) {
            for (int j = 0; j < mapLayout.getColumnCount(); j++) {
                Pair<Integer, Integer> temp = new Pair<>(i, j);
                if (!path.contains(temp)) {
                    mapArray[i][j] = makeMapButton(this, Color.GREEN, i, j);
                    mapLayout.addView(mapArray[i][j]);
                } else {
                    mapArray[i][j] = makeMapButton(this, Color.GRAY, i, j);
                    mapLayout.addView(mapArray[i][j]);
                }
            }
        }
        Pair<Integer, Integer> monumentLocation = path.get(path.size() - 1);
        int monumentRow = monumentLocation.first;
        int monumentColumn = monumentLocation.second;
        mapArray[monumentRow][monumentColumn].setBackgroundColor(Color.MAGENTA);
    }

    private Button makeMapButton(Context context, int color, int row,
                                 int column) {
        GridLayout.LayoutParams buttonParams = new GridLayout.LayoutParams();
        buttonParams.height = 0;
        buttonParams.width = 0;
        buttonParams.rowSpec = GridLayout.spec(row, (float) 1);
        buttonParams.columnSpec = GridLayout.spec(column, (float) 1);

        //Buttons on the grid that handle tower press placement
        Button button = new Button(context);
        String id;
        if (row == 0) {
            id = "999" + column;
        } else {
            id = row + "000" + column;
        }
        button.setId(Integer.parseInt(id));
        button.setBackgroundColor(color);
        button.setLayoutParams(buttonParams);

        return button;
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
                    playerSystem.buyTower(towerType, this, mapArray, row, column, this);
                }
            }
        }
    }

    // Imma be honest, we got no clue why this works but it do
    public void startCombat() {
        if (roundCounter == 1) {
            unspawnedList.add(new DogeCoin());
            unspawnedList.add(new Etherium());
            unspawnedList.add(new BitCoin());
            unspawnedList.add(new DogeCoin());
        }

        Timer timer = new Timer();
        TimerTask updateEnemyPosition = new TimerTask() {
            @Override
            public void run() {
                // Checks if each tile is occupied by an enemy. If not occupied, set to grey
                for (int i = 0; i < path.size() - 1; i++) {
                    Pair<Integer, Integer> location = path.get(i);
                    int row = location.first;
                    int column = location.second;
                    boolean occupied = false;
                    for (Enemy enemy : spawnedList) {
                        if (enemy.getPosition().equals(location)) {
                            occupied = true;
                            break;
                        }
                    }
                    if (!occupied) {
                        mapArray[row][column].setBackgroundColor(Color.GRAY);
                    }
                }

                if (!unspawnedList.isEmpty()) {
                    Enemy enemy = unspawnedList.remove(0);
                    spawnedList.add(enemy);
                    spawnedList.get(spawnedList.size() - 1).setPosition(path.get(0));

                    if (enemy instanceof DogeCoin) {
                        mapArray[path.get(0).first][path.get(0).second].
                            setBackgroundColor(Color.WHITE);
                    } else if (enemy instanceof Etherium) {
                        mapArray[path.get(0).first][path.get(0).second].
                            setBackgroundColor(Color.CYAN);
                    } else if (enemy instanceof BitCoin) {
                        mapArray[path.get(0).first][path.get(0).second].
                            setBackgroundColor(Color.DKGRAY);
                    }
                }


                // Updates position of each enemy in spawned list and displays on screen
                if (!spawnedList.isEmpty()) {
                    for (int i = 0; i < spawnedList.size(); i++) {
                        Enemy enemy = spawnedList.get(i);

                        Pair<Integer, Integer> position = enemy.getPosition();
                        int row = position.first;
                        int column = position.second;

                        if (enemy instanceof DogeCoin) {
                            mapArray[row][column].setBackgroundColor(Color.WHITE);
                        } else if (enemy instanceof Etherium) {
                            mapArray[row][column].setBackgroundColor(Color.CYAN);
                        } else if (enemy instanceof BitCoin) {
                            mapArray[row][column].setBackgroundColor(Color.DKGRAY);
                        }
                        boolean atEnd = enemy.updatePosition(path);

                        if (atEnd) {
                            spawnedList.remove(enemy);
                            i--;
                            monumentHealth -= enemy.getDamage()*10;
                            monumentHealthText.setText("Monument HP: " + monumentHealth);

                            if (monumentHealth <= 0 && hasNotFinished) {
                                Intent intent = new Intent(GameScreen.this, EndGameScreen.class);
                                startActivity(intent);
                                hasNotFinished = false;
                            }
                        }
                    }
                } else {
                    timer.purge();
                    timer.cancel();
                    mapArray[path.get(path.size() - 2).first][path.get(path.size() - 2).second].
                        setBackgroundColor(Color.GRAY);
                    roundCounter++;

                    // FIGURE OUT WHY THIS CRASHES THE APP
                    //roundCounterText.setText("Round: " + roundCounter);
                }
            }
        };

        timer.scheduleAtFixedRate(updateEnemyPosition, 500, 500);
    }
}