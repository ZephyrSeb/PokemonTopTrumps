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
    TRICK_ROOM;

    public static String setName(Context ctx, FieldEffect e) {
        switch (e) {
            case RAIN:
                return ctx.getString(R.string.field_effect_name_rain);
            case SUN:
                return ctx.getString(R.string.field_effect_name_sun);
            case SNOW:
                return ctx.getString(R.string.field_effect_name_snow);
            case SANDSTORM:
                return ctx.getString(R.string.field_effect_name_sandstorm);
            case ELECTRIC_TERRAIN:
                return ctx.getString(R.string.field_effect_name_electric_terrain);
            case GRASSY_TERRAIN:
                return ctx.getString(R.string.field_effect_name_grassy_terrain);
            case MISTY_TERRAIN:
                return ctx.getString(R.string.field_effect_name_misty_terrain);
            case PSYCHIC_TERRAIN:
                return ctx.getString(R.string.field_effect_name_psychic_terrain);
            case WIND:
                return ctx.getString(R.string.field_effect_name_wind);
            case TRICK_ROOM:
                return ctx.getString(R.string.field_effect_name_trick_room);
            default:
                return "";
        }
    }

    public static String setDescription(Context ctx, FieldEffect e) {
        switch (e) {
            case RAIN:
                return ctx.getString(R.string.field_effect_description_rain);
            case SUN:
                return ctx.getString(R.string.field_effect_description_sun);
            case SNOW:
                return ctx.getString(R.string.field_effect_description_snow);
            case SANDSTORM:
                return ctx.getString(R.string.field_effect_description_sandstorm);
            case ELECTRIC_TERRAIN:
                return ctx.getString(R.string.field_effect_description_electric_terrain);
            case GRASSY_TERRAIN:
                return ctx.getString(R.string.field_effect_description_grassy_terrain);
            case MISTY_TERRAIN:
                return ctx.getString(R.string.field_effect_description_misty_terrain);
            case PSYCHIC_TERRAIN:
                return ctx.getString(R.string.field_effect_description_psychic_terrain);
            case WIND:
                return ctx.getString(R.string.field_effect_description_wind);
            case TRICK_ROOM:
                return ctx.getString(R.string.field_effect_description_trick_room);
            default:
                return "";
        }
    }

    public static List<Effect> getEffects(FieldEffect e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case RAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.WATER) player.setLevel("spatk", player.getLevel("spatk") + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.FIRE) player.setLevel("atk", player.getLevel("atk") - 1);}, 0, "field"));
                break;
            case SUN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.FIRE) player.setLevel("atk", player.getLevel("atk") + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.WATER) player.setLevel("spatk", player.getLevel("spatk") - 1);}, 0, "field"));
                break;
            case SNOW:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.ICE) player.setLevel("spdef", player.getLevel("spdef") + 1);}, 0, "field"));
                break;
            case SANDSTORM:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.GROUND) player.setLevel("def", player.getLevel("def") + 1);}, 0, "field"));
                break;
            case ELECTRIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.ELECTRIC) player.setLevel("spd", player.getLevel("spd") + 1);}, 0, "field"));
                break;
            case GRASSY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.GRASS) player.setLevel("hp", player.getLevel("hp") + 1);}, 0, "field"));
                break;
            case MISTY_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.FAIRY) player.setLevel("spdef", player.getLevel("spdef") + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.DRAGON) player.setLevel("atk", player.getLevel("atk") - 1);}, 0, "field"));
                break;
            case PSYCHIC_TERRAIN:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.PSYCHIC) player.setLevel("spatk", player.getLevel("spatk") + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> player.setSpeed(0), -1, "field"));
                break;
            case WIND:
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.NORMAL) player.setLevel("spd", player.getLevel("spd") + 1);}, 0, "field"));
                effects.add(new Effect((game, player, opponent) -> {if (player.getPlayedCard().getType() == Type.NORMAL) player.getPlayedCard().setWeakness(null);}, 0, "field"));
            case TRICK_ROOM:
                effects.add(new Effect((game, player, opponent) -> game.setGoalReverse(true), 0, "field"));
        }
        return effects;
    }
    public static int setImage(Context ctx, FieldEffect e) {
        switch (e) {
            case RAIN:
                return R.drawable.weather_rain;
            case SUN:
                return R.drawable.weather_sun;
            case SNOW:
                return R.drawable.weather_snow;
            case SANDSTORM:
                return R.drawable.weather_sandstorm;
            case ELECTRIC_TERRAIN:
                return R.drawable.terrain_electric;
            case GRASSY_TERRAIN:
                return R.drawable.terrain_grass;
            case MISTY_TERRAIN:
                return R.drawable.terrain_misty;
            case PSYCHIC_TERRAIN:
                return R.drawable.terrain_psychic;
            case WIND:
                return R.drawable.weather_wind;
            case TRICK_ROOM:
                return R.drawable.trick_room;
            default:
                return 0;
        }
    }
}
