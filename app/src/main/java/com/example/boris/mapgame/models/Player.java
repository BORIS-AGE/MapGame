package com.example.boris.mapgame.models;

import com.example.boris.mapgame.MainActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class Player extends ViewModel {
    public List<LocationModel> usersMap = new ArrayList<>();
    private int position;
    private boolean hasGun = false, hasBomb = false, hasTreasure = false, onMap = false;

    public Player(){
        clearUserMap(MainActivity.lenthOfMAp);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean hasGun() {
        return hasGun;
    }

    public void setHasGun(boolean hasGun) {
        this.hasGun = hasGun;
    }

    public boolean hasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public boolean hasTreasure() {
        return hasTreasure;
    }

    public void setHasTreasure(boolean hasTreasure) {
        this.hasTreasure = hasTreasure;
    }

    public boolean isOnMap() {
        return onMap;
    }

    public void setOnMap(boolean onMap) {
        this.onMap = onMap;
    }

    public void clearUserMap(int lenthOfMAp){
        usersMap.clear();
        for (int i = 0; i < lenthOfMAp * lenthOfMAp; i++) {
            usersMap.add(new LocationModel(MainActivity.Location.DEFAULT));
        }
    }
}
