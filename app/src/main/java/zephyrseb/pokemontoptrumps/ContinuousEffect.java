package zephyrseb.pokemontoptrumps;

import android.content.Context;

public class ContinuousEffect {
    private Effect effect;
    private int turns;
    private int stringResource;

    public ContinuousEffect(Effect e, int t, int s) {
        effect = e;
        turns = t;
        stringResource = s;
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

    public String getString(Context ctx) {
        return ctx.getString(stringResource);
    }
}
