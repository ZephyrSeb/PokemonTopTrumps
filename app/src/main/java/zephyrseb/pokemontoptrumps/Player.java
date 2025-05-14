package zephyrseb.pokemontoptrumps;

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
    private Map<String, Integer> levels = new HashMap<>();
    private int speed = 0;
    private int critRate = 0;
    private double critMultiplier = 2d;
    private boolean supereffectiveHit = false;
    private double supereffectiveMultiplier = 1.5d;
    private Card currentCard;
    private Item currentItem;
    private int counterCharge = 0;
    private String danceStat = "";
    private StatusCondition status = null;

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

    public void setLevel(String s, int i) {
        levels.put(s,i);
    }

    public int getLevel(String s) {
        return levels.get(s);
    }

    public void resetLevels() {
        levels.put("hp",0);
        levels.put("atk",0);
        levels.put("def",0);
        levels.put("spatk",0);
        levels.put("spdef",0);
        levels.put("spd",0);
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int i) {
        critRate = i;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double d) {
        critMultiplier = d;
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

    public int getCounterCharge() {
        return counterCharge;
    }

    public void setCounterCharge(int i) {
        counterCharge = i;
    }

    public String getDanceStat() {
        return danceStat;
    }

    public void setDanceStat(String s) {
        danceStat = s;
    }

    public StatusCondition getStatusCondition() {
        return status;
    }

    public void setStatusCondition(StatusCondition s) {
        status = s;
    }

    public void reset() {
        critMultiplier = 1.5d;
        critRate = 0;
        supereffectiveMultiplier = 2d;
        supereffectiveHit = false;
        resetLevels();
        setSpeed(0);
    }
}
