package zephyrseb.pokemontoptrumps;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zephyrseb.pokemontoptrumps.Screens.CollectionScreen;
import zephyrseb.pokemontoptrumps.Screens.DeckBuilderScreen;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<ItemRegistry> localDataSet = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.cardItemRaw);
        }

        public ConstraintLayout getTextView() {
            return textView;
        }
    }

    public ItemAdapter(List<ItemRegistry> dataSet, Context ctx) {
        localDataSet = dataSet;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout_item, viewGroup, false);
        ConstraintLayout card = view.findViewById(R.id.cardItemRaw);
        card.setScaleX(0.45f);
        card.setScaleY(0.45f);
        card.setTranslationX(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -80, context.getResources().getDisplayMetrics()));
        card.setTranslationY(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -110, context.getResources().getDisplayMetrics()));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ItemRegistry.initItem(context, localDataSet.get(position)).displayCard(viewHolder.getTextView());

        if (context instanceof CollectionScreen) {
            viewHolder.getTextView().setOnLongClickListener(v -> {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_layout, null), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, context.getResources().getDisplayMetrics()) * 1.4f), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics()) * 1.4f), true);
                popupWindow.showAtLocation(((CollectionScreen) context).findViewById(R.id.collection), Gravity.CENTER, 0, 0);
                popupWindow.getContentView().setScaleX(1.4f);
                popupWindow.getContentView().setScaleY(1.4f);
                ItemRegistry.initItem(context, localDataSet.get(position)).displayCard(popupWindow.getContentView().findViewById(R.id.cardRaw));
                return true;
            });
        }

        if (context instanceof DeckBuilderScreen) {
            Deck deck = ((DeckBuilderScreen) context).getDeck();
            if (deck.containsItem(localDataSet.get(position))) {
                viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
            }
            else {
                viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
            }
            viewHolder.getTextView().setOnClickListener(v -> {
                if (deck.containsItem(localDataSet.get(position))) {
                    deck.removeCard(localDataSet.get(position));
                    viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.INVISIBLE);
                }
                else {
                    deck.addItem(localDataSet.get(position));
                    viewHolder.getTextView().findViewById(R.id.checkmark).setVisibility(View.VISIBLE);
                }
                ((DeckBuilderScreen) context).updateBanner(deck);
            });

            viewHolder.getTextView().setOnLongClickListener(v -> {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                PopupWindow popupWindow = new PopupWindow(inflater.inflate(R.layout.card_layout_item, null), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 288, context.getResources().getDisplayMetrics()) * 1.4f), (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, context.getResources().getDisplayMetrics()) * 1.4f), true);
                popupWindow.showAtLocation(((DeckBuilderScreen) context).findViewById(R.id.activityDeckBuilder), Gravity.CENTER, 0, 0);
                popupWindow.getContentView().setScaleX(1.4f);
                popupWindow.getContentView().setScaleY(1.4f);
                ItemRegistry.initItem(context, localDataSet.get(position)).displayCard(popupWindow.getContentView().findViewById(R.id.cardItemRaw));
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
