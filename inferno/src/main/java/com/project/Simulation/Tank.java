package com.project.Simulation;

import java.util.Random;

public class Tank extends MilitaryUnit{
    private Integer fuel;
    private final Random rand = new Random();
    /**
     * Konstruktor
     * @param team nazwa drużyny [char]
     * @param position pozycja początkowa
     */
    public Tank(char team,Position position){
        super(team,position);
        this.setParams();
    }

    /**
     * Metoda ustawiająca parametry początkowe obiektu
     */
    private void setParams(){
        this.fuel=50;
        this.ammunition =10;
        this.attackRange = 7;
        this.damage =50;        //maksymalny dmg, pozniej podczas ataku bedzie losowa wartosc w takim zakresie
        this.hp=100;
        this.speed=2;
    }

    @Override
    public Position move(SimulationMap map){

        Position newPosition;
        do {
            newPosition = this.generateNewPosition(map, this.speed, this.position);
        } while(map.getFieldType(newPosition) != 0);

        this.makeMove(map,this.position,newPosition);
        System.out.println("ruch czołgu [id="+this.id+"], (" + this.position.x + ", " + this.position.y +")->(" + newPosition.x + ", " + newPosition.y +")");

        this.position = newPosition;

        return this.position;
    }


}
