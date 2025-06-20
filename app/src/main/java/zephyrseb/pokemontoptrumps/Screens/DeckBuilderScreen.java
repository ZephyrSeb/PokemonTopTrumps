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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import zephyrseb.pokemontoptrumps.Attributes;
import zephyrseb.pokemontoptrumps.CardAdapter;
import zephyrseb.pokemontoptrumps.CardFilter;
import zephyrseb.pokemontoptrumps.CardRegistry;
import zephyrseb.pokemontoptrumps.CustomSpinner;
import zephyrseb.pokemontoptrumps.Deck;
import zephyrseb.pokemontoptrumps.Filter;
import zephyrseb.pokemontoptrumps.ItemAdapter;
import zephyrseb.pokemontoptrumps.ItemFilter;
import zephyrseb.pokemontoptrumps.ItemRegistry;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;
import zephyrseb.pokemontoptrumps.Type;
import zephyrseb.pokemontoptrumps.comparators.CardAttributeComparator;
import zephyrseb.pokemontoptrumps.comparators.CardNameComparator;
import zephyrseb.pokemontoptrumps.comparators.CardTypeComparator;
import zephyrseb.pokemontoptrumps.comparators.CardValueComparator;
import zephyrseb.pokemontoptrumps.comparators.ItemNameComparator;
import zephyrseb.pokemontoptrumps.comparators.ItemValueComparator;

