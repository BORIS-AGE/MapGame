package com.example.boris.mapgame.adapters;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.boris.mapgame.GameLogic;
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
    public Player player;
    private GameLogic gameLogic;

    public RecyclerMainAdapter(List<LocationModel> locationModels, MainActivity mainActivity, GameLogic gameLogic, Player player) {
        this.locationModels = locationModels;
        this.mainActivity = mainActivity;
        this.gameLogic = gameLogic;
        this.player = player;

        layoutParams = new ConstraintLayout.LayoutParams(mainActivity.phoneWidth / mainActivity.lenthOfMAp + 1, mainActivity.phoneWidth / mainActivity.lenthOfMAp + 1);
    }

    public void setNewSize(int size) {
        layoutParams.height = layoutParams.height + size;
        layoutParams.width = layoutParams.width + size;

        notifyDataSetChanged();
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
        gameLogic.setImageView(holder.imageView, position);
    }

    @Override
    public int getItemCount() { return locationModels.size(); }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public ImageView imageView;

        public MyHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRecyclerItem);
            layout = itemView.findViewById(R.id.recycleItemMain);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!player.isOnMap() && locationModels.get(getAdapterPosition()).getLocation() != MainActivity.Location.ROCK){
                        player.setPosition(getAdapterPosition());
                        player.setOnMap(true);
                        gameLogic.notifyLocationType(getAdapterPosition(), "");
                    }else{
                        PopupMenu popupMenu = gameLogic.setUpPopupMenuItem(getAdapterPosition(), itemView, imageView);
                        MenuInflater inflater = popupMenu.getMenuInflater();
                        inflater.inflate(R.menu.select_location_menu, popupMenu.getMenu());
                        popupMenu.show();
                    }
                }
            });

            /*imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(player.isOnMap()){
                        PopupMenu popupMenu = gameLogic.setUpPopupMenuWalls(getAdapterPosition(), itemView, imageView);
                        MenuInflater inflater = popupMenu.getMenuInflater();
                        inflater.inflate(R.menu.select_wall_menu, popupMenu.getMenu());
                        popupMenu.show();
                    }
                    return true;
                }
            });*/
        }
    }



}
