// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Name:  Jaime Rivera
// Date:  9/27/2019
// Class: CSC131
// ID:    301427334
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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
    boolean eval() {return a.eval();}
}
