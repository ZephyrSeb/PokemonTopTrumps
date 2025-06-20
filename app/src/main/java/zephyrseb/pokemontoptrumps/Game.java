package zephyrseb.pokemontoptrumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

import zephyrseb.pokemontoptrumps.Screens.AbstractGameScreen;
import zephyrseb.pokemontoptrumps.comparators.EffectComparator;

public class Game {
    AbstractGameScreen game;
    private int turn;
    private PriorityQueue<EffectAction> effectQueue = new PriorityQueue<>(new EffectComparator());
    private FieldEffect fieldEffect = FieldEffect.NONE;
    private Attributes chosenAttribute = null;
    private Attributes previousAttribute = null;
    private Map<Attributes, Boolean> usedAttribute = new HashMap<>();
    private Attributes[] orderedAttributes = {Attributes.ATK, Attributes.SPATK, Attributes.ACC, Attributes.SPD};
    private boolean goalLarge = true;
    private Player activePlayer;
    private final int startingPlayer;
    private final String mode;
    private final Map<Player, Deck> decks;
    private int prizePot = 1;

    public Game(AbstractGameScreen ags, String m, Map<Player, Deck> d) {
        game = ags;
        mode = m;
        Random rand = new Random();
        turn = rand.nextInt(2);
        startingPlayer = turn;
        decks = d;
        usedAttribute.put(Attributes.HP, false);
        usedAttribute.put(Attributes.ATK, false);
        usedAttribute.put(Attributes.DEF, false);
        usedAttribute.put(Attributes.SPATK, false);
        usedAttribute.put(Attributes.SPDEF, false);
        usedAttribute.put(Attributes.ACC, false);
        usedAttribute.put(Attributes.EV, false);
        usedAttribute.put(Attributes.SPD, false);
    }

