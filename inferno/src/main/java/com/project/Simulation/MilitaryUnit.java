package com.project.Simulation;

/**
 * Klasa abstrakcyjna po której dziedziczą postacie w symulacji
 */
public abstract class MilitaryUnit {
    private Integer hp;
    private Integer dmg;
    private Integer ammo;
    private Integer velocity;
    private Integer attackRange;

    public boolean isAlive=true;
    public char team;

    public void move(){}

    public void pickUpRation(){}

    public void pickUpAmmo(){}

    /**
     * Metoda wykonująca atak
     * @param unit atakowany obiekt
     */
    public void attack(MilitaryUnit unit){
        unit.takeDamage(this.dmg);
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
