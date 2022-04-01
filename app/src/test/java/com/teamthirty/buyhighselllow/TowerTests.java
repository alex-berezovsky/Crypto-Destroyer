package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TowerTests {

    /**
     * Tests if projectile behaves correctly
     */
    @Test
    public void projectileCryptoWhale() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        CryptoWhale whale = new CryptoWhale(location);

        Projectile proj = whale.generateProjectile();
        assertEquals(10, proj.getPosition().first.intValue());
        assertEquals(15, proj.getPosition().second.intValue());
        assertEquals(20, proj.getSpeed());
        assertEquals(15, proj.getDamage());

        proj.updatePosition();
        assertEquals(30, proj.getPosition().first.intValue());
        assertEquals(35, proj.getPosition().second.intValue());
    }

    /**
     * Tests that all values of a cryptoWhale tower are instantiated correctly
     */
    @Test
    public void cryptoWhaleInstantiation() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        CryptoWhale whale = new CryptoWhale(location);

        assertEquals(1, whale.getLevel());
        assertEquals(10, whale.getPosition().first.intValue());
        assertEquals(15, whale.getPosition().second.intValue());
        assertEquals(30, whale.getFireRate());
        assertEquals(15, whale.getDamage());
        assertEquals(450, whale.getUpgradeCost());
    }

    /**
     * Tests that the values of a TradingChad scale correctly with the level
     */
    @Test
    public void levelUpCryptoWhale() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        CryptoWhale whale = new CryptoWhale(location);

        whale.levelUp();
        assertEquals(2, whale.getLevel());
        assertEquals(50, whale.getFireRate());
        assertEquals(240, whale.getDamage());
        assertEquals(5400, whale.getUpgradeCost());

        whale.levelUp();
        assertEquals(3, whale.getLevel());
        assertEquals(70, whale.getFireRate());
        assertEquals(3840, whale.getDamage());
        assertEquals(64800, whale.getUpgradeCost());

    }

    /**
     * Tests that all values of a TradingChad tower are instantiated correctly
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
     * Tests that the values of a TradingChad scale correctly with the level
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
     * Tests if projectile behaves correctly
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
        assertEquals(30, proj.getPosition().first.intValue());
        assertEquals(35, proj.getPosition().second.intValue());
    }

    /**
     * Tests proper instantiation of a RedditDude tower object
     */
    @Test
    public void redditDudeInstantiation() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        RedditDude redditDude = new RedditDude(location);

        assertEquals(1, redditDude.getLevel());
        assertEquals(10, redditDude.getPosition().first.intValue());
        assertEquals(15, redditDude.getPosition().second.intValue());
        assertEquals(20, redditDude.getFireRate());
        assertEquals(10, redditDude.getDamage());
        assertEquals(250, redditDude.getUpgradeCost());
    }

    /**
     * Tests proper values of a RedditDude tower when leveled up
     */
    @Test
    public void levelUpRedditDude() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        RedditDude redditDude = new RedditDude(location);

        redditDude.levelUp();
        assertEquals(2, redditDude.getLevel());
        assertEquals(10, redditDude.getPosition().first.intValue());
        assertEquals(15, redditDude.getPosition().second.intValue());
        assertEquals(30, redditDude.getFireRate());
        assertEquals(40, redditDude.getDamage());
        assertEquals(2500, redditDude.getUpgradeCost());

        redditDude.levelUp();
        assertEquals(3, redditDude.getLevel());
        assertEquals(10, redditDude.getPosition().first.intValue());
        assertEquals(15, redditDude.getPosition().second.intValue());
        assertEquals(40, redditDude.getFireRate());
        assertEquals(160, redditDude.getDamage());
        assertEquals(25000, redditDude.getUpgradeCost());
    }

    /**
     * Tests if a RedditDude makes a correct projectile
     */
    @Test
    public void projectileRedditDude() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        RedditDude redditDude = new RedditDude(location);

        Projectile proj = redditDude.generateProjectile();
        assertEquals(10, proj.getPosition().first.intValue());
        assertEquals(15, proj.getPosition().second.intValue());
        assertEquals(20, proj.getSpeed());
        assertEquals(10, proj.getDamage());

        proj.updatePosition();
        assertEquals(30, proj.getPosition().first.intValue());
        assertEquals(35, proj.getPosition().second.intValue());
    }

}
