package zephyrseb.pokemontoptrumps.comparators;

import android.content.Context;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Item;
import zephyrseb.pokemontoptrumps.ItemRegistry;

public class ItemNameComparator implements Comparator<ItemRegistry> {
    Context context;
    public ItemNameComparator(Context ctx) {
        context = ctx;
    }
    public int compare(ItemRegistry c1, ItemRegistry c2) {
        Item card1 = ItemRegistry.initItem(context, c1);
        Item card2 = ItemRegistry.initItem(context, c2);
        return card1.getName().compareTo(card2.getName());
    }
}
