package com.example.logisim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class XOR extends Cell {
    // Unique value assigned to OR gates
    private int gateNum = 15;

    // Used for drawing
    private Bitmap bitmap;
    private RectF mRect;


    // converts previous Cell to OR Cell
    public XOR(Cell myCell) {super(myCell);}


    // Sets the bitmap to the OR gate image and prints within the cell's space
    void drawCell(Paint paint, Canvas canvas, Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.xor);
        mRect = new RectF(cellX, cellY, cellWidth, cellHeight);
        canvas.drawBitmap(bitmap,null,mRect,paint);
    }


    int getGateNum() {return gateNum;}


    // Either A or B, but not both
    boolean eval() {
        if ((a.eval() && !b.eval()) || (!a.eval() && b.eval()))
            return true;
        else
            return false;
    }
}
