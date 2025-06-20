package zephyrseb.pokemontoptrumps;

public enum Aura {
    NONE(Type.NONE, R.drawable.break_0),
    NORMAL(Type.NORMAL, R.drawable.aura_normal),
    FIRE(Type.FIRE, R.drawable.aura_fire),
    WATER(Type.WATER, R.drawable.aura_water),
    GRASS(Type.GRASS, R.drawable.aura_grass),
    ELECTRIC(Type.ELECTRIC, R.drawable.aura_electric),
    ICE(Type.ICE, R.drawable.aura_ice),
    FIGHTING(Type.FIGHTING, R.drawable.aura_fighting),
    PSYCHIC(Type.PSYCHIC, R.drawable.aura_psychic),
    DARK(Type.DARK, R.drawable.aura_dark),
    FAIRY(Type.FAIRY, R.drawable.aura_fairy),
    DRAGON(Type.DRAGON, R.drawable.aura_dragon),
    STEEL(Type.STEEL, R.drawable.aura_steel),
    GROUND(Type.GROUND, R.drawable.aura_ground),
    STELLAR(Type.STELLAR, R.drawable.break_0);

    private final Type type;
    private final int banner;

    Aura(Type t, int b) {
        type = t;
        banner = b;
    }

    public Type getType() {
        return type;
    }

    public int getBanner() {
        return banner;
    }

    public static Aura getAuraFromType(Type t) {
        return switch(t) {
            case Type.NONE -> NONE;
            case Type.NORMAL -> NORMAL;
            case Type.FIRE -> FIRE;
            case Type.WATER -> WATER;
            case Type.GRASS -> GRASS;
            case Type.ELECTRIC -> ELECTRIC;
            case Type.ICE -> ICE;
            case Type.PSYCHIC -> PSYCHIC;
            case Type.FIGHTING -> FIGHTING;
            case Type.GROUND -> GROUND;
            case Type.DARK -> DARK;
            case Type.STEEL -> STEEL;
            case Type.DRAGON -> DRAGON;
            case Type.FAIRY -> FAIRY;
            case Type.STELLAR -> STELLAR;
            default -> NONE;
        };
    }
}
