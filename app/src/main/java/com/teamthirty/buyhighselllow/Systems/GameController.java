package com.teamthirty.buyhighselllow.Systems;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import androidx.core.util.Pair;
import androidx.gridlayout.widget.GridLayout;
import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.Enemy;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.EndGameScreen;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.GameScreen;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import com.teamthirty.buyhighselllow.Utilities.Util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    private final GameScreen gameScreen;

    public GameController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
        case HARD: // hard difficulty
            gameScreen.setCash(Difficulty.HARD.getCash());
            gameScreen.setMonumentHealth(Difficulty.HARD.getMonumentHealth());
            break;
        case STANDARD: // medium difficulty
            gameScreen.setCash(Difficulty.STANDARD.getCash());
            gameScreen.setMonumentHealth(Difficulty.STANDARD.getMonumentHealth());
            break;
        case EASY: // easy difficulty
        default:
            // default is easy mode
            gameScreen.setCash(Difficulty.EASY.getCash());
            gameScreen.setMonumentHealth(Difficulty.EASY.getMonumentHealth());
            break;
        }
    }

    public void generatePath() {
        // THIS IS HARD-CODED AND NEEDS TO GO
        gameScreen.setPath(new ArrayList<Pair<Integer, Integer>>());
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 0));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 1));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 2));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 3));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 4));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 5));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 6));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 7));
        gameScreen.getPath().add(new Pair<Integer, Integer>(3, 8));
        gameScreen.getPath().add(new Pair<Integer, Integer>(null, null));
    }

    public void setTowerType(TowerType newType) {
        gameScreen.setTowerType(newType);
    }

    public void generateMap() {
        // Layout object representing the map
        GridLayout mapLayout = gameScreen.findViewById(R.id.map);
        gameScreen.setMapArray(new Button[mapLayout.getRowCount()][mapLayout.getColumnCount()]);
        for (int i = 0; i < mapLayout.getRowCount(); i++) {
            for (int j = 0; j < mapLayout.getColumnCount(); j++) {
                Pair<Integer, Integer> curPosition = new Pair<Integer, Integer>(i, j);
                if (gameScreen.getPath().contains(curPosition)) {
                    gameScreen.getMapArray()[i][j] = makeMapButton(gameScreen, Color.GRAY, i, j);
                    mapLayout.addView(gameScreen.getMapArray()[i][j]);
                } else {
                    gameScreen.getMapArray()[i][j] = makeMapButton(gameScreen, Color.GREEN, i, j);
                    mapLayout.addView(gameScreen.getMapArray()[i][j]);
                }
            }
        }
        // hard-coded monument location
        Pair<Integer, Integer> monumentLocation =
            gameScreen.getPath().get(gameScreen.getPath().size() - 2);
        int monumentRow = monumentLocation.first;
        int monumentColumn = monumentLocation.second;
        gameScreen.getMapArray()[monumentRow][monumentColumn].setBackgroundColor(Color.MAGENTA);
    }

    public Button makeMapButton(Context context, int color, int row, int column) {
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
    } // Imma be honest, we got no clue why this works but it do

    public void startCombat() {
        if (gameScreen.getRoundCounter() == 1) {
            gameScreen.getUnspawnedList().add(new DogeCoin());
            gameScreen.getUnspawnedList().add(new Etherium());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new DogeCoin());
        }

        Timer timer = new Timer();
        TimerTask updateEnemyPosition = new TimerTask() {
            @Override
            public void run() {
                // draw background tiles
                drawBackground();

                // spawn in and draw enemy colors
                spawnEnemies();


                // Updates position of each enemy in spawned list and displays on screen
                if (gameScreen.getSpawnedList().isEmpty()) {
                    timer.purge();
                    timer.cancel();
                    gameScreen.setRoundCounter(gameScreen.getRoundCounter() + 1);
                    Util.setText(gameScreen, gameScreen.getRoundCounterText(),
                                 "Round: " + gameScreen.getRoundCounter());
                } else {
                    updateEnemies();
                }
            }
        };

        TimerTask redditTask = new TimerTask() {
            @Override
            public void run() {
                for (Tower tower: GameScreen.towerList) {
                    if (tower instanceof RedditDude) {
                        int col = tower.getPosition().second;
                        ArrayList<Enemy> spawnedList = GameScreen.spawnedList;
                        for (Enemy enemy : spawnedList) {
                            if (enemy.getPosition().second == col) {
                                if (enemy.takeDamage(tower.getDamage())) {
                                    enemy.setDelete(true);
                                }
                            }
                        }
                    }
                }
            }
        };

        TimerTask tradingTask = new TimerTask() {
            @Override
            public void run() {
                for (Tower tower: GameScreen.towerList) {
                    if (tower instanceof TradingChad) {
                        int col = tower.getPosition().second;
                        ArrayList<Enemy> spawnedList = GameScreen.spawnedList;
                        for (Enemy enemy : spawnedList) {
                            if (enemy.getPosition().second == col) {
                                if (enemy.takeDamage(tower.getDamage())) {
                                    enemy.setDelete(true);
                                }
                            }
                        }
                    }
                }
            }
        };

        timer.scheduleAtFixedRate(redditTask, 0, 250);
        timer.scheduleAtFixedRate(tradingTask, 0, 125);
        timer.scheduleAtFixedRate(updateEnemyPosition, 500, 500);
    }

    public void updateEnemies() {
        for (int i = 0; i < gameScreen.getSpawnedList().size(); i++) {
            Enemy enemy = gameScreen.getSpawnedList().get(i);

            Pair<Integer, Integer> position = enemy.getPosition();
            int row = position.first;
            int column = position.second;

            drawEnemy(enemy, gameScreen.getMapArray(), position);

            if (enemy.getDelete()) {
                gameScreen.getSpawnedList().remove(i--);
            }

            boolean atEnd = enemy.updatePosition(gameScreen.getPath());

            if (atEnd) {
                gameScreen.getMapArray()[row][column].setBackgroundColor(Color.MAGENTA);
                gameScreen.getSpawnedList().remove(enemy);
                i--;
                gameScreen.setMonumentHealth(gameScreen.getMonumentHealth()
                                                 - enemy.getDamage() * 10); //remove this post-demo
                gameScreen.getMonumentHealthText()
                    .setText("Monument HP: " + gameScreen.getMonumentHealth());

                if (gameScreen.getMonumentHealth() <= 0 && gameScreen.getHasNotFinished()) {
                    Intent intent = new Intent(gameScreen, EndGameScreen.class);
                    gameScreen.startActivity(intent);
                    gameScreen.setHasNotFinished(false);
                }
                Util.setText(gameScreen, gameScreen.getMonumentHealthText(),
                             "Monument HP: " + gameScreen.getMonumentHealth());

            }
        }
    }

    public void spawnEnemies() {
        if (!gameScreen.getUnspawnedList().isEmpty()) {
            Enemy enemy = gameScreen.getUnspawnedList().remove(0);
            gameScreen.getSpawnedList().add(enemy);
            gameScreen.getSpawnedList().get(gameScreen.getSpawnedList().size() - 1)
                .setPosition(gameScreen.getPath().get(0));

            drawEnemy(enemy, gameScreen.getMapArray(), gameScreen.getPath().get(0));
        }
    }

    public void drawBackground() {
        // Checks if each tile is occupied by an enemy. If not occupied, set to grey
        for (int i = 0; i < gameScreen.getPath().size() - 2; i++) {
            Pair<Integer, Integer> location = gameScreen.getPath().get(i);
            int row = location.first;
            int column = location.second;
            boolean occupied = false;
            for (Enemy enemy : gameScreen.getSpawnedList()) {
                if (enemy.getPosition().equals(location)) {
                    occupied = true;
                    break;
                }
            }
            if (!occupied) {
                gameScreen.getMapArray()[row][column].setBackgroundColor(Color.GRAY);
            }
        }
    }

    public void drawEnemy(Enemy enemy, Button[][] map,
                          Pair<Integer, Integer> integerPair) {
        if (enemy instanceof DogeCoin) {
            map[integerPair.first][integerPair.second].setBackgroundColor(Color.WHITE);
        } else if (enemy instanceof Etherium) {
            map[integerPair.first][integerPair.second].setBackgroundColor(Color.CYAN);
        } else if (enemy instanceof BitCoin) {
            map[integerPair.first][integerPair.second].setBackgroundColor(Color.DKGRAY);
        }
    }
}