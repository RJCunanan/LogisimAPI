/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */


package com.example.logisim;
import java.util.ArrayList;


public class Clear {

    // Clears a cell of any circuit items
    public void clearCells(AbstractGridCell[][] cells,
                           Grid grid,
                           ArrayList<AbstractGridCell> componentCoordinates,
                           Wire wire) {

        for (int x = 0; x < grid.getGridWidth(); x++) {
            for (int y = 0; y < (grid.getGridHeight() - 1); y++) {
                cells[x][y] = new GridCell(x, y, "");
            }
        }
        componentCoordinates.clear();
        wire.resetNumberOfComponents();
    }
}