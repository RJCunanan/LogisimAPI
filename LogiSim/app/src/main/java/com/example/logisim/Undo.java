package com.example.logisim;
import java.util.*;

public class Undo{
    private Vector<Cell> undoSaved;
    private Stack<Vector<Cell>> save;
    private Grid grid;
    Undo(Grid gridSave){
        grid= gridSave;
        save= new Stack<>();
        undoSaved = new Vector<>();
    }
    void saveUndo(Vector<Cell> saveThis){
        Cell temp;
        for(int i=0; i<=saveThis.size();i++){
            temp=saveThis.get(i);
            if(temp instanceof EmptyCell)
                undoSaved.add(new EmptyCell(temp));
            else if(temp instanceof SWITCH)
                undoSaved.add(new SWITCH(temp));
            else if(temp instanceof AND)
                undoSaved.add(new AND(temp));
            else if(temp instanceof NAND)
                undoSaved.add(new NAND(temp));
            else if(temp instanceof OR)
                undoSaved.add(new OR(temp));
            else if(temp instanceof NOR)
                undoSaved.add(new NOR(temp));
            else if(temp instanceof XOR)
                undoSaved.add(new XOR(temp));
            else if(temp instanceof NOT)
                undoSaved.add(new NOT(temp));
            else if(temp instanceof LAMP)
                undoSaved.add(new LAMP(temp));
        }
        for(int i=0; i<=saveThis.size();i++) {
            temp=saveThis.get(i);
            if(!(temp instanceof EmptyCell)) {
                if(temp.getCellA() != null) {
                    int currCellTailAPos = grid.getCellN(temp.getCellA().cellPosition);
                    undoSaved.get(i).setCellA(undoSaved.get(currCellTailAPos));
                }
                if(temp.getCellB() != null) {
                    int currCellTailBPos = grid.getCellN(temp.getCellB().cellPosition);
                    undoSaved.get(i).setCellB(undoSaved.get(currCellTailBPos));
                }
                for(int k = 0; k < temp.head.size(); k++) {
                    int currCellHeadPos = grid.getCellN(temp.head.get(k).cellPosition);
                    undoSaved.get(i).head.set(k, undoSaved.get(currCellHeadPos));
                }
            }
        }
        save.push(undoSaved);
        undoSaved.clear();
    }
    Vector<Cell> returnUndo() {
        return save.pop();
    }
}