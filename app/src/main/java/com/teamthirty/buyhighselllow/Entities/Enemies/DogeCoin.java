package com.teamthirty.buyhighselllow.Entities.Enemies;

import android.graphics.Color;

import androidx.core.util.Pair;

public class DogeCoin extends Enemy {
    public DogeCoin() {
        position = new Pair<Integer, Integer>(null, null);
        health = 10;
        maxHealth = health;
        damage = 1;
        color = Color.WHITE;
    }
}
