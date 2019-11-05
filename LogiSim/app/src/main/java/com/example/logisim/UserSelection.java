/*
    Team API:
        Jaime Rivera
        RJ Cunanan
        Theodora Fernandez
        Yong Yang
    Course: CSC 131
    Assignment: LogiSim
    Date: 11-2-19
 */


package com.example.logisim;


public class UserSelection {

    private float touchX;
    private float touchY;
    private float horizontalTouched = -100;
    private float verticalTouched = -100;
    private int xCoordinate;
    private int yCoordinate;
    private int selectedButtonXCoordinate;
    private SavedCircuit circuit1, circuit2, circuit3;

    // Constants representing location of taskbar buttons:
    private static final int BOTTOM_ROW = 8;
    private static final int SWITCH_BUTTON = 0;
    private static final int AND_GATE_BUTTON = 1;
    private static final int OR_GATE_BUTTON = 2;
    private static final int NOT_GATE_BUTTON = 3;
    private static final int LED_BUTTON = 4;
    private static final int WIRE_BUTTON = 5;
    private static final int MOVE_BUTTON = 6;
    private static final int DELETE_BUTTON = 7;
    private static final int CLEAR_BUTTON = 8;
    private static final int TOGGLE_BUTTON = 9;
    private static final int RUN_BUTTON = 10;
    private static final int SAVE_BUTTON = 11;
    private static final int SAVE_SLOT_A = 12;
    private static final int SAVE_SLOT_B = 13;
    private static final int SAVE_SLOT_C = 14;



    // Convert the float screen coordinates into int grid coordinates
    public void convertSelectionToGridCoordinates(Grid grid) {
        horizontalTouched = (int)touchX / grid.getBlockSize();
        verticalTouched = (int)touchY / grid.getBlockSize();

        // If tapped grid cell is valid (within the set grid), set coordinates
        if ((int)horizontalTouched < grid.getGridWidth() && (int)verticalTouched < grid.getGridHeight()) {
            this.xCoordinate = (int)horizontalTouched;
            this.yCoordinate = (int)verticalTouched;
        }
    }


    // Determines if the cell the user has selected is a taskbar button:
    public void determineButtonSelection(AbstractGridCell[][] cells, Grid grid, Clear clear, Wire wire) {

        // Checks if the selected cell is located in the first row where the
        // taskbar is
        if (verticalTouched == BOTTOM_ROW && horizontalTouched < 12) {
            selectTappedButton(cells, grid, clear, wire);
        }
    }

    private void selectTappedButton(AbstractGridCell[][] cells, Grid grid, Clear clear, Wire wire) {
        // Cycle through the taskbar until the proper button is selected
        for (int x = 0; x <= 11; x++) {
            if (horizontalTouched == x) {

                // Indicate that the button has been selected and get this
                // buttons x-coordinate for future reference to this button
                cells[x][BOTTOM_ROW].setButtonSelection();
                this.selectedButtonXCoordinate = xCoordinate;

                // When the selected button is the Clear button, clear the grid
                if (horizontalTouched == CLEAR_BUTTON) {
                    clear.clearCells(cells, grid, wire.getComponentCoordinates(), wire);
                }  // Else, if selected button is the Run button, then evaluate the circuit
                else if (horizontalTouched == RUN_BUTTON) {
                    runCircuit(cells, grid);
                }
            }
            else {
                // Clear all previous button selections
                cells[x][BOTTOM_ROW].clearButtonSelection();
            }
        }
    }


    // Puts the correct selected circuit item into a grid cell or performs selected function.
    public void placeItem(AbstractGridCell[][] cells, Grid grid, Wire wire, Delete delete, Move move) {

        // First checks if a button had already been selected and that the placement of the
        // circuit item or action to take is not in the first row where the taskbar is.
        if ((cells[selectedButtonXCoordinate][BOTTOM_ROW].getSelected()) && (yCoordinate != BOTTOM_ROW)) {

            // Use saved x-coordinate of already selected button to determine what circuit
            // item to place or what action to take.
            switch (selectedButtonXCoordinate) {
                case SWITCH_BUTTON:
                    placeSwitch(cells);
                    break;

                case AND_GATE_BUTTON:
                    placeANDGate(cells);
                    break;

                case OR_GATE_BUTTON:
                    placeORGate(cells);
                    break;

                case NOT_GATE_BUTTON:
                    placeNOTGate(cells);
                    break;

                case LED_BUTTON:
                    placeLED(cells);
                    break;

                case WIRE_BUTTON:
                    // Begins process of connecting components with wires
                    wire.checkForConnectedComponents(cells, this);
                    break;

                case MOVE_BUTTON:
                    move.moveComponent(cells, this, wire.getComponentCoordinates());
                    break;

                case DELETE_BUTTON:
                    delete.deleteComponent(cells, this, wire.getComponentCoordinates(), wire);
                    break;

                case TOGGLE_BUTTON:
                    toggleSwitches(cells);
                    break;
            }
        }
        // Checks if the Save button is selected and user is about to tap on either button A, B, or C
        else if (cells[selectedButtonXCoordinate][BOTTOM_ROW].getSelected() && selectedButtonXCoordinate == SAVE_BUTTON) {
            determineSaveSlotForCircuit(cells, grid, wire);
        }
        // When Save button is NOT selected and user is about to tap on button A
        else if (xCoordinate == SAVE_SLOT_A && yCoordinate == BOTTOM_ROW && circuit1 != null) {
            // Load the circuit saved in Button A onto the grid
            circuit1.loadCircuit(cells, grid, wire.getComponentCoordinates());
        }
        // When Save button is NOT selected and user is about to tap on button B
        else if (xCoordinate == 13 && yCoordinate == BOTTOM_ROW && circuit2 != null) {
            // Load the circuit saved in Button B onto the grid
            circuit2.loadCircuit(cells, grid, wire.getComponentCoordinates());
        }
        // When Save button is NOT selected and user is about to tap on button C
        else if (xCoordinate == 14 && yCoordinate == BOTTOM_ROW && circuit3 != null) {
            // Load the circuit saved in Button C onto the grid
            circuit3.loadCircuit(cells, grid, wire.getComponentCoordinates());
        }
    }

