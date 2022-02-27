package com.teamthirty.buyhighselllow;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
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

        Editable name = nameInput.getEditableText();
        easyButton.setOnClickListener(view -> moveToGame(name.toString(), Difficulty.EASY));
        stdButton.setOnClickListener(view -> moveToGame(name.toString(), Difficulty.STANDARD));
        hardButton.setOnClickListener(view -> moveToGame(name.toString(), Difficulty.HARD));

    }

    /**
     * Transitions from name & difficulty selection screen to game activity
     *  @param name       username
     * @param difficulty difficulty, 0 for easy, 1 for standard, 2 for hard
     */
    private void moveToGame(String name, Difficulty difficulty) {

        if (Util.sanitizeNameInput(name, getApplicationContext())) {

            Intent intent = new Intent(StartMenu.this, GameScreen.class);
            intent.putExtra("name", name);
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);

        }
    }
}