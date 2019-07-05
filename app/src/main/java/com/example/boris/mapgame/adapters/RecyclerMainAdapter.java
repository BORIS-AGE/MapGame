package com.example.boris.mapgame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.boris.mapgame.GameLogic;
import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;
import com.example.boris.mapgame.models.LocationModel;
import com.example.boris.mapgame.models.Player;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.MyHolder> {

    private List<LocationModel> locationModels;
    private MainActivity mainActivity;
    private ConstraintLayout.LayoutParams layoutParams;
    public Player player;
    private GameLogic gameLogic;

    public RecyclerMainAdapter(List<LocationModel> locationModels, MainActivity mainActivity, GameLogic gameLogic, Player player) {
        this.locationModels = locationModels;
        this.mainActivity = mainActivity;
        this.gameLogic = gameLogic;
        this.player = player;

        layoutParams = new ConstraintLayout.LayoutParams(120, 120);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.layout.setLayoutParams(layoutParams);

        switch (locationModels.get(position).getLocation()) {
            case RIVERT:
                holder.imageView.setImageResource(R.drawable.water);
                break;
            case RIVERR:
                holder.imageView.setImageResource(R.drawable.water);
                break;
            case RIVERB:
                holder.imageView.setImageResource(R.drawable.water);
                break;
            case RIVERL:
                holder.imageView.setImageResource(R.drawable.water);
                break;
            case FOREST:
                holder.imageView.setImageResource(R.drawable.tree);
                break;
            case PIT1:
                holder.imageView.setImageResource(R.drawable.pit);
                break;
            case EXIT:
                holder.imageView.setImageResource(R.drawable.exit);
                break;
            case ROCK:
                holder.imageView.setImageResource(R.drawable.mountain);
                break;
            case SAND:
                holder.imageView.setImageResource(R.drawable.sand);
                break;
            case ARSENAL:
                holder.imageView.setImageResource(R.drawable.arsenal);
                break;
            case TREASURE:
                holder.imageView.setImageResource(R.drawable.treasure);
                break;
        }
    }

    @Override
    public int getItemCount() { return locationModels.size(); }

    public void setNewSize(int size) {
        layoutParams.height = layoutParams.height + size;
        layoutParams.width = layoutParams.width + size;


        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRecyclerItem);
            layout = itemView.findViewById(R.id.recycleItemMain);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!player.isOnMap() && locationModels.get(getAdapterPosition()).getLocation() != MainActivity.Location.ROCK) {
                        player.setPosition(getAdapterPosition());
                        player.setOnMap(true);
                        gameLogic.notifyLocationType(getAdapterPosition(), "");
                    }
                }
            });
        }
    }


}
