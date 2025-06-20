package zephyrseb.pokemontoptrumps;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaveData {
    private String name;
    private List<CardRegistry> collection = new ArrayList<>();
    private List<ItemRegistry> itemCollection = new ArrayList<>();
    private List<Deck> deckList = new ArrayList<>();
    private int winStreakFreePlay = 0;
    private int winStreakBattleTower = 0;
    private int winStreakBattleFactory = 0;
    private int winStreakBattleArcade = 0;
    private int winStreakBattleDojo = 0;
    private int winStreakBattleStage = 0;
    private int battlePoints = 0;

    public SaveData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public void addCard(CardRegistry cr) {
        collection.add(cr);
    }

    public void addItem(ItemRegistry ir) {
        itemCollection.add(ir);
    }

    public List<CardRegistry> getCollection() {
        return collection;
    }

    public List<ItemRegistry> getItemCollection() {
        return itemCollection;
    }

    public void addDeck(Deck d) {
        deckList.add(deckList.size(), d);
    }

    public void setDeck(int i, Deck d) {
        deckList.set(i, d);
    }

    public List<Deck> getDeckList() {
        return deckList;
    }

    public void deleteDeck(Deck d) {
        deckList.remove(d);
    }

    public void deleteDeck(int i) {
        deckList.remove(i);
    }

    public int getBattlePoints() {
        return battlePoints;
    }

    public void setBattlePoints(int bp) {
        battlePoints = bp;
    }

    public int getWinStreak(String s) {
        return switch (s) {
            case "free_play" -> winStreakFreePlay;
            case "battle_tower" -> winStreakBattleTower;
            case "battle_factory" -> winStreakBattleFactory;
            case "battle_arcade" -> winStreakBattleArcade;
            case "battle_dojo" -> winStreakBattleDojo;
            case "battle_stage" -> winStreakBattleStage;
            default -> 0;
        };
    }

    public void setWinStreak(String s, int i) {
        if (Objects.equals(s, "free_play")) winStreakFreePlay = i;
        if (Objects.equals(s, "battle_tower")) winStreakBattleTower = i;
        if (Objects.equals(s, "battle_factory")) winStreakBattleFactory = i;
        if (Objects.equals(s, "battle_arcade")) winStreakBattleArcade = i;
        if (Objects.equals(s, "battle_dojo")) winStreakBattleDojo = i;
        if (Objects.equals(s, "battle_stage")) winStreakBattleStage = i;
    }

    public void writeFile(Context ctx) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        String fileName = "playerData.json";
        FileOutputStream outputStream;
        try {
            outputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SaveData readFile(Context ctx) {
        Gson gson = new Gson();
        String fileName = "playerData.json";
        FileInputStream inputStream;
        StringBuilder result = new StringBuilder();
        int c;
        try {
            inputStream = ctx.openFileInput(fileName);
            while( (c = inputStream.read()) != -1){
                result.append((char) c);
            }
            inputStream.close();
        } catch (IOException e) {
            return null;
        }
        return gson.fromJson(result.toString(), SaveData.class);
    }
}
