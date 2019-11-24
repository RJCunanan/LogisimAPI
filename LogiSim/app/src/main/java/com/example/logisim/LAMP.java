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

public class LAMP extends Cell {

    // Unique value assigned to Lamps
    private int gateNum = 5;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;


    public LAMP(Cell myCell) {super(myCell);}


    // Sets the bitmap to the Lamp image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        if(state)
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lamp1);
        else
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lamp0);

        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect, paint);
    }


    int getGateNum() {return gateNum;}


    void evalLamp() {
        state = eval();
    }


    // Passes its connected cell's state
    boolean eval() {
        boolean value;

        try {value = a.eval(); }

        catch (Exception e) { value = a.eval(); }

        return value;
    }
}
