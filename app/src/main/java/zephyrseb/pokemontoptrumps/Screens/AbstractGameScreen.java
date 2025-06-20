package zephyrseb.pokemontoptrumps.Screens;

import androidx.appcompat.app.AppCompatActivity;

import zephyrseb.pokemontoptrumps.AI;
import zephyrseb.pokemontoptrumps.Attributes;
import zephyrseb.pokemontoptrumps.FieldEffect;
import zephyrseb.pokemontoptrumps.Player;

public abstract class AbstractGameScreen extends AppCompatActivity {
    public void tick(Player p1, Player p2) {}

    public void compare(Player p1, Player p2, Attributes s, AI ai) {}

    public void resultScreen(Player p1, Player p2) {}

    public void setFieldEffect(FieldEffect e) {}

    public void setPrizePot(int p) {}

    public void update(Player p1, Player p2) {}
}
