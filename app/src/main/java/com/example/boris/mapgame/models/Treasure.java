package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Treasure extends Location {

    public Treasure() {
        setType(LocationType.TREASURE);
    }

    @Override
    public void standPlayer(Player player) {
        player.setHasTreasure(true);
        player.makeNotification("Treasure (you have got a treasure)");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.treasure);
    }
}
