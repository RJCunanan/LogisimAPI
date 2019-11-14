package com.example.logisim;
import java.util.*;

public class Undo{
    Stack<Cell> save;
    void Undo(){
        save= new Stack<>();
    }
}
