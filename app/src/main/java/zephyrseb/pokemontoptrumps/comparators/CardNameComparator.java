package zephyrseb.pokemontoptrumps.comparators;

import android.content.Context;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Card;
import zephyrseb.pokemontoptrumps.CardRegistry;

public class CardNameComparator implements Comparator<CardRegistry> {
    Context context;
    public CardNameComparator(Context ctx) {
        context = ctx;
    }
    public int compare(CardRegistry c1, CardRegistry c2) {
        Card card1 = CardRegistry.initCard(context, c1);
        Card card2 = CardRegistry.initCard(context, c2);
        return card1.getName().compareTo(card2.getName());
    }
}
