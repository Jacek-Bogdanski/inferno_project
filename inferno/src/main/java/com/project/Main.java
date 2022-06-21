package com.project;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Klasa odpowiadająca za uruchomienie okienka z interfejsem użytkownika
 */
public class Main {
    /**
     * Konstruktor programu
     * 
     * @param args argumenty tekstowe
     */
    public static void main(String[] args) {
        Router router = new Router();
        router.showMainView();

        setParamsFromFile();
    }

    static void setParamsFromFile() {
        FileRead readStream = new FileRead("params.json");
        Gson gson = new Gson();

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> myMap = gson.fromJson(readStream.getContent(), type);

        myMap.forEach((a, b) -> {
            System.out.println(a + " ; " + b);
            switch (a) {
                case "MAP_SIZE":
                    Parameters.MAP_SIZE = Integer.valueOf(b);
                    break;
                case "MAX_ITERATION_COUNT":
                    Parameters.MAX_ITERATION_COUNT = Integer.valueOf(b);
                    break;
                case "ITERATION_COUNT":
                    Parameters.ITERATION_COUNT = Integer.valueOf(b);
                    break;
                case "PRINT_DEBUG_TO_CONSOLE":
                    Parameters.PRINT_DEBUG_TO_CONSOLE = Boolean.parseBoolean(b);
                    break;
                case "PRINT_MAP_WHILE_SIMULATION":
                    Parameters.PRINT_MAP_WHILE_SIMULATION = Boolean.parseBoolean(b);
                    break;
                case "ITERATION_DURATION":
                    Parameters.ITERATION_DURATION = Integer.valueOf(b);
                    break;
                case "TANK_A_COUNT":
                    Parameters.TANK_A_COUNT = Integer.valueOf(b);
                    break;
                case "SOLDIER_A_COUNT":
                    Parameters.SOLDIER_A_COUNT = Integer.valueOf(b);
                    break;
                case "GUNNER_A_COUNT":
                    Parameters.GUNNER_A_COUNT = Integer.valueOf(b);
                    break;
                case "TANK_B_COUNT":
                    Parameters.TANK_B_COUNT = Integer.valueOf(b);
                    break;
                case "SOLDIER_B_COUNT":
                    Parameters.SOLDIER_B_COUNT = Integer.valueOf(b);
                    break;
                case "GUNNER_B_COUNT":
                    Parameters.GUNNER_B_COUNT = Integer.valueOf(b);
                    break;
                case "FUEL_DROP_PROBABILITY":
                    Parameters.FUEL_DROP_PROBABILITY = Double.valueOf(b);
                    break;
                case "FUEL_DROP_VALUE":
                    Parameters.FUEL_DROP_VALUE = Integer.valueOf(b);
                    break;
                case "FOOD_DROP_PROBABILITY":
                    Parameters.FOOD_DROP_PROBABILITY = Double.valueOf(b);
                    break;
                case "FOOD_DROP_VALUE":
                    Parameters.FOOD_DROP_VALUE = Integer.valueOf(b);
                    break;
                case "AMMUNITION_DROP_PROBABILITY":
                    Parameters.AMMUNITION_DROP_PROBABILITY = Double.valueOf(b);
                    break;
                case "AMMUNITION_DROP_VALUE":
                    Parameters.AMMUNITION_DROP_VALUE = Integer.valueOf(b);
                    break;
                case "SOLDIER_FOOD_USAGE_PROBABILITY":
                    Parameters.SOLDIER_FOOD_USAGE_PROBABILITY = Double.valueOf(b);
                    break;
                case "SOLDIER_SPEED":
                    Parameters.SOLDIER_SPEED = Integer.valueOf(b);
                    break;
                case "SOLDIER_ATTACK_RANGE":
                    Parameters.SOLDIER_ATTACK_RANGE = Integer.valueOf(b);
                    break;
                case "SOLDIER_ATTACK_DAMAGE":
                    Parameters.SOLDIER_ATTACK_DAMAGE = Integer.valueOf(b);
                    break;
                case "SOLDIER_HP":
                    Parameters.SOLDIER_HP = Integer.valueOf(b);
                    break;
                case "SOLDIER_AMMUNITION":
                    Parameters.SOLDIER_AMMUNITION = Integer.valueOf(b);
                    break;
                case "SOLDIER_FOOD":
                    Parameters.SOLDIER_FOOD = Integer.valueOf(b);
                    break;
                case "GUNNER_SPEED":
                    Parameters.GUNNER_SPEED = Integer.valueOf(b);
                    break;
                case "GUNNER_ATTACK_RANGE":
                    Parameters.GUNNER_ATTACK_RANGE = Integer.valueOf(b);
                    break;
                case "GUNNER_ATTACK_DAMAGE":
                    Parameters.GUNNER_ATTACK_DAMAGE = Integer.valueOf(b);
                    break;
                case "GUNNER_HP":
                    Parameters.GUNNER_HP = Integer.valueOf(b);
                    break;
                case "GUNNER_AMMUNITION":
                    Parameters.GUNNER_AMMUNITION = Integer.valueOf(b);
                    break;
                case "TANK_FUEL_USAGE_PROBABILITY":
                    Parameters.TANK_FUEL_USAGE_PROBABILITY = Double.valueOf(b);
                    break;
                case "TANK_FUEL":
                    Parameters.TANK_FUEL = Integer.valueOf(b);
                    break;
                case "TANK_SPEED":
                    Parameters.TANK_SPEED = Integer.valueOf(b);
                    break;
                case "TANK_ATTACK_RANGE":
                    Parameters.TANK_ATTACK_RANGE = Integer.valueOf(b);
                    break;
                case "TANK_ATTACK_DAMAGE":
                    Parameters.TANK_ATTACK_DAMAGE = Integer.valueOf(b);
                    break;
                case "TANK_HP":
                    Parameters.TANK_HP = Integer.valueOf(b);
                    break;
                case "TANK_AMMUNITION":
                    Parameters.TANK_AMMUNITION = Integer.valueOf(b);
                    break;
                default:
                    break;
            }
        });
    }
}