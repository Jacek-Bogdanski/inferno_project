package com.project.Simulation;

import java.util.Random;

import static com.project.Parameters.*;

/**
 * Klasa postaci - żołnierz
 *
 * - Porusza się z prędkością SOLDIER_SPEED
 * - Zużywa jedzenie (jednorazowo 1szt, co iterację z prawdopodobieństwem SOLDIER_FOOD_USAGE_PROBABILITY)
 * - Strzela na odległość SOLDIER_ATTACK_RANGE zużywając 1szt amunicji na każdy atak
 * - Atak na tym samym polu nie zużywa amunicji
 * - Zadaje obrażenia SOLDIER_ATTACK_DAMAGE
 * - Ma początkową liczbę punktów życia SOLDIER_HP
 * - Ma początkową amunicję SOLDIER_AMMUNITION
 * - Ma początkowe jedzenie SOLDIER_FOOD
 * - Zbiera napotkane jedzenie i amunicję
 *
 */
public class Soldier extends MilitaryUnit {
    private final Random rand = new Random();

    private Integer food;
    private Double foodUsageProbability;


    /**
     * Konstruktor
     *
     * @param team     nazwa drużyny [char]
     * @param position pozycja początkowa
     */
    public Soldier(char team, Position position) {
        super(team, position);
        this.setParams();
    }

    /**
     * Metoda ustawiająca parametry początkowe obiektu
     */
    private void setParams() {
        this.ammunition = SOLDIER_AMMUNITION;
        this.attackRange = SOLDIER_ATTACK_RANGE;
        this.damage = SOLDIER_ATTACK_DAMAGE;
        this.hp = SOLDIER_HP;
        this.speed = SOLDIER_SPEED;
        this.food = SOLDIER_FOOD;
        this.foodUsageProbability = SOLDIER_FOOD_USAGE_PROBABILITY;
    }

    /**
     * Metoda wykonująca ruch - uwzględnia zużycie jedzenia
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    @Override
    public void move(SimulationMap map) {
        if(this.speed==0)
            return;

        Position prevPosition = this.position;
        Position newPosition;

        // Wylosowanie nowej pozycji, to moze byc tez budynek
        do {
            newPosition = this.generateNewPosition(map, this.speed, prevPosition);
        } while (map.getFieldType(newPosition) < 0);

        if(this.makeMove(map,newPosition))
            this.useFood();
    }

    /**
     * Metoda zwracająca ilość jedzenia
     * @return food amount
     */
    public Integer getFood() {
        return food;
    }

    /**
     * Metoda zużywająca jedzenie z prawdopodobieństwem z parametru
     */
    public void useFood() {
        if(this.food>0) {
            // wylosowanie, czy pobrać jedzenie
            int randomNumber = rand.nextInt(11);
            if(this.foodUsageProbability*10<randomNumber)
                this.food--;
        }

        // oznaczenie jako nieżywy
        if(food<=0)
            this.isAlive=false;
    }
}
