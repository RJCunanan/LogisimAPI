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

class Switch extends AbstractGridCell implements Node {

    // Indicates whether switch is sending an output or not
    private boolean outputState = false;



    // Constructor
    public Switch(int x, int y, String text) {
        super(x, y, text);
    }

    // Draws image of switch
    public void drawCell(Grid grid, Canvas myCanvas, Paint myPaint, Context context) {
        if (!getOutputState()) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch0);
            mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                    getYCoordinate() * grid.getBlockSize(),
                    (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                    (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
            myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
        }
        else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch1);
            mRect = new RectF(getXCoordinate() * grid.getBlockSize(),
                    getYCoordinate() * grid.getBlockSize(),
                    (getXCoordinate() * grid.getBlockSize()) + grid.getBlockSize(),
                    (getYCoordinate() * grid.getBlockSize())+ grid.getBlockSize());
            myCanvas.drawBitmap(bitmap, null, mRect, myPaint);
        }
    }

    // Turns switch on or off:
    public void toggleSwitch () {
        this.outputState = !this.outputState;
    }

    // Sends back whether or not this switch is sending an output.
    public boolean evaluate() {
        return outputState;
    }

    // Retrieves the current state
    public boolean getOutputState() {
        return this.outputState;
    }
}
