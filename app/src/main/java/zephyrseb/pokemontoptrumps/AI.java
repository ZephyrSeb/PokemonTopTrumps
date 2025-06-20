package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.List;

public class AI {
    private boolean canMegaEvolve;
    private boolean hasPriority;
    private Card card;
    private boolean preferredMegaEvolve = false;
    private int preferredItem = -1;
    private Attributes preferredStat = null;
    private Player player;
    private Player opponent;
    private Game game;
    public AI(Game g, Card c, boolean e, boolean p, Player p1, Player p2) {
        game = g;
        card = c;
        canMegaEvolve = e;
        hasPriority = p;
        player = p2;
        opponent = p1;
    }

    public void chooseAttack() {
        AIDecision[] bestDecision = {null, null, null, null};
        bestDecision[0] = evaluateAttribute(Attributes.ATK);
        bestDecision[1] = evaluateAttribute(Attributes.SPATK);
        bestDecision[2] = evaluateAttribute(Attributes.ACC);
        bestDecision[3] = evaluateAttribute(Attributes.SPD);
        if (!player.getAvailableAttribute(Attributes.ATK)) bestDecision[0] = null;
        if (!player.getAvailableAttribute(Attributes.SPATK)) bestDecision[1] = null;
        if (!player.getAvailableAttribute(Attributes.ACC)) bestDecision[2] = null;
        if (!player.getAvailableAttribute(Attributes.SPD)) bestDecision[3] = null;
        int maxIndex = 0;
        if (bestDecision[0] == null) maxIndex = 1;
        if (bestDecision[0] == null && bestDecision[1] == null) maxIndex = 2;
        if (bestDecision[0] == null && bestDecision[1] == null && bestDecision[2] == null) maxIndex = 3;
        if (bestDecision[0] == null && bestDecision[1] == null && bestDecision[2] == null && bestDecision[3] == null) {
            maxIndex = 0;
        }
        else {
            for (int i = 0; i < 4; i++) {
                if (bestDecision[i] != null) {
                    if (bestDecision[i].value > bestDecision[maxIndex].value) maxIndex = i;
                }
            }
        }
        preferredStat = bestDecision[maxIndex].stat;
        preferredItem = bestDecision[maxIndex].item;
        preferredMegaEvolve = bestDecision[maxIndex].megaEvolve;
    }

    public void chooseDefend() {
        AIDecision[] bestDecision = {null, null, null, null};
        bestDecision[0] = evaluateAttribute(Attributes.DEF);
        bestDecision[1] = evaluateAttribute(Attributes.SPDEF);
        bestDecision[2] = evaluateAttribute(Attributes.EV);
        bestDecision[3] = evaluateAttribute(Attributes.SPD);
        preferredStat = null;
        preferredMegaEvolve = modeBoolean(new boolean[]{bestDecision[0].megaEvolve, bestDecision[1].megaEvolve, bestDecision[2].megaEvolve, bestDecision[3].megaEvolve});
        preferredItem = modeInt(new int[]{bestDecision[0].item, bestDecision[1].item, bestDecision[2].item, bestDecision[3].item});
    }

    public AIDecision evaluateAttribute(Attributes a) {
        game.createQueue(opponent, player, false);
        game.applyPassiveEffect();
        boolean megaEvolve = false;
        int stat = (int)(card.getAttribute(a) * applyLevel(player.getLevel(a)));
        int bestItem = -1;
        for (int i = 0; i < 6; i++) {
            if (player.getItem(i) != null) {
                player.setPlayedItem(game.getContext(), player.getItem(i));
                game.createQueue(opponent, player, false);
                game.applyPassiveEffect();
                int itemModifier = getItemModifier(player.getItem(i));
                if (card.getAbility() == Ability.FLING) {
                    itemModifier = 10 * ItemRegistry.initItem(game.getContext(), player.getItem(i)).getPointValue();
                }
                if (((int)(card.getAttribute(a) * applyLevel(player.getLevel(a)))) + itemModifier > stat) {
                    stat = (int)(card.getAttribute(a) * applyLevel(player.getLevel(a)) + itemModifier);
                    bestItem = i;
                }
            }
        }
        if (bestItem >-1) player.setPlayedItem(game.getContext(), player.getItem(bestItem));
        game.createQueue(opponent, player, false);
        game.applyPassiveEffect();
        if (card.canMegaEvolve() && canMegaEvolve && (hasPriority || opponent.getPoints() > 4)) {
            if (card.megaEvolve().getAttribute(a) > stat) {
                stat = (int) (card.megaEvolve().getAttribute(a) * applyLevel(player.getLevel(a)));
            }
        }
        return new AIDecision(a, stat, bestItem, megaEvolve);
    }

    public ItemRegistry getPreferredItem() {
        if (preferredItem == -1) return null;
        else return player.getItem(preferredItem);
    }
    public Attributes getPreferredStat() {
        return preferredStat;
    }
    public boolean getPreferredME() {
        return preferredMegaEvolve;
    }

