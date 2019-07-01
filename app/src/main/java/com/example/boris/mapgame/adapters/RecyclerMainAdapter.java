package com.example.boris.mapgame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;
import com.example.boris.mapgame.models.LocationModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.MyHolder> {

    private List<LocationModel> locationModels;
    private MainActivity mainActivity;

    public RecyclerMainAdapter(List<LocationModel> locationModels, MainActivity mainActivity) {
        this.locationModels = locationModels;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return locationModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
