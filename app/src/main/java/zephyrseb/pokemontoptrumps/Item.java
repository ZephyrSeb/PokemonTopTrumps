package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Item extends PassiveAbility {
    private final String name;
    private final String text;
    private final int image;
    private final List<Effect> effects = new ArrayList<>();
    private boolean isBerry = false;
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

    public Item setBerry() {
        isBerry = true;
        return this;
    }

    public boolean isBerry() {
        return isBerry;
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
    public static Item SITRUS_BERRY;
    public static Item LIECHI_BERRY;
    public static Item GANLON_BERRY;
    public static Item SALAC_BERRY;
    public static Item PETAYA_BERRY;
    public static Item APICOT_BERRY;
    public static Item STARF_BERRY;
    public static Item LANSAT_BERRY;
    public static Item ENIGMA_BERRY;
    public static Item CELL_BATTERY;

    public static void init(Context ctx) {
        X_HEALTH = new Item(ctx.getResources().getString(R.string.item_name_x_health),ctx.getResources().getString(R.string.item_description_x_health),R.drawable.item_x_health).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) + 1), 0, "item"));
        X_ATTACK = new Item(ctx.getResources().getString(R.string.item_name_x_attack),ctx.getResources().getString(R.string.item_description_x_attack),R.drawable.item_x_attack).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) + 1), 0, "item"));
        X_DEFEND = new Item(ctx.getResources().getString(R.string.item_name_x_defend),ctx.getResources().getString(R.string.item_description_x_defend),R.drawable.item_x_defense).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) + 1), 0, "item"));
        X_SPECIAL = new Item(ctx.getResources().getString(R.string.item_name_x_special),ctx.getResources().getString(R.string.item_description_x_special),R.drawable.item_x_special).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) + 1), 0, "item"));
        X_SPDEF = new Item(ctx.getResources().getString(R.string.item_name_x_spdef),ctx.getResources().getString(R.string.item_description_x_spdef),R.drawable.item_x_spdef).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1), 0, "item"));
        X_SPEED = new Item(ctx.getResources().getString(R.string.item_name_x_speed),ctx.getResources().getString(R.string.item_description_x_speed),R.drawable.item_x_speed).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) + 1), 0, "item"));
        AMULET_COIN = new Item(ctx.getResources().getString(R.string.item_name_amulet_coin),ctx.getResources().getString(R.string.item_description_amulet_coin),R.drawable.item_amulet_coin).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(game.getPrizePot() * 2), 0, "item"));
        COVERT_CLOAK = new Item(ctx.getResources().getString(R.string.item_name_covert_cloak),ctx.getResources().getString(R.string.item_description_covert_cloak),R.drawable.item_covert_cloak).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("ability", player), 4, "item"));
        EJECT_BUTTON = new Item(ctx.getResources().getString(R.string.item_name_eject_button),ctx.getResources().getString(R.string.item_description_eject_button),R.drawable.item_eject_button).addEffect(new Effect((game, player, opponent) -> {
            Card temp = player.getPlayedCard();
            player.setPlayedCard(opponent.getPlayedCard());
            opponent.setPlayedCard(temp);
        }, 3, "item"));
        EXPERT_BELT = new Item(ctx.getResources().getString(R.string.item_name_expert_belt),ctx.getResources().getString(R.string.item_description_expert_belt),R.drawable.item_expert_belt).addEffect(new Effect((game, player, opponent) -> player.setSupereffectiveMultiplier(player.getSupereffectiveMultiplier() * 2), 0, "item"));
        LOADED_DICE = new Item(ctx.getResources().getString(R.string.item_name_loaded_dice),ctx.getResources().getString(R.string.item_description_loaded_dice),R.drawable.item_loaded_dice).addEffect(new Effect((game, player, opponent) -> game.randomizeAttribute(), 0, "item"));
        BATTLE_PASS = new Item(ctx.getResources().getString(R.string.item_name_battle_pass),ctx.getResources().getString(R.string.item_description_battle_pass),R.drawable.item_battle_pass).addEffect(new Effect((game, player, opponent) -> player.setSpeed(1), 0, "item"));
        EVIOLITE = new Item(ctx.getResources().getString(R.string.item_name_eviolite),ctx.getResources().getString(R.string.item_description_eviolite),R.drawable.item_eviolite).addEffect(new Effect((game, player, opponent) -> {
            if (player.getPlayedCard().checkTag(CardTags.CAN_EVOLVE)) {
                player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) + 1);
                player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1);
            }
        }, 0, "item"));
        GRIP_CLAW = new Item(ctx.getResources().getString(R.string.item_name_grip_claw),ctx.getResources().getString(R.string.item_description_grip_claw),R.drawable.item_grip_claw).addEffect(new Effect((game, player, opponent) -> player.addCard(player.getPlayedCard(), 0), 0, "item"));
        MIRROR_HERB = new Item(ctx.getResources().getString(R.string.item_name_mirror_herb),ctx.getResources().getString(R.string.item_description_mirror_herb),R.drawable.item_mirror_herb).addEffect(new Effect((game, player, opponent) -> {
            player.setLevel(Attributes.HP, opponent.getLevel(Attributes.HP));
            player.setLevel(Attributes.ATK, opponent.getLevel(Attributes.ATK));
            player.setLevel(Attributes.DEF, opponent.getLevel(Attributes.DEF));
            player.setLevel(Attributes.SPATK, opponent.getLevel(Attributes.SPATK));
            player.setLevel(Attributes.SPDEF, opponent.getLevel(Attributes.SPDEF));
            player.setLevel(Attributes.SPD, opponent.getLevel(Attributes.SPD));
        }, -1, "item"));
        SCOPE_LENS = new Item(ctx.getResources().getString(R.string.item_name_scope_lens),ctx.getResources().getString(R.string.item_description_scope_lens),R.drawable.item_scope_lens).addEffect(new Effect((game, player, opponent) -> player.setCritRate(player.getCritRate() + 1), 0, "item")).setBerry();
        SITRUS_BERRY = new Item(ctx.getResources().getString(R.string.item_name_sitrus_berry),ctx.getResources().getString(R.string.item_description_sitrus_berry),R.drawable.item_sitrus_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.HP, player.getLevel(Attributes.HP) + 1), 0, "item")).setBerry();
        LIECHI_BERRY = new Item(ctx.getResources().getString(R.string.item_name_liechi_berry),ctx.getResources().getString(R.string.item_description_liechi_berry),R.drawable.item_liechi_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) + 1), 0, "item")).setBerry();
        GANLON_BERRY = new Item(ctx.getResources().getString(R.string.item_name_ganlon_berry),ctx.getResources().getString(R.string.item_description_ganlon_berry),R.drawable.item_ganlon_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) + 1), 0, "item")).setBerry();
        SALAC_BERRY = new Item(ctx.getResources().getString(R.string.item_name_salac_berry),ctx.getResources().getString(R.string.item_description_salac_berry),R.drawable.item_salac_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) + 1), 0, "item")).setBerry();
        PETAYA_BERRY = new Item(ctx.getResources().getString(R.string.item_name_petaya_berry),ctx.getResources().getString(R.string.item_description_petaya_berry),R.drawable.item_petaya_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1), 0, "item")).setBerry();
        APICOT_BERRY = new Item(ctx.getResources().getString(R.string.item_name_apicot_berry),ctx.getResources().getString(R.string.item_description_apicot_berry),R.drawable.item_apicot_berry).addEffect(new Effect((game, player, opponent) -> player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) + 1), 0, "item")).setBerry();
        STARF_BERRY = new Item(ctx.getResources().getString(R.string.item_name_starf_berry),ctx.getResources().getString(R.string.item_description_starf_berry),R.drawable.item_starf_berry).addEffect(new Effect((game, player, opponent) -> {
            Random rand = new Random();
            Attributes[] stats = {Attributes.HP, Attributes.ATK, Attributes.DEF, Attributes.SPATK, Attributes.SPDEF, Attributes.SPD};
            int i = rand.nextInt(6);
            player.setLevel(stats[i], player.getLevel(stats[i]) + 1);
        }, 0, "item")).setBerry();
        LANSAT_BERRY = new Item(ctx.getResources().getString(R.string.item_name_lansat_berry),ctx.getResources().getString(R.string.item_description_lansat_berry),R.drawable.item_lansat_berry).addEffect(new Effect((game, player, opponent) -> player.setCritRate(player.getCritRate() + 1), 0, "item")).setBerry();
        ENIGMA_BERRY = new Item(ctx.getResources().getString(R.string.item_name_enigma_berry),ctx.getResources().getString(R.string.item_description_enigma_berry),R.drawable.item_enigma_berry).addEffect(new Effect((game, player, opponent) -> {
            Card card = player.getPlayedCard().clone();
            List<Attributes> shuffledList = new ArrayList<>();
            shuffledList.add(Attributes.HP);
            shuffledList.add(Attributes.ATK);
            shuffledList.add(Attributes.DEF);
            shuffledList.add(Attributes.SPATK);
            shuffledList.add(Attributes.SPDEF);
            shuffledList.add(Attributes.SPD);
            Collections.shuffle(shuffledList);
            player.setPlayedCard(new Card(card.getName(), card.getImage(), card.getType(), card.getAbility(), card.getAttribute(shuffledList.get(0)), card.getAttribute(shuffledList.get(1)), card.getAttribute(shuffledList.get(2)), card.getAttribute(shuffledList.get(3)), card.getAttribute(shuffledList.get(4)), card.getAttribute(shuffledList.get(5)), card.getWeakness(), card.getFlavorText()));
        }, 0, "item")).setBerry();
        CELL_BATTERY = new Item(ctx.getResources().getString(R.string.item_name_cell_battery),ctx.getResources().getString(R.string.item_description_cell_battery),R.drawable.item_enigma_berry).addEffect(new Effect((game, player, opponent) -> {
            if (player.checkType(player.getPlayedCard(), Type.ELECTRIC)) {
                player.setCurrentStat(player.getCurrentStat() + 1);
            }
        }, 0, "item"));
    }
}
