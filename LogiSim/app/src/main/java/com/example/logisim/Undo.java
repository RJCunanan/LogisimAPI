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
        for(int i=0; i<=saveThis.size();i++){
            Cell temp=saveThis.get(i);
            if(temp instanceof EmptyCell)
                undoSaved.set(i, new EmptyCell(temp));
            else if(temp instanceof SWITCH)
                undoSaved.set(i, new SWITCH(temp)); break;
            }
    }
}


            /*if(currCell instanceof EmptyCell)
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
            saveHere.set(i, new LAMP(currCell));*/