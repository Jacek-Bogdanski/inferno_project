package com.project.Simulation;

import java.util.Objects;

/**
 * Klasa odpowiadająca za przechowywanie informacji o położeniu obiektu
 */
public class Position {
    public Integer x;
    public Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static boolean equals(Position pos1, Position pos2) {
        if (!Objects.equals(pos1.x, pos2.x))
            return false;
        if (!Objects.equals(pos1.y, pos2.y))
            return false;
        return true;
    }

}
