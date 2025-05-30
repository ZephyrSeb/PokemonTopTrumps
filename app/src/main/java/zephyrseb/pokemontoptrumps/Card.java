package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import zephyrseb.pokemontoptrumps.Screens.GameScreen;

public class Card {
    private final String name;
    private Type type;
    private final Map<Attributes, Integer> attributes = new HashMap<>();
    private Type weakness;
    private Ability ability;
    private final boolean canMegaEvolve;
    private boolean hasMegaEvolved;
    private final int image;
    private Card megaCard;
    private final String flavorText;
    List<CardTags> cardTags = new ArrayList<>();
    private int pointValue;
    private int scoreValue;


    public Card(String n, int i, Type t, Ability a, int hp, int atk, int def, int spatk, int spdef, int spd, Type w, String ft) {
        name = n;
        image = i;
        type = t;
        ability = a;
        weakness = w;
        attributes.put(Attributes.HP,hp);
        attributes.put(Attributes.ATK,atk);
        attributes.put(Attributes.DEF,def);
        attributes.put(Attributes.SPATK,spatk);
        attributes.put(Attributes.SPDEF,spdef);
        attributes.put(Attributes.SPD,spd);
        canMegaEvolve = false;
        flavorText = ft;
    }

    public Card(String n, int i, Type t, Ability a, int hp, int atk, int def, int spatk, int spdef, int spd, Type w, String ft, Card mc) {
        name = n;
        image = i;
        type = t;
        ability = a;
        weakness = w;
        attributes.put(Attributes.HP,hp);
        attributes.put(Attributes.ATK,atk);
        attributes.put(Attributes.DEF,def);
        attributes.put(Attributes.SPATK,spatk);
        attributes.put(Attributes.SPDEF,spdef);
        attributes.put(Attributes.SPD,spd);
        megaCard = mc;
        megaCard.hasMegaEvolved = true;
        canMegaEvolve = true;
        flavorText = ft;
    }

    public Card megaEvolve() {
        return megaCard;
    }

    public int getAttribute(Attributes a) {
        return attributes.get(a);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type t) {
        type = t;
    }

    public Type getWeakness() {
        return weakness;
    }

    public void setWeakness(Type t) {
        weakness = t;
    }

    public int getImage() {
        return image;
    }

    public boolean canMegaEvolve() {
        return canMegaEvolve;
    }

