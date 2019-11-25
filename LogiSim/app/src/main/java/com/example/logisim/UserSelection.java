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
import android.graphics.Point;
import android.media.MediaPlayer;



public class UserSelection {

    // Several Constants used within the code for Switch Statements.
    // Each constant represents one of the buttons on the taskbar and
    // allows the code to be more readable.
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

    private final int SAVE_SLOT_A_POSITION = 2;
    private final int SAVE_SLOT_B_POSITION = 3;
    private final int SAVE_SLOT_C_POSITION = 4;

    private final int AND_GATE_BUTTON_POSITION = 2;
    private final int NAND_GATE_BUTTON_POSITION = 3;
    private final int OR_GATE_BUTTON_POSITION = 4;
    private final int NOR_GATE_BUTTON_POSITION = 5;
    private final int XOR_GATE_BUTTON_POSITION = 6;
    private final int NOT_GATE_BUTTON_POSITION = 7;

    private boolean previouslySelected = false;

    // Creates new save object that is used to save circuits into different slots.
    private Save save = new Save();

    // Creates new wire object that is used to connect two circuit components together.
    private Wire wire = new Wire();

    // Creates new ComponentMover object that is used to move circuit components from
    // one cell to another.
    private ComponentMover componentMover = new ComponentMover();



    // This is the portion of the code that will determine what is to be done
    // once the player has touched the screen
    void determineUserSelection(Point touchPosition, Grid grid) {
        int touchPositionN = grid.getCellN(touchPosition);

        if (grid.getMenuToDisplay() == grid.getMainMenu()) {
            determineMainMenuSelection(touchPosition, grid, touchPositionN);
        }
        else if (grid.getMenuToDisplay() == grid.getSaveMenu()) {
            determineSaveMenuSelection(touchPosition, grid);
        }
        else if (grid.getMenuToDisplay() == grid.getLoadMenu()) {
            determineLoadMenuSelection(touchPosition, grid);
        }
        else if (grid.getMenuToDisplay() == grid.getGatesMenu()) {
            determineGatesMenuSelection(touchPosition, grid, touchPositionN);
        }
    }


