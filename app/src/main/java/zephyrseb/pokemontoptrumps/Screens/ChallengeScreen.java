package zephyrseb.pokemontoptrumps.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;

public class ChallengeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenge);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        final Handler handler = new Handler(Looper.getMainLooper());

        SaveData playerData = new SaveData();
        playerData.readFile(this);
        ((TextView)findViewById(R.id.bp)).setText(Integer.toString(playerData.getBattlePoints()));

        Intent intentTower = new Intent(this, GameSetupScreen.class);
        Button towerButton = findViewById(R.id.battleTowerButton);
        intentTower.putExtra("mode", "battle_tower");
        towerButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            towerButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentTower), 400);
        });

        Intent intentFactory = new Intent(this, GameSetupScreen.class);
        Button factoryButton = findViewById(R.id.battleFactoryButton);
        intentFactory.putExtra("mode", "battle_factory");
        factoryButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            factoryButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentFactory), 400);
        });

        Intent intentArcade = new Intent(this, GameSetupScreen.class);
        Button arcadeButton = findViewById(R.id.battleArcadeButton);
        intentArcade.putExtra("mode", "battle_arcade");
        arcadeButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            arcadeButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentArcade), 400);
        });

        Intent intentDojo = new Intent(this, GameSetupScreen.class);
        Button dojoButton = findViewById(R.id.battleDojoButton);
        intentDojo.putExtra("mode", "battle_dojo");
        dojoButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            dojoButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentDojo), 400);
        });

        Intent intentStage = new Intent(this, GameSetupScreen.class);
        Button stageButton = findViewById(R.id.battleStageButton);
        intentStage.putExtra("mode", "battle_stage");
        stageButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            stageButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentStage), 400);
        });

        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intent), 400);
        });

        Intent intentDeck = new Intent(this, DeckRegistryScreen.class);
        Button deckButton = findViewById(R.id.deckButton);
        deckButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            deckButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intentDeck), 400);
        });
    }
}
