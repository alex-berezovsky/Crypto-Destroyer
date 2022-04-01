package com.teamthirty.buyhighselllow.Systems;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import com.teamthirty.buyhighselllow.Entities.Entity;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import com.teamthirty.buyhighselllow.Utilities.Util;

import java.util.ArrayList;

public class PlayerSystem extends System {
    // tower cost constants
    private int tradingChadCost;
    private int redditDudeCost;
    private int cryptoWhaleCost;
    // private variables
    private int money;

    public PlayerSystem(Difficulty difficulty) {
        if (difficulty.equals(Difficulty.EASY)) {
            tradingChadCost = 100;
            redditDudeCost = 300;
            cryptoWhaleCost = 500;
        } else if (difficulty.equals(Difficulty.STANDARD)) {
            tradingChadCost = 200;
            redditDudeCost = 400;
            cryptoWhaleCost = 600;
        } else if (difficulty.equals(Difficulty.HARD)) {
            tradingChadCost = 300;
            redditDudeCost = 500;
            cryptoWhaleCost = 700;
        }
    }

    public PlayerSystem(ArrayList<Entity> entityList) {
        super(entityList);
    }

    public boolean buyTower(TowerType towerType, Context context, Button[][] mapArray, int row,
                            int column, Activity activity) {
        int initialCost = findInitialCost(towerType);
        if (initialCost == -1) {
            return false;
        }
        if (initialCost <= money) {
            money -= initialCost;
            placeTower(towerType, mapArray, row, column, activity);
            return true;
        } else {
            Util.displayError(context, "Cannot afford tower!");
            return false;
        }
    }

    private void placeTower(TowerType towerType, Button[][] mapArray, int row, int column,
                            Activity activity) {
        if (towerType.equals(TowerType.RedditDude)) {
            mapArray[row][column].setBackgroundColor(Color.YELLOW);
        } else if (towerType.equals(TowerType.TradingChad)) {
            mapArray[row][column].setBackgroundColor(Color.BLUE);
        } else if (towerType.equals(TowerType.CryptoWhale)) {
            mapArray[row][column].setBackgroundColor(Color.BLACK);
        }
        TextView playerCashText = activity.findViewById(R.id.playerCash);
        playerCashText.setText("Player Cash: " + getMoney());
    }

    public int findInitialCost(TowerType towerType) {
        if (towerType.equals(TowerType.TradingChad)) {
            return tradingChadCost;
        } else if (towerType == TowerType.RedditDude) {
            return redditDudeCost;
        } else if (towerType == TowerType.CryptoWhale) {
            return cryptoWhaleCost;
        } else {
            return -1;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTradingChadCost() {
        return tradingChadCost;
    }

    public int getRedditDudeCost() {
        return redditDudeCost;
    }

    public int getCryptoWhaleCost() {
        return cryptoWhaleCost;
    }
}
