package com.teamthirty.buyhighselllow.Entities.Towers.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamthirty.buyhighselllow.R;

public class EndGameScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.end_game_screen);

        Button restartGameButton = findViewById(R.id.restartButton2);
        restartGameButton.setOnClickListener(v -> restartGame());

        Bundle extras = getIntent().getExtras();

        int cashEnded = (int) extras.get("cashEarned");
        int enemyKilled = (int) extras.get("enemiesKilled");
        int roundPlayed = (int) extras.get("roundsPlayed");

        TextView cashEndedWith = findViewById(R.id.cashEndedText3);
        cashEndedWith.setText("Cashed ended with: " + cashEnded);

        TextView roundsPlayed = findViewById(R.id.roundsPlayedText3);
        roundsPlayed.setText("Rounds Played: " + roundPlayed);

        TextView enemiesKilled = findViewById(R.id.enemiesKilled3);
        enemiesKilled.setText("Total Enemies Killed: " + enemyKilled);

    }

    private void restartGame() {

        Intent intent = new Intent(EndGameScreen.this, MainActivity.class);
        startActivity(intent);

    }

}
