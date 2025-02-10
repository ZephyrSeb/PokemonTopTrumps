package zephyrseb.pokemontoptrumps;

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
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    private int turn;
    private int startingPlayer;
    private int prizePot = 1;
    private String choice = "";
    private boolean megaEvolution = false;
    private boolean megaEvolved = false;
    private int round = 0;
    private int highestStat = 0;
    private int selectedItem = -1;
    private String highestStatName = "";
    private String chosenAttribute = "";
    private Card cardP1;
    private Card cardP2;
    private boolean newItem = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Player player1 = new Player("Player");
        Player player2 = new Player("CPU");
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
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_venusaur), R.drawable.pokemon_venusaur, Type.GRASS, Ability.OVERGROW, 80, 82, 83, 100, 100, 80, Type.FIRE, getResources().getString(R.string.pokemon_flavor_venusaur), new Card(getResources().getString(R.string.pokemon_name_mega_venusaur), R.drawable.pokemon_mega_venusaur, Type.GRASS, null, 80, 100, 123, 122, 120, 80, Type.FIRE, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_charizard), R.drawable.pokemon_charizard, Type.FIRE, Ability.BLAZE, 78, 84, 78, 109, 85, 100, Type.WATER, getResources().getString(R.string.pokemon_flavor_charizard), new Card(getResources().getString(R.string.pokemon_name_mega_charizard_x), R.drawable.pokemon_mega_charizard_x, Type.FIRE, null, 78, 130, 111, 130, 85, 100, Type.WATER, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_blastoise), R.drawable.pokemon_blastoise, Type.WATER, Ability.TORRENT, 79, 83, 100, 85, 105, 78, Type.ELECTRIC, getResources().getString(R.string.pokemon_flavor_blastoise), new Card(getResources().getString(R.string.pokemon_name_mega_blastoise), R.drawable.pokemon_mega_blastoise, Type.WATER, null, 79, 103, 120, 135, 115, 78, Type.ELECTRIC, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_gardevoir), R.drawable.pokemon_gardevoir, Type.FAIRY, null, 68, 65, 65, 125, 115, 80, Type.STEEL, getResources().getString(R.string.pokemon_flavor_gardevoir), new Card(getResources().getString(R.string.pokemon_name_mega_gardevoir), R.drawable.pokemon_mega_gardevoir, Type.FAIRY, null, 68, 85, 65, 165, 135, 100, Type.STEEL, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_gallade), R.drawable.pokemon_gallade, Type.FIGHTING, null, 68, 125, 65, 65, 115, 80, Type.DARK, getResources().getString(R.string.pokemon_flavor_gallade), new Card(getResources().getString(R.string.pokemon_name_mega_gallade), R.drawable.pokemon_mega_gallade, Type.FIGHTING, null, 68, 165, 95, 65, 115, 110, Type.DARK, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_gyarados), R.drawable.pokemon_gyarados, Type.WATER, null, 95, 125, 79, 60, 100, 81, Type.ELECTRIC, getResources().getString(R.string.pokemon_flavor_gyarados), new Card(getResources().getString(R.string.pokemon_name_mega_gyarados), R.drawable.pokemon_mega_gyarados, Type.WATER, Ability.MOLD_BREAKER, 95, 155, 109, 70, 130, 81, Type.ELECTRIC, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_lucario), R.drawable.pokemon_lucario, Type.FIGHTING, null, 70, 110, 70, 115, 70, 90, Type.FIRE, getResources().getString(R.string.pokemon_flavor_lucario), new Card(getResources().getString(R.string.pokemon_name_mega_lucario), R.drawable.pokemon_mega_lucario, Type.FIGHTING, null, 70, 145, 88, 140, 70, 112, Type.FIRE, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_sceptile), R.drawable.pokemon_sceptile, Type.GRASS, Ability.OVERGROW, 70, 85, 65, 105, 85, 120, Type.FIRE, getResources().getString(R.string.pokemon_flavor_sceptile), new Card(getResources().getString(R.string.pokemon_name_mega_sceptile), R.drawable.pokemon_mega_sceptile, Type.GRASS, null, 70, 110, 75, 145, 85, 145, Type.FIRE, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_blaziken), R.drawable.pokemon_blaziken, Type.FIRE, Ability.BLAZE, 80, 120, 70, 110, 70, 80, Type.WATER, getResources().getString(R.string.pokemon_flavor_blaziken), new Card(getResources().getString(R.string.pokemon_name_mega_blaziken), R.drawable.pokemon_mega_blaziken, Type.FIRE, null, 80, 160, 80, 130, 80, 100, Type.WATER, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_swampert), R.drawable.pokemon_swampert, Type.WATER, Ability.TORRENT, 100, 110, 90, 85, 90, 60, Type.GRASS, getResources().getString(R.string.pokemon_flavor_swampert), new Card(getResources().getString(R.string.pokemon_name_mega_swampert), R.drawable.pokemon_mega_swampert, Type.WATER, null, 100, 150, 110, 95, 110, 70, Type.GRASS, "")));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_snorlax), R.drawable.pokemon_snorlax, Type.NORMAL, null, 160, 110, 65, 65, 110, 30, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_snorlax)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_spinda), R.drawable.pokemon_spinda, Type.NORMAL, null, 60, 60, 60, 60, 60, 60, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_spinda)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_mew), R.drawable.pokemon_mew, Type.PSYCHIC, null, 100, 100, 100, 100, 100, 100, Type.DARK, getResources().getString(R.string.pokemon_flavor_mew)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_corviknight), R.drawable.pokemon_corviknight, Type.STEEL, null, 98, 87, 105, 53, 85, 67, Type.FIRE, getResources().getString(R.string.pokemon_flavor_corviknight)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_ditto), R.drawable.pokemon_ditto, Type.NORMAL, Ability.TRANSFORM, 48, 48, 48, 48, 48, 48, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_ditto)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_flygon), R.drawable.pokemon_flygon, Type.NORMAL, null, 80, 100, 80, 80, 80, 100, Type.FAIRY, getResources().getString(R.string.pokemon_flavor_flygon)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_froslass), R.drawable.pokemon_froslass, Type.WATER, null, 70, 80, 70, 80, 70, 110, Type.DARK, getResources().getString(R.string.pokemon_flavor_froslass)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_glimmora), R.drawable.pokemon_glimmora, Type.GROUND, null, 83, 55, 90, 130, 81, 86, Type.WATER, getResources().getString(R.string.pokemon_flavor_glimmora)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_ludicolo), R.drawable.pokemon_ludicolo, Type.WATER, null, 80, 70, 70, 90, 100, 70, Type.GRASS, getResources().getString(R.string.pokemon_flavor_ludicolo)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_machamp), R.drawable.pokemon_machamp, Type.FIGHTING, null, 90, 130, 80, 65, 85, 55, Type.PSYCHIC, getResources().getString(R.string.pokemon_flavor_machamp)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_raichu), R.drawable.pokemon_raichu, Type.ELECTRIC, null, 60, 90, 55, 90, 80, 110, Type.GROUND, getResources().getString(R.string.pokemon_flavor_raichu)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_slaking), R.drawable.pokemon_slaking, Type.NORMAL, Ability.TRUANT, 150, 160, 100, 95, 65, 100, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_slaking)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_talonflame), R.drawable.pokemon_talonflame, Type.FIRE, null, 78, 81, 71, 74, 69, 126, Type.WATER, getResources().getString(R.string.pokemon_flavor_talonflame)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_tinkaton), R.drawable.pokemon_tinkaton, Type.STEEL, null, 85, 75, 77, 70, 105, 94, Type.FIRE, getResources().getString(R.string.pokemon_flavor_tinkaton)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_unown), R.drawable.pokemon_unown, Type.PSYCHIC, Ability.HIDDEN_POWER, 48, 72, 48, 72, 48, 48, Type.DARK, getResources().getString(R.string.pokemon_flavor_unown)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_ursaluna), R.drawable.pokemon_ursaluna, Type.GROUND, null, 130, 140, 105, 45, 80, 50, Type.GRASS, getResources().getString(R.string.pokemon_flavor_ursaluna)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_wobuffet), R.drawable.pokemon_wobuffet, Type.PSYCHIC, null, 190, 33, 58, 33, 58, 33, Type.DARK, getResources().getString(R.string.pokemon_flavor_wobuffet)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_zoroark), R.drawable.pokemon_zoroark, Type.DARK, null, 60, 105, 60, 120, 60, 105, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_zoroark)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_pikachu), R.drawable.pokemon_pikachu, Type.ELECTRIC, null, 35, 55, 40, 50, 50, 90, Type.GROUND, getResources().getString(R.string.pokemon_flavor_pikachu)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_manaphy), R.drawable.pokemon_manaphy, Type.WATER, Ability.HEART_SWAP, 100, 100, 100, 100, 100, 100, Type.ELECTRIC, getResources().getString(R.string.pokemon_flavor_manaphy)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_zigzagoon), R.drawable.pokemon_zigzagoon, Type.NORMAL, Ability.PICKUP, 38, 30, 41, 30, 41, 60, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_zigzagoon)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_haxorus), R.drawable.pokemon_haxorus, Type.NORMAL, Ability.MOLD_BREAKER, 76, 147, 90, 60, 70, 97, Type.FAIRY, getResources().getString(R.string.pokemon_flavor_haxorus)));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_turtwig), R.drawable.pokemon_turtwig, Type.GRASS, Ability.OVERGROW, 55, 68, 64, 45, 55, 31, Type.FIRE, getResources().getString(R.string.pokemon_flavor_turtwig)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_chimchar), R.drawable.pokemon_chimchar, Type.FIRE, Ability.BLAZE, 44, 58, 44, 58, 44, 61, Type.WATER, getResources().getString(R.string.pokemon_flavor_chimchar)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_piplup), R.drawable.pokemon_piplup, Type.WATER, Ability.TORRENT, 53, 51, 53, 61, 56, 40, Type.ELECTRIC, getResources().getString(R.string.pokemon_flavor_piplup)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_eevee), R.drawable.pokemon_eevee, Type.NORMAL, null, 55, 55, 50, 45, 65, 55, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_eevee)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_porygon), R.drawable.pokemon_porygon, Type.NORMAL, Ability.DOWNLOAD, 65, 60, 70, 85, 75, 40, Type.FIGHTING, getResources().getString(R.string.pokemon_flavor_porygon)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_murkrow), R.drawable.pokemon_murkrow, Type.DARK, Ability.SUPER_LUCK, 60, 85, 42, 85, 42, 91, Type.ELECTRIC, getResources().getString(R.string.pokemon_flavor_murkrow)).setEvolve(true));
        player.addCard(new Card(getResources().getString(R.string.pokemon_name_ralts), R.drawable.pokemon_ralts, Type.PSYCHIC, Ability.TRACE, 28, 25, 25, 45, 35, 40, Type.STEEL, getResources().getString(R.string.pokemon_flavor_ralts)).setEvolve(true));
        player.shuffle();
    }

    //Main gameplay loop
    public void tick(Player p1, Player p2) {
        final Handler handler = new Handler(Looper.getMainLooper());
        Animation startAnim = AnimationUtils.loadAnimation(this, R.anim.turn_banner_enter);
        if (turn == 0) {
            updateToast("Choose an attribute to compare", -1);
            findViewById(R.id.turnBannerRed).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRedText).setVisibility(View.VISIBLE);
            findViewById(R.id.turnBannerRed).startAnimation(startAnim);
            findViewById(R.id.turnBannerRedText).startAnimation(startAnim);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRed).setVisibility(View.INVISIBLE), 1500);
            handler.postDelayed(() -> findViewById(R.id.turnBannerRedText).setVisibility(View.INVISIBLE), 1500);
        }
        if (turn == 1) {
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
        ConstraintLayout currentCard = findViewById(R.id.card);
        Animation cardEnter = AnimationUtils.loadAnimation(this, R.anim.card_enter);
        currentCard.startAnimation(cardEnter);
        findViewById(R.id.burst).setVisibility(View.INVISIBLE);
        findViewById(R.id.opponentCard).setVisibility(View.INVISIBLE);
        adjustCard(currentCard, 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.p1Attribute));
        adjustCard(findViewById(R.id.opponentCard), 0, 0, 1.25f, View.INVISIBLE, findViewById(R.id.p2Attribute));
        findViewById(R.id.p1Burst).clearAnimation();
        findViewById(R.id.p2Burst).clearAnimation();
        findViewById(R.id.p1Burst).setVisibility(View.INVISIBLE);
        findViewById(R.id.p2Burst).setVisibility(View.INVISIBLE);
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
            PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.item_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
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
                        PopupWindow popupWindow2 = new PopupWindow(inflater2.inflate(R.layout.card_layout_item, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
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
            AI ai = new AI(p2.getCurrentCard(), p2.megaEvolve, turn == 1, p1, p2);
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
    }

    //Compares the two cards and determines the winner
    public void compare(Player p1, Player p2, String s, AI ai) {
        chosenAttribute = s;
        final Handler handler = new Handler(Looper.getMainLooper());
        Button submitButton = findViewById(R.id.submitButton);
        ImageView megaButton = findViewById(R.id.megaEvolutionButton);
        Button itemButton = findViewById(R.id.itemButton);
        submitButton.setEnabled(false);
        megaButton.setEnabled(false);
        itemButton.setEnabled(false);
        cardP1 = p1.playCard();
        cardP2 = p2.playCard();
        Item itemP1 = null;
        Item itemP2 = null;
        if (ai.getPreferredItem() != null) itemP2 = p2.useItem(ai.getPreferredItem());
        if (itemP2 != null) {
            updateItemImage(findViewById(R.id.itemCardOpponent),ai.getPreferredItem());
        }
        if (selectedItem > -1) itemP1 = p1.useItem(selectedItem);
        if (megaEvolution) {
            megaEvolved = true;
            p1.megaEvolve = false;
            cardP1 = cardP1.megaEvolve();
        }
        if (ai.getPreferredME()) {
            cardP2 = cardP2.megaEvolve();
            p2.megaEvolve = false;
            cardP2.displayCard(this, findViewById(R.id.opponentCard));
        }

        //Applies ability and item modifiers
        //Priority 0 is for mold breaker
        boolean abilities = applyPassiveEffect(itemP1, 0, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
        if (!applyPassiveEffect(itemP2, 0, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute)) abilities = false;
        if (!applyPassiveEffect(cardP1.getAbility(), 0, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute)) abilities = false;
        if (!applyPassiveEffect(cardP2.getAbility(), 0, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute)) abilities = false;
        //Priority 1 is for things that change the state of the hand
        applyPassiveEffect(itemP1, 1, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
        applyPassiveEffect(itemP2, 1, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        if (abilities) {
            applyPassiveEffect(cardP1.getAbility(), 1, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
            applyPassiveEffect(cardP2.getAbility(), 1, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        }
        //Priority 2 is for card swaps
        applyPassiveEffect(itemP1, 2, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
        applyPassiveEffect(itemP2, 2, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        if (abilities) {
            PassiveAbility temp1 = cardP1.getAbility();
            PassiveAbility temp2 = cardP2.getAbility();
            applyPassiveEffect(temp1, 2, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
            applyPassiveEffect(temp2, 2, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        }

        updateChoice(findViewById(R.id.card),chosenAttribute);
        updateChoice(findViewById(R.id.opponentCard),chosenAttribute);
        p1.setCurrentStat(cardP1.getAttribute(chosenAttribute));
        p2.setCurrentStat(cardP2.getAttribute(chosenAttribute));
        p1.setCurrentMod(40);
        p2.setCurrentMod(40);

        //Applies ability and item modifiers
        //Priority 3 is for card modifiers
        applyPassiveEffect(itemP1, 3, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
        applyPassiveEffect(itemP2, 3, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        if (abilities) {
            applyPassiveEffect(cardP1.getAbility(), 3, cardP1, cardP2, p1, p2, itemP1, itemP2, chosenAttribute);
            applyPassiveEffect(cardP2.getAbility(), 3, cardP2, cardP1, p2, p1, itemP1, itemP2, chosenAttribute);
        }

        //Applies modifiers due to weaknesses
        p1.setCurrentStat(p1.getCurrentStat() + setWeakness(cardP1, cardP2, findViewById(R.id.p2Burst), p1.getCurrentMod()));
        p2.setCurrentStat(p2.getCurrentStat() + setWeakness(cardP2, cardP1, findViewById(R.id.p1Burst), p2.getCurrentMod()));

        ConstraintLayout card1 = findViewById(R.id.card);
        ConstraintLayout card2 = findViewById(R.id.opponentCard);
        card2.setVisibility(View.VISIBLE);
        findViewById(R.id.burst).setVisibility(View.VISIBLE);
        handler.postDelayed(() -> adjustCard(card1, 175f, 250f, 0.6f, View.VISIBLE, findViewById(R.id.p1Attribute)), 1);
        adjustCard(card2, -175f, -250f, 0.6f, View.VISIBLE, findViewById(R.id.p2Attribute));
        ((TextView)findViewById(R.id.p1Attribute)).setText(String.valueOf(p1.getCurrentStat()));
        ((TextView)findViewById(R.id.p2Attribute)).setText(String.valueOf(p2.getCurrentStat()));

        //Determines the higher stat and performs the appropriate action
        if (p1.getCurrentStat() > highestStat || p2.getCurrentStat() > highestStat) {
            highestStat = Math.max(p1.getCurrentStat(),p2.getCurrentStat());
            highestStatName = chosenAttribute;
        }
        if (p1.getCurrentStat() > p2.getCurrentStat()) {
            p1.addPoint(prizePot);
            p2.addItem(generateItem());
            handler.postDelayed(() -> setPrizePot(1), 3000);
            setPriority(p1, p2, cardP1, cardP2, "p1", abilities);
            updateToast(p1.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_player);
            handler.postDelayed(() -> card1.startAnimation(animCard), 2800);
            handler.postDelayed(() -> card2.startAnimation(animCard), 2800);
        }
        if (p2.getCurrentStat() > p1.getCurrentStat()) {
            p2.addPoint(prizePot);
            p1.addItem(generateItem());
            newItem = true;
            handler.postDelayed(() -> setPrizePot(1), 3000);
            setPriority(p1, p2, cardP1, cardP2, "p2", abilities);
            updateToast(p2.getName() + " wins the point", -1);
            Animation animCard = AnimationUtils.loadAnimation(this, R.anim.card_exit_opponent);
            handler.postDelayed(() -> card1.startAnimation(animCard), 2800);
            handler.postDelayed(() -> card2.startAnimation(animCard), 2800);
        }
        if (p1.getCurrentStat() == p2.getCurrentStat()) {
            handler.postDelayed(() -> setPrizePot(prizePot + 1), 3000);
            setPriority(p1, p2, cardP1, cardP2, "tie", abilities);
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

    public void setButtonFunctionAbility(ImageView abilityButton, Card c1) {
        abilityButton.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
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

    public void setPriority(Player p1, Player p2, Card card1, Card card2, String s, boolean abilities) {
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
    private int setWeakness(Card attacker, Card defender, ImageView burst, int mod) {
        if (attacker.checkType(defender)) {
            burst.setVisibility(View.VISIBLE);
            Animation burstPulse = AnimationUtils.loadAnimation(this, R.anim.burst_pulse);
            burst.startAnimation(burstPulse);
            return mod;
        }
        return 0;
    }

    //Adjusts the size of the prize pot, and displays this on screen
    private void setPrizePot(int p) {
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

    private boolean applyPassiveEffect(PassiveAbility passive, int priority, Card c1, Card c2, Player p1, Player p2, Item i1, Item i2, String s) {
        if (passive != null) {
            for (Effect effect : passive.getEffects()) {
                if (effect.checkConditions(p1, p2, c1, c2, s) && priority == 0) {
                    if (effect.checkEffect("null-ability")) return false;
                    if (effect.checkEffect("copy-ability")) {
                        if (c1 == cardP1) {
                            cardP1.setAbility(c2.getAbility());
                            cardP1.displayCard(this, findViewById(R.id.card));
                        }
                        if (c1 == cardP2) {
                            cardP2.setAbility(c2.getAbility());
                            cardP2.displayCard(this, findViewById(R.id.opponentCard));
                        }

                    }
                    if (effect.checkEffect("replay-card")) {
                        p1.addCard(c1, 0);
                    }
                }
                if (effect.checkConditions(p1, p2, c1, c2, s) && priority == 1) {
                    if (effect.checkEffect("randomize-attribute"))
                        chosenAttribute = randomizeAttribute();
                    ;
                    if (effect.checkEffect("copy-card")) {
                        if (c1 == cardP1) {
                            cardP1 = cardP2;
                            cardP1.displayCard(this, findViewById(R.id.card));
                        }
                        if (c1 == cardP2) {
                            cardP2 = cardP1;
                            cardP2.displayCard(this, findViewById(R.id.opponentCard));
                        }
                    }
                    if (effect.checkEffect("replace-card")) {
                        c1 = p1.playCard();
                        cardP1.displayCard(this, findViewById(R.id.card));
                        cardP2.displayCard(this, findViewById(R.id.opponentCard));
                    }
                }
                if (effect.checkConditions(p1, p2, c1, c2, s) && priority == 2) {
                    if (effect.checkEffect("swap-card")) {
                        Card temp = cardP1;
                        cardP1 = cardP2;
                        cardP2 = temp;
                        cardP1.displayCard(this, findViewById(R.id.card));
                        cardP2.displayCard(this, findViewById(R.id.opponentCard));
                    }
                }
                if (effect.checkConditions(p1, p2, c1, c2, s) && priority == 3) {
                    if (effect.checkEffect("modify-stat")) p1.setCurrentStat(effect.apply(p1.getCurrentStat()));
                    if (effect.checkEffect("modify-weakness")) p1.setCurrentMod(effect.apply(p1.getCurrentMod()));
                    if (effect.checkEffect("modify-prize")) setPrizePot(effect.apply(prizePot));
                    if (effect.checkEffect("get-item")) {
                        if (effect.getItem() == null) p1.addItem(generateItem());
                        else p1.addItem(effect.getItem());
                        if (c1 == cardP1) {
                            newItem = true;
                        }
                    }
                    if (effect.checkEffect("return-item")) {
                        p1.addItem(i1);
                        if (c1 == cardP1) {
                            newItem = true;
                        }
                    }
                    if (effect.checkEffect("modify-speed")) p1.setSpeed(effect.apply(p1.getSpeed()));
                }
            }
        }
        return true;
    }

    private String randomizeAttribute() {
        Random rand = new Random();
        switch (rand.nextInt(6)) {
            case 0:
                return "hp";
            case 1:
                return "atk";
            case 2:
                return "def";
            case 3:
                return "spatk";
            case 4:
                return "spdef";
            case 5:
                return "spd";
            default:
                return "";
        }
    }
    private Item generateItem() {
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
}
