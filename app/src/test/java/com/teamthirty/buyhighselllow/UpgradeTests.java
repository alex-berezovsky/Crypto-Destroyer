package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Towers.CryptoWhale;
import com.teamthirty.buyhighselllow.Entities.Towers.RedditDude;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpgradeTests {
    /**
     * Checks if damage adder gets incremented correctly on Crypto Whale level up
     */
    @Test
    public void cryptoWhaleDamageAdder() {
        CryptoWhale whale = new CryptoWhale(new Pair<>(0,0));
        whale.levelUp();

        assertEquals(whale.getDamageAdder(), 2);
    }

    /**
     * Checks that CryptoWhale can correctly increment damage of other towers
     */
    @Test
    public void towerDamageIncreases() {
        CryptoWhale whale = new CryptoWhale(new Pair<>(0,0));
        whale.levelUp();

        RedditDude dude = new RedditDude(new Pair<>(0,0));

        dude.setDamage(dude.getDamage() + whale.getDamageAdder());

        assertEquals(dude.getDamage(), 12);
    }
}
