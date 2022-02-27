package com.teamthirty.buyhighselllow;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("name");
        int difficulty = extras.getInt("difficulty");

        TextView playerNameText = findViewById(R.id.playerName);
        playerNameText.setText(playerName);
        playerNameText.setTextSize(30);

        TextView mapPlaceholderText = findViewById(R.id.mapPlaceholder);
        mapPlaceholderText.setText("Map/Path goes here");

        TextView monumentHealthText = findViewById(R.id.monumentHealth);
        TextView playerCashText = findViewById(R.id.playerCash);
        int cash = 0;
        int monumentHealth = 0;
        switch (difficulty) {
        case 2: // hard difficulty
            cash = 600;
            monumentHealth = 60;
            break;
        case 1: // medium difficulty
            cash = 800;
            monumentHealth = 80;
            break;
        case 0: // easy difficulty
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
}