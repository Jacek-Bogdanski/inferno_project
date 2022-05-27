package com.project.Simulation;

import java.util.Random;

/**
 * Klasa abstrakcyjna po której dziedziczą postacie w symulacji
 */
public abstract class MilitaryUnit {
    public static Integer instanceCount = 0;
    public Integer id;
    public Integer iterationNumber = 0;

    public Integer hp = 0;
    public Integer damage = 0;
    public Integer ammunition = 0;
    public Integer speed = 0;
    public Integer attackRange = 0;

    public boolean isAlive = true;
    public char team;
    public Position position;

    public MilitaryUnit(char team, Position position) {
        instanceCount++;
        this.id = instanceCount;
        this.team = team;
        this.position = position;
    }

    /**
     * Metoda wykonująca ruch obiektu na podstawie parametru speed
     *
     * Parametr speed określa maksymalną liczbę pól do przemieszczenia się w jednej iteracji
     * Np. speed=5 oznacza maksymalny ruch 5 pionowo i 5 poziomo
     *
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    public void move(SimulationMap map) {
        this.makeMove(map);
    }

    public void pickUpFood() {
    }

    public void pickUpAmmo() {
    }

    /**
     * Metoda wykonująca atak
     * 
     * @param unit atakowany obiekt
     */
    public void attack(MilitaryUnit unit) {
        unit.takeDamage(this.damage);
    }

    /**
     * Metoda przyjmująca atak
     * 
     * @param dmg liczba otrzymanych punktów obrażeń
     */
    public void takeDamage(Integer dmg) {
        this.hp -= dmg;
        if (this.hp < 0)
            this.hp = 0;
    }

    /**
     * Metoda wykonująca ruch
     * @param map - obiekt mapy potrzebny do wykonania ruchu
     * @return boolean whether the object has moved
     */
    protected boolean makeMove(SimulationMap map) {
        if(this.speed==0)
            return false;

        Position prevPosition = this.position;
        Position newPosition;

        do {
            newPosition = this.generateNewPosition(map, this.speed, this.position);
        } while (map.getFieldType(newPosition) != 0);
        this.position = newPosition;

        String moveMessage = "ruch postaci [id=" + this.id + "], (" + prevPosition.x + ", " + prevPosition.y + ")->("
                + newPosition.x + ", " + newPosition.y + ")";
        System.out.println(moveMessage);

        // w przypadku braku poruszenia
        if (Position.equals(newPosition, prevPosition))
            return false;

        // przeniesienie obiektu
        map.map[prevPosition.x][prevPosition.y].units.remove(this);
        map.map[newPosition.x][newPosition.y].units.add(this);

        return true;
    }

    protected Position generateNewPosition(SimulationMap map, Integer speed, Position prevPosition) {
        Position newPosition;
        Random rand = new Random();

        // szybkość 5: maksymalnie 5 poziomo i maksymalnie 5 pionowo jednocześnie

        do {
            int moveX = rand.nextInt(speed * 2 + 1) - speed;
            int moveY = rand.nextInt(speed * 2 + 1) - speed;
            int newX = prevPosition.x + moveX;
            int newY = prevPosition.y + moveY;
            newPosition = new Position(newX, newY);
        } while (map.getFieldType(newPosition) == -1); // -1 - nieprawidlowe pole

        return newPosition;
    }
}
