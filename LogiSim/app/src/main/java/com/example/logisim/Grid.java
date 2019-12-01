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
 *      11-23-19
 **/



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
    private final int BACK_BUTTON_POSITION = 0;

    private final int RUN_BUTTON_POSITION = 0;
    private final int LINK_BUTTON_POSITION = 1;
    private final int MOVE_BUTTON_POSITION = 2;
    private final int DELETE_BUTTON_POSITION = 3;
    private final int SAVE_BUTTON_POSITION = 4;
    private final int LOAD_BUTTON_POSITION = 5;
    private final int SWITCH_BUTTON_POSITION = 6;
    private final int GATES_BUTTON_POSITION = 7;
    private final int LAMP_BUTTON_POSITION = 8;
    private final int UNDO_BUTTON_POSITION = 9;
    private final int REDO_BUTTON_POSITION = 10;

    private final int SAVE_SLOT_A_POSITION = 2;
    private final int SAVE_SLOT_B_POSITION = 3;
    private final int SAVE_SLOT_C_POSITION = 4;
    private final int SAVE_SLOT_D_POSITION = 5;
    private final int SAVE_SLOT_E_POSITION = 6;
    private final int SAVE_SLOT_F_POSITION = 7;

    private final int AND_GATE_BUTTON_POSITION = 2;
    private final int NAND_GATE_BUTTON_POSITION = 3;
    private final int OR_GATE_BUTTON_POSITION = 4;
    private final int NOR_GATE_BUTTON_POSITION = 5;
    private final int XOR_GATE_BUTTON_POSITION = 6;
    private final int NOT_GATE_BUTTON_POSITION = 7;

    // Allows modification of the button's Width and Length;
    private final int buttonLength = 1;
    private final int buttonWidth = 1;

    // Variables to be seen throughout the Grid Class
    private Point numberOfPixels = new Point();
    int cellSize;
    private final int gridWidth = 18;
    private int gridHeight;

    private int previousTouchN;

    private Canvas myCanvas;
    private Paint myPaint;
    private ImageView myGameView;
    private Bitmap myBitMap;
    private Context myContext;

    private Vector<Cell> cellList;
    private List<UserInterfaceButtons> buttonList = new ArrayList<>(10);

    private Vector<Cell> cellListA;
    private Vector<Cell> cellListB;
    private Vector<Cell> cellListC;
    private Vector<Cell> cellListD;
    private Vector<Cell> cellListE;
    private Vector<Cell> cellListF;

    // New ArrayLists that will hold the different buttons that belong in different menus
    // on the taskbar:
    private List<UserInterfaceButtons> mainMenuList = new ArrayList<>(10);
    private List<UserInterfaceButtons> saveAndLoadMenuList = new ArrayList<>(10);
    private List<UserInterfaceButtons> logicGatesMenuList = new ArrayList<>(10);

    private int menuToDisplay = 0;      // Used to decide what menu should be loaded onto the taskbar
    private final int MAIN_MENU = 0;    // Used to load the main menu
    private final int SAVE_MENU = 1;    // Used to load the save menu
    private final int LOAD_MENU = 2;    // Used to load the load menu
    private final int GATES_MENU = 3;   // Used to load the gates menu




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

        // These vectors are used to save the current configuration of the grid,
        // along with all the built circuits on it, so that they could be loaded
        // later on.
        cellListA = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListB = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListC = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListD = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListE = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListF = new Vector<>((gridHeight-buttonLength)*gridWidth);

        // A nested loop will fill the entirety of the vector with EmptyCells
        // Note: the grid is filled column by column
        Point currentPos = new Point();
        for (int x = 1; x < gridWidth; x++) {
            for(int y = 0; y < (gridHeight-buttonLength); y++) {
                currentPos.x = x;
                currentPos.y = y;

                cellList.add(new EmptyCell(new Point(currentPos), cellSize));

                // Also initialize the save slots with empty grid cells
                cellListA.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListB.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListC.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListD.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListE.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListF.add(new EmptyCell(new Point(currentPos), cellSize));
            }
        }
    }






    /*
    private void initializeGrid() {
        // The grid Vector will be created to have a size of the
        // area in which the player can place gates/switches/lamps inside
        cellList = new Vector<>((gridHeight-buttonLength)*gridWidth);

        // These vectors are used to save the current configuration of the grid,
        // along with all the built circuits on it, so that they could be loaded
        // later on.
        cellListA = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListB = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListC = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListD = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListE = new Vector<>((gridHeight-buttonLength)*gridWidth);
        cellListF = new Vector<>((gridHeight-buttonLength)*gridWidth);

        // A nested loop will fill the entirety of the vector with EmptyCells
        // Note: the grid is filled column by column
        Point currentPos = new Point();
        for (int x = 0; x < gridWidth; x++) {
            for(int y = 0; y < (gridHeight-buttonLength); y++) {
                currentPos.x = x;
                currentPos.y = y;

                cellList.add(new EmptyCell(new Point(currentPos), cellSize));

                // Also initialize the save slots with empty grid cells
                cellListA.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListB.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListC.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListD.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListE.add(new EmptyCell(new Point(currentPos), cellSize));
                cellListF.add(new EmptyCell(new Point(currentPos), cellSize));
            }
        }
    }
     */





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

        for(int y = 0; y < gridWidth; y+= buttonWidth) {
            currentButtonPosition++;

            createMainMenu(currentButtonPosition, y);
            createSaveAndLoadMenu(currentButtonPosition, y);
            createGatesMenu(currentButtonPosition, y);
        }
    }





    /*
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

            createMainMenu(currentButtonPosition, x);
            createSaveAndLoadMenu(currentButtonPosition, x);
            createGatesMenu(currentButtonPosition, x);
        }
    }
     */




    // This method creates the main menu that is initially displayed to the user when he or she
    // first opens the app by filling the menu with the main menu buttons.
    public void createMainMenu(int currentButtonPosition, int y){
        switch (currentButtonPosition) {
            case RUN_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Run", RUN_BUTTON_POSITION));
                break;
            case LINK_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Link", LINK_BUTTON_POSITION));
                break;
            case MOVE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Move", MOVE_BUTTON_POSITION));
                break;
            case DELETE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Delete", DELETE_BUTTON_POSITION));
                break;
            case SAVE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "SAVE", SAVE_BUTTON_POSITION));
                break;
            case LOAD_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "LOAD", LOAD_BUTTON_POSITION));
                break;
            case SWITCH_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Switch", SWITCH_BUTTON_POSITION));
                break;
            case GATES_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Gates", GATES_BUTTON_POSITION));
                break;
            case LAMP_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "LAMP", LAMP_BUTTON_POSITION));
                break;
            case UNDO_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Undo", UNDO_BUTTON_POSITION));
                break;
            case REDO_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "Redo", REDO_BUTTON_POSITION));
                break;
            default:
                mainMenuList.add(new UserInterfaceButtons(0, y,
                        buttonWidth, buttonLength, currentButtonPosition, "", currentButtonPosition));
        }
    }





    /*
    // This method creates the main menu that is initially displayed to the user when he or she
    // first opens the app by filling the menu with the main menu buttons.
    public void createMainMenu(int currentButtonPosition, int x){
        switch (currentButtonPosition) {
            case RUN_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Run", RUN_BUTTON_POSITION));
                break;
            case LINK_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Link", LINK_BUTTON_POSITION));
                break;
            case MOVE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Move", MOVE_BUTTON_POSITION));
                break;
            case DELETE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Delete", DELETE_BUTTON_POSITION));
                break;
            case SAVE_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "SAVE", SAVE_BUTTON_POSITION));
                break;
            case LOAD_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "LOAD", LOAD_BUTTON_POSITION));
                break;
            case SWITCH_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Switch", SWITCH_BUTTON_POSITION));
                break;
            case GATES_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Gates", GATES_BUTTON_POSITION));
                break;
            case LAMP_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "LAMP", LAMP_BUTTON_POSITION));
                break;
            case UNDO_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Undo", UNDO_BUTTON_POSITION));
                break;
            case REDO_BUTTON_POSITION:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Redo", REDO_BUTTON_POSITION));
                break;
            default:
                mainMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "", currentButtonPosition));
        }
    }
     */

    // This method creates the save menu that is loaded onto the screen when the user taps on the
    // "SAVE" button from the main menu by creating several buttons that act as save "slots", each
    // of which will hold a saved circuit.
    public void createSaveAndLoadMenu(int currentButtonPosition, int x) {
        switch (currentButtonPosition) {
            case BACK_BUTTON_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "DONE", BACK_BUTTON_POSITION));
                break;
            case SAVE_SLOT_A_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot A", SAVE_SLOT_A_POSITION));
                break;
            case SAVE_SLOT_B_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot B", SAVE_SLOT_B_POSITION));
                break;
            case SAVE_SLOT_C_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot C", SAVE_SLOT_C_POSITION));
                break;
            case SAVE_SLOT_D_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot D", SAVE_SLOT_D_POSITION));
                break;
            case SAVE_SLOT_E_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot E", SAVE_SLOT_E_POSITION));
                break;
            case SAVE_SLOT_F_POSITION:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "Slot F", SAVE_SLOT_F_POSITION));
                break;
            default:
                saveAndLoadMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "", currentButtonPosition));
        }
    }


    // This method creates the logic gates menu that is loaded onto the screen when the user taps on the
    // "Gates" button in the main menu by creating several buttons that will allow the user to place
    // logic gates onto the grid.
    public void createGatesMenu(int currentButtonPosition, int x) {
        switch (currentButtonPosition) {
            case BACK_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "BACK", BACK_BUTTON_POSITION));
                break;
            case AND_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "AND", AND_GATE_BUTTON_POSITION));
                break;
            case NAND_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "NAND", NAND_GATE_BUTTON_POSITION));
                break;
            case OR_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "OR", OR_GATE_BUTTON_POSITION));
                break;
            case NOR_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "NOR", NOR_GATE_BUTTON_POSITION));
                break;
            case XOR_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "XOR", XOR_GATE_BUTTON_POSITION));
                break;
            case NOT_GATE_BUTTON_POSITION:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "NOT", NOT_GATE_BUTTON_POSITION));
                break;
            default:
                logicGatesMenuList.add(new UserInterfaceButtons(x, gridHeight-buttonLength,
                        buttonWidth, buttonLength, currentButtonPosition, "", currentButtonPosition));
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
               int totalX = currCell.cellX - currCell.getCellA().cellWidth;
               int stopY;

               // If the state of tail A is on, draw Yellow
               if(currCell.getCellA().getState() == true)
                   myPaint.setColor(Color.argb(255, 255, 255, 0));

               if (currCell instanceof NOT || currCell instanceof LAMP)
                   stopY = currCell.cellY + ((currCell.cellHeight - currCell.cellY) / 2);
               else
                   stopY = currCell.cellY + ((currCell.cellHeight - currCell.cellY) / 4);

               // Taxi-Cab Wire Routing
               myCanvas.drawLine(currCell.getCellA().cellWidth,
                       (currCell.getCellA().cellY + currCell.getCellA().cellHeight) / 2,
                       currCell.getCellA().cellWidth + (totalX / 2),
                       (currCell.getCellA().cellY + currCell.getCellA().cellHeight) / 2,
                       myPaint);

               myCanvas.drawLine(currCell.getCellA().cellWidth + (totalX / 2),
                       (currCell.getCellA().cellY + currCell.getCellA().cellHeight) / 2,
                       currCell.getCellA().cellWidth + (totalX / 2),
                       stopY,
                       myPaint);

               myCanvas.drawLine(currCell.getCellA().cellWidth + (totalX / 2),
                       stopY,
                       currCell.cellX,
                       stopY,
                       myPaint);
           }

           // Change the paint color to blue
           myPaint.setColor(Color.argb(255, 0, 0, 255));

           if (currCell.getCellB() != null) {
               int totalX = currCell.cellX - currCell.getCellB().cellWidth;

               // If the state of tail B is on, draw Yellow
               if(currCell.getCellB().getState() == true)
                   myPaint.setColor(Color.argb(255, 255, 255, 0));

               // Taxi-Cab Wire Routing
               myCanvas.drawLine(currCell.getCellB().cellWidth,
                       (currCell.getCellB().cellY + currCell.getCellB().cellHeight) / 2,
                       currCell.getCellB().cellWidth + (totalX / 2),
                       (currCell.getCellB().cellY + currCell.getCellB().cellHeight) / 2,
                       myPaint);

               myCanvas.drawLine(currCell.getCellB().cellWidth + (totalX / 2),
                       (currCell.getCellB().cellY + currCell.getCellB().cellHeight) / 2,
                       currCell.getCellB().cellWidth + (totalX / 2),
                       currCell.cellY + 3 * ((currCell.cellHeight - currCell.cellY) / 4),
                       myPaint);

               myCanvas.drawLine(currCell.getCellB().cellWidth + (totalX / 2),
                       currCell.cellY + 3 * ((currCell.cellHeight - currCell.cellY) / 4),
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

        if (menuToDisplay == MAIN_MENU) {
            buttonList.clear();
            buttonList.addAll(mainMenuList);

        }
        else if (menuToDisplay == SAVE_MENU || menuToDisplay == LOAD_MENU) {
            buttonList.clear();
            buttonList.addAll(saveAndLoadMenuList);
        }
        else if (menuToDisplay == GATES_MENU) {
            buttonList.clear();
            buttonList.addAll(logicGatesMenuList);
        }

        // Cycles through the buttonList and draws each individual button one at a time
        // to create the menu bar at the bottom of the screen
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).drawButton(myCanvas, myPaint, this);

        }
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

    public Vector<Cell> getCellListD() {
        return cellListD;
    }

    public Vector<Cell> getCellListE() {
        return cellListE;
    }

    public Vector<Cell> getCellListF() {
        return cellListF;
    }

    public int getPreviousTouchN() {
        return previousTouchN;
    }

    public void setPreviousTouchN(int touchPositionN) {
        previousTouchN = touchPositionN;
    }

    public int getMenuToDisplay() {
        return menuToDisplay;
    }

    public int getMainMenu() {
        return MAIN_MENU;
    }

    public int getSaveMenu() {
        return SAVE_MENU;
    }

    public int getLoadMenu() {
        return LOAD_MENU;
    }

    public int getGatesMenu() {
        return GATES_MENU;
    }

    public void loadMainMenu() {
        menuToDisplay = MAIN_MENU;
    }

    public void loadSaveMenu() {
        menuToDisplay = SAVE_MENU;
    }

    public void loadGatesMenu() {
        menuToDisplay = GATES_MENU;
    }

    public void loadLoadMenu() {
        menuToDisplay = LOAD_MENU;
    }

}