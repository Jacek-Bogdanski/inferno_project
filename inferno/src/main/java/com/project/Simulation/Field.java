package com.project.Simulation;

import java.util.ArrayList;

/**
 * Klasa przechowująca informacje o polu na mapie
 */
public class Field {
    public ArrayList<MilitaryUnit> units = new ArrayList<>();
    public ArrayList<Drop> drops = new ArrayList<>();
    /**
     * Typ pola
     * Wartości: 1- budynek 0- ziemia
     */
    public int type;

    /**
     * Konstruktor
     * 
     * @param type typ pola
     */
    public Field(int type) {
        this.type = type;
    }

    /**
     * Metoda zwracająca rodzaj pola
     * 
     * @return field type
     */
    public int getType() {
        return this.type;
    }

    /**
     * Metoda zwracająca listę jednostek na tym polu
     * 
     * @return unit list
     */
    public ArrayList<MilitaryUnit> getUnits() {
        return this.units;
    }

    /**
     * Metoda zwracająca listę dropów na tym polu
     * 
     * @return drop list
     */
    public ArrayList<Drop> getDrops() {
        return this.drops;
    }
}
