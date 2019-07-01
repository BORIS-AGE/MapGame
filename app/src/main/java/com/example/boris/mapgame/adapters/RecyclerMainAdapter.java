package com.example.boris.mapgame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;
import com.example.boris.mapgame.models.LocationModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.MyHolder> {

    private List<LocationModel> locationModels;
    private MainActivity mainActivity;
    private ConstraintLayout.LayoutParams layoutParams;

    public RecyclerMainAdapter(List<LocationModel> locationModels, MainActivity mainActivity) {
        this.locationModels = locationModels;
        this.mainActivity = mainActivity;

        layoutParams = new ConstraintLayout.LayoutParams(200, 200);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        layoutParams.bottomMargin = 10;
        layoutParams.topMargin = 10;
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
    }

    @Override
    public int getItemCount() {
        return locationModels.size();
    }

    public void setNewSize(int size){
        layoutParams.height = layoutParams.height + size;
        layoutParams.width = layoutParams.width + size;
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        layoutParams.bottomMargin = 10;
        layoutParams.topMargin = 10;

        notifyDataSetChanged();
    }



    public class MyHolder extends RecyclerView.ViewHolder{
        public ConstraintLayout layout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.recycleItemMain);
        }
    }
}
