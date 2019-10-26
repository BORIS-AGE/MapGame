package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Forest extends Location {

    public Forest() {
        setType(LocationType.FOREST);
    }

    @Override
    public void standPlayer(Player player) {
        if (player.hasBomb() || player.hasGun()) {
            player.setHasBomb(false);
            player.setHasGun(false);
            player.makeNotification("Forest (you lost your arsenal)");
        } else
            player.makeNotification("Forest");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.tree);
    }
}
