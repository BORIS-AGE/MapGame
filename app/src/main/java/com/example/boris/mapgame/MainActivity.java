package com.example.boris.mapgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.boris.mapgame.adapters.RecyclerMainAdapter;
import com.example.boris.mapgame.models.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public enum Location{ROCK, FOREST, RIVER, ARSENAL, TREASURE, SAND, EXIT, PIT1, PIT2, PIT3, PIT4, PIT5, PIT6}
    public enum Mode{NORMAL, MODE1, MODE2, MODE3}
    public Location[][] map = {
            {Location.FOREST, Location.FOREST, Location.FOREST, Location.FOREST, Location.SAND, Location.SAND, Location.RIVER},
            {Location.SAND, Location.SAND, Location.FOREST, Location.SAND, Location.RIVER, Location.RIVER, Location.RIVER},
            {Location.SAND, Location.FOREST, Location.SAND, Location.SAND, Location.RIVER, Location.SAND, Location.FOREST},
            {Location.PIT1, Location.SAND, Location.RIVER, Location.RIVER, Location.RIVER, Location.SAND, Location.FOREST},
            {Location.SAND, Location.SAND, Location.RIVER, Location.TREASURE, Location.ROCK, Location.SAND, Location.ARSENAL},
            {Location.RIVER, Location.RIVER, Location.RIVER, Location.ROCK, Location.SAND, Location.SAND, Location.FOREST},
            {Location.RIVER, Location.SAND, Location.SAND, Location.FOREST, Location.FOREST, Location.EXIT, Location.PIT1}
    };

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector gestureDetector;
    private RecyclerView mainRecycler;
    private List<LocationModel> locationModels;
    private RecyclerMainAdapter recyclerMainAdapter;
    public int lenthOfMAp = 7;
    public ConstraintLayout mainLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setScrollView();
        setDefaults();
        setRecyclerMain();
    }


    private void setRecyclerMain() {
        mainRecycler.setLayoutManager(new GridLayoutManager(this, lenthOfMAp));
        recyclerMainAdapter = new RecyclerMainAdapter(locationModels, this);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setAdapter(recyclerMainAdapter);
    }

    private void setDefaults(){
        mainLay = findViewById(R.id.mainLayout);
        mainRecycler = findViewById(R.id.mainRecycler);
        locationModels = new ArrayList<>();
        for (Location[] area : map) {
            for (Location l : area){
                locationModels.add(new LocationModel(l));
            }
        }
    }
    private void setScrollView() {
        //for moving ability
        final HorizontalScrollView hScroll = findViewById(R.id.scrollHorizontal);
        final ScrollView vScroll = findViewById(R.id.scrollVertical);
        vScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        hScroll.setOnTouchListener(new View.OnTouchListener() { //outer scroll listener
            private float mx, my, curX, curY;
            private boolean started = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                curX = event.getX();
                curY = event.getY();
                int dx = (int) (mx - curX);
                int dy = (int) (my - curY);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (started) {
                            vScroll.scrollBy(0, dy);
                            hScroll.scrollBy(dx, 0);
                        } else {
                            started = true;
                        }
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        vScroll.scrollBy(0, dy);
                        hScroll.scrollBy(dx, 0);
                        started = false;
                        break;
                }
                return true;
            }
        });

        //for scaling ability
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return true;
            }
        });
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = detector.getScaleFactor() - 1;

                if (scale > 0)
                    recyclerMainAdapter.setNewSize(5);
                else
                    recyclerMainAdapter.setNewSize(-5);
                return true;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        super.dispatchTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }

    public void makeErrorNotification(String exception){
        System.out.println(exception);
        Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_SHORT).show();
    }

    public void playerMove(View view) {
        if (recyclerMainAdapter.player.isOnMap())
        switch (view.getId()){
            case R.id.buttonUp:
                recyclerMainAdapter.moveUp();
                break;
            case R.id.buttonDown:
                recyclerMainAdapter.moveDown();
                break;
            case R.id.buttonLeft:
                recyclerMainAdapter.moveLeft();
                break;
            case R.id.buttonRight:
                recyclerMainAdapter.moveRight();
                break;
        }
    }
}
