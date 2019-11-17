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
import java.util.Vector;


public class Save {

    void saveList(Vector<Cell> saveThis, Vector<Cell> saveHere, Grid grid) {
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
                    int currCellTailAPos = grid.getCellN(currCell.getCellA().cellPosition);
                    saveHere.get(i).setCellA(saveHere.get(currCellTailAPos));
                }
                if(currCell.getCellB() != null) {
                    int currCellTailBPos = grid.getCellN(currCell.getCellB().cellPosition);
                    saveHere.get(i).setCellB(saveHere.get(currCellTailBPos));
                }
                for(int k = 0; k < currCell.head.size(); k++) {
                    int currCellHeadPos = grid.getCellN(currCell.head.get(k).cellPosition);
                    saveHere.get(i).head.set(k, saveHere.get(currCellHeadPos));
                }
            }
        }

    }

}
