package zephyrseb.pokemontoptrumps;

public class Effect {
    private final EffectFunction effect;
    private int priority;
    private String type;
    public Effect(EffectFunction s, int p, String t) {
        effect = s;
        priority = p;
        type = t;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public void applyEffect(GameScreen game, Player player, Player opponent) {
        effect.run(game, player, opponent);
    }
}

interface EffectFunction {
    void run(GameScreen game, Player player, Player opponent);
}