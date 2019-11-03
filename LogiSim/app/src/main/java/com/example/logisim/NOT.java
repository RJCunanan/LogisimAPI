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
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class NOT extends AbstractGridCell {

    private AbstractGridCell sourceComponent;
    private boolean sourceConnected = false;
    private boolean outputState = false;



    // Constructor
    public NOT(int x, int y, String text) {
        super(x, y, text);
    }

    // Draws image of NOT gate
    public void drawCell(Grid grid, Canvas myCanvas, Paint myPaint, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.not);
        mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                getYCoordinate() * grid.getBlockSize(),
                (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
        myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
    }

    // Assigns a single connected source
    public void setSourceComponent(AbstractGridCell cell) {
        this.sourceComponent = cell;
    }

    // Determines if NOT gate should send an output based on whether or not it is receiving an
    // input. The gate then inverses that input and sends it as the output.
    public boolean evaluate() {
        return (!sourceComponent.evaluateComponent());
    }

    // Retrieves the current state
    public boolean getOutputState() {
        return this.outputState;
    }
}