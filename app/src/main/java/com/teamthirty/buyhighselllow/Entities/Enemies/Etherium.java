package com.teamthirty.buyhighselllow.Entities.Enemies;

import androidx.core.util.Pair;

public class Etherium extends Enemy {
    public Etherium() {
        position = new Pair<Integer, Integer>(null, null);
        health = 2;
        damage = 2;
    }
}