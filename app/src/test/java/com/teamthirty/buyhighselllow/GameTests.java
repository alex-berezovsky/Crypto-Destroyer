package com.teamthirty.buyhighselllow;

import com.teamthirty.buyhighselllow.Entities.Enemies.BitCoin;
import com.teamthirty.buyhighselllow.Entities.Towers.Screens.GameScreen;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GameTests {

    /**
     * M4 Test that tests cash and monument health is correct for all difficulty levels
     */
    @Test
    @Deprecated
    @Ignore
    public void difficultyParameterVerificationTest() throws Exception {
        GameScreen gameScreen = new GameScreen();

        Method setDifficultyMethod =
                GameScreen.class.getDeclaredMethod("setDifficulty",
                        Difficulty.class);
        setDifficultyMethod.setAccessible(true);

        Field cashField = GameScreen.class.getDeclaredField("cash");
        cashField.setAccessible(true);

        Field monumentHealthField = GameScreen.class.getDeclaredField("monumentHealth");
        monumentHealthField.setAccessible(true);

        setDifficultyMethod.invoke(gameScreen, Difficulty.EASY);
        Assert.assertEquals(1000, (int) cashField.get(gameScreen));
        Assert.assertEquals(100, (int) monumentHealthField.get(gameScreen));

        setDifficultyMethod.invoke(gameScreen, Difficulty.STANDARD);
        Assert.assertEquals(800, (int) cashField.get(gameScreen));
        Assert.assertEquals(80, (int) monumentHealthField.get(gameScreen));

        setDifficultyMethod.invoke(gameScreen, Difficulty.HARD);
        Assert.assertEquals(600, (int) cashField.get(gameScreen));
        Assert.assertEquals(60, (int) monumentHealthField.get(gameScreen));
    }

    @Test
    public void addUnspawnedListTest() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.getUnspawnedList().add(new BitCoin());
        Assert.assertTrue(gameScreen.getUnspawnedList().get(0) instanceof BitCoin);
    }

    @Test
    public void addSpawnedListTest() {
        GameScreen gameScreen = new GameScreen();
        GameScreen.getSpawnedList().add(new BitCoin());
        Assert.assertTrue(GameScreen.getSpawnedList().get(0) instanceof BitCoin);
    }
}
