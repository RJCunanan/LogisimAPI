/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */


package com.example.logisim;
import java.util.ArrayList;


// Used to create an object that saves the current circuit so that it may be loaded onto
// the grid at a later time
public class SavedCircuit {

    private AbstractGridCell[][] circuit;
    private ArrayList<AbstractGridCell> coordinates;


    // Takes the 2-D array that holds the current circuit setup on the grid as well as the ArrayList
    // containing the components that are connected via wires and copies each element into a new 2-D
    // array and a new ArrayList for storage and for loading the circuit back onto the grid later
    public SavedCircuit(AbstractGridCell[][] cells, Grid grid, ArrayList<AbstractGridCell> coordinates) {

        // Creates new 2-D array and ArrayList for storage
        circuit = new AbstractGridCell[grid.getGridWidth()][grid.getGridHeight()];
        this.coordinates = new ArrayList<AbstractGridCell>(100);

        // Copy the current circuit on the gird into the new 2-D array
        for (int x = 0; x < grid.getGridWidth(); x++) {
            for (int y = 0; y < grid.getGridHeight(); y++) {
                circuit[x][y] = cells[x][y];
            }
        }

        // Copy all the wired components into the new ArrayList
        for (int i = 0; i < coordinates.size(); i++) {
            this.coordinates.add(coordinates.get(i));
        }
    }


    // Loads the Saved circuit back onto the grid
    public void loadCircuit(AbstractGridCell[][] cells, Grid grid, ArrayList<AbstractGridCell> coordinates) {

        // Cycles through and loads each saved element back onto the grid
        for (int x = 0; x < grid.getGridWidth(); x++) {
            for (int y = 0; y < grid.getGridHeight(); y++) {
                cells[x][y] = circuit[x][y];
            }
        }

        // Clears all current wired components in the ArrayList before loading in the saved components
        coordinates.clear();

        // Cycles through and loads all the components connected with wires
        for (int i = 0; i < this.coordinates.size(); i++) {
            coordinates.add(i, this.coordinates.get(i));
        }
    }


}
