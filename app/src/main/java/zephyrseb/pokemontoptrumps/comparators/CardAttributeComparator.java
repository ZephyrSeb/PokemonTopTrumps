package zephyrseb.pokemontoptrumps.comparators;

import android.content.Context;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Attributes;
import zephyrseb.pokemontoptrumps.Card;
import zephyrseb.pokemontoptrumps.CardRegistry;

public class CardAttributeComparator implements Comparator<CardRegistry> {
    Context context;
    Attributes attribute;
    public CardAttributeComparator(Context ctx, Attributes a) {
        context = ctx;
        attribute = a;
    }
    public int compare(CardRegistry c1, CardRegistry c2) {
        Card card1 = CardRegistry.initCard(context, c1);
        Card card2 = CardRegistry.initCard(context, c2);
        return Integer.compare(card1.getAttribute(attribute), card2.getAttribute(attribute));

    }
}