    private double applyLevel(int i) {
        return switch (i) {
            case -6 -> 0.4d;
            case -5 -> 0.444d;
            case -4 -> 0.5d;
            case -3 -> 0.571d;
            case -2 -> 0.667d;
            case -1 -> 0.8d;
            case 1 -> 1.25d;
            case 2 -> 1.5d;
            case 3 -> 1.75d;
            case 4 -> 2d;
            case 5 -> 2.25d;
            case 6 -> 2.5d;
            default -> 1d;
        };
    }

    private boolean modeBoolean(boolean[] b) {
        int _true = 0;
        int _false = 0;
        for (boolean entry : b) {
            if (entry) _true++; else _false++;
        }
        return _true >= _false;
    }

    private int modeInt(int[] i) {
        int largestCohort = 0;
        List<Integer> mode = new ArrayList<>();
        for (int entry : i) {
            if (count(i, entry) > largestCohort) {
                largestCohort = count(i, entry);
            }
        }
        for (int entry : i) {
            if (count(i, entry) == largestCohort && !mode.contains(entry)) {
                mode.add(entry);
            }
        }
        return mode.get(0);
    }

    private int count(int[] list, int i) {
        int count = 0;
        for (int entry : list) {
            if (entry == i) count++;
        }
        return count;
    }

