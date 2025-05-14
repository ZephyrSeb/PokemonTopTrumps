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
    private GameScreen game;
    private final String[] attributes = {"hp", "atk", "def", "spatk", "spdef", "spd"};
    public AI(GameScreen g, Card c, boolean e, boolean p, Player p1, Player p2) {
        game = g;
        card = c;
        canMegaEvolve = e;
        hasPriority = p;
        player = p1;
        opponent = p2;
    }

    public void chooseAttack() {
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
