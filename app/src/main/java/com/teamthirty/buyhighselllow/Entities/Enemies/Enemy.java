package com.teamthirty.buyhighselllow.Entities.Enemies;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class Enemy {
    protected Pair<Integer, Integer> position;
    protected int health;
    protected int damage;
    protected int speed = 1;

    public boolean updatePosition(ArrayList<Pair<Integer, Integer>> path) {
        int pathIndex = path.indexOf(position);
        position = path.get(pathIndex + 1);
        return position == path.get(path.size() - 1);
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean takeDamage(int damage) {
        health -= damage;
        return health <= 0;
    }
}
