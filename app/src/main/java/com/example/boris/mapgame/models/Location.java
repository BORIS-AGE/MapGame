package com.example.boris.mapgame.models;

import android.widget.ImageView;

public abstract class Location {
    public enum LocationType{ROCK, FOREST, RIVER, ARSENAL, TREASURE, SAND, EXIT, PIT, DEFAULT}

    private String name;
    private LocationType type;
    private boolean hasTopWall, hasBotWall, hasRightWall, hasLeftWall;

    abstract public void standPlayer(Player player);

    abstract public void setImageView(ImageView imageView);

    void setLocationName(String name){
        this.name = name;
    }

    public void setType(LocationType type) {
        this.type = type;
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

    public LocationType getType() {
        return type;
    }
}
