package com.teamthirty.buyhighselllow;

import static org.junit.Assert.assertEquals;

import androidx.core.util.Pair;

import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Projectile.Projectile;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;

import org.junit.Assert;
import org.junit.Test;

public class TowerTests {

    /**
     * Tests if projectile behaves correctly
     */
    @Test
    @Deprecated
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
        assertEquals(20, chad.getDamage());
        assertEquals(250, chad.getUpgradeCost());
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
        assertEquals(40, chad.getDamage());
        assertEquals(1250, chad.getUpgradeCost());

        chad.levelUp();
        assertEquals(3, chad.getLevel());
        assertEquals(20, chad.getFireRate());
        assertEquals(80, chad.getDamage());
        assertEquals(6250, chad.getUpgradeCost());

    }

    /**
     * Tests if projectile behaves correctly
     */
    @Test
    @Deprecated
    public void projectileTradingChad() {
        Pair<Integer, Integer> location = new Pair<>(10, 15);
        TradingChad chad = new TradingChad(location);

        Projectile proj = chad.generateProjectile();
        assertEquals(10, proj.getPosition().first.intValue());
        assertEquals(15, proj.getPosition().second.intValue());
        assertEquals(20, proj.getSpeed());
        assertEquals(20, proj.getDamage());

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
        assertEquals(100, redditDude.getUpgradeCost());
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
        assertEquals(1000, redditDude.getUpgradeCost());

        redditDude.levelUp();
        assertEquals(3, redditDude.getLevel());
        assertEquals(10, redditDude.getPosition().first.intValue());
        assertEquals(15, redditDude.getPosition().second.intValue());
        assertEquals(40, redditDude.getFireRate());
        assertEquals(160, redditDude.getDamage());
        assertEquals(10000, redditDude.getUpgradeCost());
    }

    /**
     * Tests if a RedditDude makes a correct projectile
     */
    @Test
    @Deprecated
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

    /**
     * M5 Test to test if RedditDude deals the appropriate amount of damage to an enemy.
     */
    @Test
    public void redditDudeDamage() {
        BitCoin coin = new BitCoin();
        RedditDude redditDude = new RedditDude(new Pair<>(0, 0));
        coin.takeDamage(redditDude.getDamage());
        Assert.assertEquals(20, coin.getHealth());
    }

    /**
     * M5 Test to test if TradingChad deals the appropriate amount of damage to an enemy.
     */
    @Test
    public void tradingChadDamage() {
        BitCoin coin = new BitCoin();
        TradingChad tradingChad = new TradingChad(new Pair<>(0, 0));
        coin.takeDamage(tradingChad.getDamage());
        Assert.assertEquals(10, coin.getHealth());
    }

    /**
     * Tests if RedditDude deals correct amount of damage when leveled up
     */
    @Test
    public void redditLevelUpDamage() {
        Pair<Integer, Integer> location = new Pair<>(0, 0);
        RedditDude dude = new RedditDude(location);
        DogeCoin doge = new DogeCoin();

        dude.levelUp();

        doge.takeDamage(dude.getDamage());
        assertEquals(doge.getHealth(), -30);
    }

    /**
     * Tests if TradingChad deals correct amount of damage when leveled up
     */
    @Test
    public void tradingLevelUpDamage() {
        Pair<Integer, Integer> location = new Pair<>(0, 0);
        TradingChad chad = new TradingChad(location);
        DogeCoin doge = new DogeCoin();

        chad.levelUp();

        doge.takeDamage(chad.getDamage());
        assertEquals(doge.getHealth(), -30);
    }
}