    private void determineMainMenuSelection (Point touchPosition, Grid grid, int touchPositionN) {

        // if the touch was inside the user interface figure out which button was touched
        if(touchPosition.y >= grid.getGridHeight() - grid.getButtonLength()) {

            // Cycle through each button to determine which was selected by the user
            for (int i = 0; i < grid.getButtonList().size(); i++) {
                if (touchPosition.x == grid.getButtonList().get(i).getButtonXCoordinate()) {
                    if (touchPosition.x < 9) {
                        // If the x-coordinate of the user's tap matches the x-coordinate of this
                        // button, and the selected button is not a blank button, mark this button
                        // as selected/tapped by the user
                        grid.getButtonList().get(i).wasITouched(touchPosition);
                    }
                }
                else {
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            // RUN button
            if (grid.getButtonList().get(RUN_BUTTON_POSITION).getSelected()) {
                for(int i = 0; i < grid.getCellList().size(); i++) {
                    if (grid.getCellList().get(i) instanceof LAMP) {
                        ((LAMP) grid.getCellList().get(i)).evalLamp();
                    }
                }
            }
            // SAVE button
            else if (grid.getButtonList().get(SAVE_BUTTON_POSITION).getSelected()) {
                grid.loadSaveMenu();
                grid.getButtonList().get(SAVE_BUTTON_POSITION).toggleButton();
            }
            // LOAD button
            else if (grid.getButtonList().get(LOAD_BUTTON_POSITION).getSelected()) {
                grid.loadLoadMenu();
                grid.getButtonList().get(LOAD_BUTTON_POSITION).toggleButton();
            }
            // GATES button
            else if (grid.getButtonList().get(GATES_BUTTON_POSITION).getSelected()) {
                grid.loadGatesMenu();
                grid.getButtonList().get(GATES_BUTTON_POSITION).toggleButton();
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
                case LINK_BUTTON_POSITION:
                    // if there has been a previously selected cell and it isn't an empty cell
                    if (previouslySelected && grid.getCellList().get(grid.getPreviousTouchN()).getGateNum() != -1) {
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
                case MOVE_BUTTON_POSITION:
                    // if there has been a previously selected cell
                    if (previouslySelected) {
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
                case DELETE_BUTTON_POSITION:
                    // turn the cell selected back into an EmptyCell
                    Cell deleteCell = grid.getCellList().get(touchPositionN);
                    deleteCell.deleteConnections();
                    grid.getCellList().set(touchPositionN,new EmptyCell(deleteCell));
                    break;

                // SWITCH button
                case SWITCH_BUTTON_POSITION:
                    // creates a Switch after being given information of the cell
                    grid.getCellList().set(touchPositionN,new SWITCH(grid.getCellList().get(touchPositionN)));
                    break;

                // LAMP button
                case LAMP_BUTTON_POSITION:
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
                else {
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            // BACK button
            if (grid.getButtonList().get(BACK_BUTTON_POSITION).getSelected()) {
                grid.loadMainMenu();
                grid.getButtonList().get(BACK_BUTTON_POSITION).toggleButton();
            }
            // Save Slot A
            else if (grid.getButtonList().get(SAVE_SLOT_A_POSITION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListA(), grid);
                grid.loadMainMenu();
                grid.getButtonList().get(SAVE_SLOT_A_POSITION).toggleButton();
            }
            // Save Slot B
            else if (grid.getButtonList().get(SAVE_SLOT_B_POSITION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListB(), grid);
                grid.loadMainMenu();
                grid.getButtonList().get(SAVE_SLOT_B_POSITION).toggleButton();
            }
            // Save Slot C
            else if (grid.getButtonList().get(SAVE_SLOT_C_POSITION).getSelected()) {
                save.saveList(grid.getCellList(), grid.getCellListC(), grid);
                grid.loadMainMenu();
                grid.getButtonList().get(SAVE_SLOT_C_POSITION).toggleButton();
            }
        }
    }


    private void determineLoadMenuSelection (Point touchPosition, Grid grid) {
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
                else {
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            // BACK button
            if (grid.getButtonList().get(BACK_BUTTON_POSITION).getSelected()) {
                grid.loadMainMenu();
                grid.getButtonList().get(BACK_BUTTON_POSITION).toggleButton();
            }
            // Load Save Slot A
            else if (grid.getButtonList().get(SAVE_SLOT_A_POSITION).getSelected()) {
                save.saveList(grid.getCellListA(), grid.getCellList(), grid);
            }
            // Load Save Slot B
            else if (grid.getButtonList().get(SAVE_SLOT_B_POSITION).getSelected()) {
                save.saveList(grid.getCellListB(), grid.getCellList(), grid);
            }
            // Load Save Slot C
            else if (grid.getButtonList().get(SAVE_SLOT_C_POSITION).getSelected()) {
                save.saveList(grid.getCellListC(), grid.getCellList(), grid);
            }
        }
    }


    private void determineGatesMenuSelection (Point touchPosition, Grid grid, int touchPositionN) {

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
                else {
                    // If this button is not selected and is not one of the save buttons, mark this
                    // button as not selected by the user
                    grid.getButtonList().get(i).clearButtonSelection();
                }
            }

            // BACK button
            if (grid.getButtonList().get(BACK_BUTTON_POSITION).getSelected()) {
                grid.loadMainMenu();
                grid.getButtonList().get(BACK_BUTTON_POSITION).toggleButton();
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
                case AND_GATE_BUTTON_POSITION:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new AND(grid.getCellList().get(touchPositionN)));
                    break;

                // NAND button
                case NAND_GATE_BUTTON_POSITION:
                    // creates an AND gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NAND(grid.getCellList().get(touchPositionN)));
                    break;

                // OR button
                case OR_GATE_BUTTON_POSITION:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new OR(grid.getCellList().get(touchPositionN)));
                    break;

                // NOR button
                case NOR_GATE_BUTTON_POSITION:
                    // creates an OR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOR(grid.getCellList().get(touchPositionN)));
                    break;

                // XOR button
                case XOR_GATE_BUTTON_POSITION:
                    // creates a XOR gate after being given information of the cell
                    grid.getCellList().set(touchPositionN, new XOR(grid.getCellList().get(touchPositionN)));
                    break;

                // NOT button
                case NOT_GATE_BUTTON_POSITION:
                    // creates a NOT gate after being given information of the cell
                    grid.getCellList().set(touchPositionN,new NOT(grid.getCellList().get(touchPositionN)));
                    break;
            }
        }
    }
}
