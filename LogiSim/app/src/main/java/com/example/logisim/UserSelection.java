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
import android.graphics.Point;


public class UserSelection {

    // Several Constants used within the code for Switch Statements.
    // Each constant represents one of the buttons on the taskbar and
    // allows the code to be more readable.
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


    private boolean previouslySelected = false;

    // Creates new save object that is used to save circuits into different slots.
    private Save save = new Save();

    // Creates new wire object that is used to connect two circuit components together.
    private Wire wire = new Wire();

    // Creates new ComponentMover object that is used to move circuit components from
    // one cell to another.
    private ComponentMover componentMover = new ComponentMover();


    //========================================================================================//
    // Name: RJ Cunanan
    // This is the section of variables I am testing in order to create an expandable taskbar:




    //========================================================================================//



    // This is the portion of the code that will determine what is to be done
    // once the player has touched the screen
    void determineUserSelection(Point touchPosition, Grid grid) {
        int touchPositionN = grid.getCellN(touchPosition);

        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= grid.getGridHeight() - grid.getButtonLength()) {

            // Cycle through each button to determine which was selected by the user
            for (int i = 0; i < grid.getButtonList().size(); i++) {
                if (touchPosition.x == grid.getButtonList().get(i).getButtonXCoordinate()) {
                    if (touchPosition.x != 4 && touchPosition.x != 9 && touchPosition.x < 18) {
                        // If the x-coordinate of the user's tap matches the x-coordinate of this
                        // button, and the selected button is not a blank button, mark this button
                        // as selected/tapped by the user
                        grid.getButtonList().get(i).wasITouched(touchPosition);
                    }
                }
                else if ((touchPosition.x < SAVEBUTTONOPTION) || (touchPosition.x > SAVECOPTION)){
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            if(grid.getButtonList().get(RUNBUTTONOPTION).getSelected()) {
                for(int i = 0; i < grid.getCellList().size(); i++)
                    if(grid.getCellList().get(i) instanceof LAMP) {
                        ((LAMP) grid.getCellList().get(i)).evalLamp(); }

                grid.getButtonList().get(RUNBUTTONOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVEBUTTONOPTION).getSelected() && grid.getButtonList().get(SAVEAOPTION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListA(), grid);
                grid.getButtonList().get(SAVEBUTTONOPTION).toggleButton();
                grid.getButtonList().get(SAVEAOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVEBUTTONOPTION).getSelected() && grid.getButtonList().get(SAVEBOPTION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListB(), grid);
                grid.getButtonList().get(SAVEBUTTONOPTION).toggleButton();
                grid.getButtonList().get(SAVEBOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVEBUTTONOPTION).getSelected() && grid.getButtonList().get(SAVECOPTION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListC(), grid);
                grid.getButtonList().get(SAVEBUTTONOPTION).toggleButton();
                grid.getButtonList().get(SAVECOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVEAOPTION).getSelected()) {
                save.saveList(grid.getCellListA(), grid.getCellList(), grid);
                grid.getButtonList().get(SAVEAOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVEBOPTION).getSelected()) {
                save.saveList(grid.getCellListB(), grid.getCellList(), grid);
                grid.getButtonList().get(SAVEBOPTION).toggleButton();
            }
            else if(grid.getButtonList().get(SAVECOPTION).getSelected()) {
                save.saveList(grid.getCellListC(), grid.getCellList(), grid);
                grid.getButtonList().get(SAVECOPTION).toggleButton();
            }
        }
        else {
            // This loop finds which button is currently toggled on
            int currentOption = -1;
            for(int i = 0; i < grid.getButtonList().size(); i++){
                if(grid.getButtonList().get(i).getSelected()) {
                    currentOption = grid.getButtonList().get(i).getButtonInList();
                }
            }

            // Each button has a unique number assigned to it, and once this is found out
            // the grid will respond accordingly
            switch(currentOption) {
                case LINKBUTTONOPTION:
                    // if there has been a previously selected cell and it isn't an empty cell
                    if(previouslySelected && grid.getCellList().get(grid.getPreviousTouchN()).getGateNum() != -1) {
                        wire.linkCells(touchPositionN, grid);
                        previouslySelected = false;
                    }
                    // select the cell to be linked
                    else {
                        grid.setPreviousTouchN(touchPositionN);
                        previouslySelected = true;
                    }
                    break;

                case MOVEBUTTONOPTION:
                    // if there has been a previously selected cell
                    if(previouslySelected) {
                        componentMover.moveCells(touchPosition, touchPositionN, grid);
                        previouslySelected = false;
                    }
                    // select the cell to be moved
                    else {
                        grid.setPreviousTouchN(touchPositionN);
                        previouslySelected = true;
                    }
                    break;

                case DELETEBUTTONOPTION:
                    // turn the cell selected back into an EmptyCell
                    Cell deleteCell = grid.getCellList().get(touchPositionN);
                    deleteCell.deleteConnections();
                    grid.getCellList().set(touchPositionN,new EmptyCell(deleteCell));
                    break;

                case SWITCHBUTTONOPTION:
                    // creates a Switch after being given information of the cell
                    grid.getCellList().set(touchPositionN,new SWITCH(grid.getCellList().get(touchPositionN)));
                    break;

                case ANDBUTTONOPTION:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new AND(grid.getCellList().get(touchPositionN)));
                    break;

                case NANDBUTTONOPTION:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NAND(grid.getCellList().get(touchPositionN)));
                    break;

                case ORBUTTONOPTION:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new OR(grid.getCellList().get(touchPositionN)));
                    break;

                case NORBUTTONOPTION:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOR(grid.getCellList().get(touchPositionN)));
                    break;

