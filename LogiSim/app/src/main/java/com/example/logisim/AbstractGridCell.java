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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


// Abstract parent class that holds all the different data and methods
// used by all subclasses that are cells of the grid UI. This includes
// cells that hold switches, logic gates, the taskbar buttons, etc.
public abstract class AbstractGridCell {

    private int xCoordinate;
    private int yCoordinate;
    private String text;
    private boolean selected;
    private boolean connectedCell;
    private boolean occupiedCell;
    protected Bitmap bitmap;
    protected RectF mRect;


    // Given x and y coordinates on the grid and any text when
    // applicable, initializes cell's data. It also initializes
    // variable to indicate this new cell has not been selected
    // yet.
    public AbstractGridCell(int x, int y, String text) {

        this.xCoordinate = x;
        this.yCoordinate = y;
        this.text = text;
        this.selected = false;
        this.connectedCell = false;
        this.occupiedCell = false;
    }


    // Draws individual cell at specific location on the grid
    public void drawCell(Grid grid, Canvas myCanvas, Paint myPaint, Context context) {

        // Draws a white grid square
        myCanvas.drawRect(xCoordinate * grid.getBlockSize(),
                yCoordinate * grid.getBlockSize(),
                (xCoordinate * grid.getBlockSize()) + grid.getBlockSize(),
                (yCoordinate * grid.getBlockSize())+ grid.getBlockSize(),
                myPaint );

        // Change the paint color to black
        myPaint.setColor(Color.argb(255, 0, 0, 0));


        // Draws top and left sides of the cell so then when all cells are
        // displayed as a grid, horizontal and vertical grid lines are created.
        myCanvas.drawLine(grid.getBlockSize() * xCoordinate,
                0,
                grid.getBlockSize() * xCoordinate,
                grid.getNumberVerticalPixels(),
                myPaint);

        myCanvas.drawLine(0,
                grid.getBlockSize() * yCoordinate,
                grid.getNumberHorizontalPixels(),
                grid.getBlockSize() * yCoordinate,
                myPaint);

    }


    // Draws the different taskbar buttons located at the top of the
    // screen cell by cell.
    public void drawButton(Grid grid, Canvas myCanvas, Paint myPaint) {

        // Draws a black grid square to represent a taskbar button
        myCanvas.drawRect(xCoordinate * grid.getBlockSize(),
                yCoordinate * grid.getBlockSize(),
                (xCoordinate * grid.getBlockSize()) + grid.getBlockSize(),
                (yCoordinate * grid.getBlockSize())+ grid.getBlockSize(),
                myPaint );

        // Formats the size of the text to be displayed on the button
        myPaint.setTextSize(grid.getBlockSize() / 4);

        // Change the paint color to white
        myPaint.setColor(Color.argb(255, 255, 255, 255));

        // Draw the text on the button
        myCanvas.drawText(text,
                xCoordinate * grid.getBlockSize(),
                (grid.getBlockSize() * 9) - (grid.getBlockSize() / 2),
                myPaint);
    }


    // Takes the previously selected component prior to the currently selected on and assigns that
    // previous component as a source of input for the current component only when the two components
    // are connected via a wire.
    public void setSourceComponents(AbstractGridCell source) {

        // When the current component is a logic AND gate, determine if the previously selected
        // component is the first source of input or the second.
        if (this instanceof AND) {
            if (!((AND) this).determineSourceOneConnection()) {
                ((AND) this).setSourceComponent1(source);
            }
            else if (!((AND) this).determineSourceTwoConnection()) {
                ((AND) this).setSourceComponent2(source);
            }
        }
        // When a logic OR gate, determine if previously selected component is the first or second
        // source of input.
        else if (this instanceof OR) {
            if (!((OR) this).determineSourceOneConnection()) {
                ((OR) this).setSourceComponent1(source);
            }
            else if (!((OR) this).determineSourceTwoConnection()) {
                ((OR) this).setSourceComponent2(source);
            }
        }
        // When a logic NOT gate, assign previously selected component as the single source of input.
        else if (this instanceof NOT) {
            ((NOT) this).setSourceComponent(source);
        }
        // When a lamp, assign previously selected component as the single source of input.
        else if (this instanceof LED) {
            ((LED) this).setSourceComponent(source);
        }
    }


    // Calls on each component's individual evaluate method to determine the state, or whether or not
    // the component will send an output, based on its type of component.
    public boolean evaluateComponent() {
        if (this instanceof Switch) {
            return ((Switch) this).evaluate();
        }
        else if (this instanceof AND) {
            return ((AND) this).evaluate();
        }
        else if (this instanceof OR) {
            return ((OR) this).evaluate();
        }
        else if (this instanceof NOT) {
            return ((NOT) this).evaluate();
        }
        else {
            return ((LED) this).evaluate();
        }
    }


    // Necessary setter and getter methods to indirectly access
    // and assign data:
    public void setButtonSelection() {
        this.selected = true;
    }

    public void clearButtonSelection() {
        this.selected = false;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public String getText() {
        return text;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int x) {
        xCoordinate = x;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int y) {
        yCoordinate = y;
    }

    public void setConnectedCell() {
        connectedCell = true;
    }

    public boolean getConnectedCell() {
        return connectedCell;
    }

    public void setAsOccupiedCell() {
        occupiedCell = true;
    }

    public boolean getOccupiedCell() {
        return occupiedCell;
    }

    // Checks to see if grid cell in occupied by a circuit component.
    public boolean isOccupiedCell() {
        return occupiedCell;
    }

}