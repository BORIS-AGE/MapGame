package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Exit extends Location {

    public Exit() {
        setType(LocationType.EXIT);
    }

    @Override
    public void standPlayer(Player player) {
        if (player.hasTreasure())
            player.makeNotification("You win!!!!!!!");
        else
            player.makeNotification("Exit (find treasure and come back)");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.exit);
    }
}
