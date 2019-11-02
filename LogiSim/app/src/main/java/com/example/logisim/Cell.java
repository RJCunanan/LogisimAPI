/*
 Team API:
 Jaime Rivera
 RJ Cunanan
 Theodora Fernandez
 Yong Yang

 Group Project: LogiSim
 Class: CSC 131
 Date: 10-30-19
 */

package com.example.logisim;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell {
    // Keeps information about the cell's position
    // In terms of both the grid plot and the pixels
    Point cellPosition;
    int cellX, cellY, cellWidth, cellHeight;
    boolean state = false;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;

    // Stores the two node attached to its tail
    Cell a,b;
    List<Cell> head = new ArrayList<>();

    public Cell() {}

    public Cell(Point position, int cellSize) {
        // Stores information of cell's location
        cellPosition = position;
        cellX = position.x * cellSize;
        cellY = position.y * cellSize;
        cellWidth = cellX + cellSize;
        cellHeight = cellY + cellSize;

        // used for drawing the image within a set area
        mRect = new RectF(cellX,
                cellY,
                cellWidth,
                cellHeight);

        bitmap = null;
        a = b = null;
    }

    public Cell(int x, int y, int w, int h, Point cellPosition, Cell a, Cell b, List<Cell> head, boolean state)
    {cellX = x; cellY = y; cellWidth = w; cellHeight = h; this.cellPosition = cellPosition; this.a = a; this.b = b; this.head = head; this.state = state;}

    public Cell(Cell myCell)
    {this(myCell.cellX, myCell.cellY, myCell.cellWidth, myCell.cellHeight, myCell.cellPosition, myCell.a, myCell.b, myCell.head, myCell.state);}

    void drawCell(Paint paint, Canvas canvas, Context context) {};

    void setCellPositionCoor(int x, int y, int w, int h) {cellX = x; cellY = y; cellWidth = w; cellHeight = h;}

    // Returns the gate Number unique each type of cell (gates/switch/lamp)
    int getGateNum() {return -1;}

    // Setters and Getters for the Cells attached to current Cell
    Cell getCellA() {return a;}
    Cell getCellB() {return b;}
    void setCellA(Cell A) { a = A; }
    void setCellB(Cell B) { b = B; }
    void deleteCellA() { a = null; }
    void deleteCellB() { b = null; }

    void addHead(Cell H) {head.add(H);}

    void deleteConnections() {
        for(Cell currentHead : head) {
            if(this.equals(currentHead.getCellA()))
                currentHead.deleteCellA();
            else
                currentHead.deleteCellB();
        }
        deleteCellA();
        deleteCellB();
    }

    void updateCellPosition(Point p) {
        cellPosition = p;}

    boolean eval() {return false;}
}
