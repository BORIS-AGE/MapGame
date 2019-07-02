package com.example.boris.mapgame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;
import com.example.boris.mapgame.models.LocationModel;
import com.example.boris.mapgame.models.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.MyHolder> {

    private List<LocationModel> locationModels;
    private MainActivity mainActivity;
    private ConstraintLayout.LayoutParams layoutParams;
    private LocationModel[][] map;
    public Player player;

    public RecyclerMainAdapter(List<LocationModel> locationModels, MainActivity mainActivity) {
        this.locationModels = locationModels;
        this.mainActivity = mainActivity;
        this.map = map;

        layoutParams = new ConstraintLayout.LayoutParams(200, 200);
        player = new Player();
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
            case RIVER:
                holder.imageView.setImageResource(R.drawable.water);
                break;
            case FOREST:
                holder.imageView.setImageResource(R.drawable.tree);
                break;
            case PIT:
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
    public int getItemCount() {
        return locationModels.size();
    }

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
                        notifyLocationType(getAdapterPosition());
                    }
                }
            });
        }
    }

    private void notifyLocationType(int position) {
        switch (locationModels.get(position).getLocation()) {
            case RIVER:
                makeNotification("River");
                break;
            case FOREST:
                makeNotification("Forest");
                break;
            case PIT:
                makeNotification("Pit");
                break;
            case EXIT:
                makeNotification("Exit");
                break;
            case ROCK:
                makeNotification("Mountain");
                break;
            case SAND:
                makeNotification("Sand");
                break;
            case ARSENAL:
                makeNotification("Arsenal");
                break;
            case TREASURE:
                makeNotification("Treasure");
                break;
        }
    }

    private void makeNotification(String not) {
        Toast.makeText(mainActivity, not, Toast.LENGTH_SHORT).show();
    }

    public void moveUp() {
        if (player.getPosition() - mainActivity.lenthOfMAp >= 0) {
            player.setPosition(player.getPosition() - mainActivity.lenthOfMAp);
            notifyLocationType(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveDown() {
        if (player.getPosition() + mainActivity.lenthOfMAp <= mainActivity.lenthOfMAp * mainActivity.lenthOfMAp) {
            player.setPosition(player.getPosition() + mainActivity.lenthOfMAp);
            notifyLocationType(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveLeft() {
        if ((player.getPosition() % 7) - 1 >= 0) {
            player.setPosition(player.getPosition() - 1);
            notifyLocationType(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveRight() {
        if ((player.getPosition() % 7) + 1 <= 6) {
            player.setPosition(player.getPosition() + 1);
            notifyLocationType(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }
}
