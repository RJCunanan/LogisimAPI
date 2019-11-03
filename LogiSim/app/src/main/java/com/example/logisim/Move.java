/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */


package com.example.logisim;
import java.util.ArrayList;


public class Move {

    private int previousTouchX;
    private int previousTouchY;
    private boolean itemSelected = false;



    public void moveComponent(AbstractGridCell[][] cells,
                              UserSelection selection,
                              ArrayList<AbstractGridCell> coordinates) {

        if (!itemSelected) {
            previousTouchX = selection.getXCoordinate();
            previousTouchY = selection.getYCoordinate();
            itemSelected = true;
        }
        else {
            if (cells[previousTouchX][previousTouchY] instanceof Switch) {
                cells[selection.getXCoordinate()][selection.getYCoordinate()] =
                        new Switch(selection.getXCoordinate(), selection.getYCoordinate(), "");
            }
            else if (cells[previousTouchX][previousTouchY] instanceof AND) {
                cells[selection.getXCoordinate()][selection.getYCoordinate()] =
                        new AND(selection.getXCoordinate(), selection.getYCoordinate(), "");
            }
            else if (cells[previousTouchX][previousTouchY] instanceof OR) {
                cells[selection.getXCoordinate()][selection.getYCoordinate()] =
                        new OR(selection.getXCoordinate(), selection.getYCoordinate(), "");
            }
            else if (cells[previousTouchX][previousTouchY] instanceof NOT) {
                cells[selection.getXCoordinate()][selection.getYCoordinate()] =
                        new NOT(selection.getXCoordinate(), selection.getYCoordinate(), "");
            }
            else if (cells[previousTouchX][previousTouchY] instanceof LED){
                cells[selection.getXCoordinate()][selection.getYCoordinate()] =
                        new LED(selection.getXCoordinate(), selection.getYCoordinate(), "");
            }

            for (int i = 0; i < coordinates.size(); i++) {
                if ((coordinates.get(i).getXCoordinate() == previousTouchX) && (coordinates.get(i).getYCoordinate() == previousTouchY)) {

                    coordinates.get(i).setXCoordinate(selection.getXCoordinate());
                    coordinates.get(i).setYCoordinate(selection.getYCoordinate());

                }
            }

            cells[previousTouchX][previousTouchY] = new GridCell(previousTouchX, previousTouchY, "");
            itemSelected = false;

        }
    }
}
