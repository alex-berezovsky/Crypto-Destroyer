package com.teamthirty.buyhighselllow;

import android.util.Pair;
import android.widget.Toast;

public class TradingChad extends Tower {
    private TradingChad(Difficulty difficulty, Pair<Integer, Integer> location) {
        cost = difficulty == Difficulty.EASY ? 50 : (difficulty == Difficulty.STANDARD ? 100 : 200);
        this.location = location;
    }

    public void buy(Pair<Integer, Integer> location, int cash, Difficulty difficulty) {}
}
