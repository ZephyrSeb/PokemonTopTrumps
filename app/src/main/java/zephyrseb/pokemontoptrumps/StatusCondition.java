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
        return switch (s) {
            case BURNED -> ctx.getString(R.string.status_effect_name_burned);
            case PARALYZED -> ctx.getString(R.string.status_effect_name_paralyzed);
            case POISONED -> ctx.getString(R.string.status_effect_name_poisoned);
            case FROSTBITTEN -> ctx.getString(R.string.status_effect_name_frostbitten);
            case FROZEN -> ctx.getString(R.string.status_effect_name_frozen);
            case CONFUSED -> ctx.getString(R.string.status_effect_name_confused);
            case ASLEEP -> ctx.getString(R.string.status_effect_name_asleep);
            default -> "";
        };
    }

    public static String setDescription(Context ctx, StatusCondition s) {
        return switch (s) {
            case BURNED -> ctx.getString(R.string.status_effect_description_burned);
            case PARALYZED -> ctx.getString(R.string.status_effect_description_paralyzed);
            case POISONED -> ctx.getString(R.string.status_effect_description_poisoned);
            case FROSTBITTEN -> ctx.getString(R.string.status_effect_description_frostbitten);
            case FROZEN -> ctx.getString(R.string.status_effect_description_frozen);
            case CONFUSED -> ctx.getString(R.string.status_effect_description_confused);
            case ASLEEP -> ctx.getString(R.string.status_effect_description_asleep);
            default -> "";
        };
    }

    public static List<Effect> getEffects(StatusCondition e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case BURNED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 1);
                    }
                }, 1, "status"));
                break;
            case PARALYZED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) - 1);
                    }
                }, 1, "status"));
                break;
            case POISONED:
                effects.add(new Effect((game, player, opponent) -> player.incrementBreakPoints(1 + player.getStatusLevel()), 0, "status"));
                break;
            case FROSTBITTEN:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 1);
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
        return switch (s) {
            case BURNED -> R.drawable.icon_burned;
            case PARALYZED -> R.drawable.icon_paralyzed;
            case POISONED -> R.drawable.icon_poisoned;
            case FROSTBITTEN -> R.drawable.icon_frostbitten;
            case FROZEN -> R.drawable.icon_frozen;
            case CONFUSED -> R.drawable.icon_confused;
            case ASLEEP -> R.drawable.icon_asleep;
            default -> 0;
        };
    }
}
