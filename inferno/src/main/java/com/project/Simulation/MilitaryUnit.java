package com.project.Simulation;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Klasa abstrakcyjna po której dziedziczą postacie w symulacji
 */
public abstract class MilitaryUnit implements Movable {
    public static Integer instanceCount = 0;
    public Integer id;
    public Integer iterationNumber = 0;

    public String symbol=" ";

    public Integer hp = 0;
    public Integer damage = 0;
    public Integer ammunition = 0;
    public Integer speed = 0;
    public Integer attackRange = 0;

    public boolean isAlive = true;
    public char team;
    public Position position;


    /**
     * Konstruktor
     * @param team litera reprezentująca drużynę
     * @param position obiekt pozycji początkowej
     */
    public MilitaryUnit(char team, Position position) {
        instanceCount++;
        this.id = instanceCount;
        this.team = team;
        this.position = position;
    }


    /**
     * Metoda wykonująca ruch obiektu na podstawie parametru speed
     * Parametr speed określa maksymalną liczbę pól do przemieszczenia się w jednej iteracji
     * Np. speed=5 oznacza maksymalny ruch 5 pionowo i 5 poziomo
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    public void move(SimulationMap map) {
        // do nothing
    }


    /**
     * Metoda podnosząca itemek z pola, na którym sie znajduje obiekt
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    public void pickUpDrop(SimulationMap map) {
    }


    /**
     * Metoda wykonująca atak
     * 1. jednostka przeszukuje mapę, aby znaleźć pola możliwe do zaatakowania
     * 2. spośród tych pól wybiera najbliższy obiekt
     * 3. Jeśli taki istnieje, atakuje go
     * @param simulationMap obiekt mapy potrzebny do podniesienia obiektu
     */
    public void attack(SimulationMap simulationMap) {
        MilitaryUnit unitToAttack = null;
        int distanceToAttack = simulationMap.mapSize * simulationMap.mapSize;

        // Iteracja wybierająca najblizszy obiekt do zaatakowania
        for (int attackX = 0; attackX < simulationMap.mapSize; attackX++) {
            for (int attackY = 0; attackY < simulationMap.mapSize; attackY++) {
                // obliczenie odległości euklidesowej
                int distance = (int) sqrt((attackX - this.position.x) * (attackX - this.position.x) + (attackY - this.position.y) * (attackY - this.position.y));
                if (distance > this.attackRange)
                    continue;

                // Iteracja wybierająca bezpośrednio jednostkę do zaatakowania
                ArrayList<MilitaryUnit> attackedUnits = simulationMap.map[attackX][attackY].units;
                for (MilitaryUnit attackedUnit : attackedUnits) {
                    if (!attackedUnit.isAlive)
                        continue;
                    if (attackedUnit.team == this.team)
                        continue;
                    if (distanceToAttack < distance)
                        continue;
                    unitToAttack = attackedUnit;
                    distanceToAttack = distance;
                }
            }
        }
        // Ostateczne zaatakowanie wybranej jednostki
        if (unitToAttack == null)
            return;
        unitToAttack.takeDamage(this.damage);
        System.out.println("atak obiektu [id=" + this.id + "] na obiekt [id=" + unitToAttack.id + "] : remaining hp=" + unitToAttack.hp);
    }


    /**
     * Metoda przyjmująca atak
     * Określenie śmierci postaci następuje po wykonaniu wszystkich ataków w iteracji
     * @param dmg liczba otrzymanych punktów obrażeń
     */
    public void takeDamage(Integer dmg) {
        this.hp -= dmg;
        if (this.hp < 0)
            this.hp = 0;
    }


    /**
     * Metoda wykonująca ruch na mapie
     * @param map - obiekt mapy potrzebny do wykonania ruchu
     * @param newPosition destination position
     * @return boolean whether the object has moved
     */
    protected boolean makeMove(SimulationMap map, Position newPosition) {
        Position prevPosition = this.position;
        this.position = newPosition;

        // w przypadku braku poruszenia
        if (Position.equals(newPosition, prevPosition))
            return false;

        // przeniesienie obiektu
        map.map[prevPosition.x][prevPosition.y].units.remove(this);
        map.map[newPosition.x][newPosition.y].units.add(this);

        String moveMessage = "ruch postaci [id=" + this.id + "], (" + prevPosition.x + ", " + prevPosition.y + ")->("
                + newPosition.x + ", " + newPosition.y + ")";
        System.out.println(moveMessage);

        return true;
    }


    /**
     * Metoda generująca nowy obiekt pozycji
     * @param map mapa, na której odbywa się ruch
     * @param speed parametr szybkości obiektu
     * @param prevPosition pozycja początkowa ruchu
     * @return obiekt nowej pozycji
     */
    protected Position generateNewPosition(SimulationMap map, Integer speed, Position prevPosition) {
        Position newPosition;
        Random rand = new Random();
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
