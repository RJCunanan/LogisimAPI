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

public class EmptyCell extends Cell {

    public EmptyCell(Point position, int cellSize) {
        super(position, cellSize);
    }


    public EmptyCell(Cell myCell) {
        super(myCell);}
}
