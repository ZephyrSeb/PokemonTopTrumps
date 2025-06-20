package zephyrseb.pokemontoptrumps;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zephyrseb.pokemontoptrumps.Screens.CollectionScreen;
import zephyrseb.pokemontoptrumps.Screens.DeckBuilderScreen;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardRegistry> localDataSet = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.cardRaw);
        }

        public ConstraintLayout getTextView() {
            return textView;
        }
    }

    public CardAdapter(List<CardRegistry> dataSet, Context ctx) {
        localDataSet = dataSet;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ConstraintLayout card = view.findViewById(R.id.cardRaw);
        //view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //card.setLayoutParams(new ConstraintLayout.LayoutParams(
        //        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, context.getResources().getDisplayMetrics()) * 0.4f),
        //        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics()) * 0.4f)));
        card.setScaleX(0.45f);
        card.setScaleY(0.45f);
        card.setTranslationX(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -80, context.getResources().getDisplayMetrics()));
        card.setTranslationY(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -110, context.getResources().getDisplayMetrics()));

        //card.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        CardRegistry.initCard(context, localDataSet.get(position)).displayCard(context, viewHolder.getTextView());

        if (context instanceof CollectionScreen) {
            viewHolder.getTextView().setOnLongClickListener(v -> {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_layout, null), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, context.getResources().getDisplayMetrics()) * 1.4f), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics()) * 1.4f), true);
                popupWindow.showAtLocation(((CollectionScreen) context).findViewById(R.id.collection), Gravity.CENTER, 0, 0);
                popupWindow.getContentView().setScaleX(1.4f);
                popupWindow.getContentView().setScaleY(1.4f);
                CardRegistry.initCard(context, localDataSet.get(position)).displayCard(context, popupWindow.getContentView().findViewById(R.id.cardRaw));

                ImageView abilityIcon = popupWindow.getContentView().findViewById(R.id.abilityIcon);
                abilityIcon.setOnClickListener(u -> {
                    LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    @SuppressLint("InflateParams") PopupWindow popupWindow2 = new PopupWindow(inflater2.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                    popupWindow2.showAtLocation(((CollectionScreen) context).findViewById(R.id.collection), Gravity.CENTER, 0, 0);
                    Card card = CardRegistry.initCard(context, localDataSet.get(position));
                    ((TextView) popupWindow2.getContentView().findViewById(R.id.abilityName)).setText(String.valueOf(card.getAbility().getName()));
                    ((TextView) popupWindow2.getContentView().findViewById(R.id.abilityDescription)).setText(String.valueOf(card.getAbility().getDescription()));
                });
                return true;
            });
        }

        if (context instanceof DeckBuilderScreen) {
            Deck deck = ((DeckBuilderScreen) context).getDeck();
            if (deck.containsCard(localDataSet.get(position))) {
                viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
            }
            viewHolder.getTextView().setOnClickListener(v -> {
                if (deck.containsCard(localDataSet.get(position))) {
                    deck.removeCard(localDataSet.get(position));
                    viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
                }
                else {
                    deck.addCard(localDataSet.get(position));
                    viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
                }
                ((DeckBuilderScreen) context).updateBanner(deck);
            });

            viewHolder.getTextView().setOnLongClickListener(v -> {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_layout, null), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, context.getResources().getDisplayMetrics()) * 1.4f), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics()) * 1.4f), true);
                popupWindow.showAtLocation(((DeckBuilderScreen) context).findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);
                popupWindow.getContentView().setScaleX(1.4f);
                popupWindow.getContentView().setScaleY(1.4f);
                CardRegistry.initCard(context, localDataSet.get(position)).displayCard(context, popupWindow.getContentView().findViewById(R.id.cardRaw));

                ImageView abilityIcon = popupWindow.getContentView().findViewById(R.id.abilityIcon);
                abilityIcon.setOnClickListener(u -> {
                    LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    @SuppressLint("InflateParams") PopupWindow popupWindow2 = new PopupWindow(inflater2.inflate(R.layout.ability_popup, null), ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                    popupWindow2.showAtLocation(((DeckBuilderScreen) context).findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);
                    Card card = CardRegistry.initCard(context, localDataSet.get(position));
                    ((TextView) popupWindow2.getContentView().findViewById(R.id.abilityName)).setText(String.valueOf(card.getAbility().getName()));
                    ((TextView) popupWindow2.getContentView().findViewById(R.id.abilityDescription)).setText(String.valueOf(card.getAbility().getDescription()));
                });
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
