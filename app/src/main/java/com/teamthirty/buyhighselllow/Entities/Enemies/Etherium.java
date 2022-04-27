package com.teamthirty.buyhighselllow.Entities.Enemies;

import android.graphics.Color;

import androidx.core.util.Pair;

public class Etherium extends Enemy {
    public Etherium() {
        position = new Pair<Integer, Integer>(null, null);
        health = 20;
        maxHealth = health;
        damage = 2;
        color = Color.CYAN;
    }
}
