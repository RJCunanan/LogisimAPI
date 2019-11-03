/*
    Team API:
        Jaime Rivera
        RJ Cunanan
        Theodora Fernandez
        Yong Yang
    Course: CSC 131
    Assignment: LogiSim
    Date: 11-2-19
 */


package com.example.logisim;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;


public class LogiSim extends Activity {

    // All the necessary objects needed to create and run
    // a Logic simulator:
    private Grid grid;
    private UserSelection selection;

    // Here are all the objects(instances)
    // of classes that we need to do some drawing
    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;
    Context context;


    // Get the screen resolution and based on its dimensions
    // create a grid and cells object to hold the cells within
    // that make the grid.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the current device's screen resolution
        Point size = new Point();
        determineScreenResolution(size);

        // Create grid object and other objects used later
        //  to draw the grid.
        paint = new Paint();
        grid = new Grid(size, canvas, paint);
        blankBitmap = Bitmap.createBitmap(grid.getNumberHorizontalPixels(),
                grid.getNumberVerticalPixels(),
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);

        // Set the context
        context = this;

        // Create object to hold user's selection, create wires, and delete circuits.
        selection = new UserSelection();

        // Prepare to draw the UI.
        grid.setCanvas(canvas);
        setContentView(gameView);
        draw();
    }


    // Determines the dimensions/resolution of the screen
    void determineScreenResolution(Point size) {
        // Get the current device's screen resolution
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
    }


    // Used to draw the UI and circuit
    void draw() {
        gameView.setImageBitmap(blankBitmap);

        // Draw the grid with all its cells on the screen

        grid.drawGrid(canvas, paint, context);

        // Draw the Taskbar buttons on top row of grid
        grid.drawTaskbarButtons();

    }


    // Process location where the user taps on the screen
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // Has the player removed their finger from the screen?
        if((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {

            // Process the player's selection by passing the
            // coordinates of the player's finger to makeSelection()
            selection.setTouchX(motionEvent.getX());
            selection.setTouchY(motionEvent.getY());
            makeSelection();
        }

        return true;
    }


    // Process user's selection of a taskbar button or placement
    // of an item onto the grid
    public void makeSelection() {

        // Convert the float screen coordinates
        // into int grid coordinates
        selection.convertSelectionToGridCoordinates(grid);

        // Determine which taskbar button the user has selected
        selection.determineButtonSelection(grid.getCells(), grid, grid.getClear(), grid.getWire());

        // Put selected circuit item onto the grid
        selection.placeItem(grid.getCells(), grid, grid.getWire(), grid.getDelete(), grid.getMove());

        // Redraw the grid with the new circuit items
        draw();
    }

}
