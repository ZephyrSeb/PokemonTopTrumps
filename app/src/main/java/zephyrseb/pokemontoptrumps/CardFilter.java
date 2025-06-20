package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import zephyrseb.pokemontoptrumps.Screens.DeckBuilderScreen;

public class CardFilter extends Filter {
    private String name = "";
    private String ability = "";
    private List<Type> types = new ArrayList<>();
    private List<Integer> pointValues = new ArrayList<>();
    private Attributes highestAttribute = null;
    private Boolean canEvolve = null;
    private Boolean canMegaEvolve = null;
    private Boolean inDeck = null;
    public CardFilter() {}

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setAbility(String s) {
        ability = s;
    }

    public String getAbility() {
        return ability;
    }

    public void clearType() {
        types.clear();
    }

    public void addType(Type t) {
        types.add(t);
    }

    public boolean containsType(Type t) {
        return types.contains(t);
    }

    public void removeType(Type t) {
        types.remove(t);
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

    public void setHighestAttribute(Attributes a) {
        highestAttribute = a;
    }

    public Attributes getHighestAttribute() {
        return highestAttribute;
    }

    public void setCanEvolve(Boolean b) {
        canEvolve = b;
    }

    public Boolean getCanEvolve() {
        return canEvolve;
    }

    public void setCanMegaEvolve(Boolean b) {
        canMegaEvolve = b;
    }

    public Boolean getCanMegaEvolve() {
        return canMegaEvolve;
    }

    public void setInDeck(Boolean b) {
        inDeck = b;
    }

    public Boolean getInDeck() {
        return inDeck;
    }

    public void clear() {
        name = "";
        ability = "";
        types.clear();
        pointValues.clear();
        highestAttribute = null;
        canEvolve = null;
        canMegaEvolve = null;
        inDeck = null;
    }

    public boolean filterCard(Context ctx, CardRegistry cr) {
        Card c = CardRegistry.initCard(ctx, cr);
        if (!Objects.equals(name, "") && !c.getName().toLowerCase().contains(name.toLowerCase())) return false;
        if (!Objects.equals(ability, "")) {
            if (c.getAbility() == null) return false;
            else if (!c.getAbility().getName().toLowerCase().contains(ability.toLowerCase())) return false;
        }
        if (!types.isEmpty() && !types.contains(c.getType())) return false;
        if (!pointValues.isEmpty() && !pointValues.contains(c.getPointValue())) return false;
        if (!(highestAttribute == null)) {
            if (!(c.getHighestStat() == highestAttribute)) return false;
        }
        if (canEvolve != null && !c.checkTag(CardTags.CAN_EVOLVE) == canEvolve) return false;
        if (canMegaEvolve != null && !c.canMegaEvolve() == canMegaEvolve) return false;
        if (inDeck != null && ((DeckBuilderScreen)ctx).getDeck().containsCard(cr) != inDeck) return false;
        return true;
    }
}