    private void determineSaveSlotForCircuit(AbstractGridCell[][] cells, Grid grid, Wire wire) {
        // Saves circuit into Button A
        if ( xCoordinate == SAVE_SLOT_A && yCoordinate == BOTTOM_ROW) {
            circuit1 = new SavedCircuit(cells, grid, wire.getComponentCoordinates());
        }
        // Saves circuit into Button B
        else if ( xCoordinate == SAVE_SLOT_B && yCoordinate == BOTTOM_ROW) {
            circuit2 = new SavedCircuit(cells, grid, wire.getComponentCoordinates());
        }
        // Saves circuit into Button C
        else if ( xCoordinate == SAVE_SLOT_C && yCoordinate == BOTTOM_ROW) {
            circuit3 = new SavedCircuit(cells, grid, wire.getComponentCoordinates());
        }
    }

    private void toggleSwitches(AbstractGridCell[][] cell) {
        // Turns switch on or off when the "run" button is selected and user taps on a
        // switch.
        if (cell[xCoordinate][yCoordinate] instanceof Switch) {
            ((Switch) cell[xCoordinate][yCoordinate]).toggleSwitch();
        }
    }

    private void runCircuit(AbstractGridCell[][] cells, Grid grid) {
        // Starting from the lamp and working backwards to the switch, determine the states
        /// of each circuit component to determine if the lamp should turn on or not.
        for (int x = 0; x < grid.getGridWidth(); x++) {
            for (int y = 0; y < (grid.getGridHeight() - 1); y++) {
                if (cells[x][y] instanceof LED && ((LED)cells[x][y]).getSourceComponent() != null) {
                    ((LED)cells[x][y]).setEvaluatedOutputState();
                }
            }
        }
    }

    private void placeLED(AbstractGridCell[][] cells) {
        if (!cells[xCoordinate][yCoordinate].getOccupiedCell()) {
            // Places an LED into this cell
            cells[xCoordinate][yCoordinate] = new LED(xCoordinate, yCoordinate, cells[selectedButtonXCoordinate][0].getText());

            // Indicate that cell contains component
            cells[xCoordinate][yCoordinate].setAsOccupiedCell();
        }
    }

    private void placeNOTGate(AbstractGridCell[][] cells) {
        if (!cells[xCoordinate][yCoordinate].getOccupiedCell()) {
            // Places a NOT gate into this cell
            cells[xCoordinate][yCoordinate] = new NOT(xCoordinate, yCoordinate, cells[selectedButtonXCoordinate][0].getText());

            // Indicate that cell contains component
            cells[xCoordinate][yCoordinate].setAsOccupiedCell();
        }
    }

    private void placeORGate(AbstractGridCell[][] cells) {
        if (!cells[xCoordinate][yCoordinate].getOccupiedCell()) {
            // Places an OR gate into this cell
            cells[xCoordinate][yCoordinate] = new OR(xCoordinate, yCoordinate, cells[selectedButtonXCoordinate][0].getText());

            // Indicate that cell contains component
            cells[xCoordinate][yCoordinate].setAsOccupiedCell();
        }
    }

    private void placeANDGate(AbstractGridCell[][] cells) {
        if (!cells[xCoordinate][yCoordinate].getOccupiedCell()) {
            // Places an AND gate into this cell
            cells[xCoordinate][yCoordinate] = new AND(xCoordinate, yCoordinate, cells[selectedButtonXCoordinate][0].getText());

            // Indicate that cell contains component
            cells[xCoordinate][yCoordinate].setAsOccupiedCell();
        }
    }

    private void placeSwitch(AbstractGridCell[][] cells) {
        if (!cells[xCoordinate][yCoordinate].getOccupiedCell()) {
            // Places a switch into this cell
            cells[xCoordinate][yCoordinate] = new Switch(xCoordinate, yCoordinate, cells[selectedButtonXCoordinate][0].getText());

            // Indicate that cell contains component
            cells[xCoordinate][yCoordinate].setAsOccupiedCell();
        }
    }


    // Necessary setter methods for indirectly changing coordinates
    public void setTouchX(float touchX) { this.touchX = touchX; }

    public void setTouchY(float touchY) { this.touchY = touchY; }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

}
