package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.R;

public class Arsenal extends Location {

    public Arsenal() {
        setType(LocationType.ARSENAL);
    }

    @Override
    public void standPlayer(Player player) {
        player.setHasGun(true);
        player.setHasBomb(true);
        player.makeNotification("Arsenal (you have got 1 bomb and pistol with 6 bullets)");
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.arsenal);
    }
}
