package zephyrseb.pokemontoptrumps;

import java.util.List;

public abstract class PassiveAbility {

    public PassiveAbility addEffect(Effect e) {
        return null;
    }

    public List<Effect> getEffects() {
        return java.util.Collections.emptyList();
    }
}
