package com.teamthirty.buyhighselllow.Systems;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;

import androidx.core.util.Pair;
import androidx.gridlayout.widget.GridLayout;

import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.ElonMusk;
import com.teamthirty.buyhighselllow.Entities.Enemies.Enemy;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.EndGameScreen;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.GameScreen;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.WinScreen;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import com.teamthirty.buyhighselllow.Utilities.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    private final GameScreen gameScreen;

    public GameController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void generatePath() {
        // THIS IS HARD-CODED AND NEEDS TO GO
        gameScreen.setPath(new ArrayList<Pair<Integer, Integer>>());
        gameScreen.getPath().add(new Pair<Integer, Integer>(null, null));
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
        } else if (gameScreen.getRoundCounter() == 2) {
            gameScreen.getUnspawnedList().add(new DogeCoin());
            gameScreen.getUnspawnedList().add(new DogeCoin());
            gameScreen.getUnspawnedList().add(new DogeCoin());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new Etherium());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new Etherium());
        } else if (gameScreen.getRoundCounter() == 3) {
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new BitCoin());
            gameScreen.getUnspawnedList().add(new BitCoin());
        } else if (gameScreen.getRoundCounter() == 4) {
            gameScreen.getUnspawnedList().add(new ElonMusk());
        }

        Timer timer = new Timer();
        TimerTask megaTimer = new TimerTask() {
            @Override
            public void run() {
                for (Tower tower : GameScreen.getTowerList()) {
                    if (tower instanceof RedditDude) {
                        int col = tower.getPosition().second;
                        ArrayList<Enemy> spawnedList = GameScreen.getSpawnedList();
                        for (Enemy enemy : spawnedList) {
                            if (enemy.getPosition().second == col) {
                                if (enemy.takeDamage(tower.getDamage())) {
                                    enemy.setDamaged(true);
                                    enemy.setDelete(true);
                                    if (enemy instanceof ElonMusk) {
                                        spawnedList.remove(enemy);
                                        winGame();
                                    }
                                } else {
                                    enemy.setDamaged(true);
                                }
                            }
                        }
                    } else if (tower instanceof TradingChad) {
                        int col = tower.getPosition().second;
                        ArrayList<Enemy> spawnedList = GameScreen.getSpawnedList();
                        for (Enemy enemy : spawnedList) {
                            if (enemy.getPosition().second == col) {
                                System.out.println("Trading damage: " + tower.getDamage());
                                if (enemy.takeDamage(tower.getDamage())) {
                                    enemy.setDamaged(true);
                                    enemy.setDelete(true);

                                    if (enemy instanceof ElonMusk) {
                                        spawnedList.remove(enemy);
                                        winGame();
                                    }
                                } else {
                                    enemy.setDamaged(true);
                                }
                            }
                        }
                    } else if (tower instanceof CryptoWhale) {
                        System.out.println("found cryptowhale, upgrading damage");
                        for (Tower otherTower : GameScreen.getTowerList()) {
                            otherTower.setDamage(otherTower.getDamage() + ((CryptoWhale) tower)
                                    .getDamageAdder());
                        }
                    }
                }

                // draw background tiles
                drawBackground();

                // spawn in and draw enemy colors
                spawnEnemies();


                // Updates position of each enemy in spawned list and displays on screen
                if (gameScreen.getSpawnedList().isEmpty()) {
                    timer.purge();
                    timer.cancel();
                    GameScreen.setCash(GameScreen.getCash() + 400 + 10
                            * gameScreen.getRoundCounter());
                    gameScreen.setRoundCounter(gameScreen.getRoundCounter() + 1);
                    Util.setText(gameScreen, gameScreen.getRoundCounterText(),
                            "Round: " + gameScreen.getRoundCounter());
                    Util.setText(gameScreen, gameScreen.getCashText(),
                            "Player Cash: " + GameScreen.getCash());
                } else {
                    updateEnemies();
                }
            }
        };
        timer.scheduleAtFixedRate(megaTimer, 0, 500);
    }

    public void updateEnemies() {
        for (int i = 0; i < gameScreen.getSpawnedList().size(); i++) {
            Enemy enemy = gameScreen.getSpawnedList().get(i);

            Pair<Integer, Integer> position = enemy.getPosition();
            if (position.first != null) {
                int row = position.first;
                int column = position.second;


                if (enemy.getDelete()) {
                    gameScreen.setEnemiesKilled(gameScreen.getEnemiesKilled() + 1);
                    gameScreen.getSpawnedList().remove(i--);
                }

                drawEnemy(enemy, gameScreen.getMapArray(), position);

                boolean atEnd = enemy.updatePosition(gameScreen.getPath());

                if (atEnd) {
                    gameScreen.getMapArray()[row][column].setBackgroundColor(Color.MAGENTA);
                    gameScreen.getSpawnedList().remove(enemy);
                    i--;
                    gameScreen.setMonumentHealth(gameScreen.getMonumentHealth()
                            - enemy.getDamage()
                            * 10); //remove this post-demo
                    gameScreen.getMonumentHealthText()
                            .setText("Monument HP: " + gameScreen.getMonumentHealth());

                    if (gameScreen.getMonumentHealth() <= 0 && gameScreen.getHasNotFinished()) {
                        Intent intent = new Intent(gameScreen, EndGameScreen.class);
                        intent.putExtra("enemiesKilled",
                                gameScreen.getEnemiesKilled());
                        intent.putExtra("cashEarned",
                                GameScreen.getCash());
                        intent.putExtra("roundsPlayed",
                                gameScreen.getRoundCounter());
                        gameScreen.startActivity(intent);
                        gameScreen.setHasNotFinished(false);
                    }
                    Util.setText(gameScreen, gameScreen.getMonumentHealthText(),
                            "Monument HP: " + gameScreen.getMonumentHealth());
                }
            } else {
                enemy.updatePosition(gameScreen.getPath());
            }
        }
    }

    public void spawnEnemies() {
        if (!gameScreen.getUnspawnedList().isEmpty()) {
            Enemy enemy = gameScreen.getUnspawnedList().remove(0);
            gameScreen.getSpawnedList().add(enemy);
            gameScreen.getSpawnedList().get(gameScreen.getSpawnedList().size() - 1)
                    .setPosition(gameScreen.getPath().get(0));
        }
    }

    public void drawBackground() {
        // Checks if each tile is occupied by an enemy. If not occupied, set to grey
        for (int i = 1; i < gameScreen.getPath().size() - 2; i++) {
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
        if (enemy.isDamaged()) {
            map[integerPair.first][integerPair.second].setBackgroundColor(Color.RED);
            enemy.setDamaged(false);
            float[] hsv = new float[3];
            Color.colorToHSV(enemy.getColor(), hsv);
            System.out.println("Old HSV: " + Arrays.toString(hsv));
            System.out.println(
                    "cur health: " + enemy.getHealth() + ", max health: " + enemy.getMaxHealth());
            hsv[2] = (float) Math.pow((enemy.getHealth() * 1.0) / enemy.getMaxHealth(), 1.1);
            System.out.println("New HSV: " + Arrays.toString(hsv));
            enemy.setColor(Color.HSVToColor(hsv));
        } else {
            map[integerPair.first][integerPair.second].setBackgroundColor(enemy.getColor());
        }
    }

    public void winGame() {
        gameScreen.setEnemiesKilled(gameScreen.getEnemiesKilled() + 1);
        Intent intent = new Intent(gameScreen, WinScreen.class);
        intent.putExtra("enemiesKilled",
                gameScreen.getEnemiesKilled());
        intent.putExtra("cashEarned",
                GameScreen.getCash());
        intent.putExtra("roundsPlayed",
                gameScreen.getRoundCounter());
        gameScreen.startActivity(intent);
    }
}