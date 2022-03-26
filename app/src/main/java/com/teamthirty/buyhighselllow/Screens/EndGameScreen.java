package com.teamthirty.buyhighselllow.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.teamthirty.buyhighselllow.R;

public class EndGameScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.end_game_screen);

        Button restartGameButton = findViewById(R.id.restartButton);
        restartGameButton.setOnClickListener(v -> restartGame());

    }

    private void restartGame() {

        Intent intent = new Intent(EndGameScreen.this, MainActivity.class);
        startActivity(intent);

    }

}
