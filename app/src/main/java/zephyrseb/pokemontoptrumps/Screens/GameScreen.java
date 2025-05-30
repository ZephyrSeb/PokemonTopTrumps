package zephyrseb.pokemontoptrumps.Screens;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

import zephyrseb.pokemontoptrumps.AI;
import zephyrseb.pokemontoptrumps.AIDecks;
import zephyrseb.pokemontoptrumps.Attributes;
import zephyrseb.pokemontoptrumps.Card;
import zephyrseb.pokemontoptrumps.CardRegistry;
import zephyrseb.pokemontoptrumps.ContinuousEffect;
import zephyrseb.pokemontoptrumps.Deck;
import zephyrseb.pokemontoptrumps.Effect;
import zephyrseb.pokemontoptrumps.EffectAction;
import zephyrseb.pokemontoptrumps.FieldEffect;
import zephyrseb.pokemontoptrumps.Item;
import zephyrseb.pokemontoptrumps.Player;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;
import zephyrseb.pokemontoptrumps.StatusCondition;
import zephyrseb.pokemontoptrumps.Type;
import zephyrseb.pokemontoptrumps.comparators.EffectComparator;

public class GameScreen extends AppCompatActivity {
    private int turn;
    private Player activePlayer;
    private int startingPlayer;
    private int prizePot = 1;
    private Attributes choice = null;
    private boolean megaEvolution = false;
    private boolean megaEvolved = false;
    private int round = 0;
    private int highestStat = 0;
    private int selectedItem = -1;
    private Attributes chosenAttribute = null;
    private Attributes previousAttribute = null;
    private Map<Attributes, Boolean> usedAttribute = new HashMap<>();
    private Map<Attributes, Boolean> availableAttribute = new HashMap<>();
    private boolean newItem = false;
    private FieldEffect fieldEffect = FieldEffect.NONE;
    private PriorityQueue<EffectAction> effectQueue = new PriorityQueue<>(new EffectComparator());
    private String mode;
    private Map<Player, Deck> decks = new HashMap<>();
    private boolean goalLarge = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        SaveData playerData = new SaveData();
        String nameP1 = playerData.readFile(this).getName();
        String nameP2 = generateTrainerName();
        Player player1 = new Player(nameP1);
        Player player2 = new Player(nameP2);
        TextView bannerNameP1 = findViewById(R.id.bannerPlayer).findViewById(R.id.playerName);
        bannerNameP1.setText(nameP1);
        TextView bannerNameP2 = findViewById(R.id.bannerOpponent).findViewById(R.id.playerName);
        bannerNameP2.setText(nameP2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            mode = extras.getString("mode");
            decks.put(player1, gson.fromJson(extras.getString("deck"), Deck.class));
            Random rand = new Random();
            AIDecks[] deckList = {AIDecks.TOWER_RAIN, AIDecks.TOWER_SUN};
            AIDecks opponentDeck = deckList[rand.nextInt(deckList.length)];
            decks.put(player2, AIDecks.generateDeck(opponentDeck));
        }
        usedAttribute.put(Attributes.HP, false);
        usedAttribute.put(Attributes.ATK, false);
        usedAttribute.put(Attributes.DEF, false);
        usedAttribute.put(Attributes.SPATK, false);
        usedAttribute.put(Attributes.SPDEF, false);
        usedAttribute.put(Attributes.SPD, false);
        availableAttribute.put(Attributes.HP, true);
        availableAttribute.put(Attributes.ATK, true);
        availableAttribute.put(Attributes.DEF, true);
        availableAttribute.put(Attributes.SPATK, true);
        availableAttribute.put(Attributes.SPDEF, true);
        availableAttribute.put(Attributes.SPD, true);
        Random rand = new Random();
        turn = rand.nextInt(2);
        startingPlayer = turn;
        if (turn == 0) player2.addItem(generateItem());
        if (turn == 1) player1.addItem(generateItem());
        if (turn == 1) newItem = true;
        View view = findViewById(R.id.card);
        if (view != null) {
            view.setScaleX(1.25f);
            view.setScaleY(1.25f);
        }
        initializeDeck(player1);
        initializeDeck(player2);
        tick(player1,player2);
    }

    public void initializeDeck(Player player) {
        if (Objects.equals(mode, "free_play")) {
            for (CardRegistry c : decks.get(player).getDeck()) {
                player.addCard(CardRegistry.initCard(this, c));
            }
        }
        if (Objects.equals(mode, "classic")) {
            Random rand = new Random();
            int starters1 = rand.nextInt(9);
            int starters2 = rand.nextInt(9);
            if (starters2 == 0) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.VENUSAUR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHARIZARD));
                player.addCard(CardRegistry.initCard(this, CardRegistry.BLASTOISE));
            }
            if (starters2 == 1) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.MEGANIUM));
                player.addCard(CardRegistry.initCard(this, CardRegistry.TYPHLOSION));
                player.addCard(CardRegistry.initCard(this, CardRegistry.FERALIGATR));
            }
            if (starters2 == 2) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.SCEPTILE));
                player.addCard(CardRegistry.initCard(this, CardRegistry.BLAZIKEN));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SWAMPERT));
            }
            if (starters2 == 3) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.TORTERRA));
                player.addCard(CardRegistry.initCard(this, CardRegistry.INFERNAPE));
                player.addCard(CardRegistry.initCard(this, CardRegistry.EMPOLEON));
            }
            if (starters2 == 4) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.SERPERIOR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.EMBOAR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SAMUROTT));
            }
            if (starters2 == 5) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHESNAUGHT));
                player.addCard(CardRegistry.initCard(this, CardRegistry.DELPHOX));
                player.addCard(CardRegistry.initCard(this, CardRegistry.GRENINJA));
            }
            if (starters2 == 6) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.DECIDUEYE));
                player.addCard(CardRegistry.initCard(this, CardRegistry.INCINEROAR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.PRIMARINA));
            }
            if (starters2 == 7) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.RILLABOOM));
                player.addCard(CardRegistry.initCard(this, CardRegistry.CINDERACE));
                player.addCard(CardRegistry.initCard(this, CardRegistry.INTELEON));
            }
            if (starters2 == 8) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.MEOWSCARADA));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SKELEDIRGE));
                player.addCard(CardRegistry.initCard(this, CardRegistry.QUAQUAVAL));
            }
            if (starters1 == 0) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.BULBASAUR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHARMANDER));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SQUIRTLE));
            }
            if (starters1 == 1) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHIKORITA));
                player.addCard(CardRegistry.initCard(this, CardRegistry.CYNDAQUIL));
                player.addCard(CardRegistry.initCard(this, CardRegistry.TOTODILE));
            }
            if (starters1 == 2) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.TREECKO));
                player.addCard(CardRegistry.initCard(this, CardRegistry.TORCHIC));
                player.addCard(CardRegistry.initCard(this, CardRegistry.MUDKIP));
            }
            if (starters1 == 3) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.TURTWIG));
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHIMCHAR));
                player.addCard(CardRegistry.initCard(this, CardRegistry.PIPLUP));
            }
            if (starters1 == 4) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.SNIVY));
                player.addCard(CardRegistry.initCard(this, CardRegistry.TEPIG));
                player.addCard(CardRegistry.initCard(this, CardRegistry.OSHAWOTT));
            }
            if (starters1 == 5) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.CHESPIN));
                player.addCard(CardRegistry.initCard(this, CardRegistry.FENNEKIN));
                player.addCard(CardRegistry.initCard(this, CardRegistry.FROAKIE));
            }
            if (starters1 == 6) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.ROWLET));
                player.addCard(CardRegistry.initCard(this, CardRegistry.LITTEN));
                player.addCard(CardRegistry.initCard(this, CardRegistry.POPPLIO));
            }
            if (starters1 == 7) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.GROOKEY));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SCORBUNNY));
                player.addCard(CardRegistry.initCard(this, CardRegistry.SOBBLE));
            }
            if (starters1 == 8) {
                player.addCard(CardRegistry.initCard(this, CardRegistry.SPRIGATITO));
                player.addCard(CardRegistry.initCard(this, CardRegistry.FUECOCO));
                player.addCard(CardRegistry.initCard(this, CardRegistry.QUAXLY));
            }
            CardRegistry[] notFullyEvolved = {CardRegistry.PIKACHU, CardRegistry.EEVEE, CardRegistry.MURKROW, CardRegistry.PORYGON, CardRegistry.RALTS, CardRegistry.PLUSLE, CardRegistry.MINUN, CardRegistry.PAWMI};
            CardRegistry[] pickup = {CardRegistry.ZIGZAGOON, CardRegistry.AIPOM, CardRegistry.PACHIRISU, CardRegistry.LILLIPUP};
            CardRegistry[] mythical = {CardRegistry.MEW, CardRegistry.CELEBI, CardRegistry.JIRACHI, CardRegistry.SHAYMIN_LAND_FORME, CardRegistry.VICTINI};
            CardRegistry[] moldBreaker = {CardRegistry.HAXORUS, CardRegistry.PINSIR, CardRegistry.RAMPARDOS, CardRegistry.PANGORO};
            player.addCard(CardRegistry.initCard(this, notFullyEvolved[rand.nextInt(notFullyEvolved.length)]));
            player.addCard(CardRegistry.initCard(this, pickup[rand.nextInt(pickup.length)]));
            player.addCard(CardRegistry.initCard(this, mythical[rand.nextInt(mythical.length)]));
            player.addCard(CardRegistry.initCard(this, moldBreaker[rand.nextInt(moldBreaker.length)]));
            List<Card> pokemon = new ArrayList<>();
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GARDEVOIR));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GALLADE));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GYARADOS));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.SNORLAX));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.CORVIKNIGHT));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.FLYGON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MACHAMP));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.RAICHU));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.TINKATON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.WOBBUFFET));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.KANGASKHAN));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.AERODACTYL));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.AMPHAROS));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MEDICHAM));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MANECTRIC));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.ABSOL));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.STOUTLAND));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.CLEFABLE));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.CLOYSTER));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MAROWAK));
            for (int i = 0; i < 5; i++) {
                int r = rand.nextInt(pokemon.size());
                player.addCard(pokemon.get(r).clone());
                pokemon.remove(r);
            }
            player.addCard(CardRegistry.initCard(this, CardRegistry.DITTO));
            player.addCard(CardRegistry.initCard(this, CardRegistry.UNOWN));
            player.addCard(CardRegistry.initCard(this, CardRegistry.SPINDA));
            player.addCard(CardRegistry.initCard(this, CardRegistry.SLAKING));
            player.addCard(CardRegistry.initCard(this, CardRegistry.MANAPHY));
        }
        player.shuffle();
    }

    //Main gameplay loop
    public void tick(Player p1, Player p2) {
        final Handler handler = new Handler(Looper.getMainLooper());
        Animation startAnim = AnimationUtils.loadAnimation(this, R.anim.turn_banner_enter);
        availableAttribute.put(Attributes.HP, true);
        availableAttribute.put(Attributes.ATK, true);
        availableAttribute.put(Attributes.DEF, true);
        availableAttribute.put(Attributes.SPATK, true);
        availableAttribute.put(Attributes.SPDEF, true);
        availableAttribute.put(Attributes.SPD, true);
        if (fieldEffect == FieldEffect.DARKNESS) {
            Random rand = new Random();
            int i = rand.nextInt(6);
            int j = rand.nextInt(6);
            while (i == j) {
                j = rand.nextInt(6);
            }
            if (i == 0 || j == 0) availableAttribute.put(Attributes.HP, false);
            if (i == 1 || j == 1) availableAttribute.put(Attributes.ATK, false);
            if (i == 2 || j == 2) availableAttribute.put(Attributes.DEF, false);
            if (i == 3 || j == 3) availableAttribute.put(Attributes.SPATK, false);
            if (i == 4 || j == 4) availableAttribute.put(Attributes.SPDEF, false);
            if (i == 5 || j == 5) availableAttribute.put(Attributes.SPD, false);
        }
        if (turn == 0) {
            activePlayer = p1;
            updateToast("Choose an attribute to compare", -1);
            findViewById(R.id.turnBannerRed).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRedText).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRed).startAnimation(startAnim);
            findViewById(R.id.turnBannerRedText).startAnimation(startAnim);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRed).setVisibility(View.INVISIBLE), 1500);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRedText).setVisibility(View.INVISIBLE), 1500);
        }
        if (turn == 1) {
            activePlayer = p2;
            updateToast(p2.getName() + " is choosing an attribute", -1);
            findViewById(R.id.turnBannerBlue).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerBlueText).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerBlue).startAnimation(startAnim);
            findViewById(R.id.turnBannerBlueText).startAnimation(startAnim);
            handler.postDelayed(() -> findViewById(R.id.turnBannerBlue).setVisibility(View.INVISIBLE), 1500);
            handler.postDelayed(() -> findViewById(R.id.turnBannerBlueText).setVisibility(View.INVISIBLE), 1500);
        }
        if (newItem) {
            findViewById(R.id.itemAlert).setVisibility(View.VISIBLE);
        }
        round++;
        megaEvolution = false;
        selectedItem = -1;
        updateItemImage(findViewById(R.id.itemCard), null);
        updateItemImage(findViewById(R.id.itemCardOpponent), null);
        if (!p1.hasCards()) p1.shuffle();
        if (!p2.hasCards()) p2.shuffle();
        p1.reset();
        p2.reset();
        ConstraintLayout currentCard = findViewById(R.id.card);
        Animation cardEnter = AnimationUtils.loadAnimation(this, R.anim.card_enter);
        currentCard.startAnimation(cardEnter);
        findViewById(R.id.burst).setVisibility(View.INVISIBLE);
        findViewById(R.id.opponentCard).setVisibility(View.INVISIBLE);
        adjustCard(currentCard, 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.p1Attribute));
        adjustCard(findViewById(R.id.opponentCard), 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.p2Attribute));
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).clearAnimation();
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setVisibility(View.INVISIBLE);
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setScaleX(1);
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setScaleY(1);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).clearAnimation();
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setVisibility(View.INVISIBLE);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setScaleX(1);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setScaleY(1);
        updateStatArrows(findViewById(R.id.card), p1, View.INVISIBLE);
        updateStatArrows(findViewById(R.id.opponentCard), p1, View.INVISIBLE);
        updateChoice(findViewById(R.id.card),null);
        updateChoice(findViewById(R.id.opponentCard),null);
        ImageView megaButton = findViewById(R.id.megaEvolutionButton);
        Button submitButton = findViewById(R.id.submitButton);
        Button itemButton = findViewById(R.id.itemButton);
        submitButton.setEnabled(true);
        megaButton.setEnabled(true);
        itemButton.setEnabled(true);
        findViewById(R.id.buttonHP).setEnabled(true);
        findViewById(R.id.buttonATK).setEnabled(true);
        findViewById(R.id.buttonDEF).setEnabled(true);
        findViewById(R.id.buttonSPATK).setEnabled(true);
        findViewById(R.id.buttonSPDEF).setEnabled(true);
        findViewById(R.id.buttonSPD).setEnabled(true);
        p1.getCurrentCard().displayCard(this, currentCard);
        p2.getCurrentCard().displayCard(this, findViewById(R.id.opponentCard));
        findViewById(R.id.buttonHP).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.HP)) updateChoice(currentCard, Attributes.HP);});
        findViewById(R.id.buttonATK).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.ATK)) updateChoice(currentCard, Attributes.ATK);});
        findViewById(R.id.buttonDEF).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.DEF)) updateChoice(currentCard, Attributes.DEF);});
        findViewById(R.id.buttonSPATK).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.SPATK)) updateChoice(currentCard, Attributes.SPATK);});
        findViewById(R.id.buttonSPDEF).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.SPDEF)) updateChoice(currentCard, Attributes.SPDEF);});
        findViewById(R.id.buttonSPD).setOnClickListener(v -> {if (turn == 0 && availableAttribute.get(Attributes.SPD)) updateChoice(currentCard, Attributes.SPD);});
        ImageView abilityButton = findViewById(R.id.abilityIcon);
        p1.displayBanner(this, findViewById(R.id.bannerPlayer), p2.getPoints());
        p2.displayBanner(this, findViewById(R.id.bannerOpponent), p1.getPoints());

        //Sets the functionality for clicking on the ability
        setButtonFunctionAbility(abilityButton, p1.getCurrentCard());
        abilityButton.setEnabled(true);

        if (p1.getCurrentCard().canMegaEvolve() && p1.megaEvolve) {
            megaButton.setImageResource(R.drawable.icon_mega_evolution_black);
        }
        else {
            megaButton.setImageResource(R.drawable.icon_mega_evolution_white);
        }

        //Sets the functionality of the item button
        if (!p1.hasItems()) {
            itemButton.setEnabled(false);
            ((ImageView) findViewById(R.id.itemImage)).setImageResource(R.drawable.icon_item_white);
        }
        else {
            ((ImageView) findViewById(R.id.itemImage)).setImageResource(R.drawable.icon_item_black);
        }
        itemButton.setOnClickListener(v -> {
            newItem = false;
            findViewById(R.id.itemAlert).setVisibility(View.INVISIBLE);
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            itemButton.startAnimation(buttonPulse);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.item_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            List<ConstraintLayout> views = new ArrayList<>();
            views.add(popupWindow.getContentView().findViewById(R.id.item1));
            views.add(popupWindow.getContentView().findViewById(R.id.item2));
            views.add(popupWindow.getContentView().findViewById(R.id.item3));
            views.add(popupWindow.getContentView().findViewById(R.id.item4));
            views.add(popupWindow.getContentView().findViewById(R.id.item5));
            views.add(popupWindow.getContentView().findViewById(R.id.item6));
            for (int i = 0; i < 6; i++) {
                if (p1.noItems() > i) {
                    updateItemSelection(popupWindow.getContentView(), selectedItem);
                    views.get(i).setVisibility(View.VISIBLE);
                    p1.getItem(i).displayCard(views.get(i));
                    views.get(i).setScaleX(0.375f);
                    views.get(i).setScaleY(0.375f);
                    int finalI = i;
                    views.get(i).setOnClickListener(u -> {
                        Item temp;
                        if (selectedItem != finalI) {
                            selectedItem = finalI;
                            temp = p1.getItem(selectedItem);
                        }
                        else {
                            selectedItem = -1;
                            temp = null;
                        }
                        updateItemImage(findViewById(R.id.itemCard), temp);
                        updateItemSelection(popupWindow.getContentView(), selectedItem);
                    });
                    views.get(i).setOnLongClickListener(u -> {
                        LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        @SuppressLint("InflateParams") PopupWindow popupWindow2 = new PopupWindow(inflater2.inflate(R.layout.card_layout_item, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                        popupWindow2.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
                        p1.getItem(finalI).displayCard(popupWindow2.getContentView().findViewById(R.id.cardItemRaw));
                        return true;
                    });
                }
                else views.get(i).setVisibility(View.INVISIBLE);
            }
        });

        //Sets the functionality for the mega evolution button
        megaButton.setOnClickListener(v -> {
            if (megaEvolution) {
                megaButton.setImageResource(R.drawable.icon_mega_evolution_black);
                p1.getCurrentCard().displayCard(this, currentCard);
                setButtonFunctionAbility(abilityButton, p1.getCurrentCard());
                megaEvolution = false;
            } else if (p1.getCurrentCard().canMegaEvolve() && !megaEvolved) {
                megaButton.setImageResource(R.drawable.mega_evolution);
                p1.getCurrentCard().megaEvolve().displayCard(this, currentCard);
                setButtonFunctionAbility(abilityButton, p1.getCurrentCard().megaEvolve());
                megaEvolution = true;
            }
        });

        //Sets the functionality for the submit button
        submitButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            submitButton.startAnimation(buttonPulse);
            AI ai = new AI(this, p2.getCurrentCard(), p2.megaEvolve, turn == 1, p1, p2);
            if (turn == 1) {
                ai.chooseAttack();
                updateChoice(currentCard, ai.getPreferredStat());
            }
            else ai.chooseDefend();
            Animation cardCompare = AnimationUtils.loadAnimation(this, R.anim.card_to_compare);
            ConstraintLayout opponentCard = findViewById(R.id.opponentCard);
            Animation cardOpponent = AnimationUtils.loadAnimation(this, R.anim.card_opponent_enter);
            if (!(choice == null)) {
                submitButton.setEnabled(false);
                itemButton.setEnabled(false);
                megaButton.setEnabled(false);
                abilityButton.setEnabled(false);
                findViewById(R.id.buttonHP).setEnabled(false);
                findViewById(R.id.buttonATK).setEnabled(false);
                findViewById(R.id.buttonDEF).setEnabled(false);
                findViewById(R.id.buttonSPATK).setEnabled(false);
                findViewById(R.id.buttonSPDEF).setEnabled(false);
                findViewById(R.id.buttonSPD).setEnabled(false);
                currentCard.startAnimation(cardCompare);
                opponentCard.startAnimation(cardOpponent);
                adjustCard(opponentCard, -175f, -250f, 0.6f, View.INVISIBLE, findViewById(R.id.p2Attribute));
                cardCompare.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        compare(p1, p2, choice, ai);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });

        //Sets the functionality of the field effect button (weather indicator)
        ImageView fieldIcon = findViewById(R.id.fieldEffect);
        fieldIcon.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(FieldEffect.setName(this, fieldEffect));
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(FieldEffect.setDescription(this, fieldEffect));
            ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(FieldEffect.setImage(this, fieldEffect));
        });

        //Sets the functionality of each status effect indicator
        ImageView statusIcon1 = findViewById(R.id.bannerPlayer).findViewById(R.id.statusIcon);
        statusIcon1.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(StatusCondition.setName(this, p1.getStatusCondition()));
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(StatusCondition.setDescription(this, p1.getStatusCondition()));
            ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(StatusCondition.getImage(this, p1.getStatusCondition()));
        });

        ImageView statusIcon2 = findViewById(R.id.bannerOpponent).findViewById(R.id.statusIcon);
        statusIcon2.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(StatusCondition.setName(this, p2.getStatusCondition()));
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(StatusCondition.setDescription(this, p2.getStatusCondition()));
            ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(StatusCondition.getImage(this, p2.getStatusCondition()));
        });
    }

    //Compares the two cards and determines the winner
    public void compare(Player p1, Player p2, Attributes s, AI ai) {
        chosenAttribute = s;
        goalLarge = true;
        final Handler handler = new Handler(Looper.getMainLooper());
        findViewById(R.id.submitButton).setEnabled(false);
        findViewById(R.id.megaEvolutionButton).setEnabled(false);
        findViewById(R.id.itemButton).setEnabled(false);
        p1.setPlayedItem(null);
        p2.setPlayedItem(null);
        p1.playCard();
        p2.playCard();
        if (ai.getPreferredItem() != null) {
            p2.setPlayedItem(p2.useItem(ai.getPreferredItem()));
        }
        if (p2.getPlayedItem() != null) {
            updateItemImage(findViewById(R.id.itemCardOpponent),ai.getPreferredItem());
        }
        if (selectedItem > -1) {
            p1.setPlayedItem(p1.useItem(selectedItem));
        }
        if (megaEvolution) {
            megaEvolved = true;
            p1.megaEvolve = false;
            p1.setPlayedCard(p1.getPlayedCard().megaEvolve());
        }
        if (ai.getPreferredME()) {
            p2.megaEvolve = false;
            p2.setPlayedCard(p2.getPlayedCard().megaEvolve());
            p2.getPlayedCard().displayCard(this, findViewById(R.id.opponentCard));
        }

        //Charge effects
        Effect chargeEffect = new Effect((game, player, opponent) -> player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) + 1), 0, "charge");
        if (p1.getCharge() && p1.checkType(p1.getPlayedCard(), Type.ELECTRIC)) {
            p1.setUsedCharge(true);
            effectQueue.add(new EffectAction(chargeEffect, p1, null, p1.getPlayedCard().getAttribute(Attributes.SPD)));
        }
        if (p2.getCharge() && p2.checkType(p2.getPlayedCard(), Type.ELECTRIC)) {
            p2.setUsedCharge(true);
            effectQueue.add(new EffectAction(chargeEffect, p2, null, p2.getPlayedCard().getAttribute(Attributes.SPD)));
        }

        //Applies ability and item modifiers
        if (p1.getPlayedItem() != null) {
            for (Effect e : p1.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p2.getPlayedItem() != null) {
            for (Effect e : p2.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p1.getPlayedCard().getAbility() != null) {
            for (Effect e : p1.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p2.getPlayedCard().getAbility() != null) {
            for (Effect e : p2.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (getFieldEffect() != FieldEffect.NONE) {
            for (Effect e : FieldEffect.getEffects(getFieldEffect())) {
                effectQueue.add(new EffectAction(e, p1, null, 0));
                effectQueue.add(new EffectAction(e, p2, null, 0));
            }
        }
        if (p1.getStatusCondition() != StatusCondition.NONE) {
            for (Effect e : StatusCondition.getEffects(p1.getStatusCondition())) {
                effectQueue.add(new EffectAction(e, p1, null, 0));
            }
        }
        if (p2.getStatusCondition() != StatusCondition.NONE) {
            for (Effect e : StatusCondition.getEffects(p2.getStatusCondition())) {
                effectQueue.add(new EffectAction(e, p2, null, 0));
            }
        }
        if (!p1.getDelayedEffects().isEmpty()) {
            for (ContinuousEffect e : p1.getDelayedEffects()) {
                effectQueue.add(new EffectAction(e.getEffect(), p1, null, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (!p2.getDelayedEffects().isEmpty()) {
            for (ContinuousEffect e : p2.getDelayedEffects()) {
                effectQueue.add(new EffectAction(e.getEffect(), p2, null, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        Random rand = new Random();
        if (rand.nextInt(10) < p1.getBreakPoints()) effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) - 1);
            player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);
            player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) - 1);
            player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 1);
            player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) - 1);
            player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) - 1);
            player.setBreakPoints(0);
        }, 0, "break"), p1, null, 0));
        if (rand.nextInt(10) < p2.getBreakPoints()) effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) - 1);
            player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);
            player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) - 1);
            player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 1);
            player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) - 1);
            player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) - 1);
            player.setBreakPoints(0);
        }, 0, "break"), p2, null, 0));
        p1.clearDelayedEffects();
        p2.clearDelayedEffects();

        applyPassiveEffect();

        updateChoice(findViewById(R.id.card),chosenAttribute);
        updateChoice(findViewById(R.id.opponentCard),chosenAttribute);
        p1.setCurrentStat(p1.getPlayedCard().getAttribute(chosenAttribute));
        p2.setCurrentStat(p2.getPlayedCard().getAttribute(chosenAttribute));

        //Applies modifiers due to weaknesses
        ConstraintLayout card1 = findViewById(R.id.card);
        ConstraintLayout card2 = findViewById(R.id.opponentCard);

        p1.getPlayedCard().displayCard(this, card1);
        p2.getPlayedCard().displayCard(this, card2);

        boolean critP1 = setCritical(p1.getCritRate());
        boolean critP2 = setCritical(p2.getCritRate());

        p1.setCurrentStat((int)(p1.getCurrentStat() * applyLevel(p1.getLevel(s)) * setWeakness(p1.getPlayedCard(), p2.getPlayedCard(), card2.findViewById(R.id.weaknessBurst), p1.getSupereffectiveMultiplier(), p1.getSupereffectiveHit()) * (critP1 ? p1.getCritMultiplier() : 1d)));
        p2.setCurrentStat((int)(p2.getCurrentStat() * applyLevel(p2.getLevel(s)) * setWeakness(p2.getPlayedCard(), p1.getPlayedCard(), card1.findViewById(R.id.weaknessBurst), p2.getSupereffectiveMultiplier(), p1.getSupereffectiveHit()) * (critP2 ? p2.getCritMultiplier() : 1d)));

        updateStatArrows(card1, p1, View.VISIBLE);
        updateStatArrows(card2, p2, View.VISIBLE);

        card2.setVisibility(View.VISIBLE);
        findViewById(R.id.burst).setVisibility(View.VISIBLE);
        handler.postDelayed(() -> adjustCard(card1, 175f, 250f, 0.6f, View.VISIBLE, findViewById(R.id.p1Attribute)), 1);
        adjustCard(card2, -175f, -250f, 0.6f, View.VISIBLE, findViewById(R.id.p2Attribute));
        ((TextView)findViewById(R.id.p1Attribute)).setText(String.valueOf(p1.getCurrentStat()));
        ((TextView)findViewById(R.id.p2Attribute)).setText(String.valueOf(p2.getCurrentStat()));

        previousAttribute = chosenAttribute;
        usedAttribute.put(chosenAttribute, true);

        //Determines the higher stat and performs the appropriate action
        if (p1.getCurrentStat() > highestStat || p2.getCurrentStat() > highestStat) {
            highestStat = Math.max(p1.getCurrentStat(),p2.getCurrentStat());
        }
        if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == -1) {
            if (critP1) prizePot *= (int) p1.getCritPointMultiplier();
            p1.addPoint(prizePot);
            p2.addItem(generateItem());
            handler.postDelayed(() -> setPrizePot(1), 3000);
            setPriority(p1, p2, "p1");
            updateToast(p1.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_player);
            handler.postDelayed(() -> card1.startAnimation(animCard), 2800);
            handler.postDelayed(() -> card2.startAnimation(animCard), 2800);
        }
        else if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == 1) {
            if (critP2) prizePot *= (int) p2.getCritPointMultiplier();
            p2.addPoint(prizePot);
            p1.addItem(generateItem());
            newItem = true;
            handler.postDelayed(() -> setPrizePot(1), 3000);
            setPriority(p1, p2, "p2");
            updateToast(p2.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_opponent);
            handler.postDelayed(() -> card1.startAnimation(animCard), 2800);
            handler.postDelayed(() -> card2.startAnimation(animCard), 2800);
        }
        else if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == 0) {
            handler.postDelayed(() -> setPrizePot(prizePot + 1), 3000);
            setPriority(p1, p2, "tie");
            updateToast("It's a tie", -1);
        }
        p1.displayBanner(this, findViewById(R.id.bannerPlayer), p2.getPoints());
        p2.displayBanner(this, findViewById(R.id.bannerOpponent), p1.getPoints());

        if (p1.getPoints() >= 6) {
            handler.postDelayed(() -> playerWin("Victory"), 1500);
            handler.postDelayed(() -> resultScreen(p1, p2), 3000);
        }
        else if (p2.getPoints() >= 6) {
            handler.postDelayed(() -> playerWin("Defeat"), 1500);
            handler.postDelayed(() -> resultScreen(p1, p2), 3000);
        }
        else {
            handler.postDelayed(() -> tick(p1, p2), 3000);
        }
    }

    private int checkGoal(Player p1, Player p2, int i1, int i2) {
        if (p1.getAutoWin() && !p2.getAutoWin()) return -1;
        if (!p1.getAutoWin() && p2.getAutoWin()) return 1;
        if (goalLarge) {
            return Integer.compare(i2, i1);
        }
        else {
            return Integer.compare(i1, i2);
        }
    }

    public void setGoalReverse(boolean b) {
        goalLarge = b;
    }

    public Attributes getChosenAttribute() {
        return chosenAttribute;
    }

    public Attributes getPreviousAttribute() {
        return previousAttribute;
    }

    public boolean checkAttribute(Attributes s) {
        return usedAttribute.get(s);
    }

    public void setButtonFunctionAbility(ImageView abilityButton, Card c1) {
        abilityButton.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView) popupWindow.getContentView().findViewById(R.id.abilityName)).setText(String.valueOf(c1.getAbility().getName()));
            ((TextView) popupWindow.getContentView().findViewById(R.id.abilityDescription)).setText(String.valueOf(c1.getAbility().getDescription()));
        });
    }

    //Adjusts the size and position of a layout on the screen
    public void adjustCard(ConstraintLayout card, float tX, float tY, float scale, int visible, TextView attribute) {
        card.setScaleX(scale);
        card.setScaleY(scale);
        card.setTranslationX(tX);
        card.setTranslationY(tY);
        if (attribute != null) attribute.setVisibility(visible);
    }

    public void updateItemImage(ConstraintLayout view, Item i) {
        if (i == null) {
            view.setVisibility(View.INVISIBLE);
        }
        else {
            view.setVisibility(View.VISIBLE);
            view.setScaleX(0.5f);
            view.setScaleY(0.5f);
            i.displayCard(view);
        }
    }

    public void updateItemSelection(View view, int i) {
        if (i == 0) view.findViewById(R.id.check1).setVisibility(View.VISIBLE); else view.findViewById(R.id.check1).setVisibility(View.INVISIBLE);
        if (i == 1) view.findViewById(R.id.check2).setVisibility(View.VISIBLE); else view.findViewById(R.id.check2).setVisibility(View.INVISIBLE);
        if (i == 2) view.findViewById(R.id.check3).setVisibility(View.VISIBLE); else view.findViewById(R.id.check3).setVisibility(View.INVISIBLE);
        if (i == 3) view.findViewById(R.id.check4).setVisibility(View.VISIBLE); else view.findViewById(R.id.check4).setVisibility(View.INVISIBLE);
        if (i == 4) view.findViewById(R.id.check5).setVisibility(View.VISIBLE); else view.findViewById(R.id.check5).setVisibility(View.INVISIBLE);
        if (i == 5) view.findViewById(R.id.check6).setVisibility(View.VISIBLE); else view.findViewById(R.id.check6).setVisibility(View.INVISIBLE);
        System.out.println(i);
    }

    //Updates which attribute is being used for the current round
    public void updateChoice(ConstraintLayout card, Attributes s) {
        if (s == Attributes.HP) card.findViewById(R.id.buttonHP).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonHP).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.ATK) card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.DEF) card.findViewById(R.id.buttonDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.SPATK) card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.SPDEF) card.findViewById(R.id.buttonSPDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.SPD) card.findViewById(R.id.buttonSPD).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPD).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        choice = s;
    }

    //Updates the information in the text box at the top of the screen
    public void updateToast(String s, int ms) {
        TextView toast = findViewById(R.id.toast);
        toast.setText(s);
        if (ms > 0) {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(this::removeToast, ms);
        }
    }

    public void removeToast() {
        TextView toast = findViewById(R.id.toast);
        toast.setText("");
    }

    public void setPriority(Player p1, Player p2, String s) {
        if (p1.getSpeed() > p2.getSpeed()) turn = 0;
        if (p2.getSpeed() > p1.getSpeed()) turn = 1;
        if (p1.getSpeed() == p2.getSpeed()) {
            if (Objects.equals(s, "p1")) turn = 0;
            if (Objects.equals(s, "p2")) turn = 1;
        }
        p1.setSpeed(0);
        p2.setSpeed(0);
    }

    public void playerWin(String s) {
        ImageView banner = findViewById(R.id.banner);
        TextView bannerText = findViewById(R.id.bannerText);
        banner.setVisibility(View.VISIBLE);
        bannerText.setVisibility(View.VISIBLE);
        bannerText.setText(s);
        Animation bannerAnim = AnimationUtils.loadAnimation(this, R.anim.win_banner_enter);
        Animation bannerTextAnim = AnimationUtils.loadAnimation(this, R.anim.win_banner_text);
        banner.startAnimation(bannerAnim);
        bannerText.startAnimation(bannerTextAnim);

    }

    //Sends the player to the results screen
    public void resultScreen(Player p1, Player p2) {
        setContentView(R.layout.activity_result);
        Button continueButton = findViewById(R.id.activityResult).findViewById(R.id.continueButton);
        Intent intent = new Intent(this, StartScreen.class);
        continueButton.setOnClickListener(v -> startActivity(intent));

        if (p1.getPoints() >= 6) ((TextView) findViewById(R.id.winner)).setText("You win!");
        if (p2.getPoints() >= 6) ((TextView) findViewById(R.id.winner)).setText("You lose...");
        int q1 = Math.max(0, 6 - p1.getPoints());
        int q2 = Math.max(0, 6 - p2.getPoints());
        String sPlayer = "";
        if (startingPlayer == 0) sPlayer = p1.getName();
        if (startingPlayer == 1) sPlayer = p2.getName();
        ((TextView) findViewById(R.id.turnOrderText)).setText(sPlayer);
        ((TextView) findViewById(R.id.finalScoreText)).setText(q2 + " - " + q1);
        ((TextView) findViewById(R.id.roundsText)).setText(String.valueOf(round));
        ((TextView) findViewById(R.id.highAttributeText)).setText(String.valueOf(highestStat));

        if (p1.getPoints() >= 6 && Objects.equals(mode, "classic")) {
            SaveData playerData = new SaveData();
            playerData = playerData.readFile(this);
            boolean success = false;
            while (!success) {
                CardRegistry cr = CardRegistry.generateRandom();
                if (!playerData.getCollection().contains(cr)) {
                    playerData.addCard(cr);
                    success = true;
                }
                if (playerData.getCollection().size() == CardRegistry.values().length) {
                    success = true;
                }
            }
            playerData.writeFile(this);
        }
    }

    //Alters the power of attacks due to weaknesses, and displays this on screen
    private double setWeakness(Card attacker, Card defender, ImageView burst, double mod, boolean isTrue) {
        if (attacker.checkType(defender) || isTrue) {
            burst.setVisibility(View.VISIBLE);
            Animation burstPulse = AnimationUtils.loadAnimation(this, R.anim.burst_pulse);
            burst.startAnimation(burstPulse);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                burst.setScaleX(4);
                burst.setScaleY(4);
            }, 500);
            return mod;
        }
        return 1d;
    }

    //Adjusts the size of the prize pot, and displays this on screen
    public void setPrizePot(int p) {
        prizePot = p;
        if (prizePot == 1) {
            findViewById(R.id.prizePot).setVisibility(View.INVISIBLE);
            findViewById(R.id.prizePotText).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.prizePot).setVisibility(View.VISIBLE);
            findViewById(R.id.prizePotText).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.prizePotText)).setText("x" + prizePot);
        }
    }

    public int getPrizePot() {
        return prizePot;
    }

    private void applyPassiveEffect() {
        while (!effectQueue.isEmpty()) {
            EffectAction e = effectQueue.poll();
            e.applyEffect(this);
        }
    }

    public void effectQueueAdd(EffectAction e) {
        effectQueue.add(e);
    }

    public void removeEffectFromQueue(String s) {
        effectQueue.removeIf(e -> Objects.equals(e.getEffect().getType(), s));
    }

    public void removeEffectFromQueue(String s, Player p) {
        effectQueue.removeIf(e -> Objects.equals(e.getEffect().getType(), s) && e.getPlayer() == p);
    }

    public List<EffectAction> getEffectsFromQueue(String s, Player p) {
        List<EffectAction> returnList = new ArrayList<>();
        for (EffectAction effect : effectQueue) {
            if (effect.getPlayer() == p) {
                returnList.add(effect);
            }
        }
        return returnList;
    }

    private void updateStatArrows(ConstraintLayout card, Player player, int visible) {
        if (player.getLevel(Attributes.HP) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.HP) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.HP) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.HP) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.HP) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.HP) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.ATK) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.ATK) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.ATK) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.ATK) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.ATK) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.ATK) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.DEF) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.DEF) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.DEF) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.DEF) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.DEF) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.DEF) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPATK) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPATK) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPATK) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPATK) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPATK) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPATK) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPDEF) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPDEF) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPDEF) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPDEF) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPDEF) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPDEF) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPD) > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPD) > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPD) > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel(Attributes.SPD) < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPD) < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel(Attributes.SPD) < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_3)).setImageResource(R.drawable.stat_lower);}
    }

    private double applyLevel(int i) {
        switch (i) {
            case -3:
                return 0.4d;
            case -2:
                return 0.5d;
            case -1:
                return 0.667d;
            case 1:
                return 1.5d;
            case 2:
                return 2d;
            case 3:
                return 2.5d;
            default:
                return 1d;
        }
    }

    private boolean setCritical(int i) {
        Random rand = new Random();
        if (i == 1) {
            if (rand.nextInt(100) < 15) return true;
            else return false;
        }
        if (i == 2) {
            if (rand.nextInt(100) < 25) return true;
            else return false;
        }
        if (i == 3) {
            if (rand.nextInt(100) < 50) return true;
            else return false;
        }
        if (i == 4) return true;
        return false;
    }

    public void randomizeAttribute() {
        boolean success = false;
        Random rand = new Random();
        while (!success) {
            int r = rand.nextInt(6);
            if (r == 0 && availableAttribute.get(Attributes.HP)) {chosenAttribute = Attributes.HP; success = true;}
            if (r == 0 && availableAttribute.get(Attributes.ATK)) {chosenAttribute = Attributes.ATK; success = true;}
            if (r == 0 && availableAttribute.get(Attributes.DEF)) {chosenAttribute = Attributes.DEF; success = true;}
            if (r == 0 && availableAttribute.get(Attributes.SPATK)) {chosenAttribute = Attributes.SPATK; success = true;}
            if (r == 0 && availableAttribute.get(Attributes.SPDEF)) {chosenAttribute = Attributes.SPDEF; success = true;}
            if (r == 0 && availableAttribute.get(Attributes.SPD)) {chosenAttribute = Attributes.SPD; success = true;}
        }
    }

    public FieldEffect getFieldEffect() {
        return fieldEffect;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public boolean checkAvailableAttribute(Attributes a) {
        return availableAttribute.get(a);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setFieldEffect(FieldEffect e) {
        if (!Objects.equals(mode, "classic")) {
            fieldEffect = e;
            ImageView field = findViewById(R.id.fieldEffect);
            ConstraintLayout background = findViewById(R.id.activityGame);
            field.setVisibility(View.VISIBLE);
            switch (e) {
                case RAIN:
                    field.setImageResource(R.drawable.weather_rain);
                    background.setBackground(getDrawable(R.drawable.background_gradient_rain));
                    break;
                case SUN:
                    field.setImageResource(R.drawable.weather_sun);
                    background.setBackground(getDrawable(R.drawable.background_gradient_sun));
                    break;
                case SNOW:
                    field.setImageResource(R.drawable.weather_snow);
                    background.setBackground(getDrawable(R.drawable.background_gradient_snow));
                    break;
                case SANDSTORM:
                    field.setImageResource(R.drawable.weather_sandstorm);
                    background.setBackground(getDrawable(R.drawable.background_gradient_sandstorm));
                    break;
                case MISTY_TERRAIN:
                    field.setImageResource(R.drawable.terrain_misty);
                    background.setBackground(getDrawable(R.drawable.background_gradient_misty));
                    break;
                case GRASSY_TERRAIN:
                    field.setImageResource(R.drawable.terrain_grass);
                    background.setBackground(getDrawable(R.drawable.background_gradient_grass));
                    break;
                case PSYCHIC_TERRAIN:
                    field.setImageResource(R.drawable.terrain_psychic);
                    background.setBackground(getDrawable(R.drawable.background_gradient_psychic));
                    break;
                case ELECTRIC_TERRAIN:
                    field.setImageResource(R.drawable.terrain_electric);
                    background.setBackground(getDrawable(R.drawable.background_gradient_electric));
                    break;
                case WIND:
                    field.setImageResource(R.drawable.weather_wind);
                    background.setBackground(getDrawable(R.drawable.background_gradient_snow));
                    break;
                case DARKNESS:
                    field.setImageResource(R.drawable.darkness);
                    background.setBackground(getDrawable(R.drawable.background_gradient_darkness));
                default:
                    field.setVisibility(View.INVISIBLE);
                    background.setBackground(getDrawable(R.drawable.background_gradient));
                    break;
            }
        }
    }

    public Item generateItem() {
        Random rand = new Random();
        switch (rand.nextInt(15)) {
            case 0:
                return Item.X_HEALTH;
            case 1:
                return Item.X_ATTACK;
            case 2:
                return Item.X_DEFEND;
            case 3:
                return Item.X_SPECIAL;
            case 4:
                return Item.X_SPDEF;
            case 5:
                return Item.X_SPEED;
            case 6:
                return Item.AMULET_COIN;
            case 7:
                return Item.EXPERT_BELT;
            case 8:
                return Item.LOADED_DICE;
            case 9:
                return Item.EJECT_BUTTON;
            case 10:
                return Item.COVERT_CLOAK;
            case 11:
                return Item.BATTLE_PASS;
            case 12:
                return Item.EVIOLITE;
            case 13:
                return Item.GRIP_CLAW;
            case 14:
                return Item.SCOPE_LENS;
            default:
                return null;
        }
    }

    public Item generateBerry() {
        List<Item> berries = new ArrayList<>();
        berries.add(Item.SITRUS_BERRY);
        berries.add(Item.LIECHI_BERRY);
        berries.add(Item.GANLON_BERRY);
        berries.add(Item.SALAC_BERRY);
        berries.add(Item.PETAYA_BERRY);
        berries.add(Item.APICOT_BERRY);
        berries.add(Item.STARF_BERRY);
        berries.add(Item.LANSAT_BERRY);
        berries.add(Item.ENIGMA_BERRY);
        Random rand = new Random();
        return berries.get(rand.nextInt(berries.size()));
    }

    public String generateTrainerName() {
        String[] names = {"Joey", "Ethan", "Brendan", "May", "Dawn", "Lucas", "Barry", "Cheren", "Bianca", "Hugh", "Hilbert", "Hilda", "Nate", "Rosa", "Calem", "Serena", "Tierno", "Trevor", "Shauna", "Hau", "Elio", "Selene", "Victor", "Gloria", "Hop", "Florian", "Juliana", "Nemona", "Arven", "Penny", "Carmine", "Kieran"};
        Random rand = new Random();
        return names[rand.nextInt(names.length)];
    }

    private void bannerBreak(ImageView banner, int i) {
        switch (i) {
            case 0:
                banner.setImageResource(R.drawable.break_0);
                break;
            case 1:
                banner.setImageResource(R.drawable.break_1);
                break;
            case 2:
                banner.setImageResource(R.drawable.break_2);
                break;
            case 3:
                banner.setImageResource(R.drawable.break_3);
                break;
            case 4:
                banner.setImageResource(R.drawable.break_4);
                break;
            case 5:
                banner.setImageResource(R.drawable.break_5);
                break;
            case 6:
                banner.setImageResource(R.drawable.break_6);
                break;
            case 7:
                banner.setImageResource(R.drawable.break_7);
                break;
            case 8:
                banner.setImageResource(R.drawable.break_8);
                break;
            case 9:
                banner.setImageResource(R.drawable.break_9);
                break;
            case 10:
                banner.setImageResource(R.drawable.break_10);
                break;
        }
    }

    public String getMode() {
        return mode;
    }
}
