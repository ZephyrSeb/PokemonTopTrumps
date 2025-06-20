package zephyrseb.pokemontoptrumps.Screens;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.gson.Gson;

import java.util.List;

import zephyrseb.pokemontoptrumps.Deck;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;
import zephyrseb.pokemontoptrumps.Type;

public class GameSetupScreen extends AppCompatActivity {
    Deck selectedDeck = null;
    String deckJSON = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_setup);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        final Handler handler = new Handler(Looper.getMainLooper());

        final String mode;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mode = extras.getString("mode");
        }
        else {
            mode = "free_play";
        }

        String text = switch (mode) {
            case "battle_tower" -> "Battle Tower";
            case "battle_factory" -> "Battle Factory";
            case "battle_arcade" -> "Battle Arcade";
            case "battle_dojo" -> "Battle Dojo";
            case "battle_stage" -> "Battle Stage";
            default -> "Free Play";
        };
        ((TextView)findViewById(R.id.mode)).setText(text);

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        List<Deck> deckList = playerData.getDeckList();
        ((TextView)findViewById(R.id.winStreakText)).setText(Integer.toString(playerData.getWinStreak(mode)));

        displayDeck(findViewById(R.id.deckPreview), null);

        ConstraintLayout deckButton = findViewById(R.id.deckPreview);
        deckButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            deckButton.startAnimation(buttonPulse);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.scrolling_popup, null), ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGameSetup), Gravity.CENTER, 0, 0);

            ConstraintLayout deckLayout = popupWindow.getContentView().findViewById(R.id.decksScroll);
            View[] layout = new View[deckList.size()];
            for (int i = 0; i < deckList.size(); i++) {

                layout[i] = View.inflate(this, R.layout.deck_preview, null);
                layout[i].setId(View.generateViewId());
                deckLayout.addView(layout[i]);
                layout[i].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ConstraintSet set = new ConstraintSet();
                set.clone(deckLayout);
                set.connect(layout[i].getId(), ConstraintSet.TOP, R.id.decksScroll, ConstraintSet.TOP, (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics())) + i * (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics())));
                set.connect(layout[i].getId(), ConstraintSet.LEFT, R.id.decksScroll, ConstraintSet.LEFT, 0);
                set.connect(layout[i].getId(), ConstraintSet.RIGHT, R.id.decksScroll, ConstraintSet.RIGHT, 0);
                set.applyTo(deckLayout);
                displayDeck(layout[i], deckList.get(i));

                int finalI = i;
                layout[i].setOnClickListener(u -> {
                    updateSelectedDeck(deckList.get(finalI));
                    displayDeck(findViewById(R.id.deckPreview), selectedDeck);
                    popupWindow.dismiss();
                });
            }
        });

        Intent intentStart = new Intent(this, GameScreen.class);
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            intentStart.putExtra("mode", mode);
            intentStart.putExtra("deck", deckJSON);
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            if (selectedDeck != null) {
                if (selectedDeck.validate(this, mode)) {
                    handler.postDelayed(() -> startActivity(intentStart), 400);
                }
            }
        });

        if (mode.equals("free_play")) {
            Intent intentBack = new Intent(this, StartScreen.class);
            Button backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(v -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                backButton.startAnimation(buttonPulse);
                handler.postDelayed(() -> startActivity(intentBack), 400);
            });
        }
        else {
            Intent intentBack = new Intent(this, ChallengeScreen.class);
            Button backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(v -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                backButton.startAnimation(buttonPulse);
                handler.postDelayed(() -> startActivity(intentBack), 400);
            });
        }
    }

    private void updateSelectedDeck(Deck deck) {
        selectedDeck = deck;
        Gson gson = new Gson();
        deckJSON = gson.toJson(deck);
    }

    private void displayDeck(View layout, Deck deck) {
        layout.findViewById(R.id.type1).setVisibility(View.INVISIBLE);
        layout.findViewById(R.id.type2).setVisibility(View.INVISIBLE);
        layout.findViewById(R.id.type3).setVisibility(View.INVISIBLE);
        layout.findViewById(R.id.newDeck).setVisibility(View.INVISIBLE);
        if (deck != null) {
            layout.findViewById(R.id.name).setVisibility(View.VISIBLE);
            ((TextView) layout.findViewById(R.id.name)).setText(String.valueOf(deck.getName()));
            if (!deck.getTypes(this).isEmpty() && deck.getTypes(this).size() < 4) {
                ((ImageView) layout.findViewById(R.id.type1)).setImageResource(deck.getTypes(this).get(0).getIcon());
                layout.findViewById(R.id.type1).setVisibility(View.VISIBLE);
            }
            if (deck.getTypes(this).size() >= 2 && deck.getTypes(this).size() < 4) {
                ((ImageView) layout.findViewById(R.id.type2)).setImageResource(deck.getTypes(this).get(1).getIcon());
                layout.findViewById(R.id.type2).setVisibility(View.VISIBLE);
            }
            if (deck.getTypes(this).size() == 3) {
                ((ImageView) layout.findViewById(R.id.type3)).setImageResource(deck.getTypes(this).get(2).getIcon());
                layout.findViewById(R.id.type3).setVisibility(View.VISIBLE);
            }
            if (deck.getTypes(this).size() > 3) {
                ((ImageView) layout.findViewById(R.id.type1)).setImageResource(Type.STELLAR.getIcon());
                layout.findViewById(R.id.type1).setVisibility(View.VISIBLE);
            }
        }
        else {
            layout.findViewById(R.id.name).setVisibility(View.INVISIBLE);
            layout.findViewById(R.id.newDeck).setVisibility(View.VISIBLE);
        }
    }
}
