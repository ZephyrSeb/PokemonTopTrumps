package zephyrseb.pokemontoptrumps.Screens;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import zephyrseb.pokemontoptrumps.R;

public class RulesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rules);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });

        findViewById(R.id.rulesIntroduction).setOnClickListener(v -> {
            buttonPress(findViewById(R.id.rulesIntroduction), getResources().getString(R.string.rules_name_introduction),getResources().getString(R.string.rules_description_introduction),R.drawable.icon_pokeball_color);
        });
        findViewById(R.id.rulesBasic).setOnClickListener(v -> {
            buttonPress(findViewById(R.id.rulesBasic), getResources().getString(R.string.rules_name_basic_rules),getResources().getString(R.string.rules_description_basic_rules),R.drawable.icon_pokeball_color);
        });
        findViewById(R.id.rulesItems).setOnClickListener(v -> {
            buttonPress(findViewById(R.id.rulesItems), getResources().getString(R.string.rules_name_items),getResources().getString(R.string.rules_description_items),R.drawable.item_battle_pass);
        });
        findViewById(R.id.rulesAbilities).setOnClickListener(v -> {
            buttonPress(findViewById(R.id.rulesAbilities), getResources().getString(R.string.rules_name_abilities),getResources().getString(R.string.rules_description_abilities),R.drawable.ability);
        });
        findViewById(R.id.rulesMegaEvo).setOnClickListener(v -> {
            buttonPress(findViewById(R.id.rulesMegaEvo), getResources().getString(R.string.rules_name_mega_evolution),getResources().getString(R.string.rules_description_mega_evolution),R.drawable.mega_evolution);
        });
    }

    public void buttonPress(Button button, String name, String description, int image) {
        Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
        button.startAnimation(buttonPulse);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.rules_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(this.findViewById(R.id.activityRules), Gravity.CENTER, 0, 0);
        ((TextView)popupWindow.getContentView().findViewById(R.id.ruleName)).setText(name);
        ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText(description);
        ((ImageView)popupWindow.getContentView().findViewById(R.id.ruleImage)).setImageResource(image);
    }
}
