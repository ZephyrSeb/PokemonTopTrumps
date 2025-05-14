package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public enum StatusCondition {
    BURNED,
    PARALYZED,
    POISONED,
    FROZEN,
    FROSTBITTEN,
    CONFUSED,
    ASLEEP,
    NONE;

    public static String setName(Context ctx, StatusCondition s) {
        switch (s) {
            case BURNED:
                return ctx.getString(R.string.status_effect_name_burned);
            case PARALYZED:
                return ctx.getString(R.string.status_effect_name_paralyzed);
            case POISONED:
                return ctx.getString(R.string.status_effect_name_poisoned);
            case FROSTBITTEN:
                return ctx.getString(R.string.status_effect_name_frostbitten);
            case FROZEN:
                return ctx.getString(R.string.status_effect_name_frozen);
            case CONFUSED:
                return ctx.getString(R.string.status_effect_name_confused);
            case ASLEEP:
                return ctx.getString(R.string.status_effect_name_asleep);
            default: return "";
        }
    }

    public static String setDescription(Context ctx, StatusCondition s) {
        switch (s) {
            case BURNED:
                return ctx.getString(R.string.status_effect_description_burned);
            case PARALYZED:
                return ctx.getString(R.string.status_effect_description_paralyzed);
            case POISONED:
                return ctx.getString(R.string.status_effect_description_poisoned);
            case FROSTBITTEN:
                return ctx.getString(R.string.status_effect_description_frostbitten);
            case FROZEN:
                return ctx.getString(R.string.status_effect_description_frozen);
            case CONFUSED:
                return ctx.getString(R.string.status_effect_description_confused);
            case ASLEEP:
                return ctx.getString(R.string.status_effect_description_asleep);
            default: return "";
        }
    }

    public static List<Effect> getEffects(StatusCondition e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case BURNED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel("atk", player.getLevel("atk") - 1);
                    }
                }, 1, "status"));
                break;
            case PARALYZED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel("spd", player.getLevel("spd") - 1);
                    }
                }, 1, "status"));
                break;
            case POISONED:
                break;
            case FROSTBITTEN:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel("spatk", player.getLevel("spatk") - 1);
                    }
                }, 1, "status"));
                break;
            case FROZEN:
                break;
            case CONFUSED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        game.randomizeAttribute();
                    }
                }, 1, "status"));
                break;
            case ASLEEP:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setSpeed(-1);
                        player.setStatusCondition(NONE);
                    }
                }, 1, "status"));
                break;
            case NONE:
                break;
        }
        return effects;
    }

    public static int getImage(Context ctx, StatusCondition s) {
        switch (s) {
            case BURNED:
                return R.drawable.icon_burned;
            case PARALYZED:
                return R.drawable.icon_paralyzed;
            case POISONED:
                return R.drawable.icon_poisoned;
            case FROSTBITTEN:
                return R.drawable.icon_frostbitten;
            case FROZEN:
                return R.drawable.icon_frozen;
            case CONFUSED:
                return R.drawable.icon_confused;
            case ASLEEP:
                return R.drawable.icon_asleep;
            default: return 0;
        }
    }
}
