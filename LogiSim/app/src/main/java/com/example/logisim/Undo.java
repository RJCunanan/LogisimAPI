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
import java.util.*;

public class Undo{
    Stack<Cell> save;
    void Undo(){
        save= new Stack<>();
    }
}
