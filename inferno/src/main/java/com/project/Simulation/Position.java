package com.project.Simulation;

/**
 * Klasa odpowiadająca za przechowywanie informacji o położeniu obiektu
 */
public class Position {
    public Integer x;
    public Integer y;
    public Position(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    public Position(){
        this.x = 0;
        this.y = 0;
    }
}
