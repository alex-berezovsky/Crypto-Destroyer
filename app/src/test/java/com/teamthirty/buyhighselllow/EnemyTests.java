package com.teamthirty.buyhighselllow;

import androidx.core.util.Pair;
import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.DogeCoin;
import com.teamthirty.buyhighselllow.Entities.Enemies.Etherium;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class EnemyTests {
    /**
     * M4 test for instantiation of BitCoin objects
     */
    @Test
    public void bitCoinInstantiation() {
        BitCoin coin = new BitCoin();
        Assert.assertNull(coin.getPosition().first);
        Assert.assertNull(coin.getPosition().second);
        Assert.assertEquals(coin.getDamage(), 3);
        Assert.assertEquals(coin.getHealth(), 30);
    }

    /**
     * M4 test for instantiation of DogeCoin objects
     */
    @Test
    public void dogeCoinInstantiation() {
        DogeCoin coin = new DogeCoin();
        Assert.assertNull(coin.getPosition().first);
        Assert.assertNull(coin.getPosition().second);
        Assert.assertEquals(coin.getDamage(), 1);
        Assert.assertEquals(coin.getHealth(), 10);
    }

    /**
     * M4 test for instantiation of Ethereum objects
     */
    @Test
    public void ethereumInstantiation() {
        Etherium coin = new Etherium();
        Assert.assertNull(coin.getPosition().first);
        Assert.assertNull(coin.getPosition().second);
        Assert.assertEquals(coin.getDamage(), 2);
        Assert.assertEquals(coin.getHealth(), 20);
    }

    /**
     * M4 Test that checks if the enemy is correctly moved forward by one grid unit along the path
     */
    @Test
    public void testUpdatePosition() {
        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
        path.add(new Pair<>(3, 0));
        path.add(new Pair<>(3, 1));
        path.add(new Pair<>(3, 2));
        path.add(new Pair<>(3, 3));
        path.add(new Pair<>(3, 4));
        path.add(new Pair<>(3, 5));
        path.add(new Pair<>(3, 6));
        path.add(new Pair<>(3, 7));
        path.add(new Pair<>(3, 8));
        path.add(new Pair<>(null, null));

        Etherium coin = new Etherium();
        coin.setPosition(new Pair<>(3,0));
        coin.updatePosition(path);
        Assert.assertEquals(coin.getPosition().first, new Integer(3));
        Assert.assertEquals(coin.getPosition().second, new Integer(1));
    }

    /**
     * M4 Test for detecting if an enemy is at the end of the path
     */
    @Test
    public void testEndOfPathDetection() {
        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
        path.add(new Pair<>(3, 0));
        path.add(new Pair<>(3, 1));
        path.add(new Pair<>(3, 2));
        path.add(new Pair<>(3, 3));
        path.add(new Pair<>(3, 4));
        path.add(new Pair<>(3, 5));
        path.add(new Pair<>(3, 6));
        path.add(new Pair<>(3, 7));
        path.add(new Pair<>(3, 8));
        path.add(new Pair<>(null, null));

        Etherium coin = new Etherium();
        coin.setPosition(new Pair<>(3,8));
        Assert.assertTrue(coin.updatePosition(path));
    }

    /**
     * M4 test for setting the position of an enemy
     */
    @Test
    public void testSetPosition() {
        BitCoin coin = new BitCoin();
        coin.setPosition(new Pair<>(1, 2));
        Assert.assertEquals(coin.getPosition().first, new Integer(1));
        Assert.assertEquals(coin.getPosition().second, new Integer(2));
    }
}
