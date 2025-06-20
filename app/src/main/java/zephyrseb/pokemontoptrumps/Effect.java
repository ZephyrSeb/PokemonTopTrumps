package zephyrseb.pokemontoptrumps;

public class Effect {
    private final EffectFunction effect;
    private final int priority;
    private final String type;
    private boolean isSound = false;
    private final boolean advance;
    public Effect(EffectFunction s, int p, String t, boolean b) {
        effect = s;
        priority = p;
        type = t;
        advance = b;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public boolean isAdvance() {
        return advance;
    }

    public Effect setSound(boolean b) {
        isSound = b;
        return this;
    }

    public boolean isSound() {
        return isSound;
    }

    public void applyEffect(Game game, Player player, Player opponent) {
        effect.run(game, player, opponent);
    }
}

interface EffectFunction {
    void run(Game game, Player player, Player opponent);
}