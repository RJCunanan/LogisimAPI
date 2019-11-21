package com.example.logisim;
import java.util.*;

public class Undo{
    Stack<Cell> save;
    Vector<Cell> undoSaved;
    void Undo(Vector<Cell> saveThis){
        undoSaved = new Vector<>();
        for(int i=0; i<saveThis.size(); i++)
            undoSaved.add(null);
    }
    void saveUndo(Vector<Cell> saveThis){
        Cell temp;
        for(int i=0; i<=saveThis.size();i++){
            temp=saveThis.get(i);
            if(temp instanceof EmptyCell)
                undoSaved.set(i, new EmptyCell(temp));
            else if(temp instanceof SWITCH)
                undoSaved.set(i, new SWITCH(temp));
            else if(temp instanceof AND)
                undoSaved.set(i, new AND(temp));
            else if(temp instanceof NAND)
                undoSaved.set(i, new NAND(temp));
            else if(temp instanceof OR)
                undoSaved.set(i, new OR(temp));
            else if(temp instanceof NOR)
                undoSaved.set(i, new NOR(temp));
            else if(temp instanceof XOR)
                undoSaved.set(i, new XOR(temp));
            else if(temp instanceof NOT)
                undoSaved.set(i, new NOT(temp));
            else if(temp instanceof LAMP)
                undoSaved.set(i, new LAMP(temp));
        }
        for(int i=0; i<=saveThis.size();i++) {
            temp=saveThis.get(i);
        }
    }
}