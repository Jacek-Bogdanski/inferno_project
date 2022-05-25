package com.project.Simulation;

import java.util.Random;

/**
 * Klasa abstrakcyjna po której dziedziczą postacie w symulacji
 */
public abstract class MilitaryUnit {
    public static Integer instanceCount = 0;
    public Integer id;
    public Integer iterationNumber = 0;

    public Integer hp=0;
    public Integer damage =0;
    public Integer ammunition =0;
    public Integer speed=0;
    public Integer attackRange=0;

    public boolean isAlive=true;
    public char team;
    public Position position;

    public MilitaryUnit(char team, Position position){
        instanceCount++;
        this.id = instanceCount;
        this.team = team;
        this.position = position;
    }

    public Position move(SimulationMap map){
        return this.position;
    }

    public void pickUpFood(){}

    public void pickUpAmmo(){}

    /**
     * Metoda wykonująca atak
     * @param unit atakowany obiekt
     */
    public void attack(MilitaryUnit unit){
        unit.takeDamage(this.damage);
    }

    /**
     * Metoda przyjmująca atak
     * @param dmg liczba otrzymanych punktów obrażeń
     */
    public void takeDamage(Integer dmg){
        this.hp -= dmg;
        if(this.hp<0) this.hp=0;
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
}
