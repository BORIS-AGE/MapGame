package com.example.boris.mapgame;

import android.view.View;

import com.example.boris.mapgame.models.LocationModel;
import com.example.boris.mapgame.models.Player;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GameLogic {

    private Player player;
    private MainActivity mainActivity;
    private List<LocationModel> locationModels;
    int widthOfMap;

    public GameLogic(Player player, MainActivity mainActivity, List<LocationModel> locationModels, int widthOfMap) {
        this.player = player;
        this.mainActivity = mainActivity;
        this.locationModels = locationModels;
        this.widthOfMap = widthOfMap;
    }

    public void notifyLocationType(int position, String additional) {
        switch (locationModels.get(position).getLocation()) {
            case RIVERT:
                makeNotification("River" + additional);
                break;
            case RIVERR:
                makeNotification("River" + additional);
                break;
            case RIVERB:
                makeNotification("River" + additional);
                break;
            case RIVERL:
                makeNotification("River" + additional);
                break;
            case FOREST:
                makeNotification("Forest" + additional);
                break;
            case PIT1:
                makeNotification("Pit1" + additional);
                break;
            case EXIT:
                makeNotification("Exit" + additional);
                break;
            case ROCK:
                makeNotification("Mountain" + additional);
                break;
            case SAND:
                makeNotification("Sand" + additional);
                break;
            case ARSENAL:
                makeNotification("Arsenal" + additional);
                break;
            case TREASURE:
                makeNotification("Treasure" + additional);
                break;
        }
    }

    public void moveUp() {
        if (player.getPosition() - mainActivity.lenthOfMAp >= 0) {
            player.setPosition(player.getPosition() - mainActivity.lenthOfMAp);
            performPlayerMove(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveDown() {
        if (player.getPosition() + mainActivity.lenthOfMAp <= widthOfMap * widthOfMap) {
            player.setPosition(player.getPosition() + mainActivity.lenthOfMAp);
            performPlayerMove(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveLeft() {
        if ((player.getPosition() % 7) - 1 >= 0) {
            player.setPosition(player.getPosition() - 1);
            performPlayerMove(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    public void moveRight() {
        if ((player.getPosition() % 7) + 1 <= 6) {
            player.setPosition(player.getPosition() + 1);
            performPlayerMove(player.getPosition());
        } else {
            makeNotification("end of map");
        }
    }

    private void performPlayerMove(int currentPosition) {
        switch (locationModels.get(currentPosition).getLocation()) {
            //river cases
            case RIVERT:
                if (currentPosition - widthOfMap >= 0) {
                    player.setPosition(currentPosition - widthOfMap);
                    notifyLocationType(player.getPosition(), " (you've been moved by the flow of the river");
                }
                break;
            case RIVERR:
                if ((currentPosition % 7) + 1 <= 6) {
                    player.setPosition(currentPosition + 1);
                    notifyLocationType(player.getPosition(), " (you've been moved by the flow of the river");
                }
                break;
            case RIVERB:
                if (currentPosition + widthOfMap <= widthOfMap * widthOfMap) {
                    player.setPosition(currentPosition + widthOfMap);
                    notifyLocationType(player.getPosition(), " (you've been moved by the flow of the river");
                }
                break;
            case RIVERL:
                if ((currentPosition % 7) - 1 >= 0) {
                    player.setPosition(currentPosition - 1);
                    notifyLocationType(player.getPosition(), " (you've been moved by the flow of the river");
                }
                break;
            //pit cases
            case PIT1:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT2) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit1 and gone out from another side of pit 2");
                    }
                }
                break;
            case PIT2:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT3) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit2 and gone out from another side of pit3");
                    }
                }
                break;
            case PIT3:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT4) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit3 and gone out from another side of pit4");
                        break;
                    }
                }
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT1) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit3 and gone out from another side of pit1");
                        break;
                    }
                }
            case PIT4:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT5) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit4 and gone out from another side");
                        break;
                    }
                }
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT1) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit4 and gone out from another side of pit1");
                        break;
                    }
                }
            case PIT5:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT6) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit5 and gone out from another side");
                        break;
                    }
                }
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT1) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit5 and gone out from another side of pit1");
                        break;
                    }
                }
            case PIT6:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT1) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit6 and gone out from another side of pit1");
                        break;
                    }
                }
                //other
            case FOREST:
                if (player.hasBomb() || player.hasGun()) {
                    player.setHasBomb(false);
                    player.setHasGun(false);
                    makeNotification("Forest (you lost your arsenal)");
                } else
                    makeNotification("Forest");
                break;
            case EXIT:
                if (player.hasTreasure())
                    makeNotification("You win!!!!!!!");
                else
                    makeNotification("Exit (find treasure to win)");
                break;
            case ROCK:
                makeNotification("Mountain");
                break;
            case SAND:
                if (player.hasTreasure()){
                    player.setHasTreasure(false);
                    makeNotification("Sand (you have lost your treasure)");
                }else
                makeNotification("Sand");
                break;
            case ARSENAL:
                player.setHasGun(true);
                player.setHasBomb(true);
                makeNotification("Arsenal (you have got 1 bomb and pistol with 6 bullets");
                break;
            case TREASURE:
                player.setHasTreasure(true);
                makeNotification("Treasure (you have got a treasure");
                break;
        }
    }

    private void makeNotification(String not) {
        //Toast.makeText(mainActivity, not, Toast.LENGTH_SHORT).show();
        Snackbar.make(mainActivity.mainLay, not + player.getPosition(), Snackbar.LENGTH_INDEFINITE)
                .setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(mainActivity.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }
}
