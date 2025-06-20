package zephyrseb.pokemontoptrumps;

import java.util.Random;

public enum Attributes {
    HP,
    ATK,
    DEF,
    SPATK,
    SPDEF,
    SPD,
    ACC,
    EV;

    public static Attributes getAttributeWithPriority(Attributes a, boolean hasPriority) {
        if (hasPriority) return a;
        else {
            if (a == ATK) return DEF;
            if (a == SPATK) return SPDEF;
            if (a == ACC) return EV;
            if (a == DEF) return ATK;
            if (a == SPDEF) return SPATK;
            if (a == EV) return ACC;
            else return SPD;
        }
    }

    public static Attributes getRandomAttribute() {
        Random rand = new Random();
        return switch (rand.nextInt(7)) {
            case 0 -> ATK;
            case 1 -> DEF;
            case 2 -> SPATK;
            case 3 -> SPDEF;
            case 4 -> ACC;
            case 5 -> EV;
            case 6 -> SPD;
            default -> HP;
        };
    }
}
