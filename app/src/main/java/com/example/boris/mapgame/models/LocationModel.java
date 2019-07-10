package com.example.boris.mapgame.models;

import com.example.boris.mapgame.MainActivity;

import androidx.lifecycle.ViewModel;

//rock, wall, forest(loose arsenal), river, pit, bomb(arsenal + pistol), treasure, sand(can shoot through sand and lose 1 step and treasure), exit
public class LocationModel {
    private MainActivity.Location location;
    private MainActivity.Mode mode = MainActivity.Mode.NORMAL;
    private boolean hasTopWall, hasBotWall, hasRightWall, hasLeftWall;

    public LocationModel(MainActivity.Location location) {
        this.location = location;
    }

    public MainActivity.Location getLocation() {
        return location;
    }

    public MainActivity.Mode getMode() {
        return mode;
    }

    public void setMode(MainActivity.Mode mode) {
        this.mode = mode;
    }

    public boolean hasTopWall() {
        return hasTopWall;
    }

    public void setHasTopWall(boolean hasTopWall) {
        this.hasTopWall = hasTopWall;
    }

    public boolean hasBotWall() {
        return hasBotWall;
    }

    public void setHasBotWall(boolean hasBotWall) {
        this.hasBotWall = hasBotWall;
    }

    public boolean hasRightWall() {
        return hasRightWall;
    }

    public void setHasRightWall(boolean hasRightWall) {
        this.hasRightWall = hasRightWall;
    }

    public boolean hasLeftWall() {
        return hasLeftWall;
    }

    public void setHasLeftWall(boolean hasLeftWall) {
        this.hasLeftWall = hasLeftWall;
    }
}
