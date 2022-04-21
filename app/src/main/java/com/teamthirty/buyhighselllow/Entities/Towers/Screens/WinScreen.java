package com.teamthirty.buyhighselllow.Entities.Towers.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.teamthirty.buyhighselllow.R;

public class WinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_win_screen);

        Bundle extras = getIntent().getExtras();

        int cashEnded = (int) extras.get("cashEarned");
        int enemyKilled = (int) extras.get("enemiesKilled");
        int roundPlayed = (int) extras.get("roundsPlayed");

        TextView cashEndedWith = findViewById(R.id.cashEndedText);
        cashEndedWith.setText("Cashed ended with: " + cashEnded);

        TextView roundsPlayed = findViewById(R.id.roundsPlayedText);
        roundsPlayed.setText("Rounds Played: " + roundPlayed);

        TextView enemiesKilled = findViewById(R.id.enemiesKilled);
        enemiesKilled.setText("Total Enemies Killed: " + enemyKilled);

        Button restartGameButton = findViewById(R.id.restartButtonWin);
        restartGameButton.setOnClickListener(v -> restartGame());

    }

    private void restartGame() {

        Intent intent = new Intent(WinScreen.this, MainActivity.class);
        startActivity(intent);

    }
}