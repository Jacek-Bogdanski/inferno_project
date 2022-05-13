package com.project.Simulation;

public class Field {
    public MillitaryUnit unit;
    public int type ;       // 1- budynek 0- ziemia

    public Item item;
    public Field(int type){
        this.type=type;
    }

}
