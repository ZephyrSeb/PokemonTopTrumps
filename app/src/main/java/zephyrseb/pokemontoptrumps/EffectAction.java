package zephyrseb.pokemontoptrumps;

import androidx.annotation.Nullable;

import java.util.Random;

public class EffectAction {
    private Effect effect;
    private Player user;
    private @Nullable Player opponent;
    private int speed;
    private int random;
    public EffectAction(Effect e, Player p1, Player p2, int s) {
        effect = e;
        user = p1;
        opponent = p2;
        speed = s;
        Random rand = new Random();
        random = rand.nextInt();
    }

    public Effect getEffect() {
        return effect;
    }

    public int getSpeed() {
        return speed;
    }

    public Player getPlayer() {
        return user;
    }

    public void setPlayer(Player player) {
        user = player;
    }

    public int getRandom() {
        return random;
    }

    public void applyEffect(Game game) {
        effect.applyEffect(game, user, opponent);
    }
}