public class DeckBuilderScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Deck deck;
    List<CardRegistry> collection;
    List<ItemRegistry> itemCollection;
    List<CardRegistry> filteredCollection;
    List<ItemRegistry> filteredItemCollection;
    RecyclerView recyclerView;
    boolean switched = false;
    private int cardOrder = 0;
    private int itemOrder = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deck_builder);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        final Handler handler = new Handler(Looper.getMainLooper());

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        collection = playerData.getCollection();
        collection.sort(Comparator.naturalOrder());
        itemCollection = playerData.getItemCollection();
        itemCollection.sort(Comparator.naturalOrder());
        filteredCollection = new ArrayList<>();
        filteredItemCollection = new ArrayList<>();
        filteredCollection.addAll(collection);
        filteredItemCollection.addAll(itemCollection);
        CardFilter cardFilter = new CardFilter();
        ItemFilter itemFilter = new ItemFilter();

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
        recyclerView.setAdapter(new CardAdapter(filteredCollection, this));
        scrollArea.removeAllViews();
        scrollArea.addView(recyclerView);
        recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        CustomSpinner spinner = findViewById(R.id.sortOptions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        CustomSpinner spinner1 = findViewById(R.id.sortOptionsItem);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.sort_options_item,
                android.R.layout.simple_spinner_item
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Button switchButton = findViewById(R.id.switchButton);
        switchButton.setOnClickListener(v -> {
            if (!switched) {
                recyclerView.setAdapter(new ItemAdapter(filteredItemCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
                findViewById(R.id.sortOptions).setVisibility(View.INVISIBLE);
                findViewById(R.id.sortOptionsItem).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.switchIcon)).setImageResource(R.drawable.icon_pokeball_black);
                switched = true;
            }
            else {
                recyclerView.setAdapter(new CardAdapter(filteredCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
                findViewById(R.id.sortOptions).setVisibility(View.VISIBLE);
                findViewById(R.id.sortOptionsItem).setVisibility(View.INVISIBLE);
                ((ImageView)findViewById(R.id.switchIcon)).setImageResource(R.drawable.icon_item_black);
                switched = false;
            }
        });

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(v -> filter(cardFilter, itemFilter));

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
            handler.postDelayed(() -> startActivity(intentSave), 400);
        });

        Intent intentCancel = new Intent(this, DeckRegistryScreen.class);
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.confirmation_menu, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText("Are you sure? Unsaved data can't be recovered");

            popupWindow.getContentView().findViewById(R.id.yesButton).setOnClickListener(u -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.yesButton).startAnimation(buttonPulse);
                handler.postDelayed(() -> startActivity(intentCancel), 400);
            });

            popupWindow.getContentView().findViewById(R.id.noButton).setOnClickListener(u -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.noButton).startAnimation(buttonPulse);
                handler.postDelayed(popupWindow::dismiss, 400);
            });
        });

        Intent intentDelete = new Intent(this, DeckRegistryScreen.class);
        Button deleteButton = findViewById(R.id.deleteButton);
        SaveData finalPlayerData3 = playerData;
        deleteButton.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.confirmation_menu, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText("Are you sure? Deleted data can't be recovered");

            popupWindow.getContentView().findViewById(R.id.yesButton).setOnClickListener(u -> {
                if (id != -1) {
                    finalPlayerData3.deleteDeck(id);
                    finalPlayerData3.writeFile(this);
                }
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.yesButton).startAnimation(buttonPulse);
                handler.postDelayed(() -> startActivity(intentDelete), 400);
            });

            popupWindow.getContentView().findViewById(R.id.noButton).setOnClickListener(u -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.noButton).startAnimation(buttonPulse);
                handler.postDelayed(popupWindow::dismiss, 400);
            });
        });
    }

    public void updateBanner(Deck deck) {
        findViewById(R.id.type1).setVisibility(View.INVISIBLE);
        findViewById(R.id.type2).setVisibility(View.INVISIBLE);
        findViewById(R.id.type3).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.deckCount)).setText(deck.getCardCount() + " / 20");
        ((TextView)findViewById(R.id.itemCount)).setText(deck.getItemCount() + " / 6");
        if (deck.getCardCount() != 20) ((TextView)findViewById(R.id.deckCount)).setTextColor(getResources().getColor(R.color.red, this.getTheme()));
        else ((TextView)findViewById(R.id.deckCount)).setTextColor(getResources().getColor(R.color.black, this.getTheme()));
        if (deck.getItemCount() != 6) ((TextView)findViewById(R.id.itemCount)).setTextColor(getResources().getColor(R.color.red, this.getTheme()));
        else ((TextView)findViewById(R.id.itemCount)).setTextColor(getResources().getColor(R.color.black, this.getTheme()));
        if (deck.pointValue(this) > 100) ((TextView)findViewById(R.id.pointCount)).setTextColor(getResources().getColor(R.color.red, this.getTheme()));
        else ((TextView)findViewById(R.id.pointCount)).setTextColor(getResources().getColor(R.color.black, this.getTheme()));
        ((TextView)findViewById(R.id.pointCount)).setText(deck.pointValue(this) + " / 100");
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
        if (!switched) {
            switch (pos) {
                case 1: //alphabetical
                    if (cardOrder == 1) collection.sort(new CardNameComparator(this).reversed());
                    else {collection.sort(new CardNameComparator(this)); cardOrder = 1;}
                    break;
                case 2: //by type
                    if (cardOrder == 2) collection.sort(new CardTypeComparator(this).reversed());
                    else {collection.sort(new CardTypeComparator(this)); cardOrder = 2;}
                    break;
                case 3: //by point value
                    if (cardOrder == 3) collection.sort(new CardValueComparator(this).reversed());
                    else {collection.sort(new CardValueComparator(this)); cardOrder = 3;}
                    break;
                case 4: //by HP
                    if (cardOrder == 4) collection.sort(new CardAttributeComparator(this, Attributes.HP).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.HP)); cardOrder = 4;}
                    break;
                case 5: //by HP
                    if (cardOrder == 5) collection.sort(new CardAttributeComparator(this, Attributes.ATK).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.ATK)); cardOrder = 5;}
                    break;
                case 6: //by HP
                    if (cardOrder == 6) collection.sort(new CardAttributeComparator(this, Attributes.DEF).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.DEF)); cardOrder = 6;}
                    break;
                case 7: //by HP
                    if (cardOrder == 7) collection.sort(new CardAttributeComparator(this, Attributes.SPATK).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.SPATK)); cardOrder = 7;}
                    break;
                case 8: //by HP
                    if (cardOrder == 8) collection.sort(new CardAttributeComparator(this, Attributes.SPDEF).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.SPDEF)); cardOrder = 8;}
                    break;
                case 9: //by HP
                    if (cardOrder == 9) collection.sort(new CardAttributeComparator(this, Attributes.ACC).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.ACC)); cardOrder = 9;}
                    break;
                case 10: //by HP
                    if (cardOrder == 10) collection.sort(new CardAttributeComparator(this, Attributes.EV).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.EV)); cardOrder = 10;}
                    break;
                case 11: //by HP
                    if (cardOrder == 11) collection.sort(new CardAttributeComparator(this, Attributes.SPD).reversed());
                    else {collection.sort(new CardAttributeComparator(this, Attributes.SPD)); cardOrder = 11;}
                    break;
                default: //numerical
                    collection.sort(Comparator.naturalOrder());
                    break;
            }
            ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
            recyclerView.setAdapter(new CardAdapter(filteredCollection, this));
            scrollArea.removeAllViews();
            scrollArea.addView(recyclerView);
        }
        else {
            switch (pos) {
                case 1: //alphabetical
                    if (itemOrder == 1) itemCollection.sort(new ItemNameComparator(this).reversed());
                    else {itemCollection.sort(new ItemNameComparator(this)); itemOrder = 1;}
                    break;
                case 2: //by point value
                    if (itemOrder == 1) itemCollection.sort(new ItemValueComparator(this).reversed());
                    else {itemCollection.sort(new ItemValueComparator(this)); itemOrder = 1;}
                    break;
                default: //numerical
                    itemCollection.sort(Comparator.naturalOrder());
                    break;
            }
            ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
            recyclerView.setAdapter(new ItemAdapter(filteredItemCollection, this));
            scrollArea.removeAllViews();
            scrollArea.addView(recyclerView);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    public void filter(CardFilter cardFilter, ItemFilter itemFilter) {
        if (!switched) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_filter, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);

            updateFilters(popupWindow, cardFilter);
            ((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).setText(cardFilter.getName());
            ((EditText)popupWindow.getContentView().findViewById(R.id.abilityEditable)).setText(cardFilter.getAbility());
            addTypeFilter(popupWindow, R.id.typeNormalButton, Type.NORMAL, cardFilter);
            addTypeFilter(popupWindow, R.id.typeFireButton, Type.FIRE, cardFilter);
            addTypeFilter(popupWindow, R.id.typeWaterButton, Type.WATER, cardFilter);
            addTypeFilter(popupWindow, R.id.typeGrassButton, Type.GRASS, cardFilter);
            addTypeFilter(popupWindow, R.id.typeElectricButton, Type.ELECTRIC, cardFilter);
            addTypeFilter(popupWindow, R.id.typeIceButton, Type.ICE, cardFilter);
            addTypeFilter(popupWindow, R.id.typePsychicButton, Type.PSYCHIC, cardFilter);
            addTypeFilter(popupWindow, R.id.typeFightingButton, Type.FIGHTING, cardFilter);
            addTypeFilter(popupWindow, R.id.typeGroundButton, Type.GROUND, cardFilter);
            addTypeFilter(popupWindow, R.id.typeDarkButton, Type.DARK, cardFilter);
            addTypeFilter(popupWindow, R.id.typeSteelButton, Type.STEEL, cardFilter);
            addTypeFilter(popupWindow, R.id.typeFairyButton, Type.FAIRY, cardFilter);
            addTypeFilter(popupWindow, R.id.typeDragonButton, Type.DRAGON, cardFilter);
            addTypeFilter(popupWindow, R.id.typeStellarButton, Type.STELLAR, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue1, 1, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue2, 2, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue3, 3, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue4, 4, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue5, 5, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue6, 6, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue7, 7, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue8, 8, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue9, 9, cardFilter);
            addPointFilter(popupWindow, R.id.pointValue10, 10, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrATK, Attributes.ATK, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrDEF, Attributes.DEF, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrSPATK, Attributes.SPATK, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrSPDEF, Attributes.SPDEF, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrACC, Attributes.ACC, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrEV, Attributes.EV, cardFilter);
            addAttributeFilter(popupWindow, R.id.attrSPD, Attributes.SPD, cardFilter);

            popupWindow.getContentView().findViewById(R.id.canEvolveY).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getCanEvolve(),true)) {
                    cardFilter.setCanEvolve(null);
                    popupWindow.getContentView().findViewById(R.id.canEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setCanEvolve(true);
                    popupWindow.getContentView().findViewById(R.id.canEvolveY).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.canEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.canEvolveN).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getCanEvolve(), false)) {
                    cardFilter.setCanEvolve(null);
                    popupWindow.getContentView().findViewById(R.id.canEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setCanEvolve(false);
                    popupWindow.getContentView().findViewById(R.id.canEvolveN).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.canEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getCanMegaEvolve(), true)) {
                    cardFilter.setCanMegaEvolve(null);
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setCanMegaEvolve(true);
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getCanMegaEvolve(), false)) {
                    cardFilter.setCanMegaEvolve(null);
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setCanMegaEvolve(false);
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.inDeckY).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getInDeck(), true)) {
                    cardFilter.setInDeck(null);
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setInDeck(true);
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.inDeckN).setOnClickListener(v -> {
                if (Objects.equals(cardFilter.getInDeck(), false)) {
                    cardFilter.setInDeck(null);
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    cardFilter.setInDeck(false);
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });

            popupWindow.getContentView().findViewById(R.id.applyButton).setOnClickListener(v -> {
                filteredCollection.clear();
                cardFilter.setName(((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).getText().toString());
                cardFilter.setAbility(((EditText)popupWindow.getContentView().findViewById(R.id.abilityEditable)).getText().toString());
                for (CardRegistry cr : collection) {
                    if (cardFilter.filterCard(this, cr)) {
                        filteredCollection.add(cr);
                    }
                }

                ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
                recyclerView.setAdapter(new CardAdapter(filteredCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
                popupWindow.dismiss();
            });

            popupWindow.getContentView().findViewById(R.id.resetButton).setOnClickListener(v -> {
                ((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).setText("");
                ((EditText)popupWindow.getContentView().findViewById(R.id.abilityEditable)).setText("");
                cardFilter.clear();
                filteredCollection.clear();
                filteredCollection.addAll(collection);
                updateFilters(popupWindow, cardFilter);

                ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
                recyclerView.setAdapter(new CardAdapter(filteredCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
            });
        }
        else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.item_filter, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);

            updateItemFilters(popupWindow, itemFilter);
            ((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).setText(cardFilter.getName());

            popupWindow.getContentView().findViewById(R.id.aceSpecY).setOnClickListener(v -> {
                if (Objects.equals(itemFilter.getAceSpec(),true)) {
                    itemFilter.setAceSpec(null);
                    popupWindow.getContentView().findViewById(R.id.aceSpecY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    itemFilter.setAceSpec(true);
                    popupWindow.getContentView().findViewById(R.id.aceSpecY).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.aceSpecN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.aceSpecN).setOnClickListener(v -> {
                if (Objects.equals(itemFilter.getAceSpec(), false)) {
                    itemFilter.setAceSpec(null);
                    popupWindow.getContentView().findViewById(R.id.aceSpecN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    itemFilter.setAceSpec(false);
                    popupWindow.getContentView().findViewById(R.id.aceSpecN).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.aceSpecY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.inDeckY).setOnClickListener(v -> {
                if (Objects.equals(itemFilter.getInDeck(), true)) {
                    itemFilter.setInDeck(null);
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    itemFilter.setInDeck(true);
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });
            popupWindow.getContentView().findViewById(R.id.inDeckN).setOnClickListener(v -> {
                if (Objects.equals(itemFilter.getInDeck(), false)) {
                    itemFilter.setInDeck(null);
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
                else {
                    itemFilter.setInDeck(false);
                    popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.button_pressed));
                    popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
                }
            });

            addPointFilter(popupWindow, R.id.pointValue1, 1, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue2, 2, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue3, 3, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue4, 4, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue5, 5, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue6, 6, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue7, 7, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue8, 8, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue9, 9, itemFilter);
            addPointFilter(popupWindow, R.id.pointValue10, 10, itemFilter);

            popupWindow.getContentView().findViewById(R.id.applyButton).setOnClickListener(v -> {
                filteredItemCollection.clear();
                itemFilter.setName(((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).getText().toString());
                //cardFilter.setAbility(((EditText)popupWindow.getContentView().findViewById(R.id.abilityEditable)).getText().toString());
                for (ItemRegistry cr : itemCollection) {
                    if (itemFilter.filterCard(this, cr)) {
                        filteredItemCollection.add(cr);
                    }
                }

                ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
                recyclerView.setAdapter(new ItemAdapter(filteredItemCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
                popupWindow.dismiss();
            });

            popupWindow.getContentView().findViewById(R.id.resetButton).setOnClickListener(v -> {
                ((EditText)popupWindow.getContentView().findViewById(R.id.nameEditable)).setText("");
                itemFilter.clear();
                filteredItemCollection.clear();
                filteredItemCollection.addAll(itemCollection);
                updateItemFilters(popupWindow, itemFilter);

                ConstraintLayout scrollArea = findViewById(R.id.scrollArea);
                recyclerView.setAdapter(new ItemAdapter(filteredItemCollection, this));
                scrollArea.removeAllViews();
                scrollArea.addView(recyclerView);
            });
        }
    }

    public void addTypeFilter(PopupWindow popupWindow, int typeButton, Type t, CardFilter cardFilter) {
        popupWindow.getContentView().findViewById(typeButton).setOnClickListener(v -> {
            if (cardFilter.containsType(t)) {
                cardFilter.removeType(t);
                popupWindow.getContentView().findViewById(typeButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
            }
            else {
                cardFilter.addType(t);
                popupWindow.getContentView().findViewById(typeButton).setBackground(getResources().getDrawable(R.drawable.button_pressed));
            }
        });
    }

    public void addPointFilter(PopupWindow popupWindow, int button, int i, Filter cardFilter) {
        popupWindow.getContentView().findViewById(button).setOnClickListener(v -> {
            if (cardFilter.containsPointValue(i)) {
                cardFilter.removePointValue(i);
                popupWindow.getContentView().findViewById(button).setBackground(getResources().getDrawable(R.drawable.empty_button));
            }
            else {
                cardFilter.addPointValue(i);
                popupWindow.getContentView().findViewById(button).setBackground(getResources().getDrawable(R.drawable.button_pressed));
            }
        });
    }

    public void addAttributeFilter(PopupWindow popupWindow, int button, Attributes a, CardFilter cardFilter) {
        popupWindow.getContentView().findViewById(button).setOnClickListener(v -> {
            if (cardFilter.getHighestAttribute() == a) {
                cardFilter.setHighestAttribute(null);
                popupWindow.getContentView().findViewById(button).setBackground(getResources().getDrawable(R.drawable.empty_button));
            }
            else {
                cardFilter.setHighestAttribute(a);
                popupWindow.getContentView().findViewById(R.id.attrATK).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrDEF).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrSPATK).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrSPDEF).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrACC).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrEV).setBackground(getResources().getDrawable(R.drawable.empty_button));
                popupWindow.getContentView().findViewById(R.id.attrSPD).setBackground(getResources().getDrawable(R.drawable.empty_button));

                popupWindow.getContentView().findViewById(button).setBackground(getResources().getDrawable(R.drawable.button_pressed));
            }
        });
    }

    public void updateFilters(PopupWindow popupWindow, CardFilter cardFilter) {
        if (cardFilter.containsType(Type.NORMAL)) popupWindow.getContentView().findViewById(R.id.typeNormalButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeNormalButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.FIRE)) popupWindow.getContentView().findViewById(R.id.typeFireButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeFireButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.WATER)) popupWindow.getContentView().findViewById(R.id.typeWaterButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeWaterButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.GRASS)) popupWindow.getContentView().findViewById(R.id.typeGrassButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeGrassButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.ELECTRIC)) popupWindow.getContentView().findViewById(R.id.typeElectricButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeElectricButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.ICE)) popupWindow.getContentView().findViewById(R.id.typeIceButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeIceButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.PSYCHIC)) popupWindow.getContentView().findViewById(R.id.typePsychicButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typePsychicButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.FIGHTING)) popupWindow.getContentView().findViewById(R.id.typeFightingButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeFightingButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.GROUND)) popupWindow.getContentView().findViewById(R.id.typeGroundButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeGroundButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.DARK)) popupWindow.getContentView().findViewById(R.id.typeDarkButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeDarkButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.STEEL)) popupWindow.getContentView().findViewById(R.id.typeSteelButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeSteelButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.FAIRY)) popupWindow.getContentView().findViewById(R.id.typeFairyButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeFairyButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.DRAGON)) popupWindow.getContentView().findViewById(R.id.typeDragonButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeDragonButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsType(Type.STELLAR)) popupWindow.getContentView().findViewById(R.id.typeStellarButton).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.typeStellarButton).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(1)) popupWindow.getContentView().findViewById(R.id.pointValue1).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue1).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(2)) popupWindow.getContentView().findViewById(R.id.pointValue2).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue2).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(3)) popupWindow.getContentView().findViewById(R.id.pointValue3).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue3).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(4)) popupWindow.getContentView().findViewById(R.id.pointValue4).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue4).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(5)) popupWindow.getContentView().findViewById(R.id.pointValue5).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue5).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(6)) popupWindow.getContentView().findViewById(R.id.pointValue6).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue6).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(7)) popupWindow.getContentView().findViewById(R.id.pointValue7).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue7).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(8)) popupWindow.getContentView().findViewById(R.id.pointValue8).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue8).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(9)) popupWindow.getContentView().findViewById(R.id.pointValue9).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue9).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.containsPointValue(10)) popupWindow.getContentView().findViewById(R.id.pointValue10).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue10).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.ATK) popupWindow.getContentView().findViewById(R.id.attrATK).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrATK).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.DEF) popupWindow.getContentView().findViewById(R.id.attrDEF).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrDEF).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.SPATK) popupWindow.getContentView().findViewById(R.id.attrSPATK).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrSPATK).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.SPDEF) popupWindow.getContentView().findViewById(R.id.attrSPDEF).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrSPDEF).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.ACC) popupWindow.getContentView().findViewById(R.id.attrACC).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrACC).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.EV) popupWindow.getContentView().findViewById(R.id.attrEV).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrEV).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (cardFilter.getHighestAttribute() == Attributes.SPD) popupWindow.getContentView().findViewById(R.id.attrSPD).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.attrSPD).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getCanEvolve(), true)) popupWindow.getContentView().findViewById(R.id.canEvolveY).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.canEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getCanEvolve(), false)) popupWindow.getContentView().findViewById(R.id.canEvolveN).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.canEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getCanMegaEvolve(), true)) popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.canMegaEvolveY).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getCanMegaEvolve(), false)) popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.canMegaEvolveN).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getInDeck(), true)) popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(cardFilter.getInDeck(), false)) popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
    }

    public void updateItemFilters(PopupWindow popupWindow, ItemFilter itemFilter) {
        if (itemFilter.containsPointValue(1)) popupWindow.getContentView().findViewById(R.id.pointValue1).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue1).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(2)) popupWindow.getContentView().findViewById(R.id.pointValue2).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue2).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(3)) popupWindow.getContentView().findViewById(R.id.pointValue3).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue3).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(4)) popupWindow.getContentView().findViewById(R.id.pointValue4).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue4).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(5)) popupWindow.getContentView().findViewById(R.id.pointValue5).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue5).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(6)) popupWindow.getContentView().findViewById(R.id.pointValue6).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue6).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(7)) popupWindow.getContentView().findViewById(R.id.pointValue7).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue7).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(8)) popupWindow.getContentView().findViewById(R.id.pointValue8).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue8).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(9)) popupWindow.getContentView().findViewById(R.id.pointValue9).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue9).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (itemFilter.containsPointValue(10)) popupWindow.getContentView().findViewById(R.id.pointValue10).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.pointValue10).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(itemFilter.getAceSpec(), true)) popupWindow.getContentView().findViewById(R.id.aceSpecY).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.aceSpecY).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(itemFilter.getAceSpec(), false)) popupWindow.getContentView().findViewById(R.id.aceSpecN).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.aceSpecN).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(itemFilter.getInDeck(), true)) popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.inDeckY).setBackground(getResources().getDrawable(R.drawable.empty_button));
        if (Objects.equals(itemFilter.getInDeck(), false)) popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.button_pressed)); else popupWindow.getContentView().findViewById(R.id.inDeckN).setBackground(getResources().getDrawable(R.drawable.empty_button));
    }
}
