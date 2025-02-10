package zephyrseb.pokemontoptrumps;

public class Type {
    private final String name;
    private int cardFrame;
    private int typeIcon;

    public Type(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public int getFrame() {
        return cardFrame;
    }

    public int getIcon() {
        return typeIcon;
    }

    public Type setFrame(int frame) {
        cardFrame = frame;
        return this;
    }

    /** @noinspection UnusedReturnValue*/
    public Type setIcon(int icon) {
        typeIcon = icon;
        return this;
    }

    public static Type NORMAL = new Type("Normal");
    public static Type FIRE = new Type("Fire");
    public static Type WATER = new Type("Water");
    public static Type GRASS = new Type("Grass");
    public static Type ELECTRIC = new Type("Electric");
    public static Type PSYCHIC = new Type("Psychic");
    public static Type FIGHTING = new Type("Fighting");
    public static Type GROUND = new Type("Ground");
    public static Type STEEL = new Type("Steel");
    public static Type DARK = new Type("Dark");
    public static Type FAIRY = new Type("Fairy");
    public static void init() {
        NORMAL.setFrame(R.drawable.card_normal).setIcon(R.drawable.type_normal);
        FIRE.setFrame(R.drawable.card_fire).setIcon(R.drawable.type_fire);
        WATER.setFrame(R.drawable.card_water).setIcon(R.drawable.type_water);
        GRASS.setFrame(R.drawable.card_grass).setIcon(R.drawable.type_grass);
        ELECTRIC.setFrame(R.drawable.card_electric).setIcon(R.drawable.type_electric);
        PSYCHIC.setFrame(R.drawable.card_psychic).setIcon(R.drawable.type_psychic);
        FIGHTING.setFrame(R.drawable.card_fighting).setIcon(R.drawable.type_fighting);
        GROUND.setFrame(R.drawable.card_ground).setIcon(R.drawable.type_ground);
        STEEL.setFrame(R.drawable.card_steel).setIcon(R.drawable.type_steel);
        DARK.setFrame(R.drawable.card_dark).setIcon(R.drawable.type_dark);
        FAIRY.setFrame(R.drawable.card_fairy).setIcon(R.drawable.type_fairy);
    }

}
