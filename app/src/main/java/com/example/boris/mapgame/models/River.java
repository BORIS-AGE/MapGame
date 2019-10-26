package com.example.boris.mapgame.models;

import android.widget.ImageView;

import com.example.boris.mapgame.MainActivity;
import com.example.boris.mapgame.R;

public class River extends Location {
    public static final String LEFT = "left", RIGHT = "right", TOP = "top", BOT = "bot";
    private String flow;

    public River(String flow) {
        this.flow = flow;
        setType(LocationType.RIVER);
    }

    public String getFlow() {
        return flow;
    }

    @Override
    public void standPlayer(Player player) {
        switch (flow) {
            //river cases
            case TOP:
                if (player.getPosition() - MainActivity.lenthOfMAp >= 0) {
                    player.setPosition(player.getPosition() - MainActivity.lenthOfMAp);
                    player.makeNotification("River (you've been moved by the flow of the river");
                }
                break;
            case RIGHT:
                if ((player.getPosition() % MainActivity.lenthOfMAp) + 1 <= MainActivity.lenthOfMAp - 1) {
                    player.setPosition(player.getPosition() + 1);
                    player.makeNotification("River (you've been moved by the flow of the river");
                }
                break;
            case BOT:
                if (player.getPosition() + MainActivity.lenthOfMAp <= MainActivity.lenthOfMAp * MainActivity.lenthOfMAp) {
                    player.setPosition(player.getPosition() + MainActivity.lenthOfMAp);
                    player.makeNotification("River (you've been moved by the flow of the river");
                }
                break;
            case LEFT:
                if ((player.getPosition() % MainActivity.lenthOfMAp) - 1 >= 0) {
                    player.setPosition(player.getPosition() - 1);
                    player.makeNotification("River (you've been moved by the flow of the river");
                }
                break;
        }
    }

    @Override
    public void setImageView(ImageView imageView) {
        imageView.setImageResource(R.drawable.water);
    }
}
