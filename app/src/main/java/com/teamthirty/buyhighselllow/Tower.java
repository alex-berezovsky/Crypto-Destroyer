package com.teamthirty.buyhighselllow;

import android.util.Pair;

public abstract class Tower extends Entity {
    protected int cost;
    protected int sellPrice;
    protected Pair<Integer, Integer> location;

    public abstract void buy(Pair<Integer, Integer> location, int cash, Difficulty difficulty);

    public int sell() {
        location = new Pair<>(null, null);
        return sellPrice;
    }

    public int getCost() {
        return cost;
    }
}
