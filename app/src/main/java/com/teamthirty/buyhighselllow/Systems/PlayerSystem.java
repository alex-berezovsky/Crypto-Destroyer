package com.teamthirty.buyhighselllow.Systems;

import android.content.Context;
import android.widget.Toast;
import com.teamthirty.buyhighselllow.Entities.Entity;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;

import java.util.List;

public class PlayerSystem extends System {
    // tower cost constants
    private int TRADINGCHADCOST;
    private int REDDITDUDECOST;
    private int CRYPTOWHALECOST;
    // private variables
    private int money;

    public PlayerSystem(Difficulty difficulty) {
        if(difficulty.equals(Difficulty.EASY)) {
            TRADINGCHADCOST = 100;
            REDDITDUDECOST = 300;
            CRYPTOWHALECOST = 500;
        } else if (difficulty.equals(Difficulty.STANDARD)) {
            TRADINGCHADCOST = 200;
            REDDITDUDECOST = 400;
            CRYPTOWHALECOST = 600;
        } else if (difficulty.equals(Difficulty.HARD)) {
            TRADINGCHADCOST = 300;
            REDDITDUDECOST = 500;
            CRYPTOWHALECOST = 700;
        }
    }

    public PlayerSystem(List<Entity> entityList) {
        super(entityList);
    }

    public boolean buyTower(Tower tower, TowerType type, Context context) {
        int initialCost;
        if (type.equals(TowerType.TradingChad)) {
            initialCost = TRADINGCHADCOST;
        } else if (type == TowerType.RedditDude) {
            initialCost = REDDITDUDECOST;
        } else if (type == TowerType.CryptoWhale) {
            initialCost = CRYPTOWHALECOST;
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
