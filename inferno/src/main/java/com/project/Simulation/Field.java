package com.project.Simulation;

public class Field {
    public MilitaryUnit unit;
    public int type ;       // 1- budynek 0- ziemia

    public Field(MilitaryUnit unit, int type){
        this.unit = unit;
        this.type=type;
    }

}
