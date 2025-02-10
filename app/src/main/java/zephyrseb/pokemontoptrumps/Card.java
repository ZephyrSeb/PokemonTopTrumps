package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private final String name;
    private final Type type;
    private final Map<String, Integer> attributes = new HashMap<>();
    private final Type weakness;
    private Ability ability;
    public boolean canEvolve = false;
    private final boolean canMegaEvolve;
    private final int image;
    private Card megaCard;
    private final String flavorText;

    public Card(String n, int i, Type t, Ability a, int hp, int atk, int def, int spatk, int spdef, int spd, Type w, String ft) {
        name = n;
        image = i;
        type = t;
        ability = a;
        weakness = w;
        attributes.put("hp",hp);
        attributes.put("atk",atk);
        attributes.put("def",def);
        attributes.put("spatk",spatk);
        attributes.put("spdef",spdef);
        attributes.put("spd",spd);
        canMegaEvolve = false;
        flavorText = ft;
    }

    public Card(String n, int i, Type t, Ability a, int hp, int atk, int def, int spatk, int spdef, int spd, Type w, String ft, Card mc) {
        name = n;
        image = i;
        type = t;
        ability = a;
        weakness = w;
        attributes.put("hp",hp);
        attributes.put("atk",atk);
        attributes.put("def",def);
        attributes.put("spatk",spatk);
        attributes.put("spdef",spdef);
        attributes.put("spd",spd);
        megaCard = mc;
        canMegaEvolve = true;
        flavorText = ft;
    }

    public Card megaEvolve() {
        return megaCard;
    }

    public Card setEvolve(boolean b) {
        canEvolve = b;
        return this;
    }

    /** @noinspection DataFlowIssue*/
    public int getAttribute(String a) {
        return attributes.get(a);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Type getWeakness() {
        return weakness;
    }

    public int getImage() {
        return image;
    }

    public boolean canMegaEvolve() {
        return canMegaEvolve;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability a) {
        ability = a;
    }

    public double getStatAverage() {
        int count = 0;
        count += attributes.get("hp");
        count += attributes.get("atk");
        count += attributes.get("def");
        count += attributes.get("spatk");
        count += attributes.get("spdef");
        count += attributes.get("spd");
        return count / 6d;
    }

    public boolean checkType(Card card) {
        if (type == card.getWeakness()) return true;
        return ability == Ability.HIDDEN_POWER;
    }

    public void displayCard(Context ctx, ConstraintLayout cardLayout) {
        ((TextView) cardLayout.findViewById(R.id.name)).setText(String.valueOf(getName()));
        ((ImageView) cardLayout.findViewById(R.id.cardFrame)).setImageResource(getType().getFrame());
        ((ImageView) cardLayout.findViewById(R.id.cardImage)).setImageResource(getImage());
        ((TextView) cardLayout.findViewById(R.id.hp)).setText(String.valueOf(getAttribute("hp")));
        ((TextView) cardLayout.findViewById(R.id.atk)).setText(String.valueOf(getAttribute("atk")));
        ((TextView) cardLayout.findViewById(R.id.def)).setText(String.valueOf(getAttribute("def")));
        ((TextView) cardLayout.findViewById(R.id.spatk)).setText(String.valueOf(getAttribute("spatk")));
        ((TextView) cardLayout.findViewById(R.id.spdef)).setText(String.valueOf(getAttribute("spdef")));
        ((TextView) cardLayout.findViewById(R.id.spd)).setText(String.valueOf(getAttribute("spd")));
        ((ImageView) cardLayout.findViewById(R.id.weakness)).setImageResource(getWeakness().getIcon());
        ((TextView) cardLayout.findViewById(R.id.flavor)).setText(String.valueOf(getFlavorText()));
        if (!Objects.equals(getAbility(), null)) {
            cardLayout.findViewById(R.id.abilityIcon).setVisibility(View.VISIBLE);
            ((TextView) cardLayout.findViewById(R.id.ability)).setText(String.valueOf(getAbility().getName()));
        }
        else {
            cardLayout.findViewById(R.id.abilityIcon).setVisibility(View.INVISIBLE);
            ((TextView) cardLayout.findViewById(R.id.ability)).setText("");
        }
        int color;
        if (type == Type.DARK) color = R.color.white;
        else color = R.color.black;
        ((TextView) cardLayout.findViewById(R.id.hp)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.atk)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.def)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.spatk)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.spdef)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.spd)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute1)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute2)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute3)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute4)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute5)).setTextColor(ctx.getResources().getColor(color));
        ((TextView) cardLayout.findViewById(R.id.attribute6)).setTextColor(ctx.getResources().getColor(color));
    }
}
