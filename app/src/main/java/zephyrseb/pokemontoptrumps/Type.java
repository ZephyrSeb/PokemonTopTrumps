package zephyrseb.pokemontoptrumps;

import android.content.Context;

public enum Type {
    NONE(R.string.type_name_normal, R.drawable.card_normal, R.drawable.icon_blank),
    NORMAL(R.string.type_name_normal, R.drawable.card_normal, R.drawable.type_normal),
    FIRE(R.string.type_name_fire, R.drawable.card_fire, R.drawable.type_fire),
    WATER(R.string.type_name_water, R.drawable.card_water, R.drawable.type_water),
    GRASS(R.string.type_name_grass, R.drawable.card_grass, R.drawable.type_grass),
    ELECTRIC(R.string.type_name_electric, R.drawable.card_electric, R.drawable.type_electric),
    ICE(R.string.type_name_ice, R.drawable.card_ice, R.drawable.type_ice),
    PSYCHIC(R.string.type_name_psychic, R.drawable.card_psychic, R.drawable.type_psychic),
    FIGHTING(R.string.type_name_fighting, R.drawable.card_fighting, R.drawable.type_fighting),
    GROUND(R.string.type_name_ground, R.drawable.card_ground, R.drawable.type_ground),
    DARK(R.string.type_name_dark, R.drawable.card_dark, R.drawable.type_dark),
    STEEL(R.string.type_name_steel, R.drawable.card_steel, R.drawable.type_steel),
    FAIRY(R.string.type_name_fairy, R.drawable.card_fairy, R.drawable.type_fairy),
    DRAGON(R.string.type_name_dragon, R.drawable.card_dragon, R.drawable.type_dragon),
    STELLAR(R.string.type_name_stellar, R.drawable.card_normal, R.drawable.type_stellar);
    private final int name;
    private final int cardFrame;
    private final int typeIcon;

    Type(int n, int frame, int type) {
        name = n;
        cardFrame = frame;
        typeIcon = type;
    }

    public String getName(Context ctx) {
        return ctx.getString(name);
    }

    public int getFrame() {
        return cardFrame;
    }

    public int getIcon() {
        return typeIcon;
    }
}
