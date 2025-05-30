package zephyrseb.pokemontoptrumps;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveData {
    private String name;
    private List<CardRegistry> collection = new ArrayList<>();
    private List<Deck> deckList = new ArrayList<>();

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

    public List<CardRegistry> getCollection() {
        return collection;
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
        //System.out.println(result);
        return gson.fromJson(result.toString(), SaveData.class);
    }
}
