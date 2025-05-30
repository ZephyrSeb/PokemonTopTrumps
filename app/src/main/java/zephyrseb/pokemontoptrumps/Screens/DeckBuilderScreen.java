package zephyrseb.pokemontoptrumps.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import zephyrseb.pokemontoptrumps.CardAdapter;
import zephyrseb.pokemontoptrumps.CardRegistry;
import zephyrseb.pokemontoptrumps.Deck;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;
import zephyrseb.pokemontoptrumps.Type;
import zephyrseb.pokemontoptrumps.comparators.CardNameComparator;
import zephyrseb.pokemontoptrumps.comparators.CardTypeComparator;
import zephyrseb.pokemontoptrumps.comparators.CardValueComparator;

public class DeckBuilderScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Deck deck;
    List<CardRegistry> collection;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deck_builder);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        collection = playerData.getCollection();
        collection.sort(Comparator.naturalOrder());

        Bundle extras = getIntent().getExtras();
        int id;
        deck = new Deck("");
        if (extras != null) {
            id = extras.getInt("id");
            if (!Objects.equals(extras.getString("deck"), "new")) {
                Gson gson = new Gson();
                deck = gson.fromJson(extras.getString("deck"), Deck.class);
                ((EditText)findViewById(R.id.deckNameEditable)).setText(deck.getName());
            } else {
                ((EditText)findViewById(R.id.deckNameEditable)).setText(R.string.system_new_deck);
            }
        } else {
            id = -1;
        }

        updateBanner(deck);

        ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
        recyclerView = new RecyclerView(this);
        recyclerView.setId(View.generateViewId());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.leftMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.rightMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.topMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.bottomMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -220, getResources().getDisplayMetrics()));
                return true;
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CardAdapter(collection, this));
        scrollArea.removeAllViews();
        scrollArea.addView(recyclerView);
        recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Spinner spinner = findViewById(R.id.sortOptions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent intentSave = new Intent(this, DeckRegistryScreen.class);
        Button saveButton = findViewById(R.id.saveButton);
        SaveData finalPlayerData2 = playerData;
        Deck finalDeck1 = deck;
        saveButton.setOnClickListener(v -> {
            if (id == -1) {
                finalPlayerData2.addDeck(finalDeck1);
            }
            else {
                finalPlayerData2.setDeck(id, finalDeck1);
            }
            finalDeck1.setName(((EditText)findViewById(R.id.deckNameEditable)).getText().toString());
            finalPlayerData2.writeFile(this);
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            saveButton.startAnimation(buttonPulse);
            startActivity(intentSave);
        });

        Intent intentCancel = new Intent(this, DeckRegistryScreen.class);
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            cancelButton.startAnimation(buttonPulse);
            startActivity(intentCancel);
        });

        Intent intentDelete = new Intent(this, DeckRegistryScreen.class);
        Button deleteButton = findViewById(R.id.deleteButton);
        SaveData finalPlayerData3 = playerData;
        deleteButton.setOnClickListener(v -> {
            if (id != -1) {
                finalPlayerData3.deleteDeck(id);
                finalPlayerData3.writeFile(this);
            }
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            deleteButton.startAnimation(buttonPulse);
            startActivity(intentDelete);
        });
    }

    public void updateBanner(Deck deck) {
        findViewById(R.id.type1).setVisibility(View.INVISIBLE);
        findViewById(R.id.type2).setVisibility(View.INVISIBLE);
        findViewById(R.id.type3).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.deckCount)).setText(String.valueOf(deck.getCardCount()));
        if (deck.getCardCount() != 20) ((TextView)findViewById(R.id.deckCount)).setTextColor(getResources().getColor(R.color.red));
        else ((TextView)findViewById(R.id.deckCount)).setTextColor(getResources().getColor(R.color.black));
        if (deck.pointValue(this) > 75) ((TextView)findViewById(R.id.pointCount)).setTextColor(getResources().getColor(R.color.red));
        else ((TextView)findViewById(R.id.pointCount)).setTextColor(getResources().getColor(R.color.black));
        ((TextView)findViewById(R.id.pointCount)).setText(String.valueOf(deck.pointValue(this)));
        if (!deck.getTypes(this).isEmpty() && deck.getTypes(this).size() < 4) {
            ((ImageView) findViewById(R.id.type1)).setImageResource(deck.getTypes(this).get(0).getIcon());
            findViewById(R.id.type1).setVisibility(View.VISIBLE);
        }
        if (deck.getTypes(this).size() >= 2 && deck.getTypes(this).size() < 4) {
            ((ImageView) findViewById(R.id.type2)).setImageResource(deck.getTypes(this).get(1).getIcon());
            findViewById(R.id.type2).setVisibility(View.VISIBLE);
        }
        if (deck.getTypes(this).size() == 3) {
            ((ImageView) findViewById(R.id.type3)).setImageResource(deck.getTypes(this).get(2).getIcon());
            findViewById(R.id.type3).setVisibility(View.VISIBLE);
        }
        if (deck.getTypes(this).size() > 3) {
            ((ImageView) findViewById(R.id.type1)).setImageResource(Type.STELLAR.getIcon());
            findViewById(R.id.type1).setVisibility(View.VISIBLE);
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (pos) {
            case 1: //alphabetical
                collection.sort(new CardNameComparator(this));
                break;
            case 2: //by type
                collection.sort(new CardTypeComparator(this));
                break;
            case 3: //by point value
                collection.sort(new CardValueComparator(this));
                break;
            default: //numerical
                collection.sort(Comparator.naturalOrder());
                break;
        }
        ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
        recyclerView.setAdapter(new CardAdapter(collection, this));
        scrollArea.removeAllViews();
        scrollArea.addView(recyclerView);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
}
