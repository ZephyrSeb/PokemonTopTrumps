package zephyrseb.pokemontoptrumps.comparators;

import android.content.Context;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Card;
import zephyrseb.pokemontoptrumps.CardRegistry;

public class CardValueComparator implements Comparator<CardRegistry> {
    Context context;
    public CardValueComparator(Context ctx) {
        context = ctx;
    }
    public int compare(CardRegistry c1, CardRegistry c2) {
        Card card1 = CardRegistry.initCard(context, c1);
        Card card2 = CardRegistry.initCard(context, c2);
        return Integer.compare(card1.getPointValue(), card2.getPointValue());

    }
}
