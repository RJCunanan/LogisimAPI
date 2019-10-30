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

import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceButtons{
    int buttonInList;
    List<Point> occupiedCells;
    boolean selected;

    public UserInterfaceButtons(int startX, int startY, int buttonWidth, int buttonLength, int currentButton) {
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
