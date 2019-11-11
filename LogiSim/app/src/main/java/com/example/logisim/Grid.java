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

    private boolean prevSelected = false;
    private int previousTouchN;

    private Canvas myCanvas;
    private Paint myPaint;
    private ImageView myGameView;
    private Bitmap myBitMap;
    private Context myContext;

    private Vector<Cell> cellList;
    private List<UserInterfaceButtons> buttonList = new ArrayList<>(13);

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



    void initializeButtons() {
        // A loop will create however many buttons possible within the area given to it
        // of the proper width. When creating these new buttons, a starting position is given
        // as well as the width and length of the buttons
        // The buttons will be placed on top of the grid, thus it will still read player taps as cells
        // Because of this the buttons must have a list of the different cells that combine to make
        // one large button
        int currentButton = -1;

        for(int x = 0; x < gridWidth; x+= buttonWidth) {
            currentButton++;
            buttonList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                    buttonWidth, buttonLength, currentButton));
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

       // Sets the grid lines to be drawn
       //myPaint.setColor(Color.argb(255, 0, 0, 0));
       //myPaint.setStrokeWidth(2);
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



    // This will draw all of the UI buttons and the lines to separate each button
    void drawUI() {
        drawOptionsGrid();
        myPaint.setColor((Color.argb(255, 0, 0, 0)));
        myPaint.setTextSize(40);

        // Change the paint color to white
        myPaint.setColor(Color.argb(255, 255, 255, 255));

        // Formats the size of the text to be displayed on the button
        myPaint.setTextSize(cellSize / (float)3.7);

        drawRunButton();
        drawLinkButton();
        drawMoveButton();
        drawDeleteButton();
        drawSAVEButton();
        drawSAVEAButton();
        drawSAVEBButton();
        drawSAVECButton();
        drawSWITCHButton();
        drawANDButton();
        drawNANDButton();
        drawORButton();
        drawNORButton();
        drawXORButton();
        drawNOTButton();
        drawLAMPButton();
    }



    void drawOptionsGrid() {
        // Set paint color to black
        myPaint.setColor((Color.argb(255, 0, 0, 0)));
        myCanvas.drawRect(0, (gridHeight-buttonLength)*cellSize,
                gridWidth * cellSize, gridHeight * cellSize, myPaint);

        myPaint.setColor(Color.argb(255, 0, 0, 0));

        for(int verticalLine = 1; verticalLine < 20; verticalLine++) {
            myCanvas.drawLine((verticalLine*buttonWidth) * cellSize, (gridHeight-gridWidth)* cellSize,
                    (verticalLine*buttonWidth) * cellSize, (gridHeight)* cellSize,
                    myPaint);
        }
    }



    private void drawRunButton() {
        myCanvas.drawText("Run", (buttonWidth*RUNBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawLinkButton() {
        myCanvas.drawText("Link", (buttonWidth*LINKBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawMoveButton() {
        myCanvas.drawText("Move", (buttonWidth*MOVEBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawDeleteButton() {
        myCanvas.drawText("Delete", (buttonWidth*DELETEBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawSAVEButton() {
        myCanvas.drawText("SAVE", (buttonWidth*SAVEBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawSAVEAButton() {
        myCanvas.drawText("A", (buttonWidth*SAVEAOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawSAVEBButton() {
        myCanvas.drawText("B", (buttonWidth*SAVEBOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawSAVECButton() {
        myCanvas.drawText("C", (buttonWidth*SAVECOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawSWITCHButton() {
        myCanvas.drawText("Switch", (buttonWidth*SWITCHBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawANDButton() {
        myCanvas.drawText("AND", (buttonWidth*ANDBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawNANDButton() {
        myCanvas.drawText("NAND", (buttonWidth*NANDBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawORButton() {
        myCanvas.drawText("OR", (buttonWidth*ORBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawNORButton() {
        myCanvas.drawText("NOR", (buttonWidth*NORBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawXORButton() {
        myCanvas.drawText("XOR", (buttonWidth*XORBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawNOTButton() {
        myCanvas.drawText("NOT", (buttonWidth*NOTBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }

    private void drawLAMPButton() {
        myCanvas.drawText("LAMP", (buttonWidth*LAMPBUTTONOPTION) * cellSize,
                (gridHeight - (float)buttonLength/2) * cellSize,
                myPaint);
    }



    // This is the portion of the code that will determine what is to be done
    // once the player has touched the screen
    void determineTouch(Point touchPosition) {
        int touchPositionN = getCellN(touchPosition);
        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= gridHeight-buttonLength) {
            for (int i = 0; i < buttonList.size(); i++) {
                buttonList.get(i).wasITouched(touchPosition);
            }

            if(buttonList.get(RUNBUTTONOPTION).getSelected()) {
                for(int i = 0; i < cellList.size(); i++)
                    if(cellList.get(i) instanceof LAMP) {
                        ((LAMP) cellList.get(i)).evalLamp(); }

                buttonList.get(RUNBUTTONOPTION).toggleButton();
            }
            else if(buttonList.get(SAVEBUTTONOPTION).getSelected() && buttonList.get(SAVEAOPTION).getSelected()) {
                savedList(cellList, cellListA);
                buttonList.get(SAVEBUTTONOPTION).toggleButton();
                buttonList.get(SAVEAOPTION).toggleButton();
            }
            else if(buttonList.get(SAVEBUTTONOPTION).getSelected() && buttonList.get(SAVEBOPTION).getSelected()) {
                savedList(cellList, cellListB);
                buttonList.get(SAVEBUTTONOPTION).toggleButton();
                buttonList.get(SAVEBOPTION).toggleButton();
            }
            else if(buttonList.get(SAVEBUTTONOPTION).getSelected() && buttonList.get(SAVECOPTION).getSelected()) {
                savedList(cellList, cellListC);
                buttonList.get(SAVEBUTTONOPTION).toggleButton();
                buttonList.get(SAVECOPTION).toggleButton();
            }
            else if(buttonList.get(SAVEAOPTION).getSelected()) {
                savedList(cellListA, cellList);
                buttonList.get(SAVEAOPTION).toggleButton();
            }
            else if(buttonList.get(SAVEBOPTION).getSelected()) {
                savedList(cellListB, cellList);
                buttonList.get(SAVEBOPTION).toggleButton();
            }
            else if(buttonList.get(SAVECOPTION).getSelected()) {
                savedList(cellListC, cellList);
                buttonList.get(SAVECOPTION).toggleButton();
            }
        }
        else {
            // This loop find which button is currently toggled on
            int currentOption = -1;
            for(int i = 0; i < buttonList.size(); i++){
                if(buttonList.get(i).getSelected()) {
                    currentOption = buttonList.get(i).getButtonInList();
                }
            }

            // Each button has a unique number assigned to it, and once this is found out
            // the grid will respond accordingly
            switch(currentOption) {
                case LINKBUTTONOPTION:
                    // if there has been a previously selected cell and it isnt an empty cell
                    if(prevSelected && cellList.get(previousTouchN).getGateNum() != -1) {
                        linkCells(touchPositionN);
                        prevSelected = false;
                        buttonList.get(currentOption).toggleButton();
                    }
                    // select the cell to be linked
                    else {
                        previousTouchN = touchPositionN;
                        prevSelected = true;
                    }
                    break;

                case MOVEBUTTONOPTION:
                    // if there has been a previously selected cell
                    if(prevSelected) {
                        moveCells(touchPosition, touchPositionN);
                        prevSelected = false;
                        buttonList.get(currentOption).toggleButton();
                    }
                    // select the cell to be moved
                    else {
                        previousTouchN = touchPositionN;
                        prevSelected = true;
                    }
                    break;

                case DELETEBUTTONOPTION:
                    // turn the cell selected back into an EmptyCell
                    Cell deleteCell = cellList.get(touchPositionN);
                    deleteCell.deleteConnections();
                    cellList.set(touchPositionN,new EmptyCell(deleteCell));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case SWITCHBUTTONOPTION:
                    // creates a Switch after being given information of the cell
                    cellList.set(touchPositionN,new SWITCH(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case ANDBUTTONOPTION:
                    // creates an AND gate after being given information of the cell
                    cellList.set(touchPositionN,new AND(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case NANDBUTTONOPTION:
                    // creates an AND gate after being given information of the cell
                    cellList.set(touchPositionN,new NAND(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case ORBUTTONOPTION:
                    // creates an OR gate after being given information of the cell
                    cellList.set(touchPositionN,new OR(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case NORBUTTONOPTION:
                    // creates an OR gate after being given information of the cell
                    cellList.set(touchPositionN,new NOR(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case XORBUTTONOPTION:
                    // creates a XOR gate after being given information of the cell
                    cellList.set(touchPositionN, new XOR(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case NOTBUTTONOPTION:
                    // creates a NOT gate after being given information of the cell
                    cellList.set(touchPositionN,new NOT(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                case LAMPBUTTONOPTION:
                    // creates a Lamp after being given information of the cell
                    cellList.set(touchPositionN,new LAMP(cellList.get(touchPositionN)));
                    buttonList.get(currentOption).toggleButton();
                    break;

                default:
                    // If the user tapped a Switch, toggle its state
                    if(cellList.get(touchPositionN) instanceof SWITCH) { ((SWITCH) cellList.get(touchPositionN)).toggleSwitch();}
            }
        }
    }



    private void moveCells(Point newTouchPosition, int newTapN) {
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



    private void linkCells(int newTapN) {
        // gets the current Cell of the user's tap
        Cell currCell = cellList.get(newTapN);

        cellList.get(previousTouchN).addHead(cellList.get(newTapN));

        // create a link between the current cell pointing to the previous cell if a spot is open.
        if(currCell.getCellA() == null)
            cellList.get(newTapN).setCellA(cellList.get(previousTouchN));
        else if(currCell.getCellB() == null)
            cellList.get(newTapN).setCellB(cellList.get(previousTouchN));
    }



    private void savedList(Vector<Cell> saveThis, Vector<Cell> saveHere) {
        for(int i = 0; i < saveThis.size(); i++) {
            Cell currCell = saveThis.get(i);
            if(currCell instanceof EmptyCell)
                saveHere.set(i, new EmptyCell(currCell));
            else if(currCell instanceof SWITCH)
                saveHere.set(i, new SWITCH(currCell));
            else if(currCell instanceof AND)
                saveHere.set(i, new AND(currCell));
            else if(currCell instanceof NAND)
                saveHere.set(i, new NAND(currCell));
            else if(currCell instanceof OR)
                saveHere.set(i, new OR(currCell));
            else if(currCell instanceof NOR)
                saveHere.set(i, new NOR(currCell));
            else if(currCell instanceof XOR)
                saveHere.set(i, new XOR(currCell));
            else if(currCell instanceof NOT)
                saveHere.set(i, new NOT(currCell));
            else if(currCell instanceof LAMP)
                saveHere.set(i, new LAMP(currCell));
        }

        for(int i = 0; i < saveThis.size(); i++) {
            Cell currCell = saveThis.get(i);
            if(!(currCell instanceof EmptyCell)) {
                if(currCell.getCellA() != null) {
                    int currCellTailAPos = getCellN(currCell.getCellA().cellPosition);
                    saveHere.get(i).setCellA(saveHere.get(currCellTailAPos));
                }
                if(currCell.getCellB() != null) {
                    int currCellTailBPos = getCellN(currCell.getCellB().cellPosition);
                    saveHere.get(i).setCellB(saveHere.get(currCellTailBPos));
                }
                for(int k = 0; k < currCell.head.size(); k++) {
                    int currCellHeadPos = getCellN(currCell.head.get(k).cellPosition);
                    saveHere.get(i).head.set(k, saveHere.get(currCellHeadPos));
                }
            }
        }

    }
}