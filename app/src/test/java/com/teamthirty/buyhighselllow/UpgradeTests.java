package com.teamthirty.buyhighselllow;

import static org.junit.Assert.assertEquals;

import androidx.core.util.Pair;

import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;

import org.junit.Test;

public class UpgradeTests {
    /**
     * Checks if damage adder gets incremented correctly on Crypto Whale level up
     */
    @Test
    public void cryptoWhaleDamageAdder() {
        CryptoWhale whale = new CryptoWhale(new Pair<>(0, 0));
        whale.levelUp();

        assertEquals(whale.getDamageAdder(), 2);
    }

    /**
     * Checks that CryptoWhale can correctly increment damage of other towers
     */
    @Test
    public void towerDamageIncreases() {
        CryptoWhale whale = new CryptoWhale(new Pair<>(0, 0));
        whale.levelUp();

        RedditDude dude = new RedditDude(new Pair<>(0, 0));

        dude.setDamage(dude.getDamage() + whale.getDamageAdder());

        assertEquals(dude.getDamage(), 12);
    }

    /**
     * M6 test for increasing the price of a RedditDude object
     */
    @Test
    public void redditDudeUpgradeCostIncrease() {
        RedditDude redditDude = new RedditDude(new Pair<>(0, 0));
        assertEquals(100, redditDude.getUpgradeCost());
        redditDude.levelUp();
        assertEquals(1000, redditDude.getUpgradeCost());

    }

    /**
     * M6 test for increasing the price of a TradingChad object
     */
    @Test
    public void tradingChadUpgradeCostIncrease() {
        TradingChad tradingChad = new TradingChad(new Pair<>(0, 0));
        assertEquals(250, tradingChad.getUpgradeCost());
        tradingChad.levelUp();
        assertEquals(1250, tradingChad.getUpgradeCost());
    }

    /**
     * M6 test for increasing the price of a CryptoWhale object
     */
    @Test
    public void cryptoWhaleUpgradeCostIncrease() {
        CryptoWhale cryptoWhale = new CryptoWhale(new Pair<>(0, 0));
        assertEquals(450, cryptoWhale.getUpgradeCost());
        cryptoWhale.levelUp();
        assertEquals(5400, cryptoWhale.getUpgradeCost());
    }

}
