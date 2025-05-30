package zephyrseb.pokemontoptrumps;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import zephyrseb.pokemontoptrumps.comparators.TypeComparator;

public class Deck {
    private String name;
    List<CardRegistry> cardList = new ArrayList<>();

    public Deck(String n) {
        name = n;
    }

    public void addCard(CardRegistry c) {
        cardList.add(c);
    }

    public void removeCard(CardRegistry c) {
        cardList.remove(c);
    }

    public boolean containsCard(CardRegistry cr) {
        return cardList.contains(cr);
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public List<CardRegistry> getDeck() {
        return cardList;
    }

    public int pointValue(Context ctx) {
        int temp = 0;
        for (CardRegistry c : cardList) {
            temp += CardRegistry.initCard(ctx, c).getPointValue();
        }
        return temp;
    }

    public int getCardCount() {
        return cardList.size();
    }

    public List<Type> getTypes(Context ctx) {
        Set<Type> types = new HashSet<>();
        for (CardRegistry cr : cardList) {
            types.add(CardRegistry.initCard(ctx, cr).getType());
        }
        List<Type> typeList = new ArrayList<>(types);
        typeList.sort(new TypeComparator());
        return typeList;
    }

    public boolean validate(Context ctx, String mode) {
        if (Objects.equals(mode, "free_play")) {
            if (getCardCount() != 20) return false;
            if (pointValue(ctx) > 75) return false;
            return true;
        }
        return false;
    }
}
