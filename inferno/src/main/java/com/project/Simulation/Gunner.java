package com.project.Simulation;

import java.util.Random;

import static com.project.Simulation.Parameters.*;

/**
 * Klasa postaci - artylerzysta
 *
 * - Nie porusza się
 * - Strzela na odległość GUNNER_ATTACK_RANGE zużywając 1szt amunicji na każdy atak
 * - Atak na tym samym polu nie zużywa amunicji
 * - Zadaje obrażenia GUNNER_ATTACK_DAMAGE
 * - Ma początkową liczbę punktów życia GUNNER_HP
 * - Ma początkową amunicję GUNNER_AMMUNITION
 * - Zbiera amunicję z pola, na którym się znajduje
 *
 */
public class Gunner extends MilitaryUnit {
    private final Random rand = new Random();

    /**
     * Konstruktor
     * @param team     nazwa drużyny [char]
     * @param position pozycja początkowa
     */
    public Gunner(char team, Position position) {
        super(team, position);
        this.setParams();
    }

    /**
     * Metoda ustawiająca parametry początkowe obiektu
     */
    private void setParams() {
        this.attackRange = GUNNER_ATTACK_RANGE;
        this.ammunition = GUNNER_AMMUNITION;
        this.damage = GUNNER_ATTACK_DAMAGE;
        this.hp = GUNNER_HP;
        this.speed = GUNNER_SPEED;
    }
}
