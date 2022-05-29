package com.project;

/**
 * Klasa przechowująca parametry symulacji
 */
public class Parameters {
    /**
     * Parametry symulacji
     */
    public static Integer MAP_SIZE = 50;

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
    public static Integer GUNNER_SPEED = 0;
    public static Integer GUNNER_ATTACK_RANGE = 30;
    public static Integer GUNNER_ATTACK_DAMAGE = 600;
    public static Integer GUNNER_HP = 50;
    public static Integer GUNNER_AMMUNITION = 30;

    /*
     * Parametry czołgu
     */
    public static Double TANK_FUEL_USAGE_PROBABILITY = 0.3;
    public static Integer TANK_FUEL = 30;
    public static Integer TANK_SPEED = 1;
    public static Integer TANK_ATTACK_RANGE = 6;
    public static Integer TANK_ATTACK_DAMAGE = 200;
    public static Integer TANK_HP = 500;
    public static Integer TANK_AMMUNITION = 200;
}
