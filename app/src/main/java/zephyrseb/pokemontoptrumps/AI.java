package zephyrseb.pokemontoptrumps;

public class AI {
    private boolean canMegaEvolve;
    private boolean hasPriority;
    private Card card;
    private boolean preferredMegaEvolve = false;
    private int preferredItem = -1;
    private int preferredStat = -1;
    private Player player;
    private Player opponent;
    private final String[] attributes = {"hp", "atk", "def", "spatk", "spdef", "spd"};
    public AI(Card c, boolean e, boolean p, Player p1, Player p2) {
        card = c;
        canMegaEvolve = e;
        hasPriority = p;
        player = p1;
        opponent = p2;
    }

    public void chooseAttack() {
        Card genericCard = new Card("", R.drawable.pokemon_mew, Type.PSYCHIC, null, 100, 100, 100, 100, 100, 100, Type.DARK, "");
        int[] bestValue = {0, 0, 0, 0, 0, 0};
        boolean[] bestME = {false, false, false, false, false, false};
        int[] bestItem = {-1, -1, -1, -1, -1, -1};
        for (int i = 0; i < 6; i++) {
            bestValue[i] = card.getAttribute(attributes[i]);
            if (card.canMegaEvolve() && canMegaEvolve && (hasPriority || opponent.getPoints() > 4)) {
                if (card.megaEvolve().getAttribute(attributes[i]) > bestValue[i]) {
                    bestValue[i] = card.megaEvolve().getAttribute(attributes[i]);
                    bestME[i] = true;
                }
            }
            if (card.getAbility() != null) {
                for (Effect effect : card.getAbility().getEffects()) {
                    if (effect.checkConditions(player, opponent, card, genericCard, attributes[i]) && effect.checkEffect("modify-stat")) {
                        bestValue[i] = effect.apply(bestValue[i]);
                    }
                }
            }
            if (player.noItems() > 0) {
                int bestMatch = 0;
                for (int j = 0; j < player.noItems(); j++) {
                    for (Effect effect : player.getItem(j).getEffects()) {
                        int mod = 0;
                        if (effect.checkConditions(player, opponent, card, null, attributes[i]) && effect.checkEffect("modify-stat")) {
                            mod = effect.apply(bestValue[i]);
                        }
                        if (effect.checkConditions(player, opponent, card, null, attributes[i]) && effect.checkEffect("modify-prize")) {
                            mod = bestValue[i] * 2;
                        }
                        if (effect.checkConditions(player, opponent, card, null, attributes[i]) && effect.checkEffect("null-ability") && card.getAbility() == Ability.TRUANT) {
                            mod = bestValue[i] * 2;
                        }
                        if (effect.checkConditions(player, opponent, card, null, attributes[i]) && effect.checkEffect("modify-weakness") && card.getAbility() == Ability.HIDDEN_POWER) {
                            mod = bestValue[i] + 80;
                        }
                        if (effect.checkConditions(player, opponent, card, null, attributes[i]) && effect.checkEffect("replay-card")) {
                            mod = bestValue[i];
                        }
                        if (mod > bestMatch) {
                            bestMatch = mod;
                            bestItem[i] = j;
                        }
                    }
                }
                if (bestMatch > 0) bestValue[i] = bestMatch;
            }
        }
        int maxIndex = 0;
        for (int i = 0; i < 6; i++) {
            if (bestValue[i] > bestValue[maxIndex]) maxIndex = i;
        }
        preferredStat = maxIndex;
        preferredItem = bestItem[maxIndex];
        preferredMegaEvolve = bestME[maxIndex];
    }

    public void chooseDefend() {
        preferredStat = -1;
        preferredMegaEvolve = false;
        int bestItem = -1;
        if (player.noItems() > 0) {
            int weight = 0;
            for (int i = 0; i < player.noItems(); i++) {
                for (Effect effect : player.getItem(i).getEffects()) {
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("null-ability") && card.getAbility() == Ability.TRUANT) {
                        if (weight < 2) {
                            weight = 2;
                            bestItem = i;
                        }
                    }
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("modify-weakness") && card.getAbility() == Ability.HIDDEN_POWER) {
                        if (weight < 2) {
                            weight = 2;
                            bestItem = i;
                        }
                    }
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("swap-card")) {
                        if (weight < 1) {
                            weight = 1;
                            bestItem = i;
                        }
                    }
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("randomize-attribute")) {
                        if (weight < 1) {
                            weight = 1;
                            bestItem = i;
                        }
                    }
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("replay-card") && card.getStatAverage() > 80) {
                        if (weight < 1) {
                            weight = 1;
                            bestItem = i;
                        }
                    }
                    if (effect.checkConditions(player, opponent, card, null, "") && effect.checkEffect("modify-speed") && effect.apply(0) > 0) {
                        if (weight < 1) {
                            weight = 1;
                            bestItem = i;
                        }
                    }
                }
            }
        }
        preferredItem = bestItem;
    }

    public Item getPreferredItem() {
        if (preferredItem == -1) return null;
        else return player.getItem(preferredItem);
    }
    public String getPreferredStat() {
        switch (preferredStat) {
            case 0:
                return "hp";
            case 1:
                return "atk";
            case 2:
                return "def";
            case 3:
                return "spatk";
            case 4:
                return "spdef";
            case 5:
                return "spd";
            default:
                return "";
        }
    }
    public boolean getPreferredME() {
        return preferredMegaEvolve;
    }
}
