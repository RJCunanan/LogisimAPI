package com.example.logisim;
import java.util.*;

class Undo{
    private Stack<Vector<Cell>> save;
    private Save savePlease;
    private Grid grid;
    Undo(Grid gridSave){
        grid= gridSave;
        save= new Stack<>();
        savePlease= new Save();
    }
    void saveUndo(Vector<Cell> saveThis){
        Vector<Cell> undoSaved = new Vector<>();
        for(int i=0; i<saveThis.size(); i++) {
            Cell temp = saveThis.get(i);
            undoSaved.add(new EmptyCell(temp));
        }
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