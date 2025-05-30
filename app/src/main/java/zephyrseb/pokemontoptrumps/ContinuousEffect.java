package zephyrseb.pokemontoptrumps;

public class ContinuousEffect {
    private Effect effect;
    private int turns;

    public ContinuousEffect(Effect e, int t) {
        effect = e;
        turns = t;
    }

    public void reduceTurns() {
        turns--;
    }

    public boolean timeout() {
        return (turns == 0);
    }

    public Effect getEffect() {
        return effect;
    }

    public int getTurns() {
        return turns;
    }
}
