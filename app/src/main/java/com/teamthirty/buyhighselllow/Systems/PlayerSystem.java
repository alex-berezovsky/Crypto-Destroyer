package com.teamthirty.buyhighselllow.Systems;

import com.teamthirty.buyhighselllow.Entities.Entity;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.Tower;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;

import java.util.List;

public class PlayerSystem extends System {
    // tower cost constants
    public static final int TRADINGCHADCOST = 200;
    public static final int REDDITDUDECOST = 400;
    public static final int CRYPTOWHALECOST = 600;
    // private variables
    private int money;

    public PlayerSystem() { }

    public PlayerSystem(List<Entity> entityList) {
        super(entityList);
    }

    public boolean buyTower(Tower tower) {
        int initialCost;
        if (tower instanceof TradingChad) {
            initialCost = TRADINGCHADCOST;
        } else if (tower instanceof RedditDude) {
            initialCost = REDDITDUDECOST;
        } else if (tower instanceof CryptoWhale) {
            initialCost = CRYPTOWHALECOST;
        } else {
            return false;
        }
        if (initialCost < money) {
            money -= initialCost;
            placeTower(tower);
            return true;
        } else {
            return false;
        }
    }

    private void placeTower(Tower tower) {
    }
}
