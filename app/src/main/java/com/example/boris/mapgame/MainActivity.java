package com.example.boris.mapgame;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boris.mapgame.adapters.RecyclerMainAdapter;
import com.example.boris.mapgame.models.Arsenal;
import com.example.boris.mapgame.models.Exit;
import com.example.boris.mapgame.models.Forest;
import com.example.boris.mapgame.models.Location;
import com.example.boris.mapgame.models.Location.LocationType;
import com.example.boris.mapgame.models.Mountain;
import com.example.boris.mapgame.models.Pit;
import com.example.boris.mapgame.models.Player;
import com.example.boris.mapgame.models.River;
import com.example.boris.mapgame.models.Sand;
import com.example.boris.mapgame.models.Treasure;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int lenthOfMAp = 7;
    // can be replaced with LocationFactory.get{locationName}() but there is no matter
    public Location[][] map = {
            {new Forest(),          new Forest(),           new Pit(1),         new Forest(),           new Sand(),             new Sand(),             new River(River.TOP)},
            {new Sand(),            new Sand(),             new Forest(),           new Sand(),             new River(River.RIGHT), new River(River.RIGHT), new River(River.TOP)},
            {new Sand(),            new Forest(),           new Sand(),             new Sand(),             new River(River.TOP),   new Sand(),             new Forest()},
            {new Pit(2),        new Sand(),             new River(River.RIGHT), new River(River.RIGHT), new River(River.TOP),   new Sand(),             new Forest()},
            {new Sand(),            new Sand(),             new River(River.TOP),   new Treasure(),         new Mountain(),         new Sand(),             new Arsenal()},
            {new River(River.RIGHT),new River(River.RIGHT), new River(River.TOP),   new Mountain(),         new Sand(),             new Sand(),             new Forest()},
            {new River(River.TOP),  new Sand(),             new Sand(),             new Forest(),           new Forest(),           new Exit(),             new Pit(3)}
    };
    public ConstraintLayout mainLay;
    public int phoneWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private ScaleGestureDetector mScaleGestureDetector;
    private GestureDetector gestureDetector;
    private RecyclerView mainRecycler;
    private RecyclerMainAdapter recyclerMainAdapter;
    private GameLogic gameLogic;
    private Player player;

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
        recyclerMainAdapter = new RecyclerMainAdapter(this, gameLogic, player);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setAdapter(recyclerMainAdapter);
    }

    private void setDefaults() {
        mainLay = findViewById(R.id.mainLayout);
        mainRecycler = findViewById(R.id.mainRecycler);
        List<Location> locations = new ArrayList<>();
        player = ViewModelProviders.of(this).get(Player.class);
        player.setDefaults(this, locations);
        for (Location[] area : map) {
            for (Location l : area) {
                if (l.getType() == LocationType.TREASURE) {
                    l.setHasTopWall(true);
                    l.setHasBotWall(true);
                    l.setHasLeftWall(true);
                    l.setHasRightWall(true);
                }
                locations.add(l);
            }
        }

        gameLogic = new GameLogic(player, this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setScrollView() {
        //for moving ability
        final HorizontalScrollView hScroll = findViewById(R.id.scrollHorizontal);
        final ScrollView vScroll = findViewById(R.id.scrollVertical);
        vScroll.setOnTouchListener((v, event) -> false);
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

    public void playerMove(View view) {
        if (recyclerMainAdapter.player.isOnMap())
            switch (view.getId()) {
                case R.id.buttonUp:
                    gameLogic.moveUp();
                    break;
                case R.id.buttonDown:
                    gameLogic.moveDown();
                    break;
                case R.id.buttonLeft:
                    gameLogic.moveLeft();
                    break;
                case R.id.buttonRight:
                    gameLogic.moveRight();
                    break;
            }
    }
}
