package matth.dungeon.GameUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import matth.dungeon.R;

public class InventoryDisplayAdaptor extends RecyclerView.Adapter<InventoryDisplayAdaptor.ViewHolder> {

    private ArrayList<String> patterns;
    private LayoutInflater inflater;
    private AdapterView.OnItemClickListener clickListener;

    InventoryDisplayAdaptor(Context con,ArrayList<String> patterns) {
        this.inflater = LayoutInflater.from(con);
        this.patterns = patterns;
    }

    @NonNull
    @Override
    public InventoryDisplayAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.pattern_description, viewGroup, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryDisplayAdaptor.ViewHolder myViewHolder, int i) {
        String info = patterns.get(i);

    }

    @Override
    public int getItemCount() {
        return patterns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.patternDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        //    if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