    private int getItemModifier(ItemRegistry i) {
        return switch(i) {
            case ItemRegistry.POTION -> player.getBreakPoints();
            case ItemRegistry.FULL_HEAL -> {if (player.getStatusCondition() == StatusCondition.NONE) yield 0; else yield 1;}
            case ItemRegistry.FULL_RESTORE -> {if (player.getStatusCondition() == StatusCondition.NONE) yield player.getBreakPoints(); else yield 10;}
            case ItemRegistry.POKE_BALL -> 1;
            case ItemRegistry.GREAT_BALL -> 2;
            case ItemRegistry.ULTRA_BALL -> 3;
            case ItemRegistry.MASTER_BALL -> 4;
            case ItemRegistry.NET_BALL -> 1;
            case ItemRegistry.DIVE_BALL -> 1;
            case ItemRegistry.MOON_BALL -> 1;
            case ItemRegistry.FAST_BALL -> 1;
            case ItemRegistry.HEAVY_BALL -> 1;
            case ItemRegistry.REPEAT_BALL -> 1;
            case ItemRegistry.BEAST_BALL -> 2;
            case ItemRegistry.SILK_SCARF -> {if (card.getType() == Type.NORMAL) yield 20; else yield 0;}
            case ItemRegistry.CHARCOAL -> {if (card.getType() == Type.FIRE) yield 20; else yield 0;}
            case ItemRegistry.MYSTIC_WATER -> {if (card.getType() == Type.WATER) yield 20; else yield 0;}
            case ItemRegistry.MIRACLE_SEED -> {if (card.getType() == Type.GRASS) yield 20; else yield 0;}
            case ItemRegistry.NEVER_MELT_ICE -> {if (card.getType() == Type.ICE) yield 20; else yield 0;}
            case ItemRegistry.MAGNET -> {if (card.getType() == Type.ELECTRIC) yield 20; else yield 0;}
            case ItemRegistry.TWISTED_SPOON -> {if (card.getType() == Type.PSYCHIC) yield 20; else yield 0;}
            case ItemRegistry.BLACK_GLASSES -> {if (card.getType() == Type.DARK) yield 20; else yield 0;}
            case ItemRegistry.BLACK_BELT -> {if (card.getType() == Type.FIGHTING) yield 20; else yield 0;}
            case ItemRegistry.DRAGON_FANG -> {if (card.getType() == Type.DRAGON) yield 20; else yield 0;}
            case ItemRegistry.SOFT_SAND -> {if (card.getType() == Type.GROUND) yield 20; else yield 0;}
            case ItemRegistry.METAL_COAT -> {if (card.getType() == Type.STEEL) yield 20; else yield 0;}
            case ItemRegistry.FAIRY_FEATHER -> {if (card.getType() == Type.FAIRY) yield 20; else yield 0;}
            case ItemRegistry.NORMAL_TERA_SHARD -> 1;
            case ItemRegistry.GRASS_TERA_SHARD -> 1;
            case ItemRegistry.FIRE_TERA_SHARD -> 1;
            case ItemRegistry.WATER_TERA_SHARD -> 1;
            case ItemRegistry.ELECTRIC_TERA_SHARD -> 1;
            case ItemRegistry.ICE_TERA_SHARD -> 1;
            case ItemRegistry.PSYCHIC_TERA_SHARD -> 1;
            case ItemRegistry.DARK_TERA_SHARD -> 1;
            case ItemRegistry.FIGHTING_TERA_SHARD -> 1;
            case ItemRegistry.GROUND_TERA_SHARD -> 1;
            case ItemRegistry.STEEL_TERA_SHARD-> 1;
            case ItemRegistry.DRAGON_TERA_SHARD -> 1;
            case ItemRegistry.FAIRY_TERA_SHARD -> 1;
            case ItemRegistry.ICY_ROCK -> {if (card.getAbility() == Ability.SNOW_WARNING || game.getFieldEffect() == FieldEffect.SNOW) yield 0; else yield 1;}
            case ItemRegistry.HEAT_ROCK -> {if (card.getAbility() == Ability.DROUGHT || game.getFieldEffect() == FieldEffect.SUN) yield 0; else yield 1;}
            case ItemRegistry.DAMP_ROCK -> {if (card.getAbility() == Ability.DRIZZLE || game.getFieldEffect() == FieldEffect.RAIN) yield 0; else yield 1;}
            case ItemRegistry.SMOOTH_ROCK -> {if (card.getAbility() == Ability.SAND_STREAM || game.getFieldEffect() == FieldEffect.SANDSTORM) yield 0; else yield 1;}
            case ItemRegistry.FLAME_ORB -> {if (card.getAbility() == Ability.FACADE || card.getAbility() == Ability.FLASH_FIRE) yield 10; else yield 0;}
            case ItemRegistry.TOXIC_ORB -> {if (card.getAbility() == Ability.FACADE || card.getAbility() == Ability.TOXIC_BOOST) yield 10; else yield 0;}
            case ItemRegistry.AMULET_COIN -> 1;
            case ItemRegistry.COVERT_CLOAK -> {if (card.getAbility() == Ability.TRUANT) yield 10; else yield 0;}
            case ItemRegistry.EJECT_BUTTON -> 1;
            case ItemRegistry.EXPERT_BELT -> {if (card.getAbility() == Ability.HIDDEN_POWER) yield 50; else yield 1;}
            case ItemRegistry.LOADED_DICE -> {if (hasPriority) yield 0; else yield 10;}
            case ItemRegistry.BATTLE_PASS -> {if (hasPriority) yield 0; else yield 10;}
            case ItemRegistry.GRIP_CLAW -> 1;
            case ItemRegistry.MIRROR_HERB -> 1;
            case ItemRegistry.SCOPE_LENS -> 1;
            case ItemRegistry.ROCKY_HELMET -> 1;
            case ItemRegistry.FOCUS_SASH -> player.getBreakPoints();
            case ItemRegistry.WEAKNESS_POLICY -> 1;
            case ItemRegistry.QUICK_CLAW -> 2;
            case ItemRegistry.BLACK_SLUDGE -> {if (card.getType() == Type.DARK) yield 1; else yield 0;}
            case ItemRegistry.LEFTOVERS -> 2;
            case ItemRegistry.ITEM_PRINTER -> 5;
            case ItemRegistry.CHERI_BERRY -> 1;
            case ItemRegistry.CHESTO_BERRY -> 1;
            case ItemRegistry.RAWST_BERRY -> 1;
            case ItemRegistry.ASPEAR_BERRY -> 1;
            case ItemRegistry.PECHA_BERRY -> 1;
            case ItemRegistry.PERSIM_BERRY -> 1;
            case ItemRegistry.LUM_BERRY -> 1;
            case ItemRegistry.ORAN_BERRY -> 1;
            case ItemRegistry.SITRUS_BERRY -> 1;
            case ItemRegistry.CHILAN_BERRY -> 1;
            case ItemRegistry.OCCA_BERRY -> 1;
            case ItemRegistry.PASSHO_BERRY -> 1;
            case ItemRegistry.RINDO_BERRY -> 1;
            case ItemRegistry.WACAN_BERRY -> 1;
            case ItemRegistry.YACHE_BERRY -> 1;
            case ItemRegistry.PAYAPA_BERRY -> 1;
            case ItemRegistry.COLBUR_BERRY -> 1;
            case ItemRegistry.CHOPLE_BERRY -> 1;
            case ItemRegistry.SHUCA_BERRY -> 1;
            case ItemRegistry.BABIRI_BERRY -> 1;
            case ItemRegistry.HABAN_BERRY -> 1;
            case ItemRegistry.ROSELI_BERRY -> 1;
            case ItemRegistry.LIECHI_BERRY -> 1;
            case ItemRegistry.GANLON_BERRY -> 1;
            case ItemRegistry.SALAC_BERRY -> 1;
            case ItemRegistry.PETAYA_BERRY -> 1;
            case ItemRegistry.APICOT_BERRY -> 1;
            case ItemRegistry.MICLE_BERRY -> 1;
            case ItemRegistry.CUSTAP_BERRY -> 1;
            case ItemRegistry.STARF_BERRY -> 1;
            case ItemRegistry.LANSAT_BERRY -> 1;
            case ItemRegistry.ENIGMA_BERRY -> 1;
            case ItemRegistry.BERRY_POT -> 3;
            default -> 0;
        };
    }
}

class AIDecision {
    boolean megaEvolve;
    int value;
    int item;
    Attributes stat;
    AIDecision(Attributes s, int v, int i, boolean b) {
        stat = s;
        value = v;
        item = i;
        megaEvolve = b;
    }
}
