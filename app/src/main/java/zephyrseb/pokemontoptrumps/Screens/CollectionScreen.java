package zephyrseb.pokemontoptrumps.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

import zephyrseb.pokemontoptrumps.CardAdapter;
import zephyrseb.pokemontoptrumps.CardRegistry;
import zephyrseb.pokemontoptrumps.R;
import zephyrseb.pokemontoptrumps.SaveData;

public class CollectionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_collection);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        SaveData playerData = new SaveData();
        playerData = playerData.readFile(this);
        List<CardRegistry> collection = playerData.getCollection();
        collection.sort(Comparator.naturalOrder());

        ConstraintLayout collectionLayout = findViewById(R.id.collection);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setId(View.generateViewId());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                //lp.height = (int)(((double) getWidth() / getSpanCount()) * (400d / 288d)) - (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
                //lp.width = getWidth() / getSpanCount() - (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
                lp.leftMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.rightMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.topMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                lp.bottomMargin = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -220, getResources().getDisplayMetrics()));
                return true;
            }
        };
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CardAdapter(collection, this));
        collectionLayout.addView(recyclerView);
        //recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        //recyclerView.setLayoutParams(new ConstraintLayout.LayoutParams(
        //        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, getResources().getDisplayMetrics()) * 0.4f),
        //        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics()) * 0.4f)));


        Intent intent = new Intent(this, StartScreen.class);
        Button startButton = findViewById(R.id.returnButton);
        startButton.setOnClickListener(v -> {
            Animation buttonPulse = AnimationUtils.loadAnimation(this, R.anim.button_press);
            startButton.startAnimation(buttonPulse);
            startActivity(intent);
        });
    }
}