                case XORBUTTONOPTION:
                    // creates a XOR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN, new XOR(grid.getCellList().get(touchPositionN)));
                    break;

                case NOTBUTTONOPTION:
                    // creates a NOT gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOT(grid.getCellList().get(touchPositionN)));
                    break;

                case LAMPBUTTONOPTION:
                    // creates a Lamp after being given information of the cell
                    grid.getCellList().set(touchPositionN,new LAMP(grid.getCellList().get(touchPositionN)));
                    break;

                default:
                    // If the user tapped a Switch, toggle its state
                    if(grid.getCellList().get(touchPositionN) instanceof SWITCH) { ((SWITCH) grid.getCellList().get(touchPositionN)).toggleSwitch();}
            }
        }
    }



    //========================================================================================//
    // Name: RJ Cunanan
    // This is the section of methods I am testing in order to create an expandable taskbar:

    private void determineMainMenuSelection (Point touchPosition, Grid grid, int touchPositionN) {

        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= grid.getGridHeight() - grid.getButtonLength()) {

            // Cycle through each button to determine which was selected by the user
            for (int i = 0; i < grid.getButtonList().size(); i++) {
                if (touchPosition.x == grid.getButtonList().get(i).getButtonXCoordinate()) {
                    if (touchPosition.x < 8) {
                        // If the x-coordinate of the user's tap matches the x-coordinate of this
                        // button, and the selected button is not a blank button, mark this button
                        // as selected/tapped by the user
                        grid.getButtonList().get(i).wasITouched(touchPosition);
                    }
                }
            }

            // RUN button
            if (grid.getButtonList().get(0).getSelected()) {
                for(int i = 0; i < grid.getCellList().size(); i++)
                    if(grid.getCellList().get(i) instanceof LAMP) {
                        ((LAMP) grid.getCellList().get(i)).evalLamp(); }

                grid.getButtonList().get(0).toggleButton();
            }
            // SAVE button
            else if (grid.getButtonList().get(4).getSelected()) {
                grid.loadSaveMenu();
            }
            // GATES button
            else if (grid.getButtonList().get(6).getSelected()) {
                grid.loadGatesMenu();
            }
        }
        else {
            // This loop finds which button is currently toggled on
            int currentOption = -1;
            for(int i = 0; i < grid.getButtonList().size(); i++){
                if(grid.getButtonList().get(i).getSelected()) {
                    currentOption = grid.getButtonList().get(i).getButtonInList();
                }
            }

            switch(currentOption) {
                // LINK button
                case 1:
                    // if there has been a previously selected cell and it isn't an empty cell
                    if(previouslySelected && grid.getCellList().get(grid.getPreviousTouchN()).getGateNum() != -1) {
                        wire.linkCells(touchPositionN, grid);
                        previouslySelected = false;
                    }
                    // select the cell to be linked
                    else {
                        grid.setPreviousTouchN(touchPositionN);
                        previouslySelected = true;
                    }
                    break;

                // MOVE button
                case 2:
                    // if there has been a previously selected cell
                    if(previouslySelected) {
                        componentMover.moveCells(touchPosition, touchPositionN, grid);
                        previouslySelected = false;
                    }
                    // select the cell to be moved
                    else {
                        grid.setPreviousTouchN(touchPositionN);
                        previouslySelected = true;
                    }
                    break;

                // DELETE button
                case 3:
                    // turn the cell selected back into an EmptyCell
                    Cell deleteCell = grid.getCellList().get(touchPositionN);
                    deleteCell.deleteConnections();
                    grid.getCellList().set(touchPositionN,new EmptyCell(deleteCell));
                    break;

                // SWITCH button
                case 5:
                    // creates a Switch after being given information of the cell
                    grid.getCellList().set(touchPositionN,new SWITCH(grid.getCellList().get(touchPositionN)));
                    break;

                // LAMP button
                case 7:
                    // creates a Lamp after being given information of the cell
                    grid.getCellList().set(touchPositionN,new LAMP(grid.getCellList().get(touchPositionN)));
                    break;

                default:
                    // If the user tapped a Switch, toggle its state
                    if(grid.getCellList().get(touchPositionN) instanceof SWITCH) { ((SWITCH) grid.getCellList().get(touchPositionN)).toggleSwitch();}
            }
        }
    }


    private void determineSaveMenuSelection (Point touchPosition, Grid grid) {

        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= grid.getGridHeight() - grid.getButtonLength()) {

            // Cycle through each button to determine which was selected by the user
            for (int i = 0; i < grid.getButtonList().size(); i++) {
                if (touchPosition.x == grid.getButtonList().get(i).getButtonXCoordinate()) {
                    if (touchPosition.x < 8) {
                        // If the x-coordinate of the user's tap matches the x-coordinate of this
                        // button, and the selected button is not a blank button, mark this button
                        // as selected/tapped by the user
                        grid.getButtonList().get(i).wasITouched(touchPosition);
                    }
                }
            }

            // BACK button
            if (grid.getButtonList().get(0).getSelected()) {
                grid.loadMainMenu();
            }
            // Save Slot A
            else if (grid.getButtonList().get(2).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListA(), grid);
            }
            // Save Slot B
            else if (grid.getButtonList().get(3).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListB(), grid);
            }
            // Save Slot C
            else if (grid.getButtonList().get(4).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListC(), grid);
            }
        }
    }


    private void determineGatesMenuSelection (Point touchPosition, Grid grid, int touchPositionN) {

        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= grid.getGridHeight() - grid.getButtonLength()) {

            // Cycle through each button to determine which was selected by the user
            for (int i = 0; i < grid.getButtonList().size(); i++) {
                if (touchPosition.x == grid.getButtonList().get(i).getButtonXCoordinate()) {
                    if (touchPosition.x != 4 && touchPosition.x != 9 && touchPosition.x < 18) {
                        // If the x-coordinate of the user's tap matches the x-coordinate of this
                        // button, and the selected button is not a blank button, mark this button
                        // as selected/tapped by the user
                        grid.getButtonList().get(i).wasITouched(touchPosition);
                    }
                } else if ((touchPosition.x < SAVEBUTTONOPTION) || (touchPosition.x > SAVECOPTION)) {
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            // BACK button
            if (grid.getButtonList().get(0).getSelected()) {
                grid.loadMainMenu();
            }
        }
        else {
            // This loop finds which button is currently toggled on
            int currentOption = -1;
            for(int i = 0; i < grid.getButtonList().size(); i++){
                if(grid.getButtonList().get(i).getSelected()) {
                    currentOption = grid.getButtonList().get(i).getButtonInList();
                }
            }

            // Each button has a unique number assigned to it, and once this is found out
            // the grid will respond accordingly
            switch(currentOption) {
                // AND button
                case 2:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new AND(grid.getCellList().get(touchPositionN)));
                    break;

                // NAND button
                case 3:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NAND(grid.getCellList().get(touchPositionN)));
                    break;

                // OR button
                case 4:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new OR(grid.getCellList().get(touchPositionN)));
                    break;

                // NOR button
                case 5:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOR(grid.getCellList().get(touchPositionN)));
                    break;

                // XOR button
                case 6:
                    // creates a XOR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN, new XOR(grid.getCellList().get(touchPositionN)));
                    break;

                // NOT button
                case 7:
                    // creates a NOT gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOT(grid.getCellList().get(touchPositionN)));
                    break;
            }
        }
    }


    //========================================================================================//

}
