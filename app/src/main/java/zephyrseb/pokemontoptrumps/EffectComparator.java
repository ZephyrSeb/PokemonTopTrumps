package zephyrseb.pokemontoptrumps;

import java.util.Comparator;

public class EffectComparator implements Comparator<EffectAction>  {
    public int compare(EffectAction e1, EffectAction e2) {
        if (e1.getEffect().getPriority() > e2.getEffect().getPriority()) {
            return -1;
        }
        else if (e1.getEffect().getPriority() < e2.getEffect().getPriority()) {
            return 1;
        }
        else {
        if (e1.getSpeed() > e2.getSpeed()) {
            return -1;
        }
        else if (e1.getSpeed() < e2.getSpeed()) {
            return 1;
        }
        else return Integer.compare(e1.getRandom(), e2.getRandom());
        }
    }
}
