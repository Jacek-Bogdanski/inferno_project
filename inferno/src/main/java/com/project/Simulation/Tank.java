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

    protected void makeMove(SimulationMap map, Position prevPosition, Position newPosition){
        // nieprawidlowa pozycja
        if(map.getFieldType(newPosition)==-1) return;

        // w przypadku braku poruszenia
        if(Position.equals(newPosition,prevPosition)) return;

        // przeniesienie obiektu
        map.map[prevPosition.x][prevPosition.y].units.remove(this);
        map.map[newPosition.x][newPosition.y].units.add(this);

    }

    protected Position generateNewPosition(SimulationMap map, Integer speed, Position prevPosition){
        Position newPosition;
        Random rand = new Random();

        // szybkość 5: maksymalnie 5 poziomo i maksymalnie 5 pionowo jednocześnie

        do {
            int moveX = rand.nextInt(speed*2+1) - speed;
            int moveY = rand.nextInt(speed*2+1) - speed;
            int newX = prevPosition.x + moveX;
            int newY = prevPosition.y + moveY;
            newPosition = new Position(newX,newY);
        } while(map.getFieldType(newPosition) == -1); // -1 - nieprawidlowe pole

        return newPosition;
    }

    @Override
    public Position move(SimulationMap map){

        Position newPosition;
        do {
            newPosition = this.generateNewPosition(map, this.speed, this.position);
        } while(map.getFieldType(newPosition) != 0);

        this.makeMove(map,this.position,newPosition);
        System.out.println("czolg [id="+this.id+"], (" + this.position.x + ", " + this.position.y +")->(" + newPosition.x + ", " + newPosition.y +")");

        this.position = newPosition;

        return this.position;
    }


}
