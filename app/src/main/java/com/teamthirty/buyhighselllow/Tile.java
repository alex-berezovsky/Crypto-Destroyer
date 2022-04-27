package com.teamthirty.buyhighselllow;

import android.graphics.Rect;

import androidx.core.util.Pair;

public class Tile {

    private boolean occupied;
    private Rect rectangle;

    public Tile() {

        occupied = false;
        rectangle = new Rect(0, 0, 0, 0);

    }

    public Tile(boolean occupied, Rect rectangle) {

        this.occupied = occupied;
        this.rectangle = rectangle;

    }


    public Rect getRectangle() {
        return rectangle;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Pair<Integer, Integer> getCenter() {

        return new Pair<>(rectangle.centerX(), rectangle.centerY());

    }
}
