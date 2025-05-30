package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Player {
    private final String name;
    private List<Card> deck = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();
    private final List<Item> itemDeck = new ArrayList<>();
    private int score;
    public Boolean megaEvolve = true;
    private int currentStat = 0;
    private Map<Attributes, Integer> levels = new HashMap<>();
    private int speed = 0;
    private int critRate = 0;
    private double critMultiplier = 1.5d;
    private double critPointMultiplier = 1d;
    private boolean supereffectiveHit = false;
    private double supereffectiveMultiplier = 1.5d;
    private Card currentCard;
    private Item currentItem;
    private boolean charge = false;
    private boolean useCharge = false;
    private Attributes danceStat = null;
    private int breakPoints = 0;
    private StatusCondition status = StatusCondition.NONE;
    private int statusLevel = 0;
    private List<ContinuousEffect> delayedEffects = new ArrayList<>();
    private Aura currentAura = Aura.NONE;
    private Berry[] berryPouch = {Berry.NONE, Berry.NONE, Berry.NONE};
    private boolean autoWin = false;


    public Player(String n) {
        name = n;
        score = 0;
        resetLevels();
    }

    public void addCard(Card c) {
        deck.add(c);
    }

    public void addCard(Card c, int i) {
        deck.add(i,c);
    }

    public void addItem(Item c) {
        itemDeck.add(c);
    }

    public Item useItem(int i) {
        Item item = itemDeck.get(i);
        itemDeck.remove(i);
        return item;
    }

    public Item useItem(Item i) {
        int j = itemDeck.indexOf(i);
        if (j > -1) return useItem(j);
        else return null;
    }

    public boolean hasItem(Item i) {
        boolean success = false;
        for (Item item : itemDeck) {
            if (item == i) {
                success = true;
                break;
            }
        }
        return success;
    }

    public boolean hasItems() {
        return !itemDeck.isEmpty();
    }

    public int noItems() {
        return itemDeck.size();
    }

    public Item getItem(int i) {
        return itemDeck.get(i);
    }

    public Item getPlayedItem() {
        return currentItem;
    }

    public void setPlayedItem(Item i) {
        currentItem = i;
    }

    public boolean checkType(Card c, Type t) {
        return c.getType() == t || Aura.getType(getCurrentAura()) == t;
    }

    public Card playCard() {
        if (!deck.isEmpty()) {
            Card card = deck.get(0);
            deck.remove(0);
            discard.add(card);
            currentCard = card;
            return card;
        }
        else return null;
    }

    public Card getPlayedCard() {
        return currentCard;
    }

    public void setPlayedCard(Card card) {
        currentCard = card;
    }

    public Card getCurrentCard() {
        return deck.get(0);
    }

    public void replaceCurrentCard(Card c) {
        deck.set(0, c);
    }

    public Boolean hasCards() {
        return !deck.isEmpty();
    }

    public int deckCount() {
        return deck.size();
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void shuffle() {
        Random rand = new Random();
        if (deck.isEmpty()) {
            deck = new ArrayList<>(discard);
            discard.clear();
        }
        for (int n = deck.size() - 1; n >=0; n--) {
            int k = rand.nextInt(n + 1);
            Card value = deck.get(k);
            deck.set(k, deck.get(n));
            deck.set(n, value);
        }
    }

    public void addPoint(int i) {
        score += i;
    }

    public int getPoints() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getCurrentStat() {
        return currentStat;
    }

    public void setCurrentStat(int i) {
        currentStat = i;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int i) {
        speed = i;
    }

    public void setLevel(Attributes s, int i) {
        levels.put(s,i);
        if (levels.get(s) > 3) levels.put(s, 3);
        if (levels.get(s) < -3) levels.put(s, -3);
    }

    public int getLevel(Attributes s) {
        return levels.get(s);
    }

    public void incrementLevel(Attributes s, int i) {
        levels.put(s, levels.get(s) + i);
        if (levels.get(s) > 3) levels.put(s, 3);
        if (levels.get(s) < -3) levels.put(s, -3);
    }

    public void resetLevels() {
        levels.put(Attributes.HP,0);
        levels.put(Attributes.ATK,0);
        levels.put(Attributes.DEF,0);
        levels.put(Attributes.SPATK,0);
        levels.put(Attributes.SPDEF,0);
        levels.put(Attributes.SPD,0);
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int i) {
        critRate = i;
        if (critRate < 0) critRate = 0;
        if (critRate > 4) critRate = 4;
    }
    public void incrementCritRate(int i) {
        critRate += i;
        if (critRate < 0) critRate = 0;
        if (critRate > 4) critRate = 4;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double d) {
        critMultiplier = d;
    }

    public double getCritPointMultiplier() {
        return critPointMultiplier;
    }

    public void setCritPointMultiplier(double d) {
        critPointMultiplier = d;
    }

    public boolean getSupereffectiveHit() {
        return supereffectiveHit;
    }

    public void setSupereffectiveHit(boolean b) {
        supereffectiveHit = b;
    }

    public double getSupereffectiveMultiplier() {
        return supereffectiveMultiplier;
    }

    public void setSupereffectiveMultiplier(double d) {
        supereffectiveMultiplier = d;
    }

    public boolean getCharge() {
        return charge;
    }
    public boolean getUsedCharge() {
        return useCharge;
    }

    public void setCharge(boolean b) {
        charge = b;
    }
    public void setUsedCharge(boolean b) {
        useCharge = b;
    }

    public Attributes getDanceStat() {
        return danceStat;
    }

    public void setDanceStat(Attributes s) {
        danceStat = s;
    }

    public StatusCondition getStatusCondition() {
        return status;
    }

    public void setStatusCondition(StatusCondition s) {
        status = s;
        statusLevel = 0;
    }

    public void setStatusLevel(int i) {
        statusLevel = i;
    }

    public int getStatusLevel() {
        return statusLevel;
    }

    public void reset() {
        critMultiplier = 1.5d;
        critPointMultiplier = 1d;
        critRate = 0;
        supereffectiveMultiplier = 2d;
        supereffectiveHit = false;
        resetLevels();
        setSpeed(0);
        autoWin = false;
        if (getUsedCharge()) {
            setCharge(false);
            setUsedCharge(false);
        }
    }

    public List<ContinuousEffect> getDelayedEffects() {
        return delayedEffects;
    }

    public void addDelayedEffect(Effect e, int i) {
        delayedEffects.add(new ContinuousEffect(e,i));
    }

    public void clearDelayedEffects() {
        for (int i = 0; i < delayedEffects.size(); i++) {
            delayedEffects.get(i).reduceTurns();
        }
        delayedEffects.removeIf(ContinuousEffect::timeout);
    }

    public Aura getCurrentAura() {
        return currentAura;
    }

    public void setCurrentAura(Aura aura) {
        currentAura = aura;
    }

    public Berry getBerry(int i) {
        if (i >= 0 && i< 3) return berryPouch[i];
        else return Berry.NONE;
    }

    public boolean addBerry(Berry b) {
        if (berryPouch[0] == Berry.NONE) {berryPouch[0] = b; return true;}
        if (berryPouch[1] == Berry.NONE) {berryPouch[1] = b; return true;}
        if (berryPouch[2] == Berry.NONE) {berryPouch[2] = b; return true;}
        return false;
    }

    public void removeBerry(int i) {
        berryPouch[i] = Berry.NONE;
    }

    public int getBreakPoints() {
        return breakPoints;
    }

    public void setBreakPoints(int i) {
        breakPoints = i;
        if (breakPoints < 0) breakPoints = 0;
        if (breakPoints > 10) breakPoints = 10;
    }

    public void incrementBreakPoints(int i) {
        breakPoints += i;
        if (breakPoints < 0) breakPoints = 0;
        if (breakPoints > 10) breakPoints = 10;
    }

    public void setAutoWin(boolean b) {
        autoWin = b;
    }

    public boolean getAutoWin() {
        return autoWin;
    }

    public void displayBanner(Context ctx, ConstraintLayout banner, int points) {
        if (getStatusCondition() != StatusCondition.NONE) {
            banner.findViewById(R.id.statusIcon).setVisibility(View.VISIBLE);
        }
        else {
            banner.findViewById(R.id.statusIcon).setVisibility(View.INVISIBLE);
        }
        ((ImageView)banner.findViewById(R.id.statusIcon)).setImageResource(StatusCondition.getImage(ctx, getStatusCondition()));
        ((TextView)banner.findViewById(R.id.playerName)).setText(getName());
        if (getBerry(0) != Berry.NONE) ((ImageView)banner.findViewById(R.id.berry1)).setImageResource(getBerry(0).getImage());
        if (getBerry(1) != Berry.NONE) ((ImageView)banner.findViewById(R.id.berry2)).setImageResource(getBerry(1).getImage());
        if (getBerry(2) != Berry.NONE) ((ImageView)banner.findViewById(R.id.berry3)).setImageResource(getBerry(2).getImage());
        if (getCharge()) banner.findViewById(R.id.chargeCounter).setVisibility(View.VISIBLE);
        else banner.findViewById(R.id.chargeCounter).setVisibility(View.INVISIBLE);
        bannerBreak(banner.findViewById(R.id.bannerBreak), getBreakPoints());
        ((ImageView)banner.findViewById(R.id.bannerAura)).setImageResource(Aura.getBanner(getCurrentAura()));

        ImageView score = banner.findViewById(R.id.score);
        score.requestLayout();
        score.getLayoutParams().width = (int)Math.max(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, ctx.getResources().getDisplayMetrics()) * (6 - points),1);
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
}
