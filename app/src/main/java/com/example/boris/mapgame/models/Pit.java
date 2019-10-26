package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;

public class Pit extends Location {

    private int id;

    public Pit(int id) {
        this.id = id;
        setType(LocationType.PIT);
    }

    @Override
    public void standPlayer(Player player) {
        for (int i = 0; i < MainActivity.lenthOfMAp * MainActivity.lenthOfMAp; i++) {
            if (i != player.getPosition() && player.getMap().get(i).getType() == LocationType.PIT && ((Pit)player.getMap().get(i)).id == id + 1) {
                player.setPosition(i);
                player.makeNotification("you have fallen into pit" + id + " and gone out from another side");
                return;
            }
        }
        for (int i = 0; i < MainActivity.lenthOfMAp * MainActivity.lenthOfMAp; i++) {
            if (i != player.getPosition() && player.getMap().get(i).getType() == LocationType.PIT && ((Pit)player.getMap().get(i)).id == 1) {
                player.setPosition(i);
                player.makeNotification("you have fallen into pit" + id + " and gone out from another side of pit" + id);
            }
        }
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.pit);
    }
}
