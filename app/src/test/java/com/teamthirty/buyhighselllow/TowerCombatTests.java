package com.teamthirty.buyhighselllow;
import android.graphics.Color;
import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.ElonMusk;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Entities.Towers.TradingChad;
import com.teamthirty.buyhighselllow.Systems.GameController;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerCombatTests {
    /**
     * Tests that BitCoin takes damage properly
     */
    @Test
    public void BitCoinDamage() {
        BitCoin bitCoin = new BitCoin();
        bitCoin.takeDamage(5);
        assertEquals(bitCoin.getHealth(), 25);
    }
    /**
     * Tests that DogeCoin takes damage properly
     */
    @Test
    public void DogeCoinDamage() {
        DogeCoin dogeCoin = new DogeCoin();
        dogeCoin.takeDamage(5);
        assertEquals(dogeCoin.getHealth(), 5);
    }
    /**
     * Tests that Ethereum takes damage properly
     */
    @Test
    public void EthereumDamage() {
        Etherium etherium = new Etherium();
        etherium.takeDamage(5);
        assertEquals(etherium.getHealth(), 15);
    }
    /**
     * Tests that upon taking non-lethal damage, an enemy will not be flagged for deletion
     */
    @Test
    public void EnemyNotDelete() {

        Pair<Integer, Integer> location = new Pair<>(4, 4);
        RedditDude redditDude = new RedditDude(location);

        BitCoin bitCoin = new BitCoin();
        Etherium etherium = new Etherium();

        etherium.setDelete(etherium.takeDamage(5));
        bitCoin.setDelete(bitCoin.takeDamage(5));

        assertFalse(etherium.getDelete());
        assertFalse(bitCoin.getDelete());

    }

    /**
     * Tests that upon taking lethal damage, an enemy will be flagged for deletion
     */
    @Test
    public void EnemyDelete() {

        Pair<Integer, Integer> location = new Pair<>(4, 4);
        RedditDude redditDude = new RedditDude(location);

        DogeCoin dogeCoin = new DogeCoin();

        dogeCoin.setDelete(dogeCoin.takeDamage(10));

        assertTrue(dogeCoin.getDelete());

    }

    /**
     * Tests that checks for negative damage to an enemy, which is not intended and will result
     * in an unkillable enemy
     */
    @Test (expected = RuntimeException.class)
    public void EnemyTakeDamageNegative() {

        DogeCoin dogeCoin = new DogeCoin();
        dogeCoin.takeDamage(-5);

    }

    /**
     * Tests that checks that the final boss dies after taking lethal damage, which ensures that the
     * game will end.
     */
    @Test
    public void BossTakeDamageKilled() {

        ElonMusk elonMusk = new ElonMusk(Color.WHITE);

        elonMusk.setDelete(elonMusk.takeDamage(150));

        assertTrue(elonMusk.getDelete());

    }

    /**
     * Tests that checks that the final boss does NOT die after taking non-lethal damage,
     * which ensures that the game will NOT end until the boss is dead.
     */
    @Test
    public void BossTakeDamageNotKilled() {

        ElonMusk elonMusk = new ElonMusk(Color.WHITE);

        elonMusk.setDelete(elonMusk.takeDamage(99));

        assertFalse(elonMusk.getDelete());

    }
}
