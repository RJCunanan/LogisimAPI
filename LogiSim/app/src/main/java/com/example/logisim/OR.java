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

class OR extends AbstractGridCell {

    private AbstractGridCell sourceComponent1;
    private AbstractGridCell sourceComponent2;
    private boolean sourceOneConnected = false;
    private boolean sourceTwoConnected = false;
    private boolean outputState = false;



    // Constructor
    public OR(int x, int y, String text) {
        super(x, y, text);
    }

    // Draws image of OR gate
    public void drawCell(Grid grid, Canvas myCanvas, Paint myPaint, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.or);
        mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                getYCoordinate() * grid.getBlockSize(),
                (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
        myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
    }

    // Assigns component as the first connected source
    public void setSourceComponent1(AbstractGridCell cell) {
        this.sourceComponent1 = cell;
        this.sourceOneConnected = true;
    }

    // Assigns component as the second connected source
    public void setSourceComponent2(AbstractGridCell cell) {
        this.sourceComponent2 = cell;
        this.sourceTwoConnected = true;
    }

    // Indicates whether or not this AND gate is connected to one or two sources
    public boolean determineSourceOneConnection() {
        return sourceOneConnected;
    }
    public boolean determineSourceTwoConnection() {
        return sourceTwoConnected;
    }


    // Determines if OR gate should send an output based on whether or not it receives an input from
    // either one of its sources
    public boolean evaluate() {
        return (sourceComponent1.evaluateComponent() || sourceComponent2.evaluateComponent());
    }

    // Retrieves the current state
    public boolean getOutputState() {
        return this.outputState;
    }
}
