package zephyrseb.pokemontoptrumps.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;

public class SettingsScreen extends AppCompatActivity {

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        EditText nameText = findViewById(R.id.nameEditable);
        nameText.setText(playerData.getName());
        SaveData finalPlayerData = playerData;
        startButton.setOnClickListener(v -> {
            name = nameText.getText().toString();
            finalPlayerData.setName(name);
            finalPlayerData.writeFile(this);

            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            File file = new File(getApplicationContext().getFilesDir(), "playerData.json");
            boolean deleted = file.delete();

            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            deleteButton.startAnimation(buttonPulse);
            startActivity(intent);
        });
    }
}
