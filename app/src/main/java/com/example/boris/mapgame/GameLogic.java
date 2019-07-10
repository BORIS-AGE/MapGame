package com.example.boris.mapgame;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.boris.mapgame.models.LocationModel;
import com.example.boris.mapgame.models.Player;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GameLogic {

    private Player player;
    private MainActivity mainActivity;
    private List<LocationModel> locationModels;
    private int widthOfMap;
    public List<LocationModel> usersMap;
    private PopupMenu popupMenu;

    public GameLogic(Player player, MainActivity mainActivity, List<LocationModel> locationModels, List<LocationModel> usersMap, int widthOfMap) {
        this.player = player;
        this.mainActivity = mainActivity;
        this.locationModels = locationModels;
        this.widthOfMap = widthOfMap;
        this.usersMap = usersMap;
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
        if (player.getPosition() - widthOfMap >= 0) {
            if (!locationModels.get(player.getPosition()).hasTopWall() && !locationModels.get(player.getPosition()  - mainActivity.lenthOfMAp).hasBotWall()){
                player.setPosition(player.getPosition() - widthOfMap);
                performPlayerMove(player.getPosition());
            }else{
                if (player.hasBomb()) {
                    locationModels.get(player.getPosition()).setHasTopWall(false);
                    locationModels.get(player.getPosition() - widthOfMap).setHasBotWall(false);
                    makeNotification("you have destroyed the wall");
                }else
                    makeNotification("can't move because of wall");
            }
        } else {
            makeNotification("end of map");
        }
    }

    public void moveDown() {
        if (player.getPosition() + widthOfMap <= widthOfMap * widthOfMap) {
            if (!locationModels.get(player.getPosition()).hasBotWall() && !locationModels.get(player.getPosition() + widthOfMap).hasTopWall()) {
                player.setPosition(player.getPosition() + widthOfMap);
                performPlayerMove(player.getPosition());
            }else{
                if (player.hasBomb()) {
                    locationModels.get(player.getPosition()).setHasBotWall(false);
                    locationModels.get(player.getPosition() + widthOfMap).setHasTopWall(false);
                    makeNotification("you have destroyed the wall");
                }else
                    makeNotification("can't move because of wall");
            }
        } else {
            makeNotification("end of map");
        }
    }

    public void moveLeft() {
        if ((player.getPosition() % 7) - 1 >= 0) {
            if (!locationModels.get(player.getPosition()).hasLeftWall() && !locationModels.get(player.getPosition() - 1).hasRightWall()) {
                player.setPosition(player.getPosition() - 1);
                performPlayerMove(player.getPosition());
            }else{
                if (player.hasBomb()) {
                    locationModels.get(player.getPosition()).setHasLeftWall(false);
                    locationModels.get(player.getPosition() - 1).setHasRightWall(false);
                    makeNotification("you have destroyed the wall");
                }else
                    makeNotification("can't move because of wall");
            }
        } else {
            makeNotification("end of map");
        }
    }

    public void moveRight() {
        if ((player.getPosition() % 7) + 1 <= 6) {
            if (!locationModels.get(player.getPosition()).hasRightWall() && !locationModels.get(player.getPosition() + 1).hasLeftWall()) {
                player.setPosition(player.getPosition() + 1);
                performPlayerMove(player.getPosition());
            }else{
                if (player.hasBomb()) {
                    locationModels.get(player.getPosition()).setHasRightWall(false);
                    locationModels.get(player.getPosition() + 1).setHasLeftWall(false);
                    makeNotification("you have destroyed the wall");
                }else
                makeNotification("can't move because of wall");
            }
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
                        break;
                    }
                }
                break;
            case PIT2:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT3) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit2 and gone out from another side of pit3");
                        break;
                    }
                }
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
                }break;
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
                }break;
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
                }break;
            case PIT6:
                for (int i = 0; i < locationModels.size(); i++) {
                    if (i != currentPosition && locationModels.get(i).getLocation() == MainActivity.Location.PIT1) {
                        player.setPosition(i);
                        makeNotification("you have fallen into pit6 and gone out from another side of pit1");
                        break;
                    }
                }break;
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
                    makeNotification("Exit (find treasure and come back)");
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
                makeNotification("Arsenal (you have got 1 bomb and pistol with 6 bullets)");
                break;
            case TREASURE:
                player.setHasTreasure(true);
                makeNotification("Treasure (you have got a treasure)");
                break;
        }
    }

    private void makeNotification(String not) {
        Snackbar.make(mainActivity.mainLay, not, Snackbar.LENGTH_INDEFINITE)
                .setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(mainActivity.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    public void setUpPopupMenuItem(int position, View itemView, ImageView imageView){
        popupMenu = new PopupMenu(mainActivity, itemView);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.RIVER:
                    usersMap.set(position, new LocationModel(MainActivity.Location.RIVERB));
                    break;
                case R.id.ROCK:
                    usersMap.set(position, new LocationModel(MainActivity.Location.ROCK));
                    break;
                case R.id.FOREST:
                    usersMap.set(position, new LocationModel(MainActivity.Location.FOREST));
                    break;
                case R.id.ARSENAL:
                    usersMap.set(position, new LocationModel(MainActivity.Location.ARSENAL));
                    break;
                case R.id.TREASURE:
                    usersMap.set(position, new LocationModel(MainActivity.Location.TREASURE));
                    break;
                case R.id.SAND:
                    usersMap.set(position, new LocationModel(MainActivity.Location.SAND));
                    break;
                case R.id.EXIT:
                    usersMap.set(position, new LocationModel(MainActivity.Location.EXIT));
                    break;
                case R.id.PIT1:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT1));
                    break;
                case R.id.PIT2:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT2));
                    break;
                case R.id.PIT3:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT3));
                    break;
                case R.id.PIT4:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT4));
                    break;
                case R.id.PIT5:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT5));
                    break;
                case R.id.PIT6:
                    usersMap.set(position, new LocationModel(MainActivity.Location.PIT6));
                    break;
            }
            setImageView(imageView, position);
            return true;
        });
    }

    public PopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setImageView(ImageView imageView, int position){
        switch (locationModels.get(position).getLocation()) {
            case RIVERT:
                imageView.setImageResource(R.drawable.water);
                break;
            case RIVERR:
                imageView.setImageResource(R.drawable.water);
                break;
            case RIVERB:
                imageView.setImageResource(R.drawable.water);
                break;
            case RIVERL:
                imageView.setImageResource(R.drawable.water);
                break;
            case FOREST:
                imageView.setImageResource(R.drawable.tree);
                break;
            case PIT1:
                imageView.setImageResource(R.drawable.pit);
                break;
            case PIT2:
                imageView.setImageResource(R.drawable.pit);
                break;
            case PIT3:
                imageView.setImageResource(R.drawable.pit);
                break;
            case PIT4:
                imageView.setImageResource(R.drawable.pit);
                break;
            case PIT5:
                imageView.setImageResource(R.drawable.pit);
                break;
            case PIT6:
                imageView.setImageResource(R.drawable.pit);
                break;
            case EXIT:
                imageView.setImageResource(R.drawable.exit);
                break;
            case ROCK:
                imageView.setImageResource(R.drawable.mountain);
                break;
            case SAND:
                imageView.setImageResource(R.drawable.sand);
                break;
            case ARSENAL:
                imageView.setImageResource(R.drawable.arsenal);
                break;
            case TREASURE:
                imageView.setImageResource(R.drawable.treasure);
                break;
        }
    }
}
