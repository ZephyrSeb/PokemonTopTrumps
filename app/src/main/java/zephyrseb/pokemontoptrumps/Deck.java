package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import zephyrseb.pokemontoptrumps.comparators.TypeComparator;

public class Deck {
    private String name;
    List<CardRegistry> cardList = new ArrayList<>();
    List<ItemRegistry> itemList = new ArrayList<>();

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

    public void addItem(ItemRegistry i) {
        itemList.add(i);
    }

    public void removeCard(ItemRegistry i) {
        itemList.remove(i);
    }

    public boolean containsItem(ItemRegistry i) {
        return itemList.contains(i);
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

    public List<ItemRegistry> getItemDeck() {
        return itemList;
    }

    public int pointValue(Context ctx) {
        int temp = 0;
        for (CardRegistry c : cardList) {
            temp += CardRegistry.initCard(ctx, c).getPointValue();
        }
        for (ItemRegistry c : itemList) {
            temp += ItemRegistry.initItem(ctx, c).getPointValue();
        }
        return temp;
    }

    public int aceSpecCount(Context ctx) {
        int i = 0;
        for (ItemRegistry ir : itemList) {
            if (ItemRegistry.initItem(ctx, ir).isAceSpec()) i++;
        }
        return i;
    }

    public int getCardCount() {
        return cardList.size();
    }

    public int getItemCount() {
        return itemList.size();
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
        if (Objects.equals(mode, "free_play") || Objects.equals(mode, "battle_arcade") || Objects.equals(mode, "battle_tower")) {
            if (getCardCount() != 20) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_size, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (pointValue(ctx) > 100) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_points, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (getItemCount() != 6) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_items, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (aceSpecCount(ctx) > 1) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_ace_spec, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            return true;
        }
        if (Objects.equals(mode, "battle_dojo")) {
            if (getCardCount() != 20) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_size, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (pointValue(ctx) > 100) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_points, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (getTypes(ctx).size() > 1) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_color, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (getItemCount() != 6) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_items, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (aceSpecCount(ctx) > 1) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_ace_spec, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            return true;
        }
        if (Objects.equals(mode, "battle_stage")) {
            if (getCardCount() != 20) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_size, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (pointValue(ctx) > 150) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_points_large, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (getItemCount() != 6) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_wrong_items, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            if (aceSpecCount(ctx) > 1) {
                Toast toast = Toast.makeText(ctx, R.string.deck_validation_ace_spec, Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
            return true;
        }
        return false;
    }
}
