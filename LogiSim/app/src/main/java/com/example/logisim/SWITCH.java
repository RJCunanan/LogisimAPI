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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class SWITCH extends Cell {

    // Unique value assigned to Switches
    int gateNum = 1;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;

    public SWITCH(Cell myCell) {super(myCell);}

    // Sets the bitmap to the Switch image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        if(state)
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch1);
        else
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.switch0);

        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect, paint);
    }

    int getGateNum() {return gateNum;}

    void toggleSwitch() {this.state = !state;}

    // Passes the state of the switch
    boolean eval() {return state;}
}
