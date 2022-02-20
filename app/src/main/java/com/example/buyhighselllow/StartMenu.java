package com.example.buyhighselllow;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        TextInputEditText nameInput = findViewById(R.id.nameInput);
        Button easyButton = findViewById(R.id.easyButton);
        Button stdButton = findViewById(R.id.stdButton);
        Button hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(v -> moveToGame());
        stdButton.setOnClickListener(v -> moveToGame());
        hardButton.setOnClickListener(v -> moveToGame());
    }

    private void moveToGame() {
        Intent intent = new Intent(StartMenu.this, GameScreen.class);
        startActivity(intent);
    }
}