package zephyrseb.pokemontoptrumps.Screens;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;

public class SettingsScreen extends AppCompatActivity {

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        EditText nameText = findViewById(R.id.nameEditable);
        nameText.setText(playerData.getName());
        SaveData finalPlayerData = playerData;
        final Handler handler = new Handler(Looper.getMainLooper());

        startButton.setOnClickListener(v -> {
            name = nameText.getText().toString();
            finalPlayerData.setName(name);
            finalPlayerData.writeFile(this);

            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            handler.postDelayed(() -> startActivity(intent), 400);
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.confirmation_menu, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(this.findViewById(R.id.activitySettings), Gravity.CENTER, 0, 0);
            ((TextView)popupWindow.getContentView().findViewById(R.id.ruleDescription)).setText("Are you sure? Deleted data can't be recovered");

            popupWindow.getContentView().findViewById(R.id.yesButton).setOnClickListener(u -> {
                File file = new File(getApplicationContext().getFilesDir(), "playerData.json");
                boolean deleted = file.delete();

                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.yesButton).startAnimation(buttonPulse);
                handler.postDelayed(() -> startActivity(intent), 400);
            });

            popupWindow.getContentView().findViewById(R.id.noButton).setOnClickListener(u -> {
                Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
                popupWindow.getContentView().findViewById(R.id.noButton).startAnimation(buttonPulse);
                handler.postDelayed(popupWindow::dismiss, 400);
            });

        });
    }
}
