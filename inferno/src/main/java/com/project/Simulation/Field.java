package com.project.Simulation;

public class Field {
    public MillitaryUnit unit;
    public int type ;       // 1- budynek 0- ziemia

    public Field(MillitaryUnit unit, int type){
        this.unit = unit;
        this.type=type;
    }

}
