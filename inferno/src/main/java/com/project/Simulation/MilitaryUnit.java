package com.project.Simulation;

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
        if(this.hp<0) this.isAlive = false;
    }
}
