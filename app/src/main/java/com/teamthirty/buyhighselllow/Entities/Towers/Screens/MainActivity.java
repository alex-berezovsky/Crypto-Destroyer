package com.teamthirty.buyhighselllow.Entities.Towers.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.teamthirty.buyhighselllow.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button endButton = findViewById(R.id.endButton);

        endButton.setOnClickListener(v -> System.exit(0));
        startButton.setOnClickListener(v -> startingTheGame());
    }

    private void startingTheGame() {
        Intent intent = new Intent(MainActivity.this, StartMenu.class);
        startActivity(intent);
    }
}