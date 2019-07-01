package com.example.boris.mapgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.boris.mapgame.adapters.RecyclerMainAdapter;
import com.example.boris.mapgame.models.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecycler;
    private List<LocationModel> locationModels;
    private RecyclerMainAdapter recyclerMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDefaults();
        setRecyclerMain();
    }

    private void setRecyclerMain() {
        mainRecycler.setLayoutManager(new GridLayoutManager(this, 7));
        recyclerMainAdapter = new RecyclerMainAdapter(locationModels, this);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setAdapter(recyclerMainAdapter);
    }

    private void setDefaults(){
        mainRecycler = findViewById(R.id.mainRecycler);
        locationModels = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            locationModels.add(new LocationModel());
        }
    }
}
