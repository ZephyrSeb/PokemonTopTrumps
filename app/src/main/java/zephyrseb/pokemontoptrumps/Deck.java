package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    String name;
    List<Card> cardList = new ArrayList<>();
    int pointValue = 0;

    Deck(String n) {
        name = n;
    }

    public void addCard(Card c) {
        cardList.add(c);
    }
}
