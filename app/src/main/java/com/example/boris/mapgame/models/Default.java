package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Default extends Location {
    public Default() {
        setType(LocationType.DEFAULT);
    }

    @Override
    public void standPlayer(Player player) {

    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.unknown);
    }
}
