package com.project.Simulation;

import java.util.ArrayList;

/**
 * Klasa przechowująca informacje o polu na mapie
 */
public class Field {
    public ArrayList<MilitaryUnit> units = new ArrayList<>();

    /**
     * Typ pola
     * Wartości: 1- budynek 0- ziemia
     */
    public int type ;

    /**
     * Konstruktor
     * @param type typ pola
     */
    public Field(int type){
        this.type = type;
    }

}