    public void createQueue(Player p1, Player p2, boolean advance) {
        effectQueue.clear();
        p1.resetLevels();
        p2.resetLevels();
        //Charge effects
        Effect chargeEffect = new Effect((game, player, opponent) -> player.incrementLevel(Attributes.SPATK, 1), 0, "charge", true);
        if (p1.getCharge() && p1.checkType(p1.getPlayedCard(), Type.ELECTRIC)) {
            if (advance) p1.setUsedCharge(true);
            effectQueue.add(new EffectAction(chargeEffect, p1, null, p1.getPlayedCard().getAttribute(Attributes.SPD)));
        }
        if (p2.getCharge() && p2.checkType(p2.getPlayedCard(), Type.ELECTRIC)) {
            if (advance) p2.setUsedCharge(true);
            effectQueue.add(new EffectAction(chargeEffect, p2, null, p2.getPlayedCard().getAttribute(Attributes.SPD)));
        }

        //Applies ability and item modifiers
        if (p1.getPlayedItem() != null) {
            for (Effect e : p1.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p2.getPlayedItem() != null) {
            for (Effect e : p2.getPlayedItem().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p1.getPlayedCard().getAbility() != null) {
            for (Effect e : p1.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p1, p2, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (p2.getPlayedCard().getAbility() != null) {
            for (Effect e : p2.getPlayedCard().getAbility().getEffects()) {
                effectQueue.add(new EffectAction(e, p2, p1, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (getFieldEffect() != FieldEffect.NONE) {
            for (Effect e : FieldEffect.getEffects(getFieldEffect())) {
                effectQueue.add(new EffectAction(e, p1, null, 0));
                effectQueue.add(new EffectAction(e, p2, null, 0));
            }
        }
        if (p1.getStatusCondition() != StatusCondition.NONE) {
            for (Effect e : StatusCondition.getEffects(p1.getStatusCondition())) {
                effectQueue.add(new EffectAction(e, p1, null, 0));
            }
        }
        if (p2.getStatusCondition() != StatusCondition.NONE) {
            for (Effect e : StatusCondition.getEffects(p2.getStatusCondition())) {
                effectQueue.add(new EffectAction(e, p2, null, 0));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (Effect e : Berry.generateEffect(p1.getBerry(i))) {
                effectQueue.add(new EffectAction(e, p1, p2, 0));
            }
            for (Effect e : Berry.generateEffect(p2.getBerry(i))) {
                effectQueue.add(new EffectAction(e, p2, p1, 0));
            }
        }
        if (!p1.getDelayedEffects().isEmpty()) {
            for (ContinuousEffect e : p1.getDelayedEffects()) {
                effectQueue.add(new EffectAction(e.getEffect(), p1, null, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (!p2.getDelayedEffects().isEmpty()) {
            for (ContinuousEffect e : p2.getDelayedEffects()) {
                effectQueue.add(new EffectAction(e.getEffect(), p2, null, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            }
        }
        if (advance) {
            effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
                if (player.getPlayedCard().getType() == player.getCurrentAura().getType()) {
                    player.setSupereffectiveMultiplier(player.getSupereffectiveMultiplier() * 1.5d);
                }
            }, 0, "aura", false), p1, null, p1.getPlayedCard().getAttribute(Attributes.SPD)));
            effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
                if (player.getPlayedCard().getType() == player.getCurrentAura().getType()) {
                    player.setSupereffectiveMultiplier(player.getSupereffectiveMultiplier() * 1.5d);
                }
            }, 0, "aura", false), p2, null, p2.getPlayedCard().getAttribute(Attributes.SPD)));
            Random rand = new Random();
            if (rand.nextInt(10) < p1.getBreakPoints())
                effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
                    player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 4);
                    player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) - 4);
                    player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 4);
                    player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) - 4);
                    player.setLevel(Attributes.ACC, player.getLevel(Attributes.ACC) - 4);
                    player.setLevel(Attributes.EV, player.getLevel(Attributes.EV) - 4);
                    player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) - 4);
                    player.setBreakPoints(0);
                    player.setBroken(true);
                }, 0, "break", false), p1, null, 0));
            if (rand.nextInt(10) < p2.getBreakPoints())
                effectQueue.add(new EffectAction(new Effect((game, player, opponent) -> {
                    player.setLevel(Attributes.ATK, player.getLevel(Attributes.ATK) - 4);
                    player.setLevel(Attributes.DEF, player.getLevel(Attributes.DEF) - 4);
                    player.setLevel(Attributes.SPATK, player.getLevel(Attributes.SPATK) - 4);
                    player.setLevel(Attributes.SPDEF, player.getLevel(Attributes.SPDEF) - 4);
                    player.setLevel(Attributes.ACC, player.getLevel(Attributes.ACC) - 4);
                    player.setLevel(Attributes.EV, player.getLevel(Attributes.EV) - 4);
                    player.setLevel(Attributes.SPD, player.getLevel(Attributes.SPD) - 4);
                    player.setBreakPoints(0);
                    player.setBroken(true);
                }, 0, "break", false), p2, null, 0));
            p1.clearDelayedEffects();
            p2.clearDelayedEffects();
        }
        if (!advance) {
            effectQueue.removeIf(e -> !e.getEffect().isAdvance());
        }
        game.update(p1, p2);
    }

    public void effectQueueAdd(EffectAction e) {
        effectQueue.add(e);
    }

    public void removeEffectFromQueue(String s) {
        effectQueue.removeIf(e -> Objects.equals(e.getEffect().getType(), s));
    }

    public void removeEffectFromQueue(String s, Player p) {
        effectQueue.removeIf(e -> Objects.equals(e.getEffect().getType(), s) && e.getPlayer() == p);
    }

    public void removeSoundEffectFromQueue() {
        effectQueue.removeIf(e -> e.getEffect().isSound());
    }

    public List<EffectAction> getEffectsFromQueue(String s, Player p) {
        List<EffectAction> returnList = new ArrayList<>();
        for (EffectAction effect : effectQueue) {
            if (effect.getPlayer() == p && Objects.equals(effect.getEffect().getType(), s)) {
                returnList.add(effect);
            }
        }
        return returnList;
    }

    public void applyPassiveEffect() {
        while (!effectQueue.isEmpty()) {
            EffectAction e = effectQueue.poll();
            e.applyEffect(this);
        }
    }

    public void reorderAttributes() {
        Random rand = new Random();
        Attributes[] newOrder = {Attributes.ATK, Attributes.SPATK, Attributes.ACC, Attributes.SPD};
        int i1 = rand.nextInt(4);
        int i2 = rand.nextInt(4);
        while (i2 == i1) {
            i2 = rand.nextInt(4);
        }
        int i3 = rand.nextInt(4);
        while (i3 == i1 || i3 == i2) {
            i3 = rand.nextInt(4);
        }
        int i4 = 6 - (i1 + i2 + i3);
        orderedAttributes[0] = newOrder[i1];
        orderedAttributes[1] = newOrder[i2];
        orderedAttributes[2] = newOrder[i3];
        orderedAttributes[3] = newOrder[i4];
    }

    public Attributes getOrderedAttributes(int i) {
        return orderedAttributes[i];
    }

    public void setTurn(int i) {
        turn = i;
    }

    public int getTurn() {
        return turn;
    }

    public int checkGoal(Player p1, Player p2, int i1, int i2) {
        if (p1.getAutoWin() && !p2.getAutoWin()) return -1;
        if (!p1.getAutoWin() && p2.getAutoWin()) return 1;
        if (goalLarge) {
            return Integer.compare(i2, i1);
        }
        else {
            return Integer.compare(i1, i2);
        }
    }

    public void setGoalReverse(boolean b) {
        goalLarge = b;
    }

    public boolean getGoalLarge() {
        return goalLarge;
    }

    public Attributes getChosenAttribute() {
        return chosenAttribute;
    }

    public void setChosenAttribute(Attributes s) {
        chosenAttribute = s;
    }

    public Attributes getPreviousAttribute() {
        return previousAttribute;
    }

    public void setPreviousAttribute(Attributes a) {
        previousAttribute = a;
    }

    public void setUsedAttribute(Attributes a, boolean b) {
        usedAttribute.put(a,b);
    }

    public boolean checkAttribute(Attributes s) {
        return usedAttribute.get(s);
    }

    public void randomizeAttribute(Player player) {
        boolean success = false;
        Random rand = new Random();
        while (!success) {
            int r = rand.nextInt(4);
            if (r == 0 && player.getAvailableAttribute(Attributes.ATK)) {chosenAttribute = Attributes.ATK; success = true;}
            if (r == 1 && player.getAvailableAttribute(Attributes.SPATK)) {chosenAttribute = Attributes.SPATK; success = true;}
            if (r == 2 && player.getAvailableAttribute(Attributes.SPDEF)) {chosenAttribute = Attributes.ACC; success = true;}
            if (r == 3 && player.getAvailableAttribute(Attributes.SPD)) {chosenAttribute = Attributes.SPD; success = true;}
        }
    }

    public void setPriority(Player p1, Player p2, String s) {
        if (p1.getSpeed() > p2.getSpeed()) turn = 0;
        if (p2.getSpeed() > p1.getSpeed()) turn = 1;
        if (p1.getSpeed() == p2.getSpeed()) {
            if (Objects.equals(s, "p1")) turn = 0;
            if (Objects.equals(s, "p2")) turn = 1;
        }
        p1.setSpeed(0);
        p2.setSpeed(0);
    }

    public FieldEffect getFieldEffect() {
        return fieldEffect;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void switchActivePlayer(Player player, Player opponent) {
        if (activePlayer == player) activePlayer = opponent;
        else activePlayer = player;
    }

    public void setActivePlayer(Player p) {
        activePlayer = p;
    }

    public void setFieldEffect(FieldEffect e) {
        fieldEffect = e;
        game.setFieldEffect(e);
    }

    public ItemRegistry generateItem(Player player) {
        if (Objects.equals(mode, "free_play") ||
                Objects.equals(mode, "battle_tower") ||
                Objects.equals(mode, "battle_arcade") ||
                Objects.equals(mode, "battle_dojo") ||
                Objects.equals(mode, "battle_stage")) {
            List<ItemRegistry> deck = decks.get(player).getItemDeck();
            Random rand = new Random();
            boolean success = false;
            ItemRegistry item = null;
            while (!success) {
                item = deck.get(rand.nextInt(deck.size()));
                if (!ItemRegistry.initItem(game, item).isAceSpec()) success = true;
                else if (!player.getAceSpec()) {player.setAceSpec(true); success = true;}
            }
            return item;
        }
        if (Objects.equals(mode, "classic")) return generateClassicItem();
        return null;
    }

    public ItemRegistry generateClassicItem() {
        Random rand = new Random();
        return switch (rand.nextInt(16)) {
            case 0 -> ItemRegistry.X_ATTACK;
            case 1 -> ItemRegistry.X_DEFEND;
            case 2 -> ItemRegistry.X_SPECIAL;
            case 3 -> ItemRegistry.X_SPDEF;
            case 4 -> ItemRegistry.X_ACCURACY;
            case 5 -> ItemRegistry.X_EVASION;
            case 6 -> ItemRegistry.X_SPEED;
            case 7 -> ItemRegistry.AMULET_COIN;
            case 8 -> ItemRegistry.EXPERT_BELT;
            case 9 -> ItemRegistry.LOADED_DICE;
            case 10 -> ItemRegistry.EJECT_BUTTON;
            case 11 -> ItemRegistry.COVERT_CLOAK;
            case 12 -> ItemRegistry.BATTLE_PASS;
            case 13 -> ItemRegistry.EVIOLITE;
            case 14 -> ItemRegistry.GRIP_CLAW;
            case 15 -> ItemRegistry.SCOPE_LENS;
            default -> null;
        };
    }

    public void setPrizePot(int p) {
        prizePot = p;
        game.setPrizePot(p);
    }

    public int getPrizePot() {
        return prizePot;
    }

    public String getMode() {
        return mode;
    }

    public AbstractGameScreen getContext() {
        return game;
    }
}
