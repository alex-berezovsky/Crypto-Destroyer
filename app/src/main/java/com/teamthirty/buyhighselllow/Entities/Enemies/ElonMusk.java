package com.teamthirty.buyhighselllow.Entities.Enemies;

import android.graphics.Color;
import androidx.core.util.Pair;

public class ElonMusk extends Enemy {
    public ElonMusk() {
        position = new Pair<Integer, Integer>(null, null);
        health = 100;
        maxHealth = health;
        damage = 969;
        color = Color.rgb(255, 165, 0);
    }

    public ElonMusk(int color) {

        position = new Pair<Integer, Integer>(null, null);
        health = 100;
        maxHealth = health;
        damage = 969;
        this.color = color;

    }
}
