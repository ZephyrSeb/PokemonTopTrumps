package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import zephyrseb.pokemontoptrumps.Screens.DeckBuilderScreen;

public class ItemFilter extends Filter {
    private String name = "";
    private List<Integer> pointValues = new ArrayList<>();
    private Boolean aceSpec = null;
    private Boolean inDeck = null;
    public ItemFilter() {}

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void clearPointValues() {
        pointValues.clear();
    }

    public void addPointValue(int i) {
        pointValues.add(i);
    }

    public boolean containsPointValue(int i) {
        return pointValues.contains(i);
    }

    public void removePointValue(Integer i) {
        pointValues.remove(i);
    }

    public void setAceSpec(Boolean b) {
        aceSpec = b;
    }

    public Boolean getAceSpec() {
        return aceSpec;
    }

    public void setInDeck(Boolean b) {
        inDeck = b;
    }

    public Boolean getInDeck() {
        return inDeck;
    }

    public void clear() {
        name = "";
        pointValues.clear();
        aceSpec = null;
        inDeck = null;
    }

    public boolean filterCard(Context ctx, ItemRegistry cr) {
        Item c = ItemRegistry.initItem(ctx, cr);
        if (!Objects.equals(name, "") && !c.getName().toLowerCase().contains(name.toLowerCase())) return false;

        if (!pointValues.isEmpty() && !pointValues.contains(c.getPointValue())) return false;

        if (aceSpec != null && !c.isAceSpec() == aceSpec) return false;
        if (inDeck != null && ((DeckBuilderScreen)ctx).getDeck().containsItem(cr) != inDeck) return false;
        return true;
    }
}
