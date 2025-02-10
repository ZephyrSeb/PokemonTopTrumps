package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Ability extends PassiveAbility {
    private final String name;
    private final String description;
    private final List<Effect> effects = new ArrayList<>();
    public Ability(String n, String d) {
        name = n;
        description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Ability addEffect(Effect e) {
        effects.add(e);
        return this;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    public static Ability HIDDEN_POWER;
    public static Ability TRUANT;
    public static Ability TRANSFORM;
    public static Ability TORRENT;
    public static Ability OVERGROW;
    public static Ability BLAZE;
    public static Ability HEART_SWAP;
    public static Ability PICKUP;
    public static Ability MOLD_BREAKER;
    public static Ability DOWNLOAD;
    public static Ability SUPER_LUCK;
    public static Ability TRACE;

    public static void init(Context ctx) {
        HIDDEN_POWER = new Ability(ctx.getResources().getString(R.string.ability_name_hidden_power),ctx.getResources().getString(R.string.ability_description_hidden_power));
        TRUANT = new Ability(ctx.getResources().getString(R.string.ability_name_truant),ctx.getResources().getString(R.string.ability_description_truant)).addEffect(new Effect("modify-speed", -1));
        TRANSFORM = new Ability(ctx.getResources().getString(R.string.ability_name_transform),ctx.getResources().getString(R.string.ability_description_transform)).addEffect(new Effect("copy-card"));
        TORRENT = new Ability(ctx.getResources().getString(R.string.ability_name_torrent),ctx.getResources().getString(R.string.ability_description_torrent)).addEffect(new Effect("modify-stat", 40).addCondition(new Condition("low-score")));
        OVERGROW = new Ability(ctx.getResources().getString(R.string.ability_name_overgrow),ctx.getResources().getString(R.string.ability_description_overgrow)).addEffect(new Effect("modify-stat", 40).addCondition(new Condition("low-score")));
        BLAZE = new Ability(ctx.getResources().getString(R.string.ability_name_blaze),ctx.getResources().getString(R.string.ability_description_blaze)).addEffect(new Effect("modify-stat", 40).addCondition(new Condition("low-score")));
        HEART_SWAP = new Ability(ctx.getResources().getString(R.string.ability_name_heart_swap),ctx.getResources().getString(R.string.ability_description_heart_swap)).addEffect(new Effect("swap-card"));
        PICKUP = new Ability(ctx.getResources().getString(R.string.ability_name_pickup),ctx.getResources().getString(R.string.ability_description_pickup)).addEffect(new Effect("get-item",1));
        MOLD_BREAKER = new Ability(ctx.getResources().getString(R.string.ability_name_mold_breaker),ctx.getResources().getString(R.string.ability_description_mold_breaker)).addEffect(new Effect("null-ability"));
        DOWNLOAD = new Ability(ctx.getResources().getString(R.string.ability_name_download),ctx.getResources().getString(R.string.ability_description_download)).addEffect(new Effect("modify-stat", 40).addCondition(new Condition("download")));
        SUPER_LUCK = new Ability(ctx.getResources().getString(R.string.ability_name_super_luck),ctx.getResources().getString(R.string.ability_description_super_luck)).addEffect(new Effect("modify-stat", 1.5d).addCondition(new Condition("random-chance",15)));
        TRACE = new Ability(ctx.getResources().getString(R.string.ability_name_trace),ctx.getResources().getString(R.string.ability_description_trace)).addEffect(new Effect("copy-ability"));
    }
}
