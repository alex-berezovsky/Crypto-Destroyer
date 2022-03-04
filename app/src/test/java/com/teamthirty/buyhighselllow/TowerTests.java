package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerTests {
    /**
     * Tests that all values of TradingChad tower are instantiated correctly
     */
    @Test
    public void tradingChadInstantiation() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        TradingChad chad = new TradingChad(location);

        assertEquals(1, chad.getLevel());
        assertEquals(10, chad.getPosition().first.intValue());
        assertEquals(15, chad.getPosition().second.intValue());
        assertEquals(10, chad.getFireRate());
        assertEquals(10, chad.getDamage());
        assertEquals(100, chad.getUpgradeCost());
    }

    /**
     * Tests that the values of TradingChad scale correctly with the level
     */
    @Test
    public void levelUpTradingChad() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        TradingChad chad = new TradingChad(location);

        chad.levelUp();
        assertEquals(2, chad.getLevel());
        assertEquals(15, chad.getFireRate());
        assertEquals(20, chad.getDamage());
        assertEquals(500, chad.getUpgradeCost());

        chad.levelUp();
        assertEquals(3, chad.getLevel());
        assertEquals(20, chad.getFireRate());
        assertEquals(40, chad.getDamage());
        assertEquals(2500, chad.getUpgradeCost());

    }

    /**
     * Tests is projectile behaves correctly
     */
    @Test
    public void projectileTradingChad() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        TradingChad chad = new TradingChad(location);

        Projectile proj = chad.generateProjectile();
        assertEquals(10, proj.getPosition().first.intValue());
        assertEquals(15, proj.getPosition().second.intValue());
        assertEquals(20, proj.getSpeed());
        assertEquals(10, proj.getDamage());

        proj.updatePosition();
        assertEquals(30, proj.position.first.intValue());
        assertEquals(35, proj.position.second.intValue());
    }
}
