package com.project.Simulation;

/**
 * Klasa przechowująca informacje o polu na mapie
 */
public class Field {
    public MilitaryUnit unit;

    /**
     * Typ pola
     * Wartości: 1- budynek 0- ziemia
     */
    public int type ;

    public Field(MilitaryUnit unit, int type){
        this.unit = unit;
        this.type = type;
    }

}
