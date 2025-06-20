package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum ItemRegistry {
    POTION,
    FULL_HEAL,
    FULL_RESTORE,
    POKE_BALL,
    GREAT_BALL,
    ULTRA_BALL,
    MASTER_BALL,
    NET_BALL,
    DIVE_BALL,
    MOON_BALL,
    FAST_BALL,
    HEAVY_BALL,
    REPEAT_BALL,
    BEAST_BALL,
    X_ATTACK,
    X_DEFEND,
    X_SPECIAL,
    X_SPDEF,
    X_ACCURACY,
    X_EVASION,
    X_SPEED,
    SILK_SCARF,
    CHARCOAL,
    MYSTIC_WATER,
    MIRACLE_SEED,
    MAGNET,
    NEVER_MELT_ICE,
    TWISTED_SPOON,
    BLACK_GLASSES,
    BLACK_BELT,
    DRAGON_FANG,
    METAL_COAT,
    SOFT_SAND,
    FAIRY_FEATHER,
    NORMAL_GEM,
    FIRE_GEM,
    WATER_GEM,
    GRASS_GEM,
    ELECTRIC_GEM,
    ICE_GEM,
    PSYCHIC_GEM,
    DARK_GEM,
    FIGHTING_GEM,
    GROUND_GEM,
    STEEL_GEM,
    DRAGON_GEM,
    FAIRY_GEM,
    NORMAL_TERA_SHARD,
    FIRE_TERA_SHARD,
    WATER_TERA_SHARD,
    GRASS_TERA_SHARD,
    ELECTRIC_TERA_SHARD,
    ICE_TERA_SHARD,
    PSYCHIC_TERA_SHARD,
    DARK_TERA_SHARD,
    FIGHTING_TERA_SHARD,
    GROUND_TERA_SHARD,
    STEEL_TERA_SHARD,
    DRAGON_TERA_SHARD,
    FAIRY_TERA_SHARD,
    CHOICE_SPECS,
    CHOICE_BAND,
    CHOICE_SCARF,
    CHOICE_SCOPE,
    DAMP_ROCK,
    ICY_ROCK,
    HEAT_ROCK,
    SMOOTH_ROCK,
    GRASSY_SEED,
    ELECTRIC_SEED,
    MISTY_SEED,
    PSYCHIC_SEED,
    FLAME_ORB,
    TOXIC_ORB,
    LIFE_ORB,
    IRON_BALL,
    AMULET_COIN,
    COVERT_CLOAK,
    EJECT_BUTTON,
    EXPERT_BELT,
    LOADED_DICE,
    BATTLE_PASS,
    EVIOLITE,
    GRIP_CLAW,
    MIRROR_HERB,
    SCOPE_LENS,
    ROCKY_HELMET,
    METRONOME,
    FOCUS_SASH,
    AIR_BALLOON,
    ROOM_SERVICE,
    WEAKNESS_POLICY,
    QUICK_CLAW,
    BLACK_SLUDGE,
    LEFTOVERS,
    ITEM_PRINTER,
    CHERI_BERRY,
    CHESTO_BERRY,
    RAWST_BERRY,
    ASPEAR_BERRY,
    PECHA_BERRY,
    PERSIM_BERRY,
    LUM_BERRY,
    ORAN_BERRY,
    SITRUS_BERRY,
    CHILAN_BERRY,
    OCCA_BERRY,
    PASSHO_BERRY,
    RINDO_BERRY,
    WACAN_BERRY,
    YACHE_BERRY,
    PAYAPA_BERRY,
    COLBUR_BERRY,
    CHOPLE_BERRY,
    SHUCA_BERRY,
    BABIRI_BERRY,
    HABAN_BERRY,
    ROSELI_BERRY,
    LIECHI_BERRY,
    GANLON_BERRY,
    SALAC_BERRY,
    PETAYA_BERRY,
    APICOT_BERRY,
    MICLE_BERRY,
    CUSTAP_BERRY,
    STARF_BERRY,
    LANSAT_BERRY,
    ENIGMA_BERRY,
    BERRY_POT;

    public static Item initItem(Context ctx, ItemRegistry ir) {
        return switch (ir) {
            case POTION -> new Item(ir, ctx.getResources().getString(R.string.item_name_potion), ctx.getResources().getString(R.string.item_description_potion), R.drawable.item_potion, false).addEffect(new Effect((game, player, opponent) -> player.incrementBreakPoints(-2), 0, "item", false)).setPointValue(1);
            case FULL_HEAL -> new Item(ir, ctx.getResources().getString(R.string.item_name_full_heal), ctx.getResources().getString(R.string.item_description_full_heal), R.drawable.item_full_heal, false).addEffect(new Effect((game, player, opponent) -> player.removeStatusCondition(), 0, "item", false)).setPointValue(2);
            case FULL_RESTORE -> new Item(ir, ctx.getResources().getString(R.string.item_name_full_restore), ctx.getResources().getString(R.string.item_description_full_restore), R.drawable.item_full_restore, false).addEffect(new Effect((game, player, opponent) -> {player.setBreakPoints(0); player.removeStatusCondition();}, 0, "item", false)).setPointValue(3);
            case POKE_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_poke_ball), ctx.getResources().getString(R.string.item_description_poke_ball), R.drawable.item_poke_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getPointValue() < 3) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(2);
            case GREAT_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_great_ball), ctx.getResources().getString(R.string.item_description_great_ball), R.drawable.item_great_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getPointValue() == 3 || c.getPointValue() == 4) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(4);
            case ULTRA_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_ultra_ball), ctx.getResources().getString(R.string.item_description_ultra_ball), R.drawable.item_ultra_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getPointValue() == 5 || c.getPointValue() == 6) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(6);
            case MASTER_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_master_ball), ctx.getResources().getString(R.string.item_description_master_ball), R.drawable.item_master_ball, true).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getPointValue() >= 7) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(8);
            case NET_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_net_ball), ctx.getResources().getString(R.string.item_description_net_ball), R.drawable.item_net_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.checkTag(CardTags.FISH)) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(3);
            case DIVE_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_dive_ball), ctx.getResources().getString(R.string.item_description_dive_ball), R.drawable.item_dive_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getType() == Type.WATER) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(4);
            case MOON_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_moon_ball), ctx.getResources().getString(R.string.item_description_moon_ball), R.drawable.item_moon_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.checkTag(CardTags.MOON)) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(4);
            case FAST_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_fast_ball), ctx.getResources().getString(R.string.item_description_fast_ball), R.drawable.item_fast_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getAttribute(Attributes.HP) > 100) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(4);
            case HEAVY_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_heavy_ball), ctx.getResources().getString(R.string.item_description_heavy_ball), R.drawable.item_heavy_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.getAttribute(Attributes.EV) < 30) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(4);
            case REPEAT_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_repeat_ball), ctx.getResources().getString(R.string.item_description_repeat_ball), R.drawable.item_repeat_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (player.getDiscard().contains(c)) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(3);
            case BEAST_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_beast_ball), ctx.getResources().getString(R.string.item_description_beast_ball), R.drawable.item_beast_ball, false).addEffect(new Effect((game, player, opponent) -> {
                List<Card> cards = new ArrayList<>();
                for (Card c : player.getDeck()) {
                    if (c.checkTag(CardTags.ULTRA_BEAST)) cards.add(c);
                }
                Random rand = new Random();
                if (!cards.isEmpty()) {
                    Card c = cards.get(rand.nextInt(cards.size()));
                    player.putCardAtFront(c);
                }
            }, 0, "item", false)).setPointValue(7);
            case X_ATTACK -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_attack), ctx.getResources().getString(R.string.item_description_x_attack), R.drawable.item_x_attack, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.ATK, 1), 0, "item", true)).setPointValue(3);
            case X_DEFEND -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_defend), ctx.getResources().getString(R.string.item_description_x_defend), R.drawable.item_x_defense, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.DEF, 1), 0, "item", true)).setPointValue(3);
            case X_SPECIAL -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_special), ctx.getResources().getString(R.string.item_description_x_special), R.drawable.item_x_special, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPATK, 1), 0, "item", true)).setPointValue(3);
            case X_SPDEF -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_spdef), ctx.getResources().getString(R.string.item_description_x_spdef), R.drawable.item_x_spdef, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPDEF, 1), 0, "item", true)).setPointValue(3);
            case X_ACCURACY -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_accuracy), ctx.getResources().getString(R.string.item_description_x_accuracy), R.drawable.item_x_accuracy, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.ACC, 1), 0, "item", true)).setPointValue(3);
            case X_EVASION -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_evasion), ctx.getResources().getString(R.string.item_description_x_evasion), R.drawable.item_x_evasion, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.EV, 1), 0, "item", true)).setPointValue(3);
            case X_SPEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_x_speed), ctx.getResources().getString(R.string.item_description_x_speed), R.drawable.item_x_speed, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPD, 1), 0, "item", true)).setPointValue(3);
            case SILK_SCARF -> new Item(ir, ctx.getResources().getString(R.string.item_name_silk_scarf), ctx.getResources().getString(R.string.item_description_silk_scarf), R.drawable.item_silk_scarf, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case CHARCOAL -> new Item(ir, ctx.getResources().getString(R.string.item_name_charcoal), ctx.getResources().getString(R.string.item_description_charcoal), R.drawable.item_charcoal, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case MYSTIC_WATER -> new Item(ir, ctx.getResources().getString(R.string.item_name_mystic_water), ctx.getResources().getString(R.string.item_description_mystic_water), R.drawable.item_mystic_water, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case MIRACLE_SEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_miracle_seed), ctx.getResources().getString(R.string.item_description_miracle_seed), R.drawable.item_miracle_seed, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GRASS)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case MAGNET -> new Item(ir, ctx.getResources().getString(R.string.item_name_magnet), ctx.getResources().getString(R.string.item_description_magnet), R.drawable.item_magnet, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ELECTRIC)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case NEVER_MELT_ICE -> new Item(ir, ctx.getResources().getString(R.string.item_name_never_melt_ice), ctx.getResources().getString(R.string.item_description_never_melt_ice), R.drawable.item_never_melt_ice, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ICE)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case TWISTED_SPOON -> new Item(ir, ctx.getResources().getString(R.string.item_name_twisted_spoon), ctx.getResources().getString(R.string.item_description_twisted_spoon), R.drawable.item_twisted_spoon, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.PSYCHIC)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case BLACK_GLASSES -> new Item(ir, ctx.getResources().getString(R.string.item_name_black_glasses), ctx.getResources().getString(R.string.item_description_black_glasses), R.drawable.item_black_glasses, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DARK)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case BLACK_BELT -> new Item(ir, ctx.getResources().getString(R.string.item_name_black_belt), ctx.getResources().getString(R.string.item_description_black_belt), R.drawable.item_black_belt, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIGHTING)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case DRAGON_FANG -> new Item(ir, ctx.getResources().getString(R.string.item_name_dragon_fang), ctx.getResources().getString(R.string.item_description_dragon_fang), R.drawable.item_dragon_fang, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DRAGON)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case METAL_COAT -> new Item(ir, ctx.getResources().getString(R.string.item_name_metal_coat), ctx.getResources().getString(R.string.item_description_metal_coat), R.drawable.item_metal_coat, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.STEEL)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case SOFT_SAND -> new Item(ir, ctx.getResources().getString(R.string.item_name_soft_sand), ctx.getResources().getString(R.string.item_description_soft_sand), R.drawable.item_soft_sand, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            case FAIRY_FEATHER -> new Item(ir, ctx.getResources().getString(R.string.item_name_fairy_feather), ctx.getResources().getString(R.string.item_description_fairy_feather), R.drawable.item_fairy_feather, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FAIRY)) player.incrementLevel(Attributes.getAttributeWithPriority(game.getChosenAttribute(), game.getActivePlayer() == player), 2);}, 0, "item", false)).setPointValue(3);
            //case NORMAL_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_normal_gem), ctx.getResources().getString(R.string.item_description_normal_gem), R.drawable.item_normal_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.NORMAL)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case FIRE_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_fire_gem), ctx.getResources().getString(R.string.item_description_fire_gem), R.drawable.item_fire_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIRE)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case WATER_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_water_gem), ctx.getResources().getString(R.string.item_description_water_gem), R.drawable.item_water_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.WATER)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case GRASS_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_grass_gem), ctx.getResources().getString(R.string.item_description_grass_gem), R.drawable.item_grass_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GRASS)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case ELECTRIC_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_electric_gem), ctx.getResources().getString(R.string.item_description_electric_gem), R.drawable.item_electric_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ELECTRIC)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case ICE_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_ice_gem), ctx.getResources().getString(R.string.item_description_ice_gem), R.drawable.item_ice_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.ICE)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case PSYCHIC_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_psychic_gem), ctx.getResources().getString(R.string.item_description_psychic_gem), R.drawable.item_psychic_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.PSYCHIC)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case DARK_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_dark_gem), ctx.getResources().getString(R.string.item_description_dark_gem), R.drawable.item_dark_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DARK)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case FIGHTING_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_fighting_gem), ctx.getResources().getString(R.string.item_description_fighting_gem), R.drawable.item_fighting_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FIGHTING)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case GROUND_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_ground_gem), ctx.getResources().getString(R.string.item_description_ground_gem), R.drawable.item_ground_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.GROUND)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case STEEL_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_steel_gem), ctx.getResources().getString(R.string.item_description_steel_gem), R.drawable.item_steel_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.STEEL)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case DRAGON_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_dragon_gem), ctx.getResources().getString(R.string.item_description_dragon_gem), R.drawable.item_dragon_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.DRAGON)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            //case FAIRY_GEM -> new Item(ir, ctx.getResources().getString(R.string.item_name_fairy_gem), ctx.getResources().getString(R.string.item_description_fairy_gem), R.drawable.item_fairy_gem, false).addEffect(new Effect((game, player, opponent) -> {if (player.checkType(player.getPlayedCard(), Type.FAIRY)) player.incrementLevel(Attributes.getRandomAttribute(), 2);}, 0, "item", false)).setPointValue(4);
            case NORMAL_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_normal_tera_shard), ctx.getResources().getString(R.string.item_description_normal_tera_shard), R.drawable.item_normal_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.NORMAL), 0, "item", false)).setPointValue(2);
            case FIRE_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_fire_tera_shard), ctx.getResources().getString(R.string.item_description_fire_tera_shard), R.drawable.item_fire_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FIRE), 0, "item", false)).setPointValue(2);
            case WATER_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_water_tera_shard), ctx.getResources().getString(R.string.item_description_water_tera_shard), R.drawable.item_water_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.WATER), 0, "item", false)).setPointValue(2);
            case GRASS_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_grass_tera_shard), ctx.getResources().getString(R.string.item_description_grass_tera_shard), R.drawable.item_grass_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.GRASS), 0, "item", false)).setPointValue(2);
            case ELECTRIC_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_electric_tera_shard), ctx.getResources().getString(R.string.item_description_electric_tera_shard), R.drawable.item_electric_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.ELECTRIC), 0, "item", false)).setPointValue(2);
            case ICE_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_ice_tera_shard), ctx.getResources().getString(R.string.item_description_ice_tera_shard), R.drawable.item_ice_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.ICE), 0, "item", false)).setPointValue(2);
            case PSYCHIC_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_psychic_tera_shard), ctx.getResources().getString(R.string.item_description_psychic_tera_shard), R.drawable.item_psychic_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.PSYCHIC), 0, "item", false)).setPointValue(2);
            case DARK_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_dark_tera_shard), ctx.getResources().getString(R.string.item_description_dark_tera_shard), R.drawable.item_dark_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.DARK), 0, "item", false)).setPointValue(2);
            case FIGHTING_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_fighting_tera_shard), ctx.getResources().getString(R.string.item_description_fighting_tera_shard), R.drawable.item_fighting_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FIGHTING), 0, "item", false)).setPointValue(2);
            case GROUND_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_ground_tera_shard), ctx.getResources().getString(R.string.item_description_ground_tera_shard), R.drawable.item_ground_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.GROUND), 0, "item", false)).setPointValue(2);
            case STEEL_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_steel_tera_shard), ctx.getResources().getString(R.string.item_description_steel_tera_shard), R.drawable.item_steel_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.STEEL), 0, "item", false)).setPointValue(2);
            case DRAGON_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_dragon_tera_shard), ctx.getResources().getString(R.string.item_description_dragon_tera_shard), R.drawable.item_dragon_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.DRAGON), 0, "item", false)).setPointValue(2);
            case FAIRY_TERA_SHARD -> new Item(ir, ctx.getResources().getString(R.string.item_name_fairy_tera_shard), ctx.getResources().getString(R.string.item_description_fairy_tera_shard), R.drawable.item_fairy_tera_shard, false).addEffect(new Effect((game, player, opponent) -> player.setCurrentAura(Aura.FAIRY), 0, "item", false)).setPointValue(2);
            case CHOICE_SPECS -> new Item(ir, ctx.getResources().getString(R.string.item_name_choice_specs), ctx.getResources().getString(R.string.item_description_choice_specs), R.drawable.item_choice_specs, false).addEffect(new Effect((game, player, opponent) -> {
                player.incrementLevel(Attributes.SPATK, 2);
            }, 0, "item", true)).addEffect(new Effect((game, player, opponent) -> {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    player1.setAvailableAttribute(Attributes.ATK, false);
                    player1.setAvailableAttribute(Attributes.ACC, false);
                    player1.setAvailableAttribute(Attributes.SPD, false);
                }, 2, "item", true), 3, R.string.continuous_effect_description_choice_specs);
            }, 0, "item", false)).setPointValue(5);
            case CHOICE_BAND -> new Item(ir, ctx.getResources().getString(R.string.item_name_choice_band), ctx.getResources().getString(R.string.item_description_choice_band), R.drawable.item_choice_band, false).addEffect(new Effect((game, player, opponent) -> {
                player.incrementLevel(Attributes.ATK, 2);
            }, 0, "item", true)).addEffect(new Effect((game, player, opponent) -> {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    player1.setAvailableAttribute(Attributes.SPATK, false);
                    player1.setAvailableAttribute(Attributes.ACC, false);
                    player1.setAvailableAttribute(Attributes.SPD, false);
                }, 2, "item", true), 3, R.string.continuous_effect_description_choice_band);
            }, 0, "item", false)).setPointValue(5);
            case CHOICE_SCARF -> new Item(ir, ctx.getResources().getString(R.string.item_name_choice_scarf), ctx.getResources().getString(R.string.item_description_choice_scarf), R.drawable.item_choice_scarf, false).addEffect(new Effect((game, player, opponent) -> {
                player.incrementLevel(Attributes.SPD, 2);
            }, 0, "item", true)).addEffect(new Effect((game, player, opponent) -> {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    player1.setAvailableAttribute(Attributes.ATK, false);
                    player1.setAvailableAttribute(Attributes.SPATK, false);
                    player1.setAvailableAttribute(Attributes.ACC, false);
                }, 2, "item", true), 3, R.string.continuous_effect_description_choice_scarf);
            }, 0, "item", false)).setPointValue(5);
            case CHOICE_SCOPE -> new Item(ir, ctx.getResources().getString(R.string.item_name_choice_scope), ctx.getResources().getString(R.string.item_description_choice_scope), R.drawable.item_choice_scope, false).addEffect(new Effect((game, player, opponent) -> {
                player.incrementLevel(Attributes.ACC, 2);
            }, 0, "item", true)).addEffect(new Effect((game, player, opponent) -> {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> {
                    player1.setAvailableAttribute(Attributes.ATK, false);
                    player1.setAvailableAttribute(Attributes.SPATK, false);
                    player1.setAvailableAttribute(Attributes.SPD, false);
                }, 2, "item", true), 3, R.string.continuous_effect_description_choice_scope);
            }, 0, "item", false)).setPointValue(5);
            case DAMP_ROCK -> new Item(ir, ctx.getResources().getString(R.string.item_name_damp_rock), ctx.getResources().getString(R.string.item_description_damp_rock), R.drawable.item_damp_rock, false).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.RAIN), 0, "item", false)).setPointValue(5);
            case ICY_ROCK -> new Item(ir, ctx.getResources().getString(R.string.item_name_icy_rock), ctx.getResources().getString(R.string.item_description_icy_rock), R.drawable.item_icy_rock, false).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SNOW), 0, "item", false)).setPointValue(5);
            case HEAT_ROCK -> new Item(ir, ctx.getResources().getString(R.string.item_name_heat_rock), ctx.getResources().getString(R.string.item_description_heat_rock), R.drawable.item_heat_rock, false).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SUN), 0, "item", false)).setPointValue(5);
            case SMOOTH_ROCK -> new Item(ir, ctx.getResources().getString(R.string.item_name_smooth_rock), ctx.getResources().getString(R.string.item_description_smooth_rock), R.drawable.item_smooth_rock, false).addEffect(new Effect((game, player, opponent) -> game.setFieldEffect(FieldEffect.SANDSTORM), 0, "item", false)).setPointValue(5);
            case GRASSY_SEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_grassy_seed), ctx.getResources().getString(R.string.item_description_grassy_seed), R.drawable.item_grassy_seed, false).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.incrementLevel(Attributes.DEF, 1);}, 0, "item", true)).setPointValue(3);
            case ELECTRIC_SEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_electric_seed), ctx.getResources().getString(R.string.item_description_electric_seed), R.drawable.item_electric_seed, false).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.incrementLevel(Attributes.DEF, 1);}, 0, "item", true)).setPointValue(3);
            case MISTY_SEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_misty_seed), ctx.getResources().getString(R.string.item_description_misty_seed), R.drawable.item_misty_seed, false).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.incrementLevel(Attributes.SPDEF, 1);}, 0, "item", true)).setPointValue(3);
            case PSYCHIC_SEED -> new Item(ir, ctx.getResources().getString(R.string.item_name_psychic_seed), ctx.getResources().getString(R.string.item_description_psychic_seed), R.drawable.item_psychic_seed, false).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.GRASSY_TERRAIN) player.incrementLevel(Attributes.SPDEF, 1);}, 0, "item", true)).setPointValue(3);
            case FLAME_ORB -> new Item(ir, ctx.getResources().getString(R.string.item_name_flame_orb), ctx.getResources().getString(R.string.item_description_flame_orb), R.drawable.item_flame_orb, false).addEffect(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.BURNED, false), 0, "item", false)).setPointValue(1);
            case TOXIC_ORB -> new Item(ir, ctx.getResources().getString(R.string.item_name_toxic_orb), ctx.getResources().getString(R.string.item_description_toxic_orb), R.drawable.item_toxic_orb, false).addEffect(new Effect((game, player, opponent) -> player.setStatusCondition(StatusCondition.POISONED, false), 0, "item", false)).setPointValue(1);
            case LIFE_ORB -> new Item(ir, ctx.getResources().getString(R.string.item_name_life_orb), ctx.getResources().getString(R.string.item_description_life_orb), R.drawable.item_life_orb, false).addEffect(new Effect((game, player, opponent) -> {
                player.incrementLevel(Attributes.ATK, 1);
                player.incrementLevel(Attributes.SPATK, 1);
            }, 0, "item", true)).addEffect(new Effect((game, player, opponent) -> {
                player.incrementBreakPoints(1);
            }, 0, "item", false)).setPointValue(5);
            case IRON_BALL -> new Item(ir, ctx.getResources().getString(R.string.item_name_iron_ball), ctx.getResources().getString(R.string.item_description_iron_ball), R.drawable.item_iron_ball, false).addEffect(new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPD, -4), 0, "item", true)).setPointValue(10);
            case AMULET_COIN -> new Item(ir, ctx.getResources().getString(R.string.item_name_amulet_coin), ctx.getResources().getString(R.string.item_description_amulet_coin), R.drawable.item_amulet_coin, false).addEffect(new Effect((game, player, opponent) -> game.setPrizePot(game.getPrizePot() * 2), 0, "item", false)).setPointValue(2);
            case COVERT_CLOAK -> new Item(ir, ctx.getResources().getString(R.string.item_name_covert_cloak), ctx.getResources().getString(R.string.item_description_covert_cloak), R.drawable.item_covert_cloak, false).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("ability", player), 4, "item", true)).setPointValue(1);
            case EJECT_BUTTON -> new Item(ir, ctx.getResources().getString(R.string.item_name_eject_button), ctx.getResources().getString(R.string.item_description_eject_button), R.drawable.item_eject_button, false).addEffect(new Effect((game, player, opponent) -> {
                Card temp = player.getPlayedCard();
                player.setPlayedCard(opponent.getPlayedCard());
                opponent.setPlayedCard(temp);
            }, 3, "item", false)).setPointValue(3);
            case EXPERT_BELT -> new Item(ir, ctx.getResources().getString(R.string.item_name_expert_belt), ctx.getResources().getString(R.string.item_description_expert_belt), R.drawable.item_expert_belt, false).addEffect(new Effect((game, player, opponent) -> player.setSupereffectiveMultiplier(player.getSupereffectiveMultiplier() * 2), 0, "item", false)).setPointValue(3);
            case LOADED_DICE -> new Item(ir, ctx.getResources().getString(R.string.item_name_loaded_dice), ctx.getResources().getString(R.string.item_description_loaded_dice), R.drawable.item_loaded_dice, false).addEffect(new Effect((game, player, opponent) -> game.randomizeAttribute(game.getActivePlayer()), 0, "item", false)).setPointValue(2);
            case BATTLE_PASS -> new Item(ir, ctx.getResources().getString(R.string.item_name_battle_pass), ctx.getResources().getString(R.string.item_description_battle_pass), R.drawable.item_battle_pass, false).addEffect(new Effect((game, player, opponent) -> player.setSpeed(1), 0, "item", false)).setPointValue(10);
            case EVIOLITE -> new Item(ir, ctx.getResources().getString(R.string.item_name_eviolite), ctx.getResources().getString(R.string.item_description_eviolite), R.drawable.item_eviolite, false).addEffect(new Effect((game, player, opponent) -> {
                if (player.getPlayedCard().checkTag(CardTags.CAN_EVOLVE)) {
                    player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) + 1);
                    player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) + 1);
                }
            }, 0, "item", true)).setPointValue(4);
            case GRIP_CLAW -> new Item(ir, ctx.getResources().getString(R.string.item_name_grip_claw), ctx.getResources().getString(R.string.item_description_grip_claw), R.drawable.item_grip_claw, false).addEffect(new Effect((game, player, opponent) -> player.addCard(player.getPlayedCard(), 0), 0, "item", false)).setPointValue(3);
            case MIRROR_HERB -> new Item(ir, ctx.getResources().getString(R.string.item_name_mirror_herb), ctx.getResources().getString(R.string.item_description_mirror_herb), R.drawable.item_mirror_herb, false).addEffect(new Effect((game, player, opponent) -> {
                player.setLevel(Attributes.ATK, opponent.getLevel(Attributes.ATK));
                player.setLevel(Attributes.DEF, opponent.getLevel(Attributes.DEF));
                player.setLevel(Attributes.SPATK, opponent.getLevel(Attributes.SPATK));
                player.setLevel(Attributes.SPDEF, opponent.getLevel(Attributes.SPDEF));
                player.setLevel(Attributes.ACC, opponent.getLevel(Attributes.ACC));
                player.setLevel(Attributes.EV, opponent.getLevel(Attributes.EV));
                player.setLevel(Attributes.SPD, opponent.getLevel(Attributes.SPD));
            }, -1, "item", false)).setPointValue(2);
            case SCOPE_LENS -> new Item(ir, ctx.getResources().getString(R.string.item_name_scope_lens), ctx.getResources().getString(R.string.item_description_scope_lens), R.drawable.item_scope_lens, false).addEffect(new Effect((game, player, opponent) -> player.setCritRate(player.getCritRate() + 1), 0, "item", false)).setPointValue(3);
            case ROCKY_HELMET -> new Item(ir, ctx.getResources().getString(R.string.item_name_rocky_helmet), ctx.getResources().getString(R.string.item_description_rocky_helmet), R.drawable.item_rocky_helmet, false).addEffect(new Effect((game, player, opponent) -> opponent.incrementBreakPoints(1), 0, "item", false)).setPointValue(3);
            case METRONOME -> new Item(ir, ctx.getResources().getString(R.string.item_name_metronome), ctx.getResources().getString(R.string.item_description_metronome), R.drawable.item_metronome, false).addEffect(new Effect((game, player, opponent) -> {if (game.getPreviousAttribute() != null) player.incrementLevel(game.getPreviousAttribute(), 1);}, 0, "item", true)).setPointValue(4);
            case FOCUS_SASH -> new Item(ir, ctx.getResources().getString(R.string.item_name_focus_sash), ctx.getResources().getString(R.string.item_description_focus_sash), R.drawable.item_focus_sash, false).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("break", player), 1, "item", false)).setPointValue(1);
            case AIR_BALLOON -> new Item(ir, ctx.getResources().getString(R.string.item_name_air_balloon), ctx.getResources().getString(R.string.item_description_air_balloon), R.drawable.item_air_balloon, false).addEffect(new Effect((game, player, opponent) -> game.removeEffectFromQueue("field", player), 1, "item", true)).setPointValue(1);
            case ROOM_SERVICE -> new Item(ir, ctx.getResources().getString(R.string.item_name_room_service), ctx.getResources().getString(R.string.item_description_room_service), R.drawable.item_room_service, false).addEffect(new Effect((game, player, opponent) -> {if (game.getFieldEffect() == FieldEffect.TRICK_ROOM) player.incrementLevel(Attributes.SPD, -2);}, 0, "item", true)).setPointValue(5);
            case WEAKNESS_POLICY -> new Item(ir, ctx.getResources().getString(R.string.item_name_weakness_policy), ctx.getResources().getString(R.string.item_description_weakness_policy), R.drawable.item_weakness_policy, false).addEffect(new Effect((game, player, opponent) -> {if (opponent.checkType(opponent.getPlayedCard(), player.getPlayedCard().getWeakness())) {player.incrementLevel(Attributes.ATK, 1); player.incrementLevel(Attributes.SPATK, 1); player.incrementLevel(Attributes.ACC, 1);}}, 0, "item", false)).setPointValue(4);
            case QUICK_CLAW -> new Item(ir, ctx.getResources().getString(R.string.item_name_quick_claw), ctx.getResources().getString(R.string.item_description_quick_claw), R.drawable.item_quick_claw, false).addEffect(new Effect((game, player, opponent) -> {
                Random rand = new Random();
                if (rand.nextInt(10) == 0) player.setSpeed(1);
            }, 0, "item", false)).setPointValue(3);
            case BLACK_SLUDGE -> new Item(ir, ctx.getResources().getString(R.string.item_name_black_sludge), ctx.getResources().getString(R.string.item_description_black_sludge), R.drawable.item_black_sludge, false).addEffect(new Effect((game, player, opponent) -> {
                if (player.checkType(player.getPlayedCard(), Type.DARK)) {
                    player.addDelayedEffect(new Effect((game1, player1, opponent1) -> player.incrementBreakPoints(-1), 0, "item", false), 3, R.string.continuous_effect_description_leftovers);
                }
                else {
                    player.addDelayedEffect(new Effect((game1, player1, opponent1) -> player.incrementBreakPoints(1), 0, "item", false), 3, R.string.continuous_effect_description_black_sludge);
                }
            }, 0, "item", false)).setPointValue(2);
            case LEFTOVERS -> new Item(ir, ctx.getResources().getString(R.string.item_name_leftovers), ctx.getResources().getString(R.string.item_description_leftovers), R.drawable.item_leftovers, false).addEffect(new Effect((game, player, opponent) -> {
                player.addDelayedEffect(new Effect((game1, player1, opponent1) -> player.incrementBreakPoints(-1), 0, "item", false), 3, R.string.continuous_effect_description_leftovers);
            }, 0, "item", false)).setPointValue(2);
            case ITEM_PRINTER -> new Item(ir, ctx.getResources().getString(R.string.item_name_item_printer), ctx.getResources().getString(R.string.item_description_item_printer), R.drawable.item_item_printer, true).addEffect(new Effect((game, player, opponent) -> {
                player.addItem(game.generateItem(player));
                player.addItem(game.generateItem(player));
            }, 0, "item", false)).setPointValue(4);
            case CHERI_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_cheri_berry), ctx.getResources().getString(R.string.item_description_cheri_berry), R.drawable.item_cheri_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.CHERI), 0, "item", false)).setPointValue(1);
            case CHESTO_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_chesto_berry), ctx.getResources().getString(R.string.item_description_chesto_berry), R.drawable.item_chesto_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.CHESTO), 0, "item", false)).setPointValue(1);
            case RAWST_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_rawst_berry), ctx.getResources().getString(R.string.item_description_rawst_berry), R.drawable.item_rawst_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.RAWST), 0, "item", false)).setPointValue(1);
            case ASPEAR_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_aspear_berry), ctx.getResources().getString(R.string.item_description_aspear_berry), R.drawable.item_aspear_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.ASPEAR), 0, "item", false)).setPointValue(1);
            case PECHA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_pecha_berry), ctx.getResources().getString(R.string.item_description_pecha_berry), R.drawable.item_pecha_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.PECHA), 0, "item", false)).setPointValue(1);
            case PERSIM_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_persim_berry), ctx.getResources().getString(R.string.item_description_persim_berry), R.drawable.item_persim_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.PERSIM), 0, "item", false)).setPointValue(1);
            case LUM_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_lum_berry), ctx.getResources().getString(R.string.item_description_lum_berry), R.drawable.item_lum_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.LUM), 0, "item", false)).setPointValue(2);
            case ORAN_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_oran_berry), ctx.getResources().getString(R.string.item_description_oran_berry), R.drawable.item_oran_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.ORAN), 0, "item", false)).setPointValue(1);
            case SITRUS_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_sitrus_berry), ctx.getResources().getString(R.string.item_description_sitrus_berry), R.drawable.item_sitrus_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.SITRUS), 0, "item", false)).setPointValue(2);
            case CHILAN_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_chilan_berry), ctx.getResources().getString(R.string.item_description_chilan_berry), R.drawable.item_chilan_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.CHILAN), 0, "item", false)).setPointValue(3);
            case OCCA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_occa_berry), ctx.getResources().getString(R.string.item_description_occa_berry), R.drawable.item_occa_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.OCCA), 0, "item", false)).setPointValue(3);
            case PASSHO_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_passho_berry), ctx.getResources().getString(R.string.item_description_passho_berry), R.drawable.item_passho_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.PASSHO), 0, "item", false)).setPointValue(3);
            case RINDO_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_rindo_berry), ctx.getResources().getString(R.string.item_description_rindo_berry), R.drawable.item_rindo_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.RINDO), 0, "item", false)).setPointValue(3);
            case WACAN_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_wacan_berry), ctx.getResources().getString(R.string.item_description_wacan_berry), R.drawable.item_wacan_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.WACAN), 0, "item", false)).setPointValue(3);
            case YACHE_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_yache_berry), ctx.getResources().getString(R.string.item_description_yache_berry), R.drawable.item_yache_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.YACHE), 0, "item", false)).setPointValue(3);
            case PAYAPA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_payapa_berry), ctx.getResources().getString(R.string.item_description_payapa_berry), R.drawable.item_payapa_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.PAYAPA), 0, "item", false)).setPointValue(3);
            case COLBUR_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_colbur_berry), ctx.getResources().getString(R.string.item_description_colbur_berry), R.drawable.item_colbur_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.COLBUR), 0, "item", false)).setPointValue(3);
            case CHOPLE_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_chople_berry), ctx.getResources().getString(R.string.item_description_chople_berry), R.drawable.item_chople_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.CHOPLE), 0, "item", false)).setPointValue(3);
            case SHUCA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_shuca_berry), ctx.getResources().getString(R.string.item_description_shuca_berry), R.drawable.item_shuca_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.SHUCA), 0, "item", false)).setPointValue(3);
            case BABIRI_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_babiri_berry), ctx.getResources().getString(R.string.item_description_babiri_berry), R.drawable.item_babiri_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.BABIRI), 0, "item", false)).setPointValue(3);
            case HABAN_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_haban_berry), ctx.getResources().getString(R.string.item_description_haban_berry), R.drawable.item_haban_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.HABAN), 0, "item", false)).setPointValue(3);
            case ROSELI_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_roseli_berry), ctx.getResources().getString(R.string.item_description_roseli_berry), R.drawable.item_roseli_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.ROSELI), 0, "item", false)).setPointValue(3);
            case LIECHI_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_liechi_berry), ctx.getResources().getString(R.string.item_description_liechi_berry), R.drawable.item_liechi_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.LIECHI), 0, "item", false)).setPointValue(3);
            case GANLON_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_ganlon_berry), ctx.getResources().getString(R.string.item_description_ganlon_berry), R.drawable.item_ganlon_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.GANLON), 0, "item", false)).setPointValue(3);
            case SALAC_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_salac_berry), ctx.getResources().getString(R.string.item_description_salac_berry), R.drawable.item_salac_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.SALAC), 0, "item", false)).setPointValue(3);
            case PETAYA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_petaya_berry), ctx.getResources().getString(R.string.item_description_petaya_berry), R.drawable.item_petaya_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.PETAYA), 0, "item", false)).setPointValue(3);
            case APICOT_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_apicot_berry), ctx.getResources().getString(R.string.item_description_apicot_berry), R.drawable.item_apicot_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.APICOT), 0, "item", false)).setPointValue(3);
            case MICLE_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_micle_berry), ctx.getResources().getString(R.string.item_description_micle_berry), R.drawable.item_micle_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.MICLE), 0, "item", false)).setPointValue(3);
            case CUSTAP_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_custap_berry), ctx.getResources().getString(R.string.item_description_custap_berry), R.drawable.item_custap_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.CUSTAP), 0, "item", false)).setPointValue(3);
            case STARF_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_starf_berry), ctx.getResources().getString(R.string.item_description_starf_berry), R.drawable.item_starf_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.STARF), 0, "item", false)).setPointValue(2);
            case LANSAT_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_lansat_berry), ctx.getResources().getString(R.string.item_description_lansat_berry), R.drawable.item_lansat_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.LANSAT), 0, "item", false)).setPointValue(3);
            case ENIGMA_BERRY -> new Item(ir, ctx.getResources().getString(R.string.item_name_enigma_berry), ctx.getResources().getString(R.string.item_description_enigma_berry), R.drawable.item_enigma_berry, false).addEffect(new Effect((game, player, opponent) -> player.addBerry(Berry.ENIGMA), 0, "item", false)).setPointValue(3);
            case BERRY_POT -> new Item(ir, ctx.getResources().getString(R.string.item_name_berry_pot), ctx.getResources().getString(R.string.item_description_berry_pot), R.drawable.item_berry_pot, true).addEffect(new Effect((game, player, opponent) -> {
                player.addBerry(Berry.generateBerry());
                player.addBerry(Berry.generateBerry());
                player.addBerry(Berry.generateBerry());
            }, 0, "item", false)).setPointValue(5);
            default -> new Item(ir, "", "", R.drawable.item_enigma_berry, false);
        };
    }
}
