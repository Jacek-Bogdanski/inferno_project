package com.project;

/**
 * Klasa przechowująca parametry symulacji
 */
public class Parameters {
    /**
     * Parametry symulacji
     */
    public static Integer MAP_SIZE = 100;

    /*
     * Parametry żołnierza
     */
    public static Double SOLDIER_FOOD_USAGE_PROBABILITY = 0.3;
    public static Integer SOLDIER_SPEED = 3;
    public static Integer SOLDIER_ATTACK_RANGE = 3;
    public static Integer SOLDIER_ATTACK_DAMAGE = 20;
    public static Integer SOLDIER_HP = 50;
    public static Integer SOLDIER_AMMUNITION = 50;
    public static Integer SOLDIER_FOOD = 50;

    /*
     * Parametry artylerzysty
     */
    public static Integer GUNNER_SPEED = 0;
    public static Integer GUNNER_ATTACK_RANGE = 30;
    public static Integer GUNNER_ATTACK_DAMAGE = 200;
    public static Integer GUNNER_HP = 10;
    public static Integer GUNNER_AMMUNITION = 2000;

    /*
     * Parametry czołgu
     */
    public static Double TANK_FUEL_USAGE_PROBABILITY = 0.3;
    public static Integer TANK_FUEL = 50;
    public static Integer TANK_SPEED = 1;
    public static Integer TANK_ATTACK_RANGE = 6;
    public static Integer TANK_ATTACK_DAMAGE = 160;
    public static Integer TANK_HP = 500;
    public static Integer TANK_AMMUNITION = 200;
}
