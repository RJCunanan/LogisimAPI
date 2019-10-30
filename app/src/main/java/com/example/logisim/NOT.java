// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Name:  Jaime Rivera
// Date:  9/27/2019
// Class: CSC131
// ID:    301427334
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package com.example.logisim;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;


public class NOT extends Cell {

    // Unique value assigned to NOT gates
    private int gateNum = 4;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;

    public NOT(Cell myCell) {super(myCell);}

    // Sets the bitmap to the NOT gate image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.not);
        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect, paint);
    }

    int getGateNum() {return gateNum;}

    // Passes the inverse of it's connected Cell
    boolean eval() { return !a.eval(); }
}
