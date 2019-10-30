// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Name:  Jaime Rivera
// Date:  9/27/2019
// Class: CSC131
// ID:    301427334
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package com.example.logisim;

import android.graphics.Point;

public class EmptyCell extends Cell {
    public EmptyCell(Point position, int cellSize) {
        super(position, cellSize);
    }

    public EmptyCell(Cell myCell) {
        super(myCell);}
}
