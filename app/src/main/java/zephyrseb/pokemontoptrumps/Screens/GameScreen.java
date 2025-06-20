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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import zephyrseb.pokemontoptrumps.AI;
import zephyrseb.pokemontoptrumps.AIDecks;
import zephyrseb.pokemontoptrumps.Attributes;
import zephyrseb.pokemontoptrumps.Berry;
import zephyrseb.pokemontoptrumps.Card;
import zephyrseb.pokemontoptrumps.CardRegistry;
import zephyrseb.pokemontoptrumps.ContinuousEffect;
import zephyrseb.pokemontoptrumps.Deck;
import zephyrseb.pokemontoptrumps.FieldEffect;
import zephyrseb.pokemontoptrumps.Game;
import zephyrseb.pokemontoptrumps.Item;
import zephyrseb.pokemontoptrumps.ItemRegistry;
import zephyrseb.pokemontoptrumps.Player;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;

public class GameScreen extends AbstractGameScreen {
    private Game game;
    private Attributes choice = null;
    private boolean megaEvolution = false;
    private boolean megaEvolved = false;
    private int round = 0;
    private int highestStat = 0;
    private int selectedItem = -1;
    private boolean newItem = false;
    private String mode;
    private Map<Player, Deck> decks = new HashMap<>();

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            mode = extras.getString("mode");
            decks.put(player1, gson.fromJson(extras.getString("deck"), Deck.class));
            Random rand = new Random();
            AIDecks[] deckList = {AIDecks.TOWER_RAIN, AIDecks.TOWER_SUN, AIDecks.TOWER_SNOW, AIDecks.TOWER_SANDSTORM, AIDecks.TOWER_WIND, AIDecks.TOWER_ELECTRIC_TERRAIN, AIDecks.TOWER_GRASSY_TERRAIN, AIDecks.TOWER_MISTY_TERRAIN, AIDecks.TOWER_PSYCHIC_TERRAIN, AIDecks.TOWER_DANCER, AIDecks.TOWER_DARKNESS, AIDecks.TOWER_TRICK_ROOM, AIDecks.TOWER_BERRIES};
            AIDecks opponentDeck = deckList[rand.nextInt(deckList.length)];
            decks.put(player2, opponentDeck.generateDeck());
        }
        game = new Game(this, mode, decks);
        TextView bannerNameP1 = findViewById(R.id.bannerPlayer).findViewById(R.id.playerName);
        bannerNameP1.setText(nameP1);
        TextView bannerNameP2 = findViewById(R.id.bannerOpponent).findViewById(R.id.playerName);
        bannerNameP2.setText(nameP2);
        findViewById(R.id.comparePlayer).setScaleX(0.75f);
        findViewById(R.id.comparePlayer).setScaleY(0.75f);
        findViewById(R.id.compareOpponent).setScaleX(0.75f);
        findViewById(R.id.compareOpponent).setScaleY(0.75f);
        View view = findViewById(R.id.card);
        if (view != null) {
            view.setScaleX(1.25f);
            view.setScaleY(1.25f);
        }
        initializeDeck(player1);
        initializeDeck(player2);
        if (game.getTurn() == 0) player2.addItem(game.generateItem(player2));
        if (game.getTurn() == 1) player1.addItem(game.generateItem(player1));
        if (game.getTurn() == 1) newItem = true;
        tick(player1,player2);
    }

    public void initializeDeck(Player player) {
        if (Objects.equals(mode, "free_play") ||
                Objects.equals(mode, "battle_tower") ||
                Objects.equals(mode, "battle_arcade") ||
                Objects.equals(mode, "battle_dojo") ||
                Objects.equals(mode, "battle_stage")) {
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
            pokemon.add(CardRegistry.initCard(this, CardRegistry.BUTTERFREE));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.BEEDRILL));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.ALAKAZAM));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MACHAMP));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.KINGLER));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.KANGASKHAN));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GYARADOS));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.AERODACTYL));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.STEELIX));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.SCIZOR));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.HERACROSS));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GARDEVOIR));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.AGGRON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MEDICHAM));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.SHARPEDO));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.LOPUNNY));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.GALLADE));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.ALCREMIE));

            pokemon.add(CardRegistry.initCard(this, CardRegistry.CORVIKNIGHT));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.FLYGON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.RAICHU));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.TINKATON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.WOBBUFFET));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.STOUTLAND));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.CLEFABLE));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.CLOYSTER));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.MAROWAK));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.ARCHALUDON));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.FARIGIRAF));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.BOMBIRDIER));
            pokemon.add(CardRegistry.initCard(this, CardRegistry.VIKAVOLT));
            for (int i = 0; i < 5; i++) {
                int r = rand.nextInt(pokemon.size());
                player.addCard(pokemon.get(r));
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
        game.reorderAttributes();
        if (game.getTurn() == 0) {
            game.setActivePlayer(p1);
            updateToast("Choose an attribute to compare", -1);
            findViewById(R.id.turnBannerRed).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRedText).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRed).startAnimation(startAnim);
            findViewById(R.id.turnBannerRedText).startAnimation(startAnim);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRed).setVisibility(View.INVISIBLE), 1500);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRedText).setVisibility(View.INVISIBLE), 1500);
        }
        if (game.getTurn() == 1) {
            game.setActivePlayer(p2);
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
        p1.setPlayedCard(p1.getCurrentCard());
        p2.setPlayedCard(p2.getCurrentCard());
        game.createQueue(p1, p2, false);
        game.applyPassiveEffect();
        ConstraintLayout currentCard = findViewById(R.id.card);
        Animation cardEnter = AnimationUtils.loadAnimation(this, R.anim.card_enter);
        currentCard.startAnimation(cardEnter);
        findViewById(R.id.burst).setVisibility(View.INVISIBLE);
        findViewById(R.id.opponentCard).setVisibility(View.INVISIBLE);
        adjustCard(currentCard, 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.comparePlayer));
        adjustCard(findViewById(R.id.opponentCard), 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.compareOpponent));
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).clearAnimation();
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setVisibility(View.INVISIBLE);
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setScaleX(1);
        findViewById(R.id.card).findViewById(R.id.weaknessBurst).setScaleY(1);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).clearAnimation();
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setVisibility(View.INVISIBLE);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setScaleX(1);
        findViewById(R.id.opponentCard).findViewById(R.id.weaknessBurst).setScaleY(1);
        updateStatArrows(findViewById(R.id.card), p1);
        updateStatArrows(findViewById(R.id.opponentCard), p1);
        updateChoice(findViewById(R.id.card),null);
        updateChoice(findViewById(R.id.opponentCard),null);
        ImageView megaButton = findViewById(R.id.megaEvolutionButton);
        Button submitButton = findViewById(R.id.submitButton);
        Button itemButton = findViewById(R.id.itemButton);
        submitButton.setEnabled(true);
        megaButton.setEnabled(true);
        itemButton.setEnabled(true);
        findViewById(R.id.buttonATK).setEnabled(true);
        findViewById(R.id.buttonSPATK).setEnabled(true);
        findViewById(R.id.buttonACC).setEnabled(true);
        findViewById(R.id.buttonSPD).setEnabled(true);
        p1.getCurrentCard().displayCard(this, currentCard, p1);
        p2.getCurrentCard().displayCard(this, findViewById(R.id.opponentCard), p2);
        findViewById(R.id.buttonATK).setOnClickListener(v -> {if (game.getTurn() == 0 && p1.getAvailableAttribute(Attributes.ATK)) updateChoice(currentCard, Attributes.ATK);});
        findViewById(R.id.buttonSPATK).setOnClickListener(v -> {if (game.getTurn() == 0 && p1.getAvailableAttribute(Attributes.SPATK)) updateChoice(currentCard, Attributes.SPATK);});
        findViewById(R.id.buttonACC).setOnClickListener(v -> {if (game.getTurn() == 0 && p1.getAvailableAttribute(Attributes.ACC)) updateChoice(currentCard, Attributes.ACC);});
        findViewById(R.id.buttonSPD).setOnClickListener(v -> {if (game.getTurn() == 0 && p1.getAvailableAttribute(Attributes.SPD)) updateChoice(currentCard, Attributes.SPD);});
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
                    ItemRegistry.initItem(this, p1.getItem(i)).displayCard(views.get(i));
                    views.get(i).setScaleX(0.375f);
                    views.get(i).setScaleY(0.375f);
                    int finalI = i;
                    views.get(i).setOnClickListener(u -> {
                        ItemRegistry temp;
                        if (selectedItem != finalI) {
                            selectedItem = finalI;
                            temp = p1.getItem(selectedItem);
                        }
                        else {
                            selectedItem = -1;
                            temp = null;
                            updateItemImage(findViewById(R.id.itemCard), null);
                        }
                        if (temp != null) {
                            updateItemImage(findViewById(R.id.itemCard), ItemRegistry.initItem(this, temp));

                        }
                        updateItemSelection(popupWindow.getContentView(), selectedItem);
                        p1.setPlayedItem(this, temp);
                        game.createQueue(p1, p2, false);
                        game.applyPassiveEffect();
                        updateStatArrows(findViewById(R.id.card), p1);
                    });
                    views.get(i).setOnLongClickListener(u -> {
                        LayoutInflater inflater2 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        @SuppressLint("InflateParams") PopupWindow popupWindow2 = new PopupWindow(inflater2.inflate(R.layout.card_layout_item, null), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, getResources().getDisplayMetrics()) * 1.4f), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics()) * 1.4f), true);
                        popupWindow2.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
                        popupWindow2.getContentView().setScaleX(1.4f);
                        popupWindow2.getContentView().setScaleY(1.4f);
                        ItemRegistry.initItem(this, p1.getItem(finalI)).displayCard(popupWindow2.getContentView().findViewById(R.id.cardItemRaw));
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
                p1.getCurrentCard().displayCard(this, currentCard, p1);
                setButtonFunctionAbility(abilityButton, p1.getCurrentCard());
                megaEvolution = false;
            } else if (p1.getCurrentCard().canMegaEvolve() && !megaEvolved) {
                megaButton.setImageResource(R.drawable.mega_evolution);
                p1.getCurrentCard().megaEvolve().displayCard(this, currentCard, p1);
                setButtonFunctionAbility(abilityButton, p1.getCurrentCard().megaEvolve());
                megaEvolution = true;
            }
        });

        //Sets the functionality for the submit button
        submitButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            submitButton.startAnimation(buttonPulse);
            AI ai = new AI(game, p2.getCurrentCard(), p2.megaEvolve, game.getTurn() == 1, p1, p2);
            if (game.getTurn() == 1) {
                ai.chooseAttack();
                updateChoice(currentCard, ai.getPreferredStat());
            }
            else ai.chooseDefend();
            Animation cardCompare = AnimationUtils.loadAnimation(this, R.anim.card_to_compare);
            ConstraintLayout opponentCard = findViewById(R.id.opponentCard);
            Animation cardOpponent = AnimationUtils.loadAnimation(this, R.anim.card_opponent_enter);
            if (!(choice == null) || (game.getActivePlayer() == p1 && !p1.hasAvailableAttributes())) {
                submitButton.setEnabled(false);
                itemButton.setEnabled(false);
                megaButton.setEnabled(false);
                abilityButton.setEnabled(false);
                findViewById(R.id.buttonATK).setEnabled(false);
                findViewById(R.id.buttonSPATK).setEnabled(false);
                findViewById(R.id.buttonACC).setEnabled(false);
                findViewById(R.id.buttonSPD).setEnabled(false);
                currentCard.startAnimation(cardCompare);
                opponentCard.startAnimation(cardOpponent);
                adjustCard(opponentCard, -175f, -250f, 0.6f, View.INVISIBLE, findViewById(R.id.compareOpponent));
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
        infoPopup(findViewById(R.id.fieldEffect), getString(game.getFieldEffect().getName()), getString(game.getFieldEffect().getDescription()), game.getFieldEffect().getImage());

        //Sets the functionality of each status effect indicator
        infoPopup(findViewById(R.id.bannerPlayer).findViewById(R.id.statusIcon), getString(p1.getStatusCondition().getName()), getString(p1.getStatusCondition().getDescription()), p1.getStatusCondition().getImage());
        infoPopup(findViewById(R.id.bannerOpponent).findViewById(R.id.statusIcon), getString(p2.getStatusCondition().getName()), getString(p2.getStatusCondition().getDescription()), p2.getStatusCondition().getImage());

        //Sets the functionality of each delayed effects button
        Button delayedEffectButton1 = findViewById(R.id.bannerPlayer).findViewById(R.id.delayedEffects);
        delayedEffectButton1.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.abilityName)).setText("Continuous Effects");
            List<ContinuousEffect> l = p1.getDelayedEffects();
            StringBuilder s = new StringBuilder();
            if (!l.isEmpty()) {
                for (int i = 0; i < l.size(); i++) {
                    s.append(l.get(i).getString(this));
                    s.append("\n");
                    s.append("\n");
                }
            }
            ((TextView)popupWindow.getContentView().findViewById(R.id.abilityDescription)).setText(s.toString());
        });

        Button delayedEffectButton2 = findViewById(R.id.bannerOpponent).findViewById(R.id.delayedEffects);
        delayedEffectButton2.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.abilityName)).setText("Continuous Effects");
            ((TextView)popupWindow.getContentView().findViewById(R.id.abilityDescription)).setText("");
            List<ContinuousEffect> l = p2.getDelayedEffects();
            StringBuilder s = new StringBuilder();
            if (!l.isEmpty()) {
                for (int i = 0; i < l.size(); i++) {
                    s.append(l.get(i).getString(this));
                    s.append("\n");
                    s.append("\n");
                }
            }
            ((TextView)popupWindow.getContentView().findViewById(R.id.abilityDescription)).setText(s.toString());
        });

        //Sets the functionality for each berry icon
        if (p1.getBerry(0) != Berry.NONE) infoPopup(findViewById(R.id.bannerPlayer).findViewById(R.id.berry1), getString(p1.getBerry(0).getName()), getString(p1.getBerry(0).getDescription()), p1.getBerry(0).getImage());
        if (p1.getBerry(1) != Berry.NONE) infoPopup(findViewById(R.id.bannerPlayer).findViewById(R.id.berry2), getString(p1.getBerry(1).getName()), getString(p1.getBerry(1).getDescription()), p1.getBerry(1).getImage());
        if (p1.getBerry(2) != Berry.NONE) infoPopup(findViewById(R.id.bannerPlayer).findViewById(R.id.berry3), getString(p1.getBerry(2).getName()), getString(p1.getBerry(2).getDescription()), p1.getBerry(2).getImage());
        if (p2.getBerry(0) != Berry.NONE) infoPopup(findViewById(R.id.bannerOpponent).findViewById(R.id.berry1), getString(p2.getBerry(0).getName()), getString(p2.getBerry(0).getDescription()), p2.getBerry(0).getImage());
        if (p2.getBerry(1) != Berry.NONE) infoPopup(findViewById(R.id.bannerOpponent).findViewById(R.id.berry2), getString(p2.getBerry(1).getName()), getString(p2.getBerry(1).getDescription()), p2.getBerry(1).getImage());
        if (p2.getBerry(2) != Berry.NONE) infoPopup(findViewById(R.id.bannerOpponent).findViewById(R.id.berry3), getString(p2.getBerry(2).getName()), getString(p2.getBerry(2).getDescription()), p2.getBerry(2).getImage());
    }

    private void infoPopup(View id, String name, String description, int image) {
        id.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(name);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(description);
            ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(image);
        });
    }

    //Compares the two cards and determines the winner
    public void compare(Player p1, Player p2, Attributes s, AI ai) {
        game.setChosenAttribute(s);
        if (game.getChosenAttribute() == null) {
            if (game.getActivePlayer() == p1) p2.setAutoWin(true);
            if (game.getActivePlayer() == p2) p1.setAutoWin(true);
            game.setChosenAttribute(Attributes.HP);
        }
        game.setGoalReverse(true);
        final Handler handler = new Handler(Looper.getMainLooper());
        findViewById(R.id.submitButton).setEnabled(false);
        findViewById(R.id.megaEvolutionButton).setEnabled(false);
        findViewById(R.id.itemButton).setEnabled(false);
        p1.setPlayedItem(this, null);
        p2.setPlayedItem(this, null);
        p1.playCard();
        p2.playCard();
        if (ai.getPreferredItem() != null) {
            ItemRegistry ir = ai.getPreferredItem();
            p2.setPlayedItem(this, p2.useItem(ai.getPreferredItem()));
            updateItemImage(findViewById(R.id.itemCardOpponent),ItemRegistry.initItem(this, ir));
        }
        if (selectedItem > -1) {
            p1.setPlayedItem(this, p1.useItem(selectedItem));
        }
        if (megaEvolution) {
            megaEvolved = true;
            p1.megaEvolve = false;
            p1.setPlayedCard(p1.getPlayedCard().megaEvolve());
        }
        if (ai.getPreferredME()) {
            p2.megaEvolve = false;
            p2.setPlayedCard(p2.getPlayedCard().megaEvolve());
            p2.getPlayedCard().displayCard(this, findViewById(R.id.opponentCard), p2);
        }

        game.createQueue(p1, p2, true);
        game.applyPassiveEffect();

        updateChoice(findViewById(R.id.card),game.getChosenAttribute());
        updateChoice(findViewById(R.id.opponentCard),game.getChosenAttribute());
        p1.setCurrentStat(p1.getPlayedCard().getAttribute(Attributes.getAttributeWithPriority(game.getChosenAttribute(), p1 == game.getActivePlayer())));
        p2.setCurrentStat(p2.getPlayedCard().getAttribute(Attributes.getAttributeWithPriority(game.getChosenAttribute(), p2 == game.getActivePlayer())));

        //Applies modifiers due to weaknesses
        ConstraintLayout card1 = findViewById(R.id.card);
        ConstraintLayout card2 = findViewById(R.id.opponentCard);

        p1.getPlayedCard().displayCard(this, card1, p1);
        p2.getPlayedCard().displayCard(this, card2, p2);

        boolean critP1 = setCritical(p1.getCritRate());
        boolean critP2 = setCritical(p2.getCritRate());

        p1.setCurrentStat((int)(p1.getCurrentStat() * applyLevel(p1.getLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), p1 == game.getActivePlayer()))) * setWeakness(p1, p1.getPlayedCard(), p2.getPlayedCard(), p1.getSupereffectiveMultiplier(), p1.getSupereffectiveHit()) * (critP1 ? p1.getCritMultiplier() : 1d)));
        p2.setCurrentStat((int)(p2.getCurrentStat() * applyLevel(p2.getLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), p2 == game.getActivePlayer()))) * setWeakness(p2, p2.getPlayedCard(), p1.getPlayedCard(), p2.getSupereffectiveMultiplier(), p2.getSupereffectiveHit()) * (critP2 ? p2.getCritMultiplier() : 1d)));

        updateStatArrows(card1, p1);
        updateStatArrows(card2, p2);

        card2.setVisibility(View.VISIBLE);
        findViewById(R.id.burst).setVisibility(View.VISIBLE);
        handler.postDelayed(() -> adjustCard(card1, 175f, 250f, 0.6f, View.VISIBLE, findViewById(R.id.comparePlayer)), 1);
        adjustCard(card2, -175f, -250f, 0.6f, View.VISIBLE, findViewById(R.id.compareOpponent));
        updateCompare(p1, findViewById(R.id.comparePlayer), p2.getSupereffectiveHit(), critP2, p1.getBroken());
        updateCompare(p2, findViewById(R.id.compareOpponent), p1.getSupereffectiveHit(), critP1, p2.getBroken());

        game.setPreviousAttribute(game.getChosenAttribute());
        game.setUsedAttribute(game.getChosenAttribute(), true);
        game.setUsedAttribute(Attributes.getAttributeWithPriority(game.getChosenAttribute(), false), true);

        if (!Objects.equals(mode, "classic")) {
            if (p1.getCurrentStat() > p2.getCurrentStat() + p2.getPlayedCard().getAttribute(Attributes.HP))
                p2.incrementBreakPoints(1);
            if (p2.getCurrentStat() > p1.getCurrentStat() + p1.getPlayedCard().getAttribute(Attributes.HP))
                p1.incrementBreakPoints(1);
        }

        //Determines the higher stat and performs the appropriate action
        if (p1.getCurrentStat() > highestStat || p2.getCurrentStat() > highestStat) {
            highestStat = Math.max(p1.getCurrentStat(),p2.getCurrentStat());
        }
        if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == -1) {
            if (critP1) game.setPrizePot(game.getPrizePot() * (int) p1.getCritPointMultiplier());
            p1.addPoint(game.getPrizePot());
            p2.addItem(game.generateItem(p2));
            handler.postDelayed(() -> game.setPrizePot(1), 3500);
            game.setPriority(p1, p2, "p1");
            updateToast(p1.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_player);
            handler.postDelayed(() -> card1.startAnimation(animCard), 3300);
            handler.postDelayed(() -> card2.startAnimation(animCard), 3300);
        }
        else if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == 1) {
            if (critP2) game.setPrizePot(game.getPrizePot() * (int) p2.getCritPointMultiplier());
            p2.addPoint(game.getPrizePot());
            p1.addItem(game.generateItem(p1));
            newItem = true;
            handler.postDelayed(() -> game.setPrizePot(1), 3500);
            game.setPriority(p1, p2, "p2");
            updateToast(p2.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_opponent);
            handler.postDelayed(() -> card1.startAnimation(animCard), 3300);
            handler.postDelayed(() -> card2.startAnimation(animCard), 3300);
        }
        else if (checkGoal(p1, p2, p1.getCurrentStat(),p2.getCurrentStat()) == 0) {
            handler.postDelayed(() -> game.setPrizePot(game.getPrizePot() + 1), 3500);
            game.setPriority(p1, p2, "tie");
            updateToast("It's a tie", -1);
        }
        p1.displayBanner(this, findViewById(R.id.bannerPlayer), p2.getPoints());
        p2.displayBanner(this, findViewById(R.id.bannerOpponent), p1.getPoints());

        if (p1.getPoints() >= 6) {
            handler.postDelayed(() -> playerWin("Victory"), 1500);
            handler.postDelayed(() -> resultScreen(p1, p2), 3500);
        }
        else if (p2.getPoints() >= 6) {
            handler.postDelayed(() -> playerWin("Defeat"), 1500);
            handler.postDelayed(() -> resultScreen(p1, p2), 3500);
        }
        else {
            handler.postDelayed(() -> tick(p1, p2), 3500);
        }
    }

    public void update(Player p1, Player p2) {
        updateStatArrows(findViewById(R.id.card), p1);
        updateStatArrows(findViewById(R.id.opponentCard), p2);
    }

    public int checkGoal(Player p1, Player p2, int i1, int i2) {
        if (p1.getAutoWin() && !p2.getAutoWin()) return -1;
        if (!p1.getAutoWin() && p2.getAutoWin()) return 1;
        if (game.getGoalLarge()) {
            return Integer.compare(i2, i1);
        }
        else {
            return Integer.compare(i1, i2);
        }
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
    public void adjustCard(ConstraintLayout card, float tX, float tY, float scale, int visible, ConstraintLayout attribute) {
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
    }

    //Updates which attribute is being used for the current round
    public void updateChoice(ConstraintLayout card, Attributes s) {
        if (s == Attributes.ATK) card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.SPATK) card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (s == Attributes.ACC) card.findViewById(R.id.buttonACC).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonACC).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
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
        if (game.getStartingPlayer() == 0) sPlayer = p1.getName();
        if (game.getStartingPlayer() == 1) sPlayer = p2.getName();
        ((TextView) findViewById(R.id.turnOrderText)).setText(sPlayer);
        ((TextView) findViewById(R.id.finalScoreText)).setText(q2 + " - " + q1);
        ((TextView) findViewById(R.id.roundsText)).setText(String.valueOf(round));
        ((TextView) findViewById(R.id.highAttributeText)).setText(String.valueOf(highestStat));

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        if (p1.getPoints() >= 6) {
            int i = playerData.getWinStreak(mode);
            i++;
            playerData.setWinStreak(mode, i);
            if (Objects.equals(mode, "battle_tower") ||
                    Objects.equals(mode, "battle_arcade") ||
                    Objects.equals(mode, "battle_factory") ||
                    Objects.equals(mode, "battle_dojo") ||
                    Objects.equals(mode, "battle_stage")) {
                int bp = playerData.getBattlePoints();
                bp += (int)Math.ceil(i / 10d);
                playerData.setBattlePoints(bp);
            }
        }
        if (p2.getPoints() >= 6) {
            playerData.setWinStreak(mode, 0);
        }
        playerData.writeFile(this);
    }

    //Alters the power of attacks due to weaknesses, and displays this on screen
    private double setWeakness(Player player, Card attacker, Card defender, double mod, boolean isTrue) {
        if (attacker.checkType(defender) || isTrue) {
            player.setSupereffectiveHit(true);
            return mod;
        }
        return 1d;
    }

    //Adjusts the size of the prize pot, and displays this on screen
    public void setPrizePot(int p) {
        if (game.getPrizePot() == 1) {
            findViewById(R.id.prizePot).setVisibility(View.INVISIBLE);
            findViewById(R.id.prizePotText).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.prizePot).setVisibility(View.VISIBLE);
            findViewById(R.id.prizePotText).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.prizePotText)).setText("x" + game.getPrizePot());
        }
    }

    private void updateStatArrows(ConstraintLayout card, Player player) {
        updateArrows(card, player, Attributes.ATK, R.id.statChange1_1, R.id.statChange1_2, R.id.statChange1_3, R.id.statChange1_4, R.id.statChange1_5, R.id.statChange1_6);
        updateArrows(card, player, Attributes.DEF, R.id.statChange2_1, R.id.statChange2_2, R.id.statChange2_3, R.id.statChange2_4, R.id.statChange2_5, R.id.statChange2_6);
        updateArrows(card, player, Attributes.SPATK, R.id.statChange3_1, R.id.statChange3_2, R.id.statChange3_3, R.id.statChange3_4, R.id.statChange3_5, R.id.statChange3_6);
        updateArrows(card, player, Attributes.SPDEF, R.id.statChange4_1, R.id.statChange4_2, R.id.statChange4_3, R.id.statChange4_4, R.id.statChange4_5, R.id.statChange4_6);
        updateArrows(card, player, Attributes.ACC, R.id.statChange5_1, R.id.statChange5_2, R.id.statChange5_3, R.id.statChange5_4, R.id.statChange5_5, R.id.statChange5_6);
        updateArrows(card, player, Attributes.EV, R.id.statChange6_1, R.id.statChange6_2, R.id.statChange6_3, R.id.statChange6_4, R.id.statChange6_5, R.id.statChange6_6);
        updateArrows(card, player, Attributes.SPD, R.id.statChange7_1, R.id.statChange7_2, R.id.statChange7_3, R.id.statChange7_4, R.id.statChange7_5, R.id.statChange7_6);
    }

    private void updateArrows(ConstraintLayout card, Player player, Attributes a, int a1, int a2, int a3, int a4, int a5, int a6) {
        if (player.getLevel(a) == 0) ((ImageView)card.findViewById(a1)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 0) ((ImageView)card.findViewById(a1)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < 0) ((ImageView)card.findViewById(a1)).setImageResource(R.drawable.stat_lower);
        if (player.getLevel(a) <= 1 && player.getLevel(a) >= -1) ((ImageView)card.findViewById(a2)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 1) ((ImageView)card.findViewById(a2)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < -1) ((ImageView)card.findViewById(a2)).setImageResource(R.drawable.stat_lower);
        if (player.getLevel(a) <= 2 && player.getLevel(a) >= -2) ((ImageView)card.findViewById(a3)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 2) ((ImageView)card.findViewById(a3)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < -2) ((ImageView)card.findViewById(a3)).setImageResource(R.drawable.stat_lower);
        if (player.getLevel(a) <= 3 && player.getLevel(a) >= -3) ((ImageView)card.findViewById(a4)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 3) ((ImageView)card.findViewById(a4)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < -3) ((ImageView)card.findViewById(a4)).setImageResource(R.drawable.stat_lower);
        if (player.getLevel(a) <= 4 && player.getLevel(a) >= -4) ((ImageView)card.findViewById(a5)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 4) ((ImageView)card.findViewById(a5)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < -4) ((ImageView)card.findViewById(a5)).setImageResource(R.drawable.stat_lower);
        if (player.getLevel(a) <= 5 && player.getLevel(a) >= -5) ((ImageView)card.findViewById(a6)).setImageResource(R.drawable.icon_blank);
        if (player.getLevel(a) > 5) ((ImageView)card.findViewById(a6)).setImageResource(R.drawable.stat_raise);
        if (player.getLevel(a) < -5) ((ImageView)card.findViewById(a6)).setImageResource(R.drawable.stat_lower);

    }

    private double applyLevel(int i) {
        return switch (i) {
            case -6 -> 0.4d;
            case -5 -> 0.444d;
            case -4 -> 0.5d;
            case -3 -> 0.571d;
            case -2 -> 0.667d;
            case -1 -> 0.8d;
            case 1 -> 1.25d;
            case 2 -> 1.5d;
            case 3 -> 1.75d;
            case 4 -> 2d;
            case 5 -> 2.25d;
            case 6 -> 2.5d;
            default -> 1d;
        };
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

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setFieldEffect(FieldEffect e) {
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
                break;
            case TRICK_ROOM:
                field.setImageResource(R.drawable.trick_room);
                background.setBackground(getDrawable(R.drawable.background_gradient_psychic));
                break;
            default:
                field.setVisibility(View.INVISIBLE);
                background.setBackground(getDrawable(R.drawable.background_gradient));
                break;
        }
    }

    public String generateTrainerName() {
        String[] names = {"Joey", "Ethan", "Brendan", "May", "Dawn", "Lucas", "Barry", "Cheren", "Bianca", "Hugh", "Hilbert", "Hilda", "Nate", "Rosa", "Calem", "Serena", "Tierno", "Trevor", "Shauna", "Hau", "Elio", "Selene", "Victor", "Gloria", "Hop", "Florian", "Juliana", "Nemona", "Arven", "Penny", "Carmine", "Kieran"};
        Random rand = new Random();
        return names[rand.nextInt(names.length)];
    }

    private void updateCompare(Player player, ConstraintLayout layout, boolean weak, boolean crit, boolean b) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.burst_pulse);
        layout.setAnimation(animation);
        ((ImageView)layout.findViewById(R.id.background)).setImageResource(R.drawable.compare_back_normal);
        if (weak || crit) ((ImageView)layout.findViewById(R.id.background)).setImageResource(R.drawable.compare_back_weak);
        if (b) ((ImageView)layout.findViewById(R.id.background)).setImageResource(R.drawable.compare_back_break);
        ((TextView)layout.findViewById(R.id.compareValue)).setText(Integer.toString(player.getCurrentStat()));
        ((ImageView)layout.findViewById(R.id.weak)).setImageResource(R.drawable.icon_blank);
        if (weak) ((ImageView)layout.findViewById(R.id.weak)).setImageResource(R.drawable.weakness_burst);
        if (crit) ((ImageView)layout.findViewById(R.id.weak)).setImageResource(R.drawable.critical_burst);
        if (b) ((ImageView)layout.findViewById(R.id.weak)).setImageResource(R.drawable.break_burst);
        updateArrows(layout, player, game.getChosenAttribute(), R.id.statRaise1, R.id.statRaise2, R.id.statRaise3, R.id.statRaise4, R.id.statRaise5, R.id.statRaise6);
    }
}
