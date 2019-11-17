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


public class Wire {

    void linkCells(int newTapN, Grid grid) {
        // gets the current Cell of the user's tap
        Cell currCell = grid.getCellList().get(newTapN);

        grid.getCellList().get(grid.getPreviousTouchN()).addHead(grid.getCellList().get(newTapN));

        // create a link between the current cell pointing to the previous cell if a spot is open.
        if(currCell.getCellA() == null)
            grid.getCellList().get(newTapN).setCellA(grid.getCellList().get(grid.getPreviousTouchN()));
        else if(currCell.getCellB() == null)
            grid.getCellList().get(newTapN).setCellB(grid.getCellList().get(grid.getPreviousTouchN()));
    }

}
