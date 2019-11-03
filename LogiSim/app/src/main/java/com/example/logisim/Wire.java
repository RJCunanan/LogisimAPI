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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;


public class Wire {

    private AbstractGridCell firstComponent;
    private boolean firstComponentConnected = false;
    private int numberOfComponents = 0;
    private ArrayList<AbstractGridCell> componentCoordinates = new ArrayList<AbstractGridCell>(100);


    // Determine whether the component selected to be connected with a wire is the source component
    // or the component receiving input from the source.
    public void checkForConnectedComponents(AbstractGridCell[][] cells, UserSelection selection) {

        // Check if first component to be connected with wire
        if (!firstComponentConnected && cells[selection.getXCoordinate()][selection.getYCoordinate()].isOccupiedCell()) {

            // Save component's coordinates for drawing wire later
            componentCoordinates.add(cells[selection.getXCoordinate()][selection.getYCoordinate()]);

            // Add to total number of components to be connected
            numberOfComponents++;

            // Save component for later assignment as a source for other circuit components
            firstComponent = componentCoordinates.get(numberOfComponents - 1);

            // indicate component has been selected for connection
            firstComponentConnected = true;
        }
        // Check if second component to be connected with wire
        else if ((cells[selection.getXCoordinate()][selection.getYCoordinate()].isOccupiedCell()) && (cells[selection.getXCoordinate()][selection.getYCoordinate()] != firstComponent)) {

            // Save component's coordinates for drawing wire between two components.
            componentCoordinates.add(cells[selection.getXCoordinate()][selection.getYCoordinate()]);

            // Assign previous component as this component's source.
            cells[selection.getXCoordinate()][selection.getYCoordinate()].setSourceComponents(firstComponent);

            // Add to total number of components connected.
            numberOfComponents++;

            // Indicates that this component has been connected to another and has been wired.
            componentCoordinates.get(numberOfComponents - 1).setConnectedCell();

            // Allows for selection of new source component to be wired.
            resetCheckForFirstComponents();
        }
    }


    // Allows for selection of new source component to be wired.
    private void resetCheckForFirstComponents() {
        firstComponentConnected = false;
    }


    // Draws a blue wire between two selected components
    public void connectTwoComponents(Grid grid, Canvas myCanvas, Paint myPaint) {

        // Change the paint color to blue
        myPaint.setColor(Color.argb(255, 0, 0, 255));

        // Change the Line Width
        myPaint.setStrokeWidth(20F);

        // Cycle through saved components and connect two components with wire
        for (int i = 0; i < componentCoordinates.size(); i++) {
            if ((componentCoordinates.get(i).getConnectedCell()) && i % 2 != 0) {
                // Draws the blue wire between two components.
                myCanvas.drawLine(componentCoordinates.get(i - 1).getXCoordinate() * grid.getBlockSize() + (grid.getBlockSize()),
                        componentCoordinates.get(i - 1).getYCoordinate() * grid.getBlockSize() + (grid.getBlockSize() / 2),
                        componentCoordinates.get(i).getXCoordinate() * grid.getBlockSize(),
                        componentCoordinates.get(i).getYCoordinate() * grid.getBlockSize() + (grid.getBlockSize() / 2),
                        myPaint);
            }
        }
    }


    // Necessary getter methods:
    public ArrayList<AbstractGridCell> getComponentCoordinates() {
        return componentCoordinates;
    }

    public int getNumberOfComponents() {
        return numberOfComponents;
    }

    public void resetNumberOfComponents() {
        numberOfComponents = 0;
    }
}

