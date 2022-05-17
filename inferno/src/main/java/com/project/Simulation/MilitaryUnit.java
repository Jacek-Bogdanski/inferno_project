package com.project.Simulation;

/**
 * Klasa abstrakcyjna po której dziedziczą postacie w symulacji
 */
public abstract class MilitaryUnit {
    public Integer hp=0;
    public Integer damage =0;
    public Integer ammunition =0;
    public Integer speed=0;
    public Integer attackRange=0;

    public boolean isAlive=true;
    public char team;
    public Position position;

    public MilitaryUnit(char team, Position position){
        this.team = team;
        this.position = position;
    }

    public void move(){}

    public void pickUpRation(){}

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
