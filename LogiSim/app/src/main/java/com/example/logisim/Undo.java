package com.example.logisim;
import java.util.*;

public class Undo{
    Stack<Cell> save;
    ArrayList<Cell> temp;
    void Undo(Vector<Cell> saveThis){
        temp = new ArrayList<>();
        for(int i=0; i<saveThis.size(); i++)
            temp.add(null);
    }
}
