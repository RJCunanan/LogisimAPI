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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Grid {

    // Several Constants used within the code for Switch Statements
    // Allowing the code to be more readable
    private final int RUNBUTTONOPTION = 0;
    private final int LINKBUTTONOPTION = 1;
    private final int MOVEBUTTONOPTION = 2;
    private final int DELETEBUTTONOPTION = 3;

    private final int SAVEBUTTONOPTION = 5;
    private final int SAVEAOPTION = 6;
    private final int SAVEBOPTION = 7;
    private final int SAVECOPTION = 8;

    private final int SWITCHBUTTONOPTION = 10;
    private final int ANDBUTTONOPTION = 11;
    private final int NANDBUTTONOPTION = 12;
    private final int ORBUTTONOPTION = 13;
    private final int NORBUTTONOPTION = 14;
    private final int XORBUTTONOPTION = 15;
    private final int NOTBUTTONOPTION = 16;
    private final int LAMPBUTTONOPTION = 17;


    // Allows modification of the button's Width and Length;
    private final int buttonLength = 1;
    private final int buttonWidth = 1;

    // Variables to be seen throughout the Grid Class
    private Point numberOfPixels = new Point();
    int cellSize;
    private final int gridWidth = 20;
    private int gridHeight;

    private int previousTouchN;

    private Canvas myCanvas;
    private Paint myPaint;
    private ImageView myGameView;
    private Bitmap myBitMap;
    private Context myContext;

    private Vector<Cell> cellList;
    private List<UserInterfaceButtons> buttonList = new ArrayList<>(20);

    private Vector<Cell> cellListA;
    private Vector<Cell> cellListB;
    private Vector<Cell> cellListC;



    public Grid(Context context, Point size, Canvas canvas, Paint paint, ImageView gameView, Bitmap blankBitMap) {
        // Gathers & computes information about the user's Screen
        numberOfPixels.x = size.x;
        numberOfPixels.y = size.y;
        cellSize = numberOfPixels.x / gridWidth;
        gridHeight = numberOfPixels.y / cellSize;

        // Assigns information for drawing
        myCanvas = canvas;
        myPaint = paint;
        myGameView = gameView;
        myBitMap = blankBitMap;
        myContext = context;

        // Once the Grid has all the necessary variables,
        // The Grid Vector and Buttons Arraylist will get initialized
        initializeGrid();
        initializeButtons();
    }



    private void initializeGrid() {
        // The grid Vector will be created to have a size of the
        // area in which the player can place gates/switches/lamps inside
        cellList = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListA = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListB = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListC = new Vector<>((gridHeight-buttonLength)*gridWidth);

        // A nested loop will fill the entirety of the vector with EmptyCells
        // Note: the grid filled with columns first
        Point currentPos = new Point();
        for (int x = 0; x < gridWidth; x++) {
            for(int y = 0; y < (gridHeight-buttonLength); y++) {
                currentPos.x = x;
                currentPos.y = y;

                cellList.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListA.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListB.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListC.add(new EmptyCell(new Point(currentPos), cellSize));
            }
        }
    }



    // Using an arrayList called buttonList, new UserInterfaceButton instances are created
    // to represent the UI buttons that are used to accomplish tasks and build circuits
    void initializeButtons() {
        // A loop will create however many buttons possible within the area given to it
        // of the proper width. When creating these new buttons, a starting position is given
        // as well as the width and length of the buttons
        // The buttons will be placed on top of the grid, thus it will still read player taps as cells
        // Because of this the buttons must have a list of the different cells that combine to make
        // one large button
        int currentButtonPosition = -1;

        for(int x = 0; x < gridWidth; x+= buttonWidth) {
            currentButtonPosition++;

            // Determine which button to create given the current position
            switch (currentButtonPosition) {
                case RUNBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "Run", RUNBUTTONOPTION));
                    break;
                case LINKBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "Link", LINKBUTTONOPTION));
                    break;
                case MOVEBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "Move", MOVEBUTTONOPTION));
                    break;
                case DELETEBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "Delete", DELETEBUTTONOPTION));
                    break;
                case SAVEBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "SAVE", SAVEBUTTONOPTION));
                    break;
                case SAVEAOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "A", SAVEAOPTION));
                    break;
                case SAVEBOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "B", SAVEBOPTION));
                    break;
                case SAVECOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "C", SAVECOPTION));
                    break;
                case SWITCHBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "Switch", SWITCHBUTTONOPTION));
                    break;
                case ANDBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "AND", ANDBUTTONOPTION));
                    break;
                case NANDBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "NAND", NANDBUTTONOPTION));
                    break;
                case ORBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "OR", ORBUTTONOPTION));
                    break;
                case NORBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "NOR", NORBUTTONOPTION));
                    break;
                case XORBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "XOR", XORBUTTONOPTION));
                    break;
                case NOTBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "NOT", NOTBUTTONOPTION));
                    break;
                case LAMPBUTTONOPTION:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "LAMP", LAMPBUTTONOPTION));
                    break;
                default:
                    buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                            buttonWidth, buttonLength, currentButtonPosition, "", currentButtonPosition));
            }
        }
    }



    // This will return the spot in the cell vector list of whatever point is given to it
    int getCellN(Point tap) {return ((gridHeight-buttonLength)*tap.x+tap.y);}



    void clearScreen() {
       myGameView.setImageBitmap(myBitMap);
       myCanvas.drawColor(Color.argb(255, 255, 255, 255));
    }



    void drawGrid() {
       clearScreen();

       // Sets the grid lines to be drawn:
       myCanvas.drawColor(Color.argb(255, 255, 255, 255));

       // This will loop through the entire cell list
       for(Cell currCell : cellList) {

           //myPaint.setStrokeWidth(2F);
           myPaint.setColor(Color.argb(255, 0, 0, 0));

           // Draw Horizontal & Vertical lines based on cell location of current cell
           myCanvas.drawLine(currCell.cellX, currCell.cellY,
                       currCell.cellX + cellSize, currCell.cellY, myPaint);

           myCanvas.drawLine(currCell.cellX, currCell.cellY,
                             currCell.cellX, currCell.cellY + cellSize, myPaint);

           // Draw whatever the current cell is (emptycell, gate, switch, lamp)
           currCell.drawCell(myPaint, myCanvas, myContext);


           // If the current cell has any other Cells linked to it, draw a thick line
           myPaint.setStrokeWidth(20F);

           // Change the paint color to blue
           myPaint.setColor(Color.argb(255, 0, 0, 255));

           if (currCell.getCellA() != null) {
               int stopY;
               if (currCell instanceof NOT || currCell instanceof LAMP)
                   stopY = currCell.cellY + ((currCell.cellHeight - currCell.cellY) / 2);
               else
                   stopY = currCell.cellY + ((currCell.cellHeight - currCell.cellY) / 4);

               myCanvas.drawLine(currCell.getCellA().cellWidth,
                       (currCell.getCellA().cellY + currCell.getCellA().cellHeight) / 2,
                       currCell.cellX,
                       stopY,
                       myPaint);
           }

           if (currCell.getCellB() != null) {
               myCanvas.drawLine(currCell.getCellB().cellWidth,
                       (currCell.getCellB().cellY + currCell.getCellB().cellHeight) / 2,
                       currCell.cellX,
                       currCell.cellY + 3 * ((currCell.cellHeight - currCell.cellY) / 4),
                       myPaint);
           }

           myPaint.setStrokeWidth(2F);
       }
    }



    // This method will draw all of the UI buttons and the lines to separate each button
    void drawUI() {

        // Set paint color to black
        myPaint.setColor((Color.argb(255, 0, 0, 0)));

        // Draws the separating lines
        for(int verticalLine = 1; verticalLine < 20; verticalLine++) {
            myCanvas.drawLine((verticalLine*buttonWidth) * cellSize, (gridHeight-gridWidth)* cellSize,
                    (verticalLine*buttonWidth) * cellSize, (gridHeight)* cellSize,
                    myPaint);
        }

        // Cycles through the buttonList and draws each individual button one at a time
        // to create the menu bar at the bottom of the screen
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).drawButton(myCanvas, myPaint, this);
        }
    }



    void moveCells(Point newTouchPosition, int newTapN) {
        // Create a hold variable of the previous Tap
        Cell newCell = cellList.get(newTapN);
        Cell hold = cellList.get(previousTouchN);

        // Update the hold Cell's touch position and move it to the right position in the cellList
        // Set the previous Touch to an empty cell
        cellList.set(previousTouchN,new EmptyCell(cellList.get(previousTouchN)));
        cellList.get(previousTouchN).a = null;
        cellList.get(previousTouchN).b = null;
        hold.setCellPositionCoor(newCell.cellX, newCell.cellY, newCell.cellWidth, newCell.cellHeight);
        hold.updateCellPosition(new Point(newTouchPosition));
        cellList.set(newTapN, hold);
    }



    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getButtonLength() {
        return buttonLength;
    }

    public List<UserInterfaceButtons> getButtonList() {
        return buttonList;
    }

    public Vector<Cell> getCellList() {
        return cellList;
    }

    public Vector<Cell> getCellListA() {
        return cellListA;
    }

    public Vector<Cell> getCellListB() {
        return cellListB;
    }

    public Vector<Cell> getCellListC() {
        return cellListC;
    }

    public int getPreviousTouchN() {
        return previousTouchN;
    }

    public void setPreviousTouchN(int touchPositionN) {
        previousTouchN = touchPositionN;
    }

}