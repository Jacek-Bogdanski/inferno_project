package com.project;

/**
 * Klasa przechowująca parametry symulacji
 */
public class Parameters {
    /**
     * Parametry symulacji
     */
    public static Integer MAP_SIZE = 60;

    // liczba iteracji, po których automatyczna symulacja (do wyginięcia) zostaje przerwana
    public static Integer MAX_ITERATION_COUNT = 99999;

    // Liczba iteracji
    // liczba iteracji równa -1 oznacza trwanie symulacji do wyginięcia jednostek jednej z druzyn.
    // liczba iteracji równa -2 oznacza automatyczne iterowanie co 1s do wyginięcia...
    public static Integer ITERATION_COUNT = -2;

    public static boolean PRINT_DEBUG_TO_CONSOLE = false;

    // true znacznie spowalnia symulację
    public static boolean PRINT_MAP_WHILE_SIMULATION = true;
    public static Integer ITERATION_DURATION = 1000;

    public static Integer TANK_A_COUNT = 15;
    public static Integer SOLDIER_A_COUNT = 30;
    public static Integer GUNNER_A_COUNT = 10;

    public static Integer TANK_B_COUNT = 15;
    public static Integer SOLDIER_B_COUNT = 30;
    public static Integer GUNNER_B_COUNT = 10;

    public static Double FUEL_DROP_PROBABILITY = 0.18;
    public static Integer FUEL_DROP_VALUE = 50;

    public static Double FOOD_DROP_PROBABILITY = 0.23;
    public static Integer FOOD_DROP_VALUE = 40;

    public static Double AMMUNITION_DROP_PROBABILITY = 0.15;
    public static Integer AMMUNITION_DROP_VALUE = 100;

    /*
     * Parametry żołnierza
     */
    public static Double SOLDIER_FOOD_USAGE_PROBABILITY = 0.1;
    public static Integer SOLDIER_SPEED = 3;
    public static Integer SOLDIER_ATTACK_RANGE = 3;
    public static Integer SOLDIER_ATTACK_DAMAGE = 40;
    public static Integer SOLDIER_HP = 70;
    public static Integer SOLDIER_AMMUNITION = 10;
    public static Integer SOLDIER_FOOD = 20;

    /*
     * Parametry artylerzysty
     */
    public static Integer GUNNER_SPEED = 1;
    public static Integer GUNNER_ATTACK_RANGE = 30;
    public static Integer GUNNER_ATTACK_DAMAGE = 600;
    public static Integer GUNNER_HP = 50;
    public static Integer GUNNER_AMMUNITION = 30;

    /*
     * Parametry czołgu
     */
    public static Double TANK_FUEL_USAGE_PROBABILITY = 0.3;
    public static Integer TANK_FUEL = 30;
    public static Integer TANK_SPEED = 2;
    public static Integer TANK_ATTACK_RANGE = 6;
    public static Integer TANK_ATTACK_DAMAGE = 200;
    public static Integer TANK_HP = 500;
    public static Integer TANK_AMMUNITION = 200;
}
