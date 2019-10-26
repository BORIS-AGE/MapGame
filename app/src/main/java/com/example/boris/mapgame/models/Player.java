package com.example.boris.mapgame.models;

import android.content.Context;
import android.view.View;

import com.example.boris.mapgame.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class Player extends ViewModel {
    public List<Location> usersMap = new ArrayList<>();
    private int position;
    private boolean hasGun = false, hasBomb = false, hasTreasure = false, onMap = false;
    private List<Location> map;
    private Context context;
    public void setDefaults(Context context, List<Location> locations){
        this.context = context;
        this.map = locations;
        clearUserMap();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Location> getMap() {
        return map;
    }

    public List<Location> getUsersMap() {
        return usersMap;
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

    public void clearUserMap(){
        usersMap.clear();
        for (int i = 0; i < MainActivity.lenthOfMAp * MainActivity.lenthOfMAp; i++) {
            usersMap.add(new Default());
        }
    }

    public void makeNotification(String not) {
        Snackbar.make(((MainActivity)context).mainLay, not, Snackbar.LENGTH_INDEFINITE)
                .setAction("close", v -> {

                })
                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }
}
