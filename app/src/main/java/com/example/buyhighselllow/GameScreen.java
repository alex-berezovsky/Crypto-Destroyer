package com.example.buyhighselllow;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

    TextView playerCashText = findViewById(R.id.playerCash);
    int cash = 0;
    switch (difficulty){
      case 0: cash = 1000;
              break;
      case 1: cash = 800;
              break;
      case 2: cash = 600;
              break;
    }
    playerCashText.setText("Player Cash: " + cash);
    playerCashText.setTextSize(15);
  }
}