package zephyrseb.pokemontoptrumps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        Type.init();
        Ability.init(this);
        Item.init(this);
        CardRegistry.init(this);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("mode", "free_play");
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });

        Intent intentClassic = new Intent(this, GameScreen.class);
        intentClassic.putExtra("mode", "classic");
        Button classicButton = findViewById(R.id.classicButton);
        classicButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            classicButton.startAnimation(buttonPulse);
            startActivity(intentClassic);
        });

        Intent intent2 = new Intent(this, RulesScreen.class);
        Button rulesButton = findViewById(R.id.rulesButton);
        rulesButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            rulesButton.startAnimation(buttonPulse);
            startActivity(intent2);
        });

        Intent intent3 = new Intent(this, CollectionScreen.class);
        Button collectionButton = findViewById(R.id.collectionButton);
        collectionButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            collectionButton.startAnimation(buttonPulse);
            startActivity(intent3);
        });

        Intent intent4 = new Intent(this, SettingsScreen.class);
        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            settingsButton.startAnimation(buttonPulse);
            startActivity(intent4);
        });
    }
}