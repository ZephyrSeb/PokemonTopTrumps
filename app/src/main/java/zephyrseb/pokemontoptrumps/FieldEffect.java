package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public enum FieldEffect {
    NONE,
    RAIN,
    SUN,
    SNOW,
    SANDSTORM,
    GRASSY_TERRAIN,
    ELECTRIC_TERRAIN,
    MISTY_TERRAIN,
    PSYCHIC_TERRAIN,
    WIND,
    TRICK_ROOM,
    DARKNESS;

    public static String setName(Context ctx, FieldEffect e) {
        return switch (e) {
            case RAIN -> ctx.getString(R.string.field_effect_name_rain);
            case SUN -> ctx.getString(R.string.field_effect_name_sun);
            case SNOW -> ctx.getString(R.string.field_effect_name_snow);
            case SANDSTORM -> ctx.getString(R.string.field_effect_name_sandstorm);
            case ELECTRIC_TERRAIN -> ctx.getString(R.string.field_effect_name_electric_terrain);
            case GRASSY_TERRAIN -> ctx.getString(R.string.field_effect_name_grassy_terrain);
            case MISTY_TERRAIN -> ctx.getString(R.string.field_effect_name_misty_terrain);
            case PSYCHIC_TERRAIN -> ctx.getString(R.string.field_effect_name_psychic_terrain);
            case WIND -> ctx.getString(R.string.field_effect_name_wind);
            case TRICK_ROOM -> ctx.getString(R.string.field_effect_name_trick_room);
            case DARKNESS -> ctx.getString(R.string.field_effect_name_darkness);
            default -> "";
        };
    }

    public static String setDescription(Context ctx, FieldEffect e) {
        return switch (e) {
            case RAIN -> ctx.getString(R.string.field_effect_description_rain);
            case SUN -> ctx.getString(R.string.field_effect_description_sun);
            case SNOW -> ctx.getString(R.string.field_effect_description_snow);
            case SANDSTORM -> ctx.getString(R.string.field_effect_description_sandstorm);
            case ELECTRIC_TERRAIN ->
                    ctx.getString(R.string.field_effect_description_electric_terrain);
            case GRASSY_TERRAIN -> ctx.getString(R.string.field_effect_description_grassy_terrain);
            case MISTY_TERRAIN -> ctx.getString(R.string.field_effect_description_misty_terrain);
            case PSYCHIC_TERRAIN ->
                    ctx.getString(R.string.field_effect_description_psychic_terrain);
            case WIND -> ctx.getString(R.string.field_effect_description_wind);
            case TRICK_ROOM -> ctx.getString(R.string.field_effect_description_trick_room);
            case DARKNESS -> ctx.getString(R.string.field_effect_description_darkness);
            default -> "";
        };
    }

    public static List<Effect> getEffects(FieldEffect e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case RAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);}, 0, "field"));
                break;
            case SUN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 1);}, 0, "field"));
                break;
            case SNOW:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ICE)) player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1);}, 0, "field"));
                break;
            case SANDSTORM:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (!player.checkType(player.getPlayedCard(), Type.GROUND) && !player.checkType(player.getPlayedCard(), Type.STEEL)) player.setBreakPoints(player.getBreakPoints() + 1);}, 0, "field"));
                break;
            case ELECTRIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ELECTRIC)) player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.getStatusCondition() == StatusCondition.ASLEEP) player.setStatusCondition(StatusCondition.NONE);}, -1, "field"));
                break;
            case GRASSY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GRASS)) player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);}, 0, "field"));
                break;
            case MISTY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FAIRY)) player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DRAGON)) player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.NONE), -1, "field"));
                break;
            case PSYCHIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.PSYCHIC)) player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> player.setSpeed(0), -1, "field"));
                break;
            case WIND:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.getPlayedCard().setWeakness(null);}, 0, "field"));
            case TRICK_ROOM:
                effects.add(new Effect((game, player, opponent) -> game.setGoalReverse(true), 0, "field"));
            case DARKNESS:
                effects.add(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DARK)) player.incrementCritRate(1);}, 0, "ability"));
        }
        return effects;
    }
    public static int setImage(Context ctx, FieldEffect e) {
        return switch (e) {
            case RAIN -> R.drawable.weather_rain;
            case SUN -> R.drawable.weather_sun;
            case SNOW -> R.drawable.weather_snow;
            case SANDSTORM -> R.drawable.weather_sandstorm;
            case ELECTRIC_TERRAIN -> R.drawable.terrain_electric;
            case GRASSY_TERRAIN -> R.drawable.terrain_grass;
            case MISTY_TERRAIN -> R.drawable.terrain_misty;
            case PSYCHIC_TERRAIN -> R.drawable.terrain_psychic;
            case WIND -> R.drawable.weather_wind;
            case TRICK_ROOM -> R.drawable.trick_room;
            case DARKNESS -> R.drawable.darkness;
            default -> 0;
        };
    }
}
