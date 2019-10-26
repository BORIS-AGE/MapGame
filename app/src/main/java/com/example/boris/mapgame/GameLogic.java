package com.example.boris.mapgame;

import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.boris.mapgame.models.Arsenal;
import com.example.boris.mapgame.models.Exit;
import com.example.boris.mapgame.models.Forest;
import com.example.boris.mapgame.models.Mountain;
import com.example.boris.mapgame.models.Pit;
import com.example.boris.mapgame.models.Player;
import com.example.boris.mapgame.models.River;
import com.example.boris.mapgame.models.Sand;
import com.example.boris.mapgame.models.Treasure;

public class GameLogic {

    private Player player;
    private MainActivity mainActivity;

    public GameLogic(Player player, MainActivity mainActivity) {
        this.player = player;
        this.mainActivity = mainActivity;
    }

    /*public void notifyLocationType(int position, String additional) {
        switch (player.getMap().get(position).getLocation()) {
            case RIVERT:
                player.makeNotification("River" + additional);
                break;
            case RIVERR:
                player.makeNotification("River" + additional);
                break;
            case RIVERB:
                player.makeNotification("River" + additional);
                break;
            case RIVERL:
                player.makeNotification("River" + additional);
                break;
            case FOREST:
                player.makeNotification("Forest" + additional);
                break;
            case PIT1:
                player.makeNotification("Pit1" + additional);
                break;
            case EXIT:
                player.makeNotification("Exit" + additional);
                break;
            case ROCK:
                player.makeNotification("Mountain" + additional);
                break;
            case SAND:
                player.makeNotification("Sand" + additional);
                break;
            case ARSENAL:
                player.makeNotification("Arsenal" + additional);
                break;
            case TREASURE:
                player.makeNotification("Treasure" + additional);
                break;
        }
    }*/

    public void moveUp() {
        if (player.getPosition() - mainActivity.lenthOfMAp >= 0) { 
            if (!player.getMap().get(player.getPosition()).hasTopWall() && !player.getMap().get(player.getPosition() - mainActivity.lenthOfMAp).hasBotWall()) {
                player.setPosition(player.getPosition() - mainActivity.lenthOfMAp);
                performPlayerMove();
            } else {
                if (player.hasBomb()) {
                    player.getMap().get(player.getPosition()).setHasTopWall(false);
                    player.getMap().get(player.getPosition() - mainActivity.lenthOfMAp).setHasBotWall(false);
                    player.makeNotification("you have destroyed the wall");
                } else
                    player.makeNotification("can't move because of wall");
            }
        } else {
            player.makeNotification("end of map");
        }
    }

    public void moveDown() {
        if (player.getPosition() + mainActivity.lenthOfMAp <= mainActivity.lenthOfMAp * mainActivity.lenthOfMAp) {
            if (!player.getMap().get(player.getPosition()).hasBotWall() && !player.getMap().get(player.getPosition() + mainActivity.lenthOfMAp).hasTopWall()) {
                player.setPosition(player.getPosition() + mainActivity.lenthOfMAp);
                performPlayerMove();
            } else {
                if (player.hasBomb()) {
                    player.getMap().get(player.getPosition()).setHasBotWall(false);
                    player.getMap().get(player.getPosition() + mainActivity.lenthOfMAp).setHasTopWall(false);
                    player.makeNotification("you have destroyed the wall");
                } else
                    player.makeNotification("can't move because of wall");
            }
        } else {
            player.makeNotification("end of map");
        }
    }

    public void moveLeft() {
        if ((player.getPosition() % 7) - 1 >= 0) {
            if (!player.getMap().get(player.getPosition()).hasLeftWall() && !player.getMap().get(player.getPosition() - 1).hasRightWall()) {
                player.setPosition(player.getPosition() - 1);
                performPlayerMove();
            } else {
                if (player.hasBomb()) {
                    player.getMap().get(player.getPosition()).setHasLeftWall(false);
                    player.getMap().get(player.getPosition() - 1).setHasRightWall(false);
                    player.makeNotification("you have destroyed the wall");
                } else
                    player.makeNotification("can't move because of wall");
            }
        } else {
            player.makeNotification("end of map");
        }
    }

    public void moveRight() {
        if ((player.getPosition() % 7) + 1 <= 6) {
            if (!player.getMap().get(player.getPosition()).hasRightWall() && !player.getMap().get(player.getPosition() + 1).hasLeftWall()) {
                player.setPosition(player.getPosition() + 1);
                performPlayerMove();
            } else {
                if (player.hasBomb()) {
                    player.getMap().get(player.getPosition()).setHasRightWall(false);
                    player.getMap().get(player.getPosition() + 1).setHasLeftWall(false);
                    player.makeNotification("you have destroyed the wall");
                } else
                    player.makeNotification("can't move because of wall");
            }
        } else {
            player.makeNotification("end of map");
        }
    }

    private void performPlayerMove() {
        player.getMap().get(player.getPosition()).standPlayer(player);
    }


    public PopupMenu setUpPopupMenuItem(int position, View itemView, ImageView imageView) {
        PopupMenu popupMenu = new PopupMenu(mainActivity, itemView); 
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) { 
                case R.id.RIVER:
                    player.getUsersMap().set(position, new River(River.BOT));
                    break;
                case R.id.ROCK:
                    player.getUsersMap().set(position, new Mountain());
                    break;
                case R.id.FOREST:
                    player.getUsersMap().set(position, new Forest());
                    break;
                case R.id.ARSENAL:
                    player.getUsersMap().set(position, new Arsenal());
                    break;
                case R.id.TREASURE:
                    player.getUsersMap().set(position, new Treasure());
                    break;
                case R.id.SAND:
                    player.getUsersMap().set(position, new Sand());
                    break;
                case R.id.EXIT:
                    player.getUsersMap().set(position, new Exit());
                    break;
                case R.id.PIT1:
                    player.getUsersMap().set(position, new Pit(1));
                    break;
            }
            setImageView(imageView, position);
            return true;
        });
        return popupMenu;
    }

/*    public PopupMenu setUpPopupMenuWalls(int position, View itemView, ImageView imageView) {
        PopupMenu popupMenu = new PopupMenu(mainActivity, itemView);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.topWall:
                    player.getUsersMap().get(position).setHasTopWall(true);
                    break;
                case R.id.botWall:
                    player.getUsersMap().get(position).setHasBotWall(true);
                    break;
                case R.id.leftWall:
                    player.getUsersMap().get(position).setHasLeftWall(true);
                    break;
                case R.id.rightWall:
                    player.getUsersMap().get(position).setHasRightWall(true);
                    break;
            }

            return true;
        });
        return popupMenu;
    }*/

    public void setImageView(ImageView imageView, int position) {
        player.getUsersMap().get(position).setImageView(imageView);
    }

}
