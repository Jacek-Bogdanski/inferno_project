package com.project.Simulation;

/**
 * Klasa przechowująca informacje o polu na mapie
 */
public class Field {
    public MilitaryUnit[] units = {};

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
