package zephyrseb.pokemontoptrumps;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionScreen extends AppCompatActivity {

    List<Card> cardCollection = new ArrayList<>(Arrays.asList(
            CardRegistry.VENUSAUR,
            CardRegistry.CHARIZARD,
            CardRegistry.BLASTOISE,
            CardRegistry.RATICATE,
            CardRegistry.ALOLAN_RATICATE,
            CardRegistry.PIKACHU,
            CardRegistry.RAICHU,
            CardRegistry.ALOLAN_RAICHU,
            CardRegistry.SANDSLASH,
            CardRegistry.ALOLAN_SANDSLASH,
            CardRegistry.NINETALES,
            CardRegistry.ALOLAN_NINETALES,
            CardRegistry.DUGTRIO,
            CardRegistry.ALOLAN_DUGTRIO,
            CardRegistry.PERSIAN,
            CardRegistry.ALOLAN_PERSIAN,
            CardRegistry.MACHAMP,
            CardRegistry.GOLEM,
            CardRegistry.ALOLAN_GOLEM,
            CardRegistry.MUK,
            CardRegistry.ALOLAN_MUK,
            CardRegistry.MAROWAK,
            CardRegistry.ALOLAN_MAROWAK,
            CardRegistry.EXEGGCUTE,
            CardRegistry.EXEGGUTOR,
            CardRegistry.ALOLAN_EXEGGUTOR,
            CardRegistry.PALDEAN_TAUROS_COMBAT,
            CardRegistry.GYARADOS,
            CardRegistry.DITTO,
            CardRegistry.EEVEE,
            CardRegistry.SNORLAX,
            CardRegistry.PORYGON,
            CardRegistry.MEW,
            CardRegistry.MURKROW,
            CardRegistry.WOBBUFFET,
            CardRegistry.UNOWN,
            CardRegistry.AIPOM,
            CardRegistry.SCEPTILE,
            CardRegistry.BLAZIKEN,
            CardRegistry.SWAMPERT,
            CardRegistry.ZIGZAGOON,
            CardRegistry.LUDICOLO,
            CardRegistry.PELIPPER,
            CardRegistry.RALTS,
            CardRegistry.GARDEVOIR,
            CardRegistry.TORKOAL,
            CardRegistry.FLYGON,
            CardRegistry.SPINDA,
            CardRegistry.LUNATONE,
            CardRegistry.SOLROCK,
            CardRegistry.ALTARIA,
            CardRegistry.SLAKING,
            CardRegistry.CASTFORM,
            CardRegistry.TROPIUS,
            CardRegistry.LUVDISC,
            CardRegistry.TURTWIG,
            CardRegistry.CHIMCHAR,
            CardRegistry.PIPLUP,
            CardRegistry.PACHIRISU,
            CardRegistry.CHERUBI,
            CardRegistry.CHERRIM,
            CardRegistry.ABOMASNOW,
            CardRegistry.HIPPOWDON,
            CardRegistry.LUCARIO,
            CardRegistry.MUNCHLAX,
            CardRegistry.GARCHOMP,
            CardRegistry.GALLADE,
            CardRegistry.FROSLASS,
            CardRegistry.MANAPHY,
            CardRegistry.HAXORUS,
            CardRegistry.ZOROARK,
            CardRegistry.TALONFLAME,
            CardRegistry.MALAMAR,
            CardRegistry.GOGOAT,
            CardRegistry.ROWLET,
            CardRegistry.DECIDUEYE,
            CardRegistry.LITTEN,
            CardRegistry.INCINEROAR,
            CardRegistry.POPPLIO,
            CardRegistry.PRIMARINA,
            CardRegistry.VIKAVOLT,
            CardRegistry.WISHIWASHI,
            CardRegistry.ARAQUANID,
            CardRegistry.LURANTIS,
            CardRegistry.SHIINOTIC,
            CardRegistry.SALAZZLE,
            CardRegistry.PALOSSAND,
            CardRegistry.MINIOR_METEOR,
            CardRegistry.TOGEDEMARU,
            CardRegistry.MIMIKYU,
            CardRegistry.KOMMO_O,
            CardRegistry.TAPU_KOKO,
            CardRegistry.TAPU_LELE,
            CardRegistry.TAPU_BULU,
            CardRegistry.TAPU_FINI,
            CardRegistry.COSMOG,
            CardRegistry.SOLGALEO,
            CardRegistry.LUNALA,
            CardRegistry.MAGEARNA,
            CardRegistry.SKWOVET,
            CardRegistry.GREEDENT,
            CardRegistry.CORVIKNIGHT,
            CardRegistry.APPLIN,
            CardRegistry.FLAPPLE,
            CardRegistry.APPLETUN,
            CardRegistry.URSALUNA,
            CardRegistry.TINKATON,
            CardRegistry.GLIMMORA,
            CardRegistry.DIPPLIN,
            CardRegistry.HYDRAPPLE));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_collection);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ConstraintLayout collectionLayout = findViewById(R.id.collectionScroll);
        View[] layout = new View[cardCollection.size()];
        for (int i = 0; i < cardCollection.size(); i++) {
            int x = i % 3;
            int y = (int) Math.floor(i / 3d);

            layout[i] = View.inflate(this, R.layout.card_layout, null);
            layout[i].setId(View.generateViewId());
            collectionLayout.addView(layout[i]);
            layout[i].setScaleX(0.4f);
            layout[i].setScaleY(0.4f);
            layout[i].setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ConstraintSet set = new ConstraintSet();
            set.clone(collectionLayout);
            set.connect(layout[i].getId(), ConstraintSet.TOP, R.id.collectionScroll, ConstraintSet.TOP, y * 425);
            set.connect(layout[i].getId(), ConstraintSet.LEFT, R.id.collectionScroll, ConstraintSet.LEFT, x * 325);
            set.applyTo(collectionLayout);
            layout[i].setTranslationX(-150);
            layout[i].setTranslationY(-225);
            cardCollection.get(i).displayCard(this, (ConstraintLayout)layout[i]);

            int finalI = i;
            layout[i].setOnLongClickListener(v -> {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_layout, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(this.findViewById(R.id.collection), Gravity.CENTER, 0, 0);
                cardCollection.get(finalI).displayCard(this, popupWindow.getContentView().findViewById(R.id.cardRaw));
                return true;
            });
        }

        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });
    }
}
