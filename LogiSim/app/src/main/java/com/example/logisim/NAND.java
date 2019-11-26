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

public class NAND extends Cell {
    // Unique value assigned to OR gates
    private int gateNum = 12;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;


    // converts previous Cell to OR Cell
    public NAND(Cell myCell) {super(myCell);}


    // Sets the bitmap to the OR gate image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.nand);
        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect,paint);
    }

    boolean getState() {
        try {
            if (a.eval() && b.eval())
                this.state = false;
            else
                this.state = true;
        }
        catch (Exception e){this.state = false;}
        return this.state;
    }
    
    int getGateNum() {
        return gateNum;
    }

    // Will only be false if both a and b are true (inverse of and gate)
    boolean eval() { return getState(); }
}
