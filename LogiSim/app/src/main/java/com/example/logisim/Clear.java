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