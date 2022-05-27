package com.project.Simulation;

import java.util.Random;

import static com.project.Simulation.Parameters.*;

/**
 * Klasa postaci - czołg
 *
 * - Porusza się z prędkością TANK_SPEED
 * - Zużywa paliwo przy ruchu (jednorazowo 1szt, co iterację z prawdopodobieństwem TANK_FUEL_USAGE_PROBABILITY)
 * - Strzela na odległość TANK_ATTACK_RANGE zużywając 1szt amunicji na każdy atak
 * - Atak na tym samym polu też zużywa amunicje
 * - Zadaje obrażenia TANK_ATTACK_DAMAGE
 * - Ma początkową liczbę punktów życia TANK_HP
 * - Ma początkową amunicję TANK_AMMUNITION
 * - Ma początkowe jedzenie TANK_FUEL
 * - Zbiera napotkane paliwo i amunicję
 *
 */
public class Tank extends MilitaryUnit {
    private final Random rand = new Random();

    private Integer fuel;
    private Double fuelUsageProbability;


    /**
     * Konstruktor
     * 
     * @param team     nazwa drużyny [char]
     * @param position pozycja początkowa
     */
    public Tank(char team, Position position) {
        super(team, position);
        this.setParams();
    }

    /**
     * Metoda ustawiająca parametry początkowe obiektu
     */
    private void setParams() {
        this.fuel = TANK_FUEL;
        this.ammunition = TANK_AMMUNITION;
        this.attackRange = TANK_ATTACK_RANGE;
        this.damage = TANK_ATTACK_DAMAGE; // maksymalny dmg, pozniej podczas ataku bedzie losowa wartosc w takim zakresie
        this.hp = TANK_HP;
        this.speed = TANK_SPEED;
        this.fuelUsageProbability =TANK_FUEL_USAGE_PROBABILITY;
    }

    public Integer getFuel() {
        return fuel;
    }
}
