/**
 * Team API:
 *      Jaime Rivera
 *      RJ Cunanan
 *      Theodora Fernandez
 *      Yong Yang
 *
 * Group Project:
 *      LogiSim
 *
 * Class:
 *      CSC 131
 *
 * Date Modified:
 *      11-16-19
 **/


package com.example.logisim;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class Logisim extends Activity {

    Point gridSize;
    Point touchPosition = new Point(-100,-100);

    // The brains of the Logisim
    Grid grid;
    UserSelection selection;
    Undo undoSave;

    // Here are all the objects(instances)
    // of classes that we need to do some drawing
    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the current device's screen resolution
        Display display = getWindowManager().getDefaultDisplay();

        gridSize = new Point();
        display.getSize(gridSize);

        // Initialize all the objects ready for drawing
        blankBitmap = Bitmap.createBitmap(gridSize.x, gridSize.y, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();

        // Tell Android to set our drawing
        // as the view for this app
        setContentView(gameView);

        // Construct the grid and scoreboard class and give them access to drawing on the screen
        grid = new Grid(this, gridSize, canvas, paint, gameView, blankBitmap);
        undoSave= new Undo(grid, grid.cellList);
        selection = new UserSelection(undoSave);
        draw();

        //Media Player definition
        final MediaPlayer elevatorOn = MediaPlayer.create(this, R.raw.elevator_music);
        elevatorOn.start();
        elevatorOn.setLooping(true);

    }



    void draw() {
        grid.drawGrid();
        grid.drawUI();
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // Has the player removed their finger from the screen?
        if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {

            // Calculate the player's touch in terms of the grid squares
            setTouchPosition(motionEvent.getX(), motionEvent.getY());

            // The grid will determine what to do with this player's touch
            selection.determineUserSelection(touchPosition, grid);
            draw();
            //Media Player definition
            if (touchPosition.x <= grid.getButtonLength() && touchPosition.y >= 0) {
                final MediaPlayer clickOn = MediaPlayer.create(this, R.raw.click_on);
                clickOn.start();
            }

        }
        return true;
    }



    // Fills Point with grid position
    void setTouchPosition(float touchX, float touchY) {
        touchPosition.x = (int) touchX / grid.cellSize;
        touchPosition.y = (int) touchY / grid.cellSize;
    }
}
