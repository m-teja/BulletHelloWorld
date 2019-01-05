package matth.dungeon.GameUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class InventoryDisplayAdaptor extends RecyclerView.Adapter {

    private ArrayList<String> patterns;
    private LayoutInflater inflater;

    InventoryDisplayAdaptor(Context con,ArrayList<String> patterns) {
        this.inflater = LayoutInflater.from(con);
        this.patterns = patterns;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
