package com.teamthirty.buyhighselllow.Screens;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.teamthirty.buyhighselllow.Utilities.Difficulty;
import com.teamthirty.buyhighselllow.R;

public class GameScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("name");

        Difficulty difficulty = (Difficulty) extras.get("difficulty");

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
}