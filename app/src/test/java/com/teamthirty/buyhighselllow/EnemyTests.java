package com.teamthirty.buyhighselllow;
import androidx.core.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import com.teamthirty.buyhighselllow.Entities.Enemies.*;
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
        Assert.assertEquals(coin.getHealth(), 3);
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
        Assert.assertEquals(coin.getHealth(), 1);
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
        Assert.assertEquals(coin.getHealth(), 2);
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
