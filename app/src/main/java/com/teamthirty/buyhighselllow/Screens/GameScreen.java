package com.teamthirty.buyhighselllow.Screens;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.R;
import com.teamthirty.buyhighselllow.Utilities.TowerType;
import androidx.core.util.Pair;
import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {
    private GridLayout mapLayout;
    private ArrayList<Pair<Integer, Integer>> path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("name");
        Difficulty difficulty = (Difficulty) extras.get("difficulty");
        TowerType towerType = null;

        // User interface buttons
        Button redditDude = findViewById(R.id.RedditDude);
        Button tradingChad = findViewById(R.id.TradingChad);
        Button cryptoWhale = findViewById(R.id.CryptoWhale);
        // THIS IS HARD-CODED AND NEEDS TO GO
        path = new ArrayList<>();
        path.add(new Pair<>(3, 8));
        path.add(new Pair<>(3, 7));
        path.add(new Pair<>(3, 6));
        path.add(new Pair<>(3, 5));
        path.add(new Pair<>(3, 4));
        path.add(new Pair<>(3, 3));
        path.add(new Pair<>(3, 2));
        path.add(new Pair<>(3, 1));
        path.add(new Pair<>(3, 0));
        makeMap();

        redditDude.setOnClickListener(view -> setTowerType(towerType, TowerType.RedditDude));
        tradingChad.setOnClickListener(view -> setTowerType(towerType, TowerType.TradingChad));
        cryptoWhale.setOnClickListener(view -> setTowerType(towerType, TowerType.CryptoWhale));

        TextView playerNameText = findViewById(R.id.playerName);
        playerNameText.setText(playerName);

        TextView monumentHealthText = findViewById(R.id.monumentHealth);
        TextView playerCashText = findViewById(R.id.playerCash);
        int cash = 0;
        int monumentHealth = 0;
        switch (difficulty) {
        case HARD: // hard difficulty
            cash = 600;
            monumentHealth = 60;
            break;
        case STANDARD: // medium difficulty
            cash = 800;
            monumentHealth = 80;
            break;
        case EASY: // easy difficulty
        default:
            // default is easy mode
            cash = 1000;
            monumentHealth = 100;
            break;
        }
        monumentHealthText.setText("Monument HP: " + monumentHealth);
        playerCashText.setText("Player Cash: " + cash);
        playerCashText.setTextSize(15);
    }

    private void setTowerType(TowerType towerType, TowerType newType) {
        towerType = newType;
    }

    private void makeMap() {
        // Layout object representing the map
        GridLayout mapLayout = findViewById(R.id.map);
        Button[][] mapArray = new Button[mapLayout.getRowCount()][mapLayout.getColumnCount()];
        for (int i = 0; i < mapLayout.getRowCount(); i++) {
            for (int j = 0; j < mapLayout.getColumnCount(); j++) {
                Pair<Integer, Integer> temp = new Pair<>(i, j);
                if (!path.contains(temp)) {
                    mapArray[i][j] = makeMapButton(mapLayout, this, Color.GREEN, i, j);
                    mapLayout.addView(mapArray[i][j]);
                } else {
                    mapArray[i][j] = makeMapButton(mapLayout, this, Color.GRAY, i, j);
                    mapLayout.addView(mapArray[i][j]);
                }
            }
        }

    }

    private Button makeMapButton(GridLayout mapLayout, Context context, int color, int row,
                                 int column) {
        GridLayout.LayoutParams buttonParams = new GridLayout.LayoutParams();
        buttonParams.height = 0;
        buttonParams.width = 0;
        buttonParams.rowSpec = GridLayout.spec(row, (float) 1);
        buttonParams.columnSpec = GridLayout.spec(column, (float) 1);

        //Buttons on the grid that handle tower press placement
        Button button = new Button(context);
        button.setBackgroundColor(color);
        button.setLayoutParams(buttonParams);

        return button;
    }
}