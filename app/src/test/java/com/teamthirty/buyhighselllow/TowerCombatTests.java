package com.teamthirty.buyhighselllow;
import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import com.teamthirty.buyhighselllow.Systems.GameController;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerCombatTests {

    /**
     * Tests that upon taking non-lethal damage, an enemy will not be flagged for deletion
     */
    @Test
    public void EnemyTakeDamageNotDead() {

        Pair<Integer, Integer> location = new Pair<>(4, 4);
        RedditDude redditDude = new RedditDude(location);

        BitCoin bitCoin = new BitCoin();
        Etherium etherium = new Etherium();

        etherium.setDelete(etherium.takeDamage(redditDude.getDamage()));
        bitCoin.setDelete(bitCoin.takeDamage(redditDude.getDamage()));

        assertFalse(etherium.getDelete());
        assertFalse(bitCoin.getDelete());

    }

    /**
     * Tests that upon taking lethal damage, an enemy will be flagged for deletion
     */
    @Test
    public void EnemyTakeDamageDead() {

        Pair<Integer, Integer> location = new Pair<>(4, 4);
        RedditDude redditDude = new RedditDude(location);

        DogeCoin dogeCoin = new DogeCoin();

        dogeCoin.setDelete(dogeCoin.takeDamage(redditDude.getDamage()));

        assertTrue(dogeCoin.getDelete());

    }

}
