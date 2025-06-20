package zephyrseb.pokemontoptrumps;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Item extends PassiveAbility {
    private final ItemRegistry id;
    private final String name;
    private final String text;
    private final int image;
    private final List<Effect> effects = new ArrayList<>();
    private boolean isBerry = false;
    private final boolean isAceSpec;
    private int pointValue = 0;
    public Item(ItemRegistry ir, String n, String t, int i, boolean as) {
        id = ir;
        name = n;
        text = t;
        image = i;
        isAceSpec = as;
    }

    public ItemRegistry getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Item addEffect(Effect e) {
        effects.add(e);
        return this;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    public Item setBerry() {
        isBerry = true;
        return this;
    }

    public boolean isBerry() {
        return isBerry;
    }

    public boolean isAceSpec() {
        return isAceSpec;
    }

    public Item setPointValue(int i) {
        pointValue = i;
        return this;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void displayCard(ConstraintLayout cardLayout) {
        int frame;
        if (isAceSpec) frame = R.drawable.card_item_ace_spec;
        else frame = R.drawable.card_item;
        ((ImageView) cardLayout.findViewById(R.id.cardFrame)).setImageResource(frame);
        ((TextView) cardLayout.findViewById(R.id.name)).setText(String.valueOf(name));
        ((ImageView) cardLayout.findViewById(R.id.cardImage)).setImageResource(image);
        ((TextView) cardLayout.findViewById(R.id.text)).setText(String.valueOf(text));
        ((TextView) cardLayout.findViewById(R.id.pointText)).setText(String.valueOf(getPointValue()));
    }
}
