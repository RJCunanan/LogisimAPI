/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */


package com.example.logisim;
import java.util.ArrayList;


public class Delete {

    public void deleteComponent(AbstractGridCell[][] cells,
                                UserSelection selection,
                                ArrayList<AbstractGridCell> componentCoordinates,
                                Wire wire) {

        cells[selection.getXCoordinate()][selection.getYCoordinate()] = new GridCell(selection.getXCoordinate(), selection.getYCoordinate(), "");
        componentCoordinates.clear();
        wire.resetNumberOfComponents();

    }

}
