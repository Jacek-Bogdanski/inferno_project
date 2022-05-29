package com.project.Simulation;

/**
 * Klasa obiektu zbieranego z mapy przez postacie
 */
public class Drop implements Pickable{
    String type = ""; // ammo,food,fuel
    private int value = 0; // 0-100

    public Integer getValue(){
        return this.value;
    }

    public Integer collect(Integer value){
        if(value==0) {
            return 0;
        }
        if(value>this.value) {
            Integer toCollect = this.value;
            this.value=0;
            return toCollect;
        }
        this.value-= value;
        return value;
    }
    public Drop(String type, Integer value){
        this.type = type;
        this.value = value;
    }
}