    public boolean hasMegaEvolved() {
        return hasMegaEvolved;
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

    @NonNull
    public Card clone() {
        if (!canMegaEvolve()) {
            return new Card(name, image, type, ability, attributes.get(Attributes.HP), attributes.get(Attributes.ATK), attributes.get(Attributes.DEF), attributes.get(Attributes.SPATK), attributes.get(Attributes.SPDEF), attributes.get(Attributes.SPD), weakness, flavorText).setTagList(getTagList());
        }
        else {
            return new Card(name, image, type, ability, attributes.get(Attributes.HP), attributes.get(Attributes.ATK), attributes.get(Attributes.DEF), attributes.get(Attributes.SPATK), attributes.get(Attributes.SPDEF), attributes.get(Attributes.SPD), weakness, flavorText, megaCard).setTagList(getTagList());
        }
    }

    public double getStatAverage() {
        int count = 0;
        count += attributes.get(Attributes.HP);
        count += attributes.get(Attributes.ATK);
        count += attributes.get(Attributes.DEF);
        count += attributes.get(Attributes.SPATK);
        count += attributes.get(Attributes.SPDEF);
        count += attributes.get(Attributes.SPD);
        return count / 6d;
    }

    public Attributes getHighestStat() {
        int value = 0;
        Attributes stat = null;
        if (attributes.get(Attributes.HP) > value) {value = attributes.get(Attributes.HP); stat = Attributes.HP;}
        if (attributes.get(Attributes.ATK) > value) {value = attributes.get(Attributes.ATK); stat = Attributes.ATK;}
        if (attributes.get(Attributes.DEF) > value) {value = attributes.get(Attributes.DEF); stat = Attributes.DEF;}
        if (attributes.get(Attributes.SPATK) > value) {value = attributes.get(Attributes.SPATK); stat = Attributes.SPATK;}
        if (attributes.get(Attributes.SPDEF) > value) {value = attributes.get(Attributes.SPDEF); stat = Attributes.SPDEF;}
        if (attributes.get(Attributes.SPD) > value) {value = attributes.get(Attributes.SPD); stat = Attributes.SPD;}
        return stat;
    }

    public boolean checkType(Card card) {
        return type == card.getWeakness();
    }

    public int getPointValue() {
        return pointValue;
    }

    public Card setPointValue(int v) {
        pointValue = v;
        return this;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public Card setScoreValue(int v) {
        scoreValue = v;
        return this;
    }

    public Card addTag(CardTags tag) {
        cardTags.add(tag);
        return this;
    }

    public Card setTagList(List<CardTags> tags) {
        cardTags = tags;
        return this;
    }

    public List<CardTags> getTagList() {
        return cardTags;
    }

    public boolean checkTag(CardTags tag) {
        return cardTags.contains(tag);
    }


    public void displayCard(Context ctx, ConstraintLayout cardLayout) {
        ((TextView) cardLayout.findViewById(R.id.name)).setText(String.valueOf(getName()));
        Glide.with(ctx).load(getType().getFrame()).into((ImageView) cardLayout.findViewById(R.id.cardFrame));
        Glide.with(ctx).load(getImage()).into((ImageView) cardLayout.findViewById(R.id.cardImage));
        ((TextView) cardLayout.findViewById(R.id.hp)).setText(String.valueOf(getAttribute(Attributes.HP)));
        ((TextView) cardLayout.findViewById(R.id.atk)).setText(String.valueOf(getAttribute(Attributes.ATK)));
        ((TextView) cardLayout.findViewById(R.id.def)).setText(String.valueOf(getAttribute(Attributes.DEF)));
        ((TextView) cardLayout.findViewById(R.id.spatk)).setText(String.valueOf(getAttribute(Attributes.SPATK)));
        ((TextView) cardLayout.findViewById(R.id.spdef)).setText(String.valueOf(getAttribute(Attributes.SPDEF)));
        ((TextView) cardLayout.findViewById(R.id.spd)).setText(String.valueOf(getAttribute(Attributes.SPD)));
        if (getWeakness() != null) Glide.with(ctx).load(getWeakness().getIcon()).into((ImageView) cardLayout.findViewById(R.id.weakness));
        ((TextView) cardLayout.findViewById(R.id.flavor)).setText(String.valueOf(getFlavorText()));
        ((TextView) cardLayout.findViewById(R.id.pointText)).setText(String.valueOf(getPointValue()));
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
        if (ctx instanceof GameScreen) {
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.HP)) {
                cardLayout.findViewById(R.id.darknessHP).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessHP).setVisibility(View.VISIBLE);
            }
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.ATK)) {
                cardLayout.findViewById(R.id.darknessATK).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessATK).setVisibility(View.VISIBLE);
            }
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.DEF)) {
                cardLayout.findViewById(R.id.darknessDEF).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessDEF).setVisibility(View.VISIBLE);
            }
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.SPATK)) {
                cardLayout.findViewById(R.id.darknessSPATK).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessSPATK).setVisibility(View.VISIBLE);
            }
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.SPDEF)) {
                cardLayout.findViewById(R.id.darknessSPDEF).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessSPDEF).setVisibility(View.VISIBLE);
            }
            if (((GameScreen) ctx).checkAvailableAttribute(Attributes.SPD)) {
                cardLayout.findViewById(R.id.darknessSPD).setVisibility(View.INVISIBLE);
            } else {
                cardLayout.findViewById(R.id.darknessSPD).setVisibility(View.VISIBLE);
            }
        }
    }
}
