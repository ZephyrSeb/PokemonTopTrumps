package zephyrseb.pokemontoptrumps;

import java.util.Objects;
import java.util.Random;

public class Condition {
    private final String condition;
    private final String value;
    private final int accuracy;
    public Condition(String s, String v) {
        condition = s;
        value = v;
        accuracy = 0;
    }
    public Condition(String s) {
        condition = s;
        value = "";
        accuracy = 0;
    }

    public Condition(String s, int v) {
        condition = s;
        accuracy = v;
        value = "";
    }

    public boolean checkCondition(Player p1, Player p2, Card c1, Card c2, String s) {
        Random rand = new Random();
        if (Objects.equals(condition, "match-stat") && Objects.equals(s, value)) return true;
        if (Objects.equals(condition, "low-score") && p2.getPoints() >= 4) return true;
        if (Objects.equals(condition, "random-chance") && rand.nextInt(100) < accuracy) return true;
        if (Objects.equals(condition, "can-evolve") && c1.canEvolve) return true;
        if (Objects.equals(condition, "download")) {
            if (Objects.equals(s, "atk") && c2.getAttribute("def") < c2.getAttribute("spdef")) return true;
            if (Objects.equals(s, "spatk") && c2.getAttribute("def") > c2.getAttribute("spdef")) return true;
        }
        return false;
    }
}
