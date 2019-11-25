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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class AND extends Cell {

    // Unique value assigned to AND gates
    private int gateNum = 2;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;


    // converts previous Cell to AND Cell
    public AND(Cell myCell) { super(myCell); }


    // Sets the bitmap to the AND gate image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.and);
        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect, paint);
    }


    int getGateNum() {return gateNum;}

    boolean getState() {
        this.state = a.eval() & b.eval();
        return this.state;
    }

    // If both of its Tail Cells are toggled on, pass on
    boolean eval() { return this.getState(); }
}
