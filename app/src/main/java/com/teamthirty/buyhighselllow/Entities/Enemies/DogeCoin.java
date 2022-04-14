package com.teamthirty.buyhighselllow.Entities.Enemies;

import androidx.core.util.Pair;

public class DogeCoin extends Enemy {
    public DogeCoin() {
        position = new Pair<Integer, Integer>(null, null);
        health = 100;
        damage = 1;
    }
}
