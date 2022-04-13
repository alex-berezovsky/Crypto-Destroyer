package com.teamthirty.buyhighselllow.Entities.Enemies;

import androidx.core.util.Pair;

public class BitCoin extends Enemy {
    public BitCoin() {
        position = new Pair<Integer, Integer>(null, null);
        health = 30;
        damage = 3;
    }
}
