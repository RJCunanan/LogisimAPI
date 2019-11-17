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
import android.graphics.Point;


public class ComponentMover {

    void moveCells(Point newTouchPosition, int newTapN, Grid grid) {
        // Create a hold variable of the previous Tap
        Cell newCell = grid.getCellList().get(newTapN);
        Cell hold = grid.getCellList().get(grid.getPreviousTouchN());

        // Update the hold Cell's touch position and move it to the right position in the cellList
        // Set the previous Touch to an empty cell
        grid.getCellList().set(grid.getPreviousTouchN(),new EmptyCell(grid.getCellList().get(grid.getPreviousTouchN())));
        grid.getCellList().get(grid.getPreviousTouchN()).a = null;
        grid.getCellList().get(grid.getPreviousTouchN()).b = null;
        hold.setCellPositionCoor(newCell.cellX, newCell.cellY, newCell.cellWidth, newCell.cellHeight);
        hold.updateCellPosition(new Point(newTouchPosition));
        grid.getCellList().set(newTapN, hold);
    }

}
