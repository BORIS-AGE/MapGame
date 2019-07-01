package com.example.boris.mapgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.boris.mapgame.adapters.RecyclerMainAdapter;
import com.example.boris.mapgame.models.LocationModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public enum Location{ROCK, WALL, FOREST, RIVER, PIT, ARSENAL, TREASURE, SAND, EXIT}
    public enum Mode{NORMAL, MODE1, MODE2, MODE3}

    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector gestureDetector;
    private RecyclerView mainRecycler;
    private List<LocationModel> locationModels;
    private RecyclerMainAdapter recyclerMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setScrollView();
        setDefaults();
        setRecyclerMain();
    }


    private void setRecyclerMain() {
        mainRecycler.setLayoutManager(new GridLayoutManager(this, 15));
        recyclerMainAdapter = new RecyclerMainAdapter(locationModels, this);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setAdapter(recyclerMainAdapter);
    }

    private void setDefaults(){
        mainRecycler = findViewById(R.id.mainRecycler);
        locationModels = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            locationModels.add(new LocationModel(Location.ARSENAL));
        }
    }
    private void setScrollView() {
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
                //float prevScale = mScale;
                //mScale += scale;

                if (scale > 0)
                    recyclerMainAdapter.setNewSize(5);
                else
                    recyclerMainAdapter.setNewSize(-5);

                /*ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevScale, 1f / mScale, 1f / prevScale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);
                hScroll.startAnimation(scaleAnimation);*/
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
        Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_LONG).show();
    }
}
