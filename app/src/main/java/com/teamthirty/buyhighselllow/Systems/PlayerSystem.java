package com.teamthirty.buyhighselllow.Systems;

import android.content.Context;
import android.widget.Toast;
import com.teamthirty.buyhighselllow.Entities.Entity;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;

import java.util.List;

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

    public PlayerSystem(List<Entity> entityList) {
        super(entityList);
    }

    public boolean buyTower(Tower tower, TowerType type, Context context) {
        int initialCost;
        if (type.equals(TowerType.TradingChad)) {
            initialCost = tradingChadCost;
        } else if (type == TowerType.RedditDude) {
            initialCost = redditDudeCost;
        } else if (type == TowerType.CryptoWhale) {
            initialCost = cryptoWhaleCost;
        } else {
            return false;
        }
        if (initialCost <= money) {
            money -= initialCost;
            placeTower(tower);
            return true;
        } else {
            String errorMessage = "Cannot afford tower!";
            int popUpDuration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, errorMessage, popUpDuration);
            toast.show();
            return false;
        }
    }

    private void placeTower(Tower tower) {

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
