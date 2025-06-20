package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;

public enum StatusCondition {
    BURNED(R.string.status_effect_name_burned, R.string.status_effect_description_burned, R.drawable.icon_burned),
    PARALYZED(R.string.status_effect_name_paralyzed, R.string.status_effect_description_paralyzed, R.drawable.icon_paralyzed),
    POISONED(R.string.status_effect_name_poisoned, R.string.status_effect_description_poisoned, R.drawable.icon_poisoned),
    FROZEN(R.string.status_effect_name_frozen, R.string.status_effect_description_frozen, R.drawable.icon_frozen),
    FROSTBITTEN(R.string.status_effect_name_frostbitten, R.string.status_effect_description_frostbitten, R.drawable.icon_frostbitten),
    CONFUSED(R.string.status_effect_name_confused, R.string.status_effect_description_confused, R.drawable.icon_confused),
    ASLEEP(R.string.status_effect_name_asleep, R.string.status_effect_description_asleep, R.drawable.icon_asleep),
    BLINDED(R.string.status_effect_name_blinded, R.string.status_effect_description_blinded, R.drawable.icon_blinded),
    NONE(R.string.status_effect_name_burned, R.string.status_effect_description_burned, R.drawable.icon_blank);

    private final int name;
    private final int description;
    private final int image;
    StatusCondition(int n, int d, int i) {
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

    public static List<Effect> getEffects(StatusCondition e) {
        List<Effect> effects = new ArrayList<>();
        switch (e) {
            case BURNED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.incrementLevel(Attributes.ATK, -1);
                    }
                }, 1, "status", true));
                break;
            case PARALYZED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.incrementLevel(Attributes.SPD, -1);
                    }
                }, 1, "status", true));
                break;
            case POISONED:
                effects.add(new Effect((game, player, opponent) -> player.incrementBreakPoints(1 + player.getStatusLevel()), 0, "status", false));
                break;
            case FROSTBITTEN:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.incrementLevel(Attributes.SPATK, -1);
                    }
                }, 1, "status", true));
                break;
            case FROZEN:
                break;
            case CONFUSED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        game.randomizeAttribute(player);
                    }
                }, 1, "status", false));
                break;
            case ASLEEP:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.setSpeed(-1);
                        player.removeStatusCondition();
                    }
                }, 1, "status", false));
                break;
            case BLINDED:
                effects.add(new Effect((game, player, opponent) -> {
                    if (game.getActivePlayer() == player) {
                        player.incrementLevel(Attributes.ACC, -1);
                    }
                }, 1, "status", true));
                break;
            case NONE:
                break;
        }
        return effects;
    }
}
