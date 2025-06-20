package zephyrseb.pokemontoptrumps.comparators;

import android.content.Context;

import java.util.Comparator;

import zephyrseb.pokemontoptrumps.Item;
import zephyrseb.pokemontoptrumps.ItemRegistry;

public class ItemValueComparator implements Comparator<ItemRegistry> {
        Context context;
    public ItemValueComparator(Context ctx) {
        context = ctx;
    }
        public int compare(ItemRegistry c1, ItemRegistry c2) {
        Item card1 = ItemRegistry.initItem(context, c1);
        Item card2 = ItemRegistry.initItem(context, c2);
        return Integer.compare(card1.getPointValue(), card2.getPointValue());

    }
    }
