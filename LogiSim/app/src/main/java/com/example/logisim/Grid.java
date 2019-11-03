/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */



package com.example.logisim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;


// Creates the grid for the UI necessary to place items to
// make a circuit
public class Grid {

    private int numberHorizontalPixels;
    private int numberVerticalPixels;
    private int blockSize;
    private int gridWidth = 15;
    private int gridHeight;
    private Canvas myCanvas;
    private Paint myPaint;
    private Point size;
    private final int BOTTOMROW = 8;

    private AbstractGridCell[][] cells;
    private Clear clear;
    private Delete delete;
    private Wire wire;
    private Move move;



    // Initializes the grid based on the screen resolution
    public Grid(Point size, Canvas myCanvas, Paint myPaint)
    {
        // Initialize our size based variables based on the screen resolution
        this.size = size;
        numberHorizontalPixels = this.size.x;
        numberVerticalPixels = this.size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        this.myCanvas = myCanvas;
        this.myPaint = myPaint;

        wire = new Wire();
        clear = new Clear();
        delete = new Delete();
        move = new Move();

        initializeGrid();
        initializeButtons();
    }


    public void initializeGrid()
    {
        // Create the cells object used to hold the different cells that
        // go on the grid
        cells = new AbstractGridCell[getGridWidth()][getGridHeight()];

        // Cycle through the cells object, creating blank cells that will compose
        // the initial blank grid.
        for (int x = 0; x < getGridWidth(); x++) {
            for (int y = 0; y < getGridHeight(); y++) {
                cells[x][y] = new GridCell(x, y, "");
            }
        }
    }


    public void initializeButtons()
    {
        // Create the GridCell objects that will represent buttons in the taskbar
        cells[0][BOTTOMROW] = new GridCell(0, BOTTOMROW, "Switch");
        cells[1][BOTTOMROW] = new GridCell(1, BOTTOMROW, "AND");
        cells[2][BOTTOMROW] = new GridCell(2, BOTTOMROW, "OR");
        cells[3][BOTTOMROW] = new GridCell(3, BOTTOMROW, "NOT");
        cells[4][BOTTOMROW] = new GridCell(4, BOTTOMROW, "LED");
        cells[5][BOTTOMROW] = new GridCell(5, BOTTOMROW, "Wire");
        cells[6][BOTTOMROW] = new GridCell(6, BOTTOMROW, "Move");
        cells[7][BOTTOMROW] = new GridCell(7, BOTTOMROW, "Del");
        cells[8][BOTTOMROW] = new GridCell(8, BOTTOMROW, "Clear");
        cells[9][BOTTOMROW] = new GridCell(9, BOTTOMROW, "Toggle");
        cells[10][BOTTOMROW] = new GridCell(10, BOTTOMROW, "Run");
        cells[11][BOTTOMROW] = new GridCell(11, BOTTOMROW, "Save");
        cells[12][BOTTOMROW] = new GridCell(12, BOTTOMROW, "A");
        cells[13][BOTTOMROW] = new GridCell(13, BOTTOMROW, "B");
        cells[14][BOTTOMROW] = new GridCell(14, BOTTOMROW, "C");
    }


    // Draws the grid on the screen one cell at a time
    public void drawGrid(Canvas canvas, Paint paint, Context context)
    {

        // Wipe the screen with a white color
        myCanvas.drawColor(Color.argb(255, 255, 255, 255));

        // Change the Line Width
        paint.setStrokeWidth(5F);

        // Draw entire grid cells, checking what components currently exit and drawing them in their
        // respective cells. All other grid cells are filled with blanks.
        for (int x = 0; x < getGridWidth(); x++) {
            for (int y = 0; y < getGridHeight(); y++) {

                // Change the paint color to white
                paint.setColor(Color.argb(255, 255, 255, 255));

                // Draw the cell
                cells[x][y].drawCell(this, canvas, paint, context);
            }
        }

        // Draw the wires connecting two components together on the grid.
        wire.connectTwoComponents(this, canvas, paint);
    }


    // Draw the taskbar buttons on the top row of the screen
    public void drawTaskbarButtons() {
        // Now draw only Taskbar button cells
        for (int x = 0; x < getGridWidth(); x++) {

            if (cells[x][BOTTOMROW].getSelected()) {
                // Change the paint color to grey
                myPaint.setColor(Color.argb(255, 64, 64, 64));
            }
            else {
                // Change the paint color to black
                myPaint.setColor(Color.argb(255, 0, 0, 0));
            }

            // Draw each individual button
            cells[x][BOTTOMROW].drawButton(this, myCanvas, myPaint);
        }
    }


    // Necessary getter and setter methods:
    public int getGridHeight() { return gridHeight; }

    public int getGridWidth() { return gridWidth; }

    public int getBlockSize() { return blockSize; }

    public int getNumberHorizontalPixels() { return numberHorizontalPixels; }

    public int getNumberVerticalPixels() { return numberVerticalPixels; }

    public AbstractGridCell[][] getCells() {
        return cells;
    }

    public Clear getClear() {
        return clear;
    }

    public Delete getDelete() {
        return delete;
    }

    public Move getMove() {
        return move;
    }

    public Wire getWire() {
        return wire;
    }

    public void setCanvas(Canvas myCanvas) { this.myCanvas = myCanvas; }
}




