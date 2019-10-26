package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Mountain extends Location {

    public Mountain() {
        setType(LocationType.ROCK);
    }

    @Override
    public void standPlayer(Player player) {
        player.makeNotification("Mountain");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.mountain);
    }
}
