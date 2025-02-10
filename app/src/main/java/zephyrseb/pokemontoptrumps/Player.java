package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private List<Card> deck = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();
    private final List<Item> itemDeck = new ArrayList<>();
    private int score;
    public Boolean megaEvolve = true;
    private int currentStat = 0;
    private int currentMod = 40;
    private int speed = 0;

    public Player(String n) {
        name = n;
        score = 0;
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
            if (item == i) success = true;
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

    public Card playCard() {
        if (!deck.isEmpty()) {
            Card card = deck.get(0);
            deck.remove(0);
            discard.add(card);
            return card;
        }
        else return null;
    }

    public Card getCurrentCard() {
        return deck.get(0);
    }

    public Boolean hasCards() {
        return !deck.isEmpty();
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

    public int getCurrentMod() {
        return currentMod;
    }

    public void setCurrentStat(int i) {
        currentStat = i;
    }

    public void setCurrentMod(int i) {
        currentMod = i;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int i) {
        speed = i;
    }
}
