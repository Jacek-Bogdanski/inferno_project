package com.project.Simulation;

import java.util.Random;

import static com.project.Simulation.Parameters.*;

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

    public Integer getFood() {
        return food;
    }
}
