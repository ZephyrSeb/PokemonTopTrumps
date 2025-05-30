package zephyrseb.pokemontoptrumps;

public enum Aura {
    NONE,
    NORMAL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    ICE,
    FIGHTING,
    PSYCHIC,
    DARK,
    FAIRY,
    DRAGON,
    STEEL,
    GROUND,
    STELLAR,
    SHADOW;

    public static Type getType(Aura a) {
        if (a == NONE) return null;
        if (a == NORMAL) return Type.NORMAL;
        if (a == FIRE) return Type.FIRE;
        if (a == WATER) return Type.WATER;
        if (a == GRASS) return Type.GRASS;
        if (a == ELECTRIC) return Type.ELECTRIC;
        if (a == ICE) return Type.ICE;
        if (a == PSYCHIC) return Type.PSYCHIC;
        if (a == DARK) return Type.DARK;
        if (a == FIGHTING) return Type.FIGHTING;
        if (a == GROUND) return Type.GROUND;
        if (a == STEEL) return Type.STEEL;
        if (a == DRAGON) return Type.DRAGON;
        if (a == FAIRY) return Type.FAIRY;
        if (a == STELLAR) return Type.STELLAR;
        return null;
    }

    public static int getBanner(Aura a) {
        return switch (a) {
            case NORMAL -> R.drawable.aura_normal;
            case FIRE -> R.drawable.aura_fire;
            case WATER -> R.drawable.aura_water;
            case GRASS -> R.drawable.aura_grass;
            case ELECTRIC -> R.drawable.aura_electric;
            case ICE -> R.drawable.aura_ice;
            case PSYCHIC -> R.drawable.aura_psychic;
            case DARK -> R.drawable.aura_dark;
            case FIGHTING -> R.drawable.aura_fighting;
            case GROUND -> R.drawable.aura_ground;
            case STEEL -> R.drawable.aura_steel;
            case FAIRY -> R.drawable.aura_fairy;
            case DRAGON -> R.drawable.aura_dragon;
            default -> R.drawable.break_0;
        };
    }
}
