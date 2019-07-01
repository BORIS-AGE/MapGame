package com.example.boris.mapgame.models;

import com.example.boris.mapgame.MainActivity;

//rock, wall, forest(loose arsenal), river, pit, bomb(arsenal + pistol), treasure, sand(can shoot through sand and lose 1 step and treasure), exit
public class LocationModel {
    private MainActivity.Location location;
    private MainActivity.Mode mode = MainActivity.Mode.NORMAL;

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
}
