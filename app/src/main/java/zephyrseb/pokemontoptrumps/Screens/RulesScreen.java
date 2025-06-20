package zephyrseb.pokemontoptrumps.Screens;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
        toMainRules();

        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });
    }

    private void toMainRules() {
        final Handler handler = new Handler(Looper.getMainLooper());
        setContentView(R.layout.activity_rules);

        findViewById(R.id.rulesIntroduction).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesIntroduction), getResources().getString(R.string.rules_name_introduction),getResources().getString(R.string.rules_description_introduction),R.drawable.icon_pokeball_color));
        findViewById(R.id.rulesBasic).setOnClickListener(v -> toBasicRules());
        findViewById(R.id.rulesFieldEffects).setOnClickListener(v -> toFieldRules());
        findViewById(R.id.rulesStatusConditions).setOnClickListener(v -> toStatusRules());

        Intent intent = new Intent(this, StartScreen.class);
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            returnButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intent), 400);
        });
    }

    private void toBasicRules() {
        final Handler handler = new Handler(Looper.getMainLooper());
        setContentView(R.layout.activity_basic_rules);

        findViewById(R.id.rulesBasic).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesBasic), getResources().getString(R.string.rules_name_basic_rules),getResources().getString(R.string.rules_description_basic_rules),R.drawable.icon_pokeball_color));
        findViewById(R.id.rulesItems).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesItems), getResources().getString(R.string.rules_name_items),getResources().getString(R.string.rules_description_items),R.drawable.item_battle_pass));
        findViewById(R.id.rulesAbilities).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesAbilities), getResources().getString(R.string.rules_name_abilities),getResources().getString(R.string.rules_description_abilities),R.drawable.ability));
        findViewById(R.id.rulesMegaEvo).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesMegaEvo), getResources().getString(R.string.rules_name_mega_evolution),getResources().getString(R.string.rules_description_mega_evolution),R.drawable.mega_evolution));
        findViewById(R.id.rulesBreak).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesBreak), getResources().getString(R.string.rules_name_break),getResources().getString(R.string.rules_description_break),R.drawable.break_burst));
        findViewById(R.id.rulesAuras).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesAuras), getResources().getString(R.string.rules_name_auras),getResources().getString(R.string.rules_description_auras),R.drawable.aura_water));
        findViewById(R.id.rulesBerries).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesBerries), getResources().getString(R.string.rules_name_berries),getResources().getString(R.string.rules_description_berries),R.drawable.oran_berry));

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            returnButton.startAnimation(buttonPulse);
            handler.postDelayed(this::toMainRules, 400);
        });
    }

    private void toFieldRules() {
        final Handler handler = new Handler(Looper.getMainLooper());
        setContentView(R.layout.activity_rules_fields);

        findViewById(R.id.rulesBasics).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesBasics), getResources().getString(R.string.rules_name_field_effects),getResources().getString(R.string.rules_description_field_effects),R.drawable.weather_rain));
        findViewById(R.id.rulesFieldSun).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldSun), getResources().getString(R.string.field_effect_name_sun),getResources().getString(R.string.field_effect_description_sun),R.drawable.weather_sun));
        findViewById(R.id.rulesFieldRain).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldRain), getResources().getString(R.string.field_effect_name_rain),getResources().getString(R.string.field_effect_description_rain),R.drawable.weather_rain));
        findViewById(R.id.rulesFieldSnow).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldSnow), getResources().getString(R.string.field_effect_name_snow),getResources().getString(R.string.field_effect_description_snow),R.drawable.weather_snow));
        findViewById(R.id.rulesFieldSandstorm).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldSandstorm), getResources().getString(R.string.field_effect_name_sandstorm),getResources().getString(R.string.field_effect_description_sandstorm),R.drawable.weather_sandstorm));
        findViewById(R.id.rulesFieldElectric).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldElectric), getResources().getString(R.string.field_effect_name_electric_terrain),getResources().getString(R.string.field_effect_description_electric_terrain),R.drawable.terrain_electric));
        findViewById(R.id.rulesFieldGrassy).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldGrassy), getResources().getString(R.string.field_effect_name_grassy_terrain),getResources().getString(R.string.field_effect_description_grassy_terrain),R.drawable.terrain_grass));
        findViewById(R.id.rulesFieldMisty).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldMisty), getResources().getString(R.string.field_effect_name_misty_terrain),getResources().getString(R.string.field_effect_description_misty_terrain),R.drawable.terrain_misty));
        findViewById(R.id.rulesFieldPsychic).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldPsychic), getResources().getString(R.string.field_effect_name_psychic_terrain),getResources().getString(R.string.field_effect_description_psychic_terrain),R.drawable.terrain_psychic));
        findViewById(R.id.rulesFieldWind).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldWind), getResources().getString(R.string.field_effect_name_wind),getResources().getString(R.string.field_effect_description_wind),R.drawable.weather_wind));
        findViewById(R.id.rulesFieldDarkness).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldDarkness), getResources().getString(R.string.field_effect_name_darkness),getResources().getString(R.string.field_effect_description_darkness),R.drawable.darkness));
        findViewById(R.id.rulesFieldTrickRoom).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesFieldTrickRoom), getResources().getString(R.string.field_effect_name_trick_room),getResources().getString(R.string.field_effect_description_trick_room),R.drawable.trick_room));

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            returnButton.startAnimation(buttonPulse);
            handler.postDelayed(this::toMainRules, 400);
        });
    }

    private void toStatusRules() {
        final Handler handler = new Handler(Looper.getMainLooper());
        setContentView(R.layout.activity_rules_status);

        findViewById(R.id.rulesBasics).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesBasics), getResources().getString(R.string.rules_name_status_conditions),getResources().getString(R.string.rules_description_status_conditions),R.drawable.icon_paralyzed));
        findViewById(R.id.rulesStatusBurned).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusBurned), getResources().getString(R.string.status_effect_name_burned),getResources().getString(R.string.status_effect_description_burned),R.drawable.icon_burned));
        findViewById(R.id.rulesStatusParalyzed).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusParalyzed), getResources().getString(R.string.status_effect_name_paralyzed),getResources().getString(R.string.status_effect_description_paralyzed),R.drawable.icon_paralyzed));
        findViewById(R.id.rulesStatusPoisoned).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusPoisoned), getResources().getString(R.string.status_effect_name_poisoned),getResources().getString(R.string.status_effect_description_poisoned),R.drawable.icon_poisoned));
        findViewById(R.id.rulesStatusFrozen).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusFrozen), getResources().getString(R.string.status_effect_name_frozen),getResources().getString(R.string.status_effect_description_frozen),R.drawable.icon_frozen));
        findViewById(R.id.rulesStatusFrostbitten).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusFrostbitten), getResources().getString(R.string.status_effect_name_frostbitten),getResources().getString(R.string.status_effect_description_frostbitten),R.drawable.icon_frostbitten));
        findViewById(R.id.rulesStatusAsleep).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusAsleep), getResources().getString(R.string.status_effect_name_asleep),getResources().getString(R.string.status_effect_description_asleep),R.drawable.icon_asleep));
        findViewById(R.id.rulesStatusConfused).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusConfused), getResources().getString(R.string.status_effect_name_confused),getResources().getString(R.string.status_effect_description_confused),R.drawable.icon_confused));
        findViewById(R.id.rulesStatusBlinded).setOnClickListener(v -> buttonPress(findViewById(R.id.rulesStatusBlinded), getResources().getString(R.string.status_effect_name_blinded),getResources().getString(R.string.status_effect_description_blinded),R.drawable.icon_blinded));

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            returnButton.startAnimation(buttonPulse);
            handler.postDelayed(this::toMainRules, 400);
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
