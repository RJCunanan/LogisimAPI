package com.example.logisim;
import java.util.*;

public class Undo{
    private Vector<Cell> undoSaved;
    private Stack<Vector<Cell>> save;
    Save savePlease;
    private Grid grid;
    Undo(Grid gridSave, Vector<Cell> initialArray){
        grid= gridSave;
        save= new Stack<>();
        savePlease= new Save();
        undoSaved = new Vector<>();
        Cell temp;
        for(int i=0; i<initialArray.size(); i++) {
            temp = initialArray.get(i);
            undoSaved.add(new EmptyCell(temp));
        }
    }
    void saveUndo(Vector<Cell> saveThis){
        savePlease.saveList(saveThis, undoSaved, grid);
        save.push(undoSaved);
    }
    Vector<Cell> returnUndo() {
        return save.pop();
    }
    boolean isEmpty(){
        return save.isEmpty();
    }

}