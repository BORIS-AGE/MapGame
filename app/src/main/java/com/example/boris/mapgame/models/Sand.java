package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Sand extends Location {

    public Sand() {
        setType(LocationType.SAND);
    }

    @Override
    public void standPlayer(Player player) {
        if (player.hasTreasure()) {
            player.setHasTreasure(false);
            player.makeNotification("Sand (you have lost your treasure)");
        } else
            player.makeNotification("Sand");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.sand);
    }
}
