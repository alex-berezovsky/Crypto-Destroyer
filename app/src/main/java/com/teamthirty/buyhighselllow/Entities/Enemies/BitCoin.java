package com.teamthirty.buyhighselllow.Entities.Enemies;

import android.graphics.Color;
import androidx.core.util.Pair;

public class BitCoin extends Enemy {
    public BitCoin() {
        position = new Pair<Integer, Integer>(null, null);
        health = 3;
        maxHealth = health;
        damage = 3;
        color = Color.DKGRAY;
    }


}
