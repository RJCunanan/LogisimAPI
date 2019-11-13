/*
 Team API:
 Jaime Rivera
 RJ Cunanan
 Theodora Fernandez
 Yong Yang

 Group Project: LogiSim
 Class: CSC 131
 Date: 10-30-19
 */



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
    private String text = "";
    private int buttonXCoordinate;


    public UserInterfaceButtons(int startX,
                                int startY,
                                int buttonWidth,
                                int buttonLength,
                                int currentButton,
                                String text,
                                int buttonXCoordinate) {

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
        this.buttonXCoordinate = buttonXCoordinate;
    }


    public void drawButton(Canvas myCanvas, Paint myPaint, Grid grid) {

        if (selected) {
            // Change the paint color to grey ti indicate selected button
            myPaint.setColor(Color.argb(255, 64, 64, 64));
        }
        else {
            // Set paint color to black
            myPaint.setColor((Color.argb(255, 0, 0, 0)));
        }

        myCanvas.drawRect(buttonXCoordinate * grid.getCellSize(),
                (grid.getGridHeight() - grid.getButtonLength()) * grid.getCellSize(),
                (buttonXCoordinate * grid.getCellSize()) + grid.getCellSize(),
                grid.getGridHeight() * grid.getCellSize(),
                myPaint);

        // Change the paint color to white
        myPaint.setColor(Color.argb(255, 255, 255, 255));

        // Formats the size of the text to be displayed on the button
        myPaint.setTextSize(grid.getCellSize() / (float)3.7);

        myCanvas.drawText(text, (grid.getButtonWidth() * buttonXCoordinate) * grid.getCellSize(),
                (grid.getGridHeight() - (float) grid.getButtonLength() / 2) * grid.getCellSize(),
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
}
