package com.teamthirty.buyhighselllow;

import android.graphics.Rect;
import androidx.core.util.Pair;

import java.util.HashMap;

public class TileView {

    private int tileWidth;
    private int tileHeight;
    private final int ROWS = 12;
    private final int COLUMNS = 12;
    private int tileCount;
    private Tile[][] tiles;
    private HashMap<Pair<Integer, Integer>, Integer> occupied;

    /**
     * Tileview constructor which makes all the tiles for unit placement on the game screen.
     * rows = amount of rows in grid layout
     * columns = amount of columns in grid layout
     * tileWidth = width of gridlayout(in dp) / columns
     * tileHeight = height of gridlayout(in dp) / rows
     * titleCount = number of rows * number of columns  - might not need this variable
     *
     * tiles[][] = 2D array that stores tiles which have 2 attributes, occupied(boolean) and
     * Rectangle(Rect)
     * Loops through all rows and columns and creates a tile with a rectangle of appropriate coords
     *
     * We can check if a tile is occupied by looping through all towers and comparing their state
     * coords with Tile's coords(there are methods for it for Rects)
     */

    public TileView() {
        tiles = new Tile[ROWS][COLUMNS];
        occupied = new HashMap<>();
        tileWidth = 720 / COLUMNS;
        tileHeight = 408 / ROWS;
        tileCount = ROWS * COLUMNS;

        for (int i = 0; i < ROWS; i++) {

            int top = ROWS * tileHeight;
            int bottom = (ROWS + 1) * (tileHeight);

            for (int j = 0; j < COLUMNS; j++) {

                int left = COLUMNS * tileWidth;
                int right = (COLUMNS + 1) * tileWidth;
                Rect rectangle = new Rect(left, top, right, bottom);
                tiles[i][j] = new Tile(false, rectangle);

            }
        }
    }

    public int getTileCount() {
        return tileCount;
    }

    //Will move this method into a different class, just making here so we have it

    //do we make a hashmap object instead and check "if x,y contained in map(where map takes in
    //key value pairs of (x,y) -> 0 or 1 depending on whether it's placeable. (much faster!?)

    /*
    public boolean isPlaceable(int x, int y) {

        for (int i = 0; i < tiles.length; i++) {

            for (int j = 0; j < tiles[i].length; j++) {

                if (tiles[i][j].getRectangle().exactCenterX() == x
                    && tiles[i][j].getRectangle().exactCenterY() == y) {

                    if (tiles[i][j].isOccupied()) {

                        return false;

                    }

                }

            }

        }

        return true;

    }

     */

    public HashMap<Pair<Integer, Integer>, Integer> getOccupied() {

        return occupied;

    }

    public boolean isPlaceable(Pair<Integer, Integer> position) {

        return occupied.containsKey(position);

    }

}
