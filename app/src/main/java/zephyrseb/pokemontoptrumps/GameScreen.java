package zephyrseb.pokemontoptrumps;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    private int turn;
    private Player activePlayer;
    private int startingPlayer;
    private int prizePot = 1;
    private String choice = "";
    private boolean megaEvolution = false;
    private boolean megaEvolved = false;
    private int round = 0;
    private int highestStat = 0;
    private int selectedItem = -1;
    private String chosenAttribute = "";
    private boolean newItem = false;
    private FieldEffect fieldEffect = FieldEffect.NONE;
    private PriorityQueue<EffectAction> effectQueue = new PriorityQueue<>(new EffectComparator());
    private String mode;
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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mode = extras.getString("mode");
        }
        Player player1 = new Player("Player");
        Player player2 = new Player(generateTrainerName());
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
            player.addCard(CardRegistry.VENUSAUR.clone());
            player.addCard(CardRegistry.CHARIZARD.clone());
            player.addCard(CardRegistry.BLASTOISE.clone());
            player.addCard(CardRegistry.GARDEVOIR.clone());
            player.addCard(CardRegistry.GALLADE.clone());
            player.addCard(CardRegistry.GYARADOS.clone());
            player.addCard(CardRegistry.LUCARIO.clone());
            player.addCard(CardRegistry.SCEPTILE.clone());
            player.addCard(CardRegistry.BLAZIKEN.clone());
            player.addCard(CardRegistry.SWAMPERT.clone());
            player.addCard(CardRegistry.SNORLAX.clone());
            player.addCard(CardRegistry.SPINDA.clone());
            player.addCard(CardRegistry.MEW.clone());
            player.addCard(CardRegistry.CORVIKNIGHT.clone());
            player.addCard(CardRegistry.DITTO.clone());
            player.addCard(CardRegistry.FLYGON.clone());
            player.addCard(CardRegistry.FROSLASS.clone());
            player.addCard(CardRegistry.GLIMMORA.clone());
            player.addCard(CardRegistry.LUDICOLO.clone());
            player.addCard(CardRegistry.MACHAMP.clone());
            player.addCard(CardRegistry.RAICHU.clone());
            player.addCard(CardRegistry.SLAKING.clone());
            player.addCard(CardRegistry.TALONFLAME.clone());
            player.addCard(CardRegistry.TINKATON.clone());
            player.addCard(CardRegistry.UNOWN.clone());
            player.addCard(CardRegistry.URSALUNA.clone());
            player.addCard(CardRegistry.WOBBUFFET.clone());
            player.addCard(CardRegistry.ZOROARK.clone());
            player.addCard(CardRegistry.PIKACHU.clone());
            player.addCard(CardRegistry.MANAPHY.clone());
            player.addCard(CardRegistry.ZIGZAGOON.clone());
            player.addCard(CardRegistry.HAXORUS.clone());
            player.addCard(CardRegistry.TURTWIG.clone());
            player.addCard(CardRegistry.CHIMCHAR.clone());
            player.addCard(CardRegistry.PIPLUP.clone());
            player.addCard(CardRegistry.EEVEE.clone());
            player.addCard(CardRegistry.PORYGON.clone());
            player.addCard(CardRegistry.MURKROW.clone());
            player.addCard(CardRegistry.RALTS.clone());
            player.addCard(CardRegistry.ABOMASNOW.clone());
            player.addCard(CardRegistry.HIPPOWDON.clone());
            player.addCard(CardRegistry.TORKOAL.clone());
            player.addCard(CardRegistry.PELIPPER.clone());
            player.addCard(CardRegistry.ALTARIA.clone());
            player.addCard(CardRegistry.CASTFORM.clone());
            player.addCard(CardRegistry.GARCHOMP.clone());
            player.addCard(CardRegistry.TROPIUS.clone());
            player.addCard(CardRegistry.LUVDISC.clone());
            player.addCard(CardRegistry.CHERRIM.clone());
            player.addCard(CardRegistry.MALAMAR.clone());
            player.addCard(CardRegistry.ROWLET.clone());
            player.addCard(CardRegistry.DECIDUEYE.clone());
            player.addCard(CardRegistry.LITTEN.clone());
            player.addCard(CardRegistry.INCINEROAR.clone());
            player.addCard(CardRegistry.POPPLIO.clone());
            player.addCard(CardRegistry.PRIMARINA.clone());
            player.addCard(CardRegistry.VIKAVOLT.clone());
            player.addCard(CardRegistry.ARAQUANID.clone());
            player.addCard(CardRegistry.WISHIWASHI.clone());
            player.addCard(CardRegistry.LURANTIS.clone());
            player.addCard(CardRegistry.SHIINOTIC.clone());
            player.addCard(CardRegistry.PALOSSAND.clone());
            player.addCard(CardRegistry.MINIOR_METEOR.clone());
            player.addCard(CardRegistry.TOGEDEMARU.clone());
            player.addCard(CardRegistry.MIMIKYU.clone());
            player.addCard(CardRegistry.KOMMO_O.clone());
            player.addCard(CardRegistry.TAPU_KOKO.clone());
            player.addCard(CardRegistry.TAPU_LELE.clone());
            player.addCard(CardRegistry.TAPU_BULU.clone());
            player.addCard(CardRegistry.TAPU_FINI.clone());
            player.addCard(CardRegistry.COSMOG.clone());
            player.addCard(CardRegistry.SOLGALEO.clone());
            player.addCard(CardRegistry.LUNALA.clone());
            player.addCard(CardRegistry.MAGEARNA.clone());
            player.addCard(CardRegistry.LUNATONE.clone());
            player.addCard(CardRegistry.SOLROCK.clone());
            player.addCard(CardRegistry.RATICATE.clone());
            player.addCard(CardRegistry.ALOLAN_RATICATE.clone());
            player.addCard(CardRegistry.ALOLAN_RAICHU.clone());
            player.addCard(CardRegistry.PERSIAN.clone());
            player.addCard(CardRegistry.ALOLAN_PERSIAN.clone());
            player.addCard(CardRegistry.SANDSLASH.clone());
            player.addCard(CardRegistry.ALOLAN_SANDSLASH.clone());
            player.addCard(CardRegistry.NINETALES.clone());
            player.addCard(CardRegistry.ALOLAN_NINETALES.clone());
            player.addCard(CardRegistry.MUK.clone());
            player.addCard(CardRegistry.ALOLAN_MUK.clone());
            player.addCard(CardRegistry.DUGTRIO.clone());
            player.addCard(CardRegistry.ALOLAN_DUGTRIO.clone());
            player.addCard(CardRegistry.GOLEM.clone());
            player.addCard(CardRegistry.ALOLAN_GOLEM.clone());
            player.addCard(CardRegistry.EXEGGCUTE.clone());
            player.addCard(CardRegistry.EXEGGUTOR.clone());
            player.addCard(CardRegistry.ALOLAN_EXEGGUTOR.clone());
            player.addCard(CardRegistry.MAROWAK.clone());
            player.addCard(CardRegistry.ALOLAN_MAROWAK.clone());
            player.addCard(CardRegistry.GOGOAT.clone());
            player.addCard(CardRegistry.AIPOM.clone());
            player.addCard(CardRegistry.PACHIRISU.clone());
            player.addCard(CardRegistry.MUNCHLAX.clone());
            player.addCard(CardRegistry.CHERUBI.clone());
            player.addCard(CardRegistry.APPLIN.clone());
            player.addCard(CardRegistry.FLAPPLE.clone());
            player.addCard(CardRegistry.APPLETUN.clone());
            player.addCard(CardRegistry.DIPPLIN.clone());
            player.addCard(CardRegistry.HYDRAPPLE.clone());
            player.addCard(CardRegistry.SKWOVET.clone());
            player.addCard(CardRegistry.GREEDENT.clone());
            player.addCard(CardRegistry.PALDEAN_TAUROS_COMBAT.clone());
        }
        if (Objects.equals(mode, "classic")) {
            Random rand = new Random();
            int starters1 = rand.nextInt(2);
            int starters2 = rand.nextInt(3);
            if (starters2 == 0) {
                player.addCard(CardRegistry.VENUSAUR.clone());
                player.addCard(CardRegistry.CHARIZARD.clone());
                player.addCard(CardRegistry.BLASTOISE.clone());
            }
            if (starters2 == 1) {
                player.addCard(CardRegistry.SCEPTILE.clone());
                player.addCard(CardRegistry.BLAZIKEN.clone());
                player.addCard(CardRegistry.SWAMPERT.clone());
            }
            if (starters2 == 2) {
                player.addCard(CardRegistry.DECIDUEYE.clone());
                player.addCard(CardRegistry.INCINEROAR.clone());
                player.addCard(CardRegistry.PRIMARINA.clone());
            }
            if (starters1 == 0) {
                player.addCard(CardRegistry.TURTWIG.clone());
                player.addCard(CardRegistry.CHIMCHAR.clone());
                player.addCard(CardRegistry.PIPLUP.clone());
            }
            if (starters1 == 1) {
                player.addCard(CardRegistry.ROWLET.clone());
                player.addCard(CardRegistry.LITTEN.clone());
                player.addCard(CardRegistry.POPPLIO.clone());
            }
            Card[] notFullyEvolved = {CardRegistry.PIKACHU, CardRegistry.EEVEE, CardRegistry.MURKROW, CardRegistry.PORYGON, CardRegistry.RALTS, CardRegistry.APPLIN};
            Card[] pickup = {CardRegistry.ZIGZAGOON, CardRegistry.AIPOM};
            player.addCard(notFullyEvolved[rand.nextInt(notFullyEvolved.length)].clone());
            player.addCard(pickup[rand.nextInt(pickup.length)].clone());
            List<Card> pokemon = new ArrayList<>();
            pokemon.add(CardRegistry.GARDEVOIR);
            pokemon.add(CardRegistry.GALLADE);
            pokemon.add(CardRegistry.GYARADOS);
            pokemon.add(CardRegistry.LUCARIO);
            pokemon.add(CardRegistry.SNORLAX);
            pokemon.add(CardRegistry.CORVIKNIGHT);
            pokemon.add(CardRegistry.FLYGON);
            pokemon.add(CardRegistry.FROSLASS);
            pokemon.add(CardRegistry.GLIMMORA);
            pokemon.add(CardRegistry.LUDICOLO);
            pokemon.add(CardRegistry.MACHAMP);
            pokemon.add(CardRegistry.RAICHU);
            pokemon.add(CardRegistry.TALONFLAME);
            pokemon.add(CardRegistry.TINKATON);
            pokemon.add(CardRegistry.URSALUNA);
            pokemon.add(CardRegistry.WOBBUFFET);
            pokemon.add(CardRegistry.ZOROARK);
            for (int i = 0; i < 5; i++) {
                int r = rand.nextInt(pokemon.size());
                player.addCard(pokemon.get(r).clone());
                pokemon.remove(r);
            }
            player.addCard(CardRegistry.DITTO.clone());
            player.addCard(CardRegistry.UNOWN.clone());
            player.addCard(CardRegistry.SPINDA.clone());
            player.addCard(CardRegistry.MEW.clone());
            player.addCard(CardRegistry.SLAKING.clone());
            player.addCard(CardRegistry.MANAPHY.clone());
            player.addCard(CardRegistry.HAXORUS.clone());
        }
        player.shuffle();
    }

    //Main gameplay loop
    public void tick(Player p1, Player p2) {
        final Handler handler = new Handler(Looper.getMainLooper());
        Animation startAnim = AnimationUtils.loadAnimation(this, R.anim.turn_banner_enter);
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
        updateChoice(findViewById(R.id.card),"");
        updateChoice(findViewById(R.id.opponentCard),"");
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
        findViewById(R.id.buttonHP).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "hp");});
        findViewById(R.id.buttonATK).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "atk");});
        findViewById(R.id.buttonDEF).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "def");});
        findViewById(R.id.buttonSPATK).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "spatk");});
        findViewById(R.id.buttonSPDEF).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "spdef");});
        findViewById(R.id.buttonSPD).setOnClickListener(v -> {if (turn == 0) updateChoice(currentCard, "spd");});
        ImageView abilityButton = findViewById(R.id.abilityIcon);
        if (p1.getCounterCharge() > 0) findViewById(R.id.chargeCounter).setVisibility(View.VISIBLE);
        else findViewById(R.id.chargeCounter).setVisibility(View.INVISIBLE);
        setStatusEffect(findViewById(R.id.statusEffectPlayer), p1.getStatusCondition());
        setStatusEffect(findViewById(R.id.statusEffectOpponent), p2.getStatusCondition());

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
            if (!Objects.equals(choice, "")) {
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
        ImageView statusIcon1 = findViewById(R.id.statusEffectPlayer);
        statusIcon1.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activityGame), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(StatusCondition.setName(this, p1.getStatusCondition()));
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(StatusCondition.setDescription(this, p1.getStatusCondition()));
            ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(StatusCondition.getImage(this, p1.getStatusCondition()));
        });

        ImageView statusIcon2 = findViewById(R.id.statusEffectOpponent);
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
    public void compare(Player p1, Player p2, String s, AI ai) {
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
        Effect chargeEffect = new Effect((game, player, opponent) -> player.setLevel("spatk", player.getLevel("spatk") + 1), 0, "charge");
        if (p1.getCounterCharge() > 0 && p1.getPlayedCard().getType() == Type.ELECTRIC) {
            p1.getPlayedCard().setCharged(true);
            p1.setCounterCharge(p1.getCounterCharge() - 1);
            effectQueue.add(new EffectAction(chargeEffect, p1, null, p1.getPlayedCard().getAttribute("spd")));
        }
        if (p2.getCounterCharge() > 0 && p2.getPlayedCard().getType() == Type.ELECTRIC) {
            p2.getPlayedCard().setCharged(true);
            p2.setCounterCharge(p2.getCounterCharge() - 1);
            effectQueue.add(new EffectAction(chargeEffect, p2, null, p2.getPlayedCard().getAttribute("spd")));
        }

        //Applies ability and item modifiers
        if (p1.getPlayedItem() != null) {
            for (Effect e : p1.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute("spd")));
            }
        }
        if (p2.getPlayedItem() != null) {
            for (Effect e : p2.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute("spd")));
            }
        }
        if (p1.getPlayedCard().getAbility() != null) {
            for (Effect e : p1.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute("spd")));
            }
        }
        if (p2.getPlayedCard().getAbility() != null) {
            for (Effect e : p2.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute("spd")));
            }
        }
        if (getFieldEffect() != FieldEffect.NONE) {
            for (Effect e : FieldEffect.getEffects(getFieldEffect())) {
                effectQueue.add(new EffectAction(e, p1, null, 0));
                effectQueue.add(new EffectAction(e, p2, null, 0));
            }
        }
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

        p1.setCurrentStat((int)(p1.getCurrentStat() * applyLevel(p1.getLevel(s)) * setWeakness(p1.getPlayedCard(), p2.getPlayedCard(), card2.findViewById(R.id.weaknessBurst), p1.getSupereffectiveMultiplier(), p1.getSupereffectiveHit()) * setCritical(p1.getCritRate(), p1.getCritMultiplier())));
        p2.setCurrentStat((int)(p2.getCurrentStat() * applyLevel(p2.getLevel(s)) * setWeakness(p2.getPlayedCard(), p1.getPlayedCard(), card1.findViewById(R.id.weaknessBurst), p2.getSupereffectiveMultiplier(), p1.getSupereffectiveHit()) * setCritical(p2.getCritRate(), p2.getCritMultiplier())));

        updateStatArrows(card1, p1, View.VISIBLE);
        updateStatArrows(card2, p2, View.VISIBLE);

        card2.setVisibility(View.VISIBLE);
        findViewById(R.id.burst).setVisibility(View.VISIBLE);
        handler.postDelayed(() -> adjustCard(card1, 175f, 250f, 0.6f, View.VISIBLE, findViewById(R.id.p1Attribute)), 1);
        adjustCard(card2, -175f, -250f, 0.6f, View.VISIBLE, findViewById(R.id.p2Attribute));
        ((TextView)findViewById(R.id.p1Attribute)).setText(String.valueOf(p1.getCurrentStat()));
        ((TextView)findViewById(R.id.p2Attribute)).setText(String.valueOf(p2.getCurrentStat()));

        //Determines the higher stat and performs the appropriate action
        if (p1.getCurrentStat() > highestStat || p2.getCurrentStat() > highestStat) {
            highestStat = Math.max(p1.getCurrentStat(),p2.getCurrentStat());
        }
        if (checkGoal(p1.getCurrentStat(),p2.getCurrentStat()) == -1) {
            p1.addPoint(prizePot);
            p2.addItem(generateItem());
            handler.postDelayed(() -> setPrizePot(1), 3000);
            setPriority(p1, p2, "p1");
            updateToast(p1.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_player);
            handler.postDelayed(() -> card1.startAnimation(animCard), 2800);
            handler.postDelayed(() -> card2.startAnimation(animCard), 2800);
        }
        else if (checkGoal(p1.getCurrentStat(),p2.getCurrentStat()) == 1) {
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
        else if (checkGoal(p1.getCurrentStat(),p2.getCurrentStat()) == 0) {
            handler.postDelayed(() -> setPrizePot(prizePot + 1), 3000);
            setPriority(p1, p2, "tie");
            updateToast("It's a tie", -1);
        }
        findViewById(R.id.score1).getLayoutParams().width = (int)Math.max(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()) * (6 - p2.getPoints()),1);
        findViewById(R.id.score2).getLayoutParams().width = (int)Math.max(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()) * (6 - p1.getPoints()),1);
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

    private int checkGoal(int i1, int i2) {
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

    public String getChosenAttribute() {
        return chosenAttribute;
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
    public void updateChoice(ConstraintLayout card, String s) {
        if (Objects.equals(s, "hp")) card.findViewById(R.id.buttonHP).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonHP).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (Objects.equals(s, "atk")) card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (Objects.equals(s, "def")) card.findViewById(R.id.buttonDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (Objects.equals(s, "spatk")) card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPATK).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (Objects.equals(s, "spdef")) card.findViewById(R.id.buttonSPDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPDEF).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
        if (Objects.equals(s, "spd")) card.findViewById(R.id.buttonSPD).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection)); else card.findViewById(R.id.buttonSPD).setBackground(AppCompatResources.getDrawable(this, R.drawable.attribute_selection_off));
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

    private void updateStatArrows(ConstraintLayout card, Player player, int visible) {
        if (player.getLevel("hp") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("hp") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("hp") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("hp") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("hp") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("hp") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange1_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange1_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("atk") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("atk") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("atk") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("atk") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("atk") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("atk") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange2_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange2_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("def") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("def") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("def") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("def") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("def") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("def") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange3_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange3_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spatk") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spatk") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spatk") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spatk") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spatk") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spatk") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange4_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange4_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spdef") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spdef") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spdef") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spdef") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spdef") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spdef") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange5_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange5_3)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spd") > 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_1)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spd") > 1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_2)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spd") > 2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_3)).setImageResource(R.drawable.stat_raise);}
        if (player.getLevel("spd") < 0 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_1).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_1)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spd") < -1 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_2).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_2)).setImageResource(R.drawable.stat_lower);}
        if (player.getLevel("spd") < -2 || visible == View.INVISIBLE) {card.findViewById(R.id.statChange6_3).setVisibility(visible); ((ImageView)card.findViewById(R.id.statChange6_3)).setImageResource(R.drawable.stat_lower);}
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

    private double setCritical(int i, double d) {
        Random rand = new Random();
        if (i == 1) {
            if (rand.nextInt(100) < 15) return d;
            else return 1d;
        }
        if (i == 2) {
            if (rand.nextInt(100) < 25) return d;
            else return 1d;
        }
        if (i == 3) {
            if (rand.nextInt(100) < 50) return d;
            else return 1d;
        }
        if (i == 4) return d;
        return 1d;
    }

    public void randomizeAttribute() {
        Random rand = new Random();
        switch (rand.nextInt(6)) {
            case 0:
                chosenAttribute = "hp";
                break;
            case 1:
                chosenAttribute = "atk";
                break;
            case 2:
                chosenAttribute = "def";
                break;
            case 3:
                chosenAttribute = "spatk";
                break;
            case 4:
                chosenAttribute = "spdef";
                break;
            case 5:
                chosenAttribute = "spd";
                break;
        }
    }

    public FieldEffect getFieldEffect() {
        return fieldEffect;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setFieldEffect(FieldEffect e) {
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
            default:
                field.setVisibility(View.INVISIBLE);
                background.setBackground(getDrawable(R.drawable.background_gradient));
                break;
        }
    }

    public void setStatusEffect(ImageView effectIcon, StatusCondition s) {
        if (s == StatusCondition.NONE) {
            effectIcon.setVisibility(View.INVISIBLE);
        }
        else {
            effectIcon.setVisibility(View.VISIBLE);
            effectIcon.setImageResource(StatusCondition.getImage(this, s));
        }
    }

    public Item generateItem() {
        Random rand = new Random();
        switch (rand.nextInt(16)) {
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
            case 15:
                return Item.MIRROR_HERB;
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
}
