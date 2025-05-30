package zephyrseb.pokemontoptrumps.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
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

public class DeckRegistryScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deck_registry);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        List<Deck> deckList = playerData.getDeckList();

        ConstraintLayout deckLayout = findViewById(R.id.decksScroll);
        View[] layout = new View[deckList.size() + 1];
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
            displayDeck(layout[i], deckList.get(i), false);

            Gson gson = new Gson();
            String deckString = gson.toJson(deckList.get(i));
            Intent intentDeck = new Intent(this, DeckBuilderScreen.class);
            intentDeck.putExtra("deck", deckString);
            intentDeck.putExtra("id", i);
            int finalI = i;
            layout[i].setOnClickListener(v -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                layout[finalI].startAnimation(buttonPulse);
                startActivity(intentDeck);
            });
        }

        layout[deckList.size()] = View.inflate(this, R.layout.deck_preview, null);
        layout[deckList.size()].setId(View.generateViewId());
        deckLayout.addView(layout[deckList.size()]);
        layout[deckList.size()].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ConstraintSet set = new ConstraintSet();
        set.clone(deckLayout);
        set.connect(layout[deckList.size()].getId(), ConstraintSet.TOP, R.id.decksScroll, ConstraintSet.TOP, (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics())) + deckList.size() * (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics())));
        set.connect(layout[deckList.size()].getId(), ConstraintSet.LEFT, R.id.decksScroll, ConstraintSet.LEFT, 0);
        set.connect(layout[deckList.size()].getId(), ConstraintSet.RIGHT, R.id.decksScroll, ConstraintSet.RIGHT, 0);
        set.applyTo(deckLayout);
        displayDeck(layout[deckList.size()], null, true);

        Intent intentNewDeck = new Intent(this, DeckBuilderScreen.class);
        intentNewDeck.putExtra("deck", "new");
        intentNewDeck.putExtra("id", -1);
        layout[deckList.size()].setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            layout[deckList.size()].startAnimation(buttonPulse);
            startActivity(intentNewDeck);
        });

        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });
    }

    private void displayDeck(View layout, Deck deck, boolean isNew) {
        layout.findViewById(R.id.type1).setVisibility(View.INVISIBLE);
        layout.findViewById(R.id.type2).setVisibility(View.INVISIBLE);
        layout.findViewById(R.id.type3).setVisibility(View.INVISIBLE);
        if (!isNew) {
            layout.findViewById(R.id.newDeck).setVisibility(View.INVISIBLE);
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
