package com.teamthirty.buyhighselllow;

import android.graphics.Rect;

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
}
