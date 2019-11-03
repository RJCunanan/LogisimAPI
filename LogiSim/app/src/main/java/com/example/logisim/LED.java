/*
    Name: RJ Cunanan 9811
    Course: CSC 131
    Assignment: LogiSim
    Date: 10-12-19
 */


package com.example.logisim;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


class LED extends AbstractGridCell {

    private AbstractGridCell sourceComponent;
    private boolean outputState = false;


    // Constructor
    public LED(int x, int y, String text) {
        super(x, y, text);
    }

    // Draws image of lamp
    public void drawCell(Grid grid, Canvas myCanvas, Paint myPaint, Context context) {
        if (!getOutputState()) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.led0);
            mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                    getYCoordinate() * grid.getBlockSize(),
                    (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                    (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
            myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
        }
        else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.led1);
            mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                    getYCoordinate() * grid.getBlockSize(),
                    (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                    (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
            myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
        }
    }

    // Assigns a single connected source
    public void setSourceComponent(AbstractGridCell cell) {
        sourceComponent = cell;
    }

    public AbstractGridCell getSourceComponent() {
        return sourceComponent;
    }

    // Determines if lamp should turn on based on whether or not it is receiving an input from
    // the source.
    public boolean evaluate() {
        return sourceComponent.evaluateComponent();
    }

    // Assigns the current state of the lamp (on or off) after evaluation
    public void setEvaluatedOutputState() {
        outputState = evaluate();
    }

    // Retrieves the current state
    public boolean getOutputState() {
        return this.outputState;
    }
}