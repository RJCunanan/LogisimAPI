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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class UserInterfaceButtons{

    private int buttonInList;
    private List<Point> occupiedCells;
    private boolean selected;
    private String text;    // holds the text for the button
    private int buttonYCoordinate;  // holds the x-coordinate for the button at the
                                    // bottom of the screen


    // Constructor:
    public UserInterfaceButtons(int startX,
                                int startY,
                                int buttonWidth,
                                int buttonLength,
                                int currentButton,
                                String text,
                                int buttonYCoordinate) {

        occupiedCells = new ArrayList<>(buttonLength*buttonWidth);

        // fill the arraylist with all grid cells that corresponds to tapping the button
        for(int i = 0; i < buttonWidth; i++) {
            for (int j = 0; j < buttonLength; j++) {
                Point currentPos = new Point(startX+i,startY+j);
                occupiedCells.add(currentPos);
            }
        }

        selected = false;

        // each button is given their order in the list. The button in the list corresponds to
        // an int value found within the grid class
        buttonInList = currentButton;

        this.text = text;
        this.buttonYCoordinate = buttonYCoordinate;
    }


    // This method draws the actual UI button at the bottom of the screen along
    // with that button's text.
    public void drawButton(Canvas myCanvas, Paint myPaint, Grid grid) {

        //If this button is selected by the user, color the button grey. Otherwise,
        //color the button black to indicate it has not been selected yet.
        if (selected) {
            // Change the paint color to grey to indicate selected button
            myPaint.setColor(Color.argb(255, 64, 64, 64));
        }
        else {
            // Set paint color to black
            myPaint.setColor((Color.argb(255, 0, 0, 0)));
        }

        // Draw the button
        myCanvas.drawRect(0,
                buttonYCoordinate * grid.getCellSize(),
                grid.getCellSize(),
                buttonYCoordinate * grid.getCellSize() + grid.getCellSize(),
                myPaint);

        // Change the paint color to white
        myPaint.setColor(Color.argb(255, 255, 255, 255));

        // Formats the size of the text to be displayed on the button
        myPaint.setTextSize(grid.getCellSize() / (float)3.4);

        // Draw the button's text
        myCanvas.drawText(text, 0,
                (buttonYCoordinate * grid.getCellSize()) + (grid.getCellSize() / 2),
                myPaint);
    }


    // toggles the button on and off
    void toggleButton() {selected = !selected;}


    // determines if the player's selected grid cell falls within the button's list of grid cells
    void wasITouched(Point tap) {
        Point currentOccupiedCell;
        for(int i = 0; i < occupiedCells.size(); i++) {
            currentOccupiedCell = occupiedCells.get(i);
            if (currentOccupiedCell.equals(tap)) {
                toggleButton();
            }
        }
    }


    // returns the location of the button in the list
    int getButtonInList() {return buttonInList;}

    // returns if the button is selected or not
    boolean getSelected() {return selected;}

    // Getter method that returns the button's x-coordinate on the grid
    int getButtonYCoordinate() {
        return buttonYCoordinate;
    }

    // Marks this button as not selected by the user
    void clearButtonSelection() {
        selected = false;
    }
}
