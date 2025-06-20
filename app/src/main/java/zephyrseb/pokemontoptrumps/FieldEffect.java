package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;

public enum FieldEffect {
    NONE(R.string.field_effect_name_trick_room, R.string.field_effect_name_trick_room, 0),
    RAIN(R.string.field_effect_name_rain, R.string.field_effect_description_rain, R.drawable.weather_rain),
    SUN(R.string.field_effect_name_sun, R.string.field_effect_description_sun, R.drawable.weather_sun),
    SNOW(R.string.field_effect_name_snow, R.string.field_effect_description_snow, R.drawable.weather_snow),
    SANDSTORM(R.string.field_effect_name_sandstorm, R.string.field_effect_description_sandstorm, R.drawable.weather_sandstorm),
    GRASSY_TERRAIN(R.string.field_effect_name_grassy_terrain, R.string.field_effect_description_grassy_terrain, R.drawable.terrain_grass),
    ELECTRIC_TERRAIN(R.string.field_effect_name_electric_terrain, R.string.field_effect_description_electric_terrain, R.drawable.terrain_electric),
    MISTY_TERRAIN(R.string.field_effect_name_misty_terrain, R.string.field_effect_description_misty_terrain, R.drawable.terrain_misty),
    PSYCHIC_TERRAIN(R.string.field_effect_name_psychic_terrain, R.string.field_effect_description_psychic_terrain, R.drawable.terrain_psychic),
    WIND(R.string.field_effect_name_wind, R.string.field_effect_description_wind, R.drawable.weather_wind),
    TRICK_ROOM(R.string.field_effect_name_trick_room, R.string.field_effect_description_trick_room, R.drawable.trick_room),
    DARKNESS(R.string.field_effect_name_darkness, R.string.field_effect_description_darkness, R.drawable.darkness);

    private final int name;
    private final int description;
    private final int image;
    FieldEffect(int n, int d, int i) {
        name = n;
        description = d;
        image = i;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public static List<Effect> getEffects(FieldEffect e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case RAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.SPATK, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.ATK, -1);}, 0, "field", true));
                break;
            case SUN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.ATK, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.SPATK, -1);}, 0, "field", true));
                break;
            case SNOW:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ICE)) player.incrementLevel(Attributes.EV, 1);}, 0, "field", true));
                break;
            case SANDSTORM:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.incrementLevel(Attributes.DEF, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (!player.checkType(player.getPlayedCard(), Type.GROUND) && !player.checkType(player.getPlayedCard(), Type.STEEL)) player.incrementBreakPoints(1);}, 0, "field", false));
                break;
            case ELECTRIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ELECTRIC)) player.incrementLevel(Attributes.SPD, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.ASLEEP) player.removeStatusCondition();}, -1, "field", false));
                break;
            case GRASSY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GRASS)) player.incrementLevel(Attributes.SPDEF,  1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.incrementLevel(Attributes.ATK, -1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> player.incrementBreakPoints(-1), 0, "field", false));
                break;
            case MISTY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FAIRY)) player.incrementLevel(Attributes.SPDEF, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DRAGON)) player.incrementLevel(Attributes.ATK, -1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> player.removeStatusCondition(), -1, "field", false));
                break;
            case PSYCHIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.PSYCHIC)) player.incrementLevel(Attributes.SPATK, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> player.setSpeed(0), -1, "field", false));
                break;
            case WIND:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.incrementLevel(Attributes.SPD, 1);}, 0, "field", true));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.getPlayedCard().setWeakness(null);}, 0, "field", false));
                break;
            case TRICK_ROOM:
                effects.add(new Effect((game, player, opponent) -> game.setGoalReverse(false), 0, "field", false));
                break;
            case DARKNESS:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DARK)) player.incrementCritRate(1);}, 0, "ability", false));
                effects.add(new Effect((game, player, opponent) -> {
                    player.setAvailableAttribute(game.getOrderedAttributes(0), false);
                    player.setAvailableAttribute(game.getOrderedAttributes(1), false);
                }, 0, "field", true));
                break;
        }
        return effects;
    }
}
