package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class Item extends PassiveAbility {
    private final String name;
    private final String text;
    private final int image;
    private final List<Effect> effects = new ArrayList<>();
    public Item(String n, String t, int i) {
        name = n;
        text = t;
        image = i;
    }

    @Override
    public Item addEffect(Effect e) {
        effects.add(e);
        return this;
    }

    @Override
    public List<Effect> getEffects() {
        return effects;
    }

    public void displayCard(ConstraintLayout cardLayout) {
        ((TextView) cardLayout.findViewById(R.id.name)).setText(String.valueOf(name));
        ((ImageView) cardLayout.findViewById(R.id.cardImage)).setImageResource(image);
        ((TextView) cardLayout.findViewById(R.id.text)).setText(String.valueOf(text));
    }

    public static Item X_HEALTH;
    public static Item X_ATTACK;
    public static Item X_DEFEND;
    public static Item X_SPECIAL;
    public static Item X_SPDEF;
    public static Item X_SPEED;
    public static Item AMULET_COIN;
    public static Item COVERT_CLOAK;
    public static Item EJECT_BUTTON;
    public static Item EXPERT_BELT;
    public static Item LOADED_DICE;
    public static Item BATTLE_PASS;
    public static Item EVIOLITE;
    public static Item GRIP_CLAW;
    public static Item MIRROR_HERB;
    public static Item SCOPE_LENS;

    public static void init(Context ctx) {
        X_HEALTH = new Item(ctx.getResources().getString(R.string.item_name_x_health),ctx.getResources().getString(R.string.item_description_x_health),R.drawable.item_x_health).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "hp")));
        X_ATTACK = new Item(ctx.getResources().getString(R.string.item_name_x_attack),ctx.getResources().getString(R.string.item_description_x_attack),R.drawable.item_x_attack).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "atk")));
        X_DEFEND = new Item(ctx.getResources().getString(R.string.item_name_x_defend),ctx.getResources().getString(R.string.item_description_x_defend),R.drawable.item_x_defense).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "def")));
        X_SPECIAL = new Item(ctx.getResources().getString(R.string.item_name_x_special),ctx.getResources().getString(R.string.item_description_x_special),R.drawable.item_x_special).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "spatk")));
        X_SPDEF = new Item(ctx.getResources().getString(R.string.item_name_x_spdef),ctx.getResources().getString(R.string.item_description_x_spdef),R.drawable.item_x_spdef).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "spdef")));
        X_SPEED = new Item(ctx.getResources().getString(R.string.item_name_x_speed),ctx.getResources().getString(R.string.item_description_x_speed),R.drawable.item_x_speed).addEffect(new Effect("modify-stat",40).addCondition(new Condition("match-stat", "spd")));
        AMULET_COIN = new Item(ctx.getResources().getString(R.string.item_name_amulet_coin),ctx.getResources().getString(R.string.item_description_amulet_coin),R.drawable.item_amulet_coin).addEffect(new Effect("modify-prize",2d));
        COVERT_CLOAK = new Item(ctx.getResources().getString(R.string.item_name_covert_cloak),ctx.getResources().getString(R.string.item_description_covert_cloak),R.drawable.item_covert_cloak).addEffect(new Effect("null-ability"));
        EJECT_BUTTON = new Item(ctx.getResources().getString(R.string.item_name_eject_button),ctx.getResources().getString(R.string.item_description_eject_button),R.drawable.item_eject_button).addEffect(new Effect("swap-card"));
        EXPERT_BELT = new Item(ctx.getResources().getString(R.string.item_name_expert_belt),ctx.getResources().getString(R.string.item_description_expert_belt),R.drawable.item_expert_belt).addEffect(new Effect("modify-weakness",2d));
        LOADED_DICE = new Item(ctx.getResources().getString(R.string.item_name_loaded_dice),ctx.getResources().getString(R.string.item_description_loaded_dice),R.drawable.item_loaded_dice).addEffect((new Effect("randomize-attribute")));
        BATTLE_PASS = new Item(ctx.getResources().getString(R.string.item_name_battle_pass),ctx.getResources().getString(R.string.item_description_battle_pass),R.drawable.item_battle_pass).addEffect(new Effect("modify-speed", 1));
        EVIOLITE = new Item(ctx.getResources().getString(R.string.item_name_eviolite),ctx.getResources().getString(R.string.item_description_eviolite),R.drawable.item_eviolite).addEffect(new Effect("modify-stat",1.5).addCondition(new Condition("match-stat","def"))).addEffect(new Effect("modify-stat",1.5).addCondition(new Condition("match-stat","spdef")));
        GRIP_CLAW = new Item(ctx.getResources().getString(R.string.item_name_grip_claw),ctx.getResources().getString(R.string.item_description_grip_claw),R.drawable.item_grip_claw).addEffect(new Effect("replay-card"));
        MIRROR_HERB = new Item(ctx.getResources().getString(R.string.item_name_mirror_herb),ctx.getResources().getString(R.string.item_description_mirror_herb),R.drawable.item_mirror_herb);
        SCOPE_LENS = new Item(ctx.getResources().getString(R.string.item_name_scope_lens),ctx.getResources().getString(R.string.item_description_scope_lens),R.drawable.item_scope_lens).addEffect(new Effect("modify-stat", 1.5d).addCondition(new Condition("random-chance",15)));
    }
}
