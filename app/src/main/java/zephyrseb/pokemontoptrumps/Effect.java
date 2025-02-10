package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Effect {
    List<Condition> conditions = new ArrayList<>();
    private int modSum = 0;
    private double modMult = 1d;
    private final String effect;
    private Item item = null;
    public Effect(String s) {
        effect = s;
    }

    public Effect(String s, int i) {
        effect = s;
        modSum = i;
    }

    public Effect(String s, double d) {
        effect = s;
        modMult = d;
    }

    public Effect(String s, Item i) {
        effect = s;
        item = i;
    }

    public boolean checkConditions(Player p1, Player p2, Card c1, Card c2, String s) {
        if (conditions.isEmpty()) return true;
        boolean check = true;
        for (Condition condition : conditions) {
            if (!condition.checkCondition(p1, p2, c1, c2, s)) check = false;
        }
        return check;
    }

    public boolean checkEffect(String s) {
        return Objects.equals(s, effect);
    }

    public int apply(int i) {
        return (int)(i * modMult) + modSum;
    }

    public Effect addCondition(Condition c) {
        conditions.add(c);
        return this;
    }

    public Item getItem() {
        return item;
    }
}
