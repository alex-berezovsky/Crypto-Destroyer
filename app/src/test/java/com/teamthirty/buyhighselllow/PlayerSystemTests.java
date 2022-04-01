package com.teamthirty.buyhighselllow;

import com.teamthirty.buyhighselllow.Systems.PlayerSystem;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import org.junit.Assert;
import org.junit.Test;

public class PlayerSystemTests {

    /**
     * M4 Test that tests PlayerSystem instantiation is correct for all difficulty levels
     */
    @Test
    public void playerSystemInstantiation() {
        PlayerSystem playerEasy = new PlayerSystem(Difficulty.EASY);
        PlayerSystem playerStandard = new PlayerSystem(Difficulty.STANDARD);
        PlayerSystem playerHard = new PlayerSystem((Difficulty.HARD));

        Assert.assertEquals(playerEasy.getCryptoWhaleCost(), 500);
        Assert.assertEquals(playerEasy.getRedditDudeCost(), 300);
        Assert.assertEquals(playerEasy.getTradingChadCost(), 100);

        Assert.assertEquals(playerStandard.getCryptoWhaleCost(), 600);
        Assert.assertEquals(playerStandard.getRedditDudeCost(), 400);
        Assert.assertEquals(playerStandard.getTradingChadCost(), 200);

        Assert.assertEquals(playerHard.getCryptoWhaleCost(), 700);
        Assert.assertEquals(playerHard.getRedditDudeCost(), 500);
        Assert.assertEquals(playerHard.getTradingChadCost(), 300);
    }

    /**
     * M4 Test that finds the appropriate initial cost for a given tower type
     */
    @Test
    public void testCosts() {
        PlayerSystem playerStandard = new PlayerSystem(Difficulty.STANDARD);
        Assert.assertEquals(playerStandard.findInitialCost(TowerType.TradingChad), 200);
        Assert.assertEquals(playerStandard.findInitialCost(TowerType.RedditDude), 400);
        Assert.assertEquals(playerStandard.findInitialCost(TowerType.CryptoWhale), 600);
    }

    /**
     * M4 Test that checks that insufficient funds are correctly handled when buying a tower
     */
    @Test
    public void testInsufficientFunds() {
        PlayerSystem playerStandard = new PlayerSystem(Difficulty.STANDARD);
        playerStandard.setMoney(100);
        Assert.assertFalse(playerStandard.buyTower(TowerType.TradingChad, null, null, 0, 0, null));
    }
}
