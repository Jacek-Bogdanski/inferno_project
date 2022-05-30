package com.project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Klasa odpowiadająca za wyświetlenie widoku konfiguracji symulacji
 */
public class OptionsView extends JPanel {
    OptionsView(Router parent) {
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*
         * Napisy na ekranie
         */
        Label title = new Label("Podgląd parametrów symulacji", 25, Colors.white);
        Label alert = new Label("", 18, Colors.red);
        alert.setVisible(false);
        Label errorOutput = new Label("", 14, Colors.red);
        Label description = new Label("Parametry zmienia się w klasie Parameters", 15, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Edytowalne pola: Rozmiar mapy, losowość itemków
         */
        NumberField sizeField = new NumberField(40);
        NumberField buildingCountField = new NumberField(15);
        NumberField iterationCountField = new NumberField(1000);
        NumberField EParameterField = new NumberField(20);
        NumberField FParameterField = new NumberField(20);
        NumberField GParameterField = new NumberField(20);

        /*
         * Edytowalne pola: Liczby czołgów w danych drużynach
         */
        NumberField A1CountField = new NumberField(20);
        NumberField A2CountField = new NumberField(20);

        /*
         * Edytowalne pola: Liczby piechurów w danych drużynach
         */
        NumberField B1CountField = new NumberField(30);
        NumberField B2CountField = new NumberField(30);

        /*
         * Edytowalne pola: Liczby artylerzystów w danych drużynach
         */
        NumberField C1CountField = new NumberField(10);
        NumberField C2CountField = new NumberField(10);

        /*
         * Przyciski
         */
        Button backButton = new Button("Powrót", 20, Colors.darkGrey);
        backButton.addActionListener(e -> parent.showMainView());
        Button startButton = new Button("Rozpocznij symulację", 20, Colors.black);
        startButton.addActionListener(e -> {
            Map<String, Integer> data = new HashMap<>();

            try {

                data.put("mapSize", parseInt(sizeField.getText()));
                data.put("buildingCount", parseInt(buildingCountField.getText()));
                data.put("iterationCount", parseInt(iterationCountField.getText()));
                data.put("tankCountA", parseInt(A1CountField.getText()));
                data.put("tankCountB", parseInt(A2CountField.getText()));
                data.put("soldierCountA", parseInt(B1CountField.getText()));
                data.put("soldierCountB", parseInt(B2CountField.getText()));
                data.put("gunnerCountA", parseInt(C1CountField.getText()));
                data.put("gunnerCountB", parseInt(C2CountField.getText()));
                data.put("fuelProbability", parseInt(GParameterField.getText()));
                data.put("ammunitionProbability", parseInt(EParameterField.getText()));
                data.put("foodProbability", parseInt(FParameterField.getText()));
                alert.setVisible(false);
                parent.showSimulationView(data);
            } catch (Exception exc) {
                alert.setVisible(true);
                errorOutput.setText(exc.toString());
                System.out.println("EXCEPTION OCCURRED: " + exc);
            }
        });

        /*
         * Utworzenie panelu z formularzem
         */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3));
        panel.setBackground(Colors.darkerGrey);
        panel.setPreferredSize(new Dimension(800, 300));
        panel.setMaximumSize(panel.getPreferredSize());
        panel.setMinimumSize(panel.getPreferredSize());

        java.awt.Component[] panelItems = {
                // with editable fields

                // new Label("Liczba czołgów A", 15, Colors.white),
                // new Label("Liczba czołgów B", 15, Colors.white),
                // A1CountField,
                // A2CountField,
                // new Label("Liczba piechurów A", 15, Colors.white),
                // new Label("Liczba piechurów B", 15, Colors.white),
                // B1CountField,
                // B2CountField,
                // new Label("Liczba artylerzystów A", 15, Colors.white),
                // new Label("Liczba artylerzystów B", 15, Colors.white),
                // C1CountField,
                // C2CountField,
                // new Label("Rozmiar mapy (wyłączone)", 15, Colors.white),
                // new Label("Liczba budynków", 15, Colors.white),
                // sizeField,
                // buildingCountField,
                // new Label("Szybkość dostaw jedzenia [%]", 15, Colors.white),
                // new Label("Szybkość dostaw paliwa [%]", 15, Colors.white),
                // FParameterField,
                // GParameterField,
                // new Label("Szybkość dostaw amunicji [%]", 15, Colors.white),
                // new Label("Liczba iteracji", 15, Colors.white),
                // EParameterField,
                // iterationCountField,

                // without editable fields

                new Label("Rozmiar mapy: " + Parameters.MAP_SIZE, 15, Colors.white),
                new Label("Liczba iteracji: " + Parameters.ITERATION_COUNT, 15, Colors.white),
                Box.createRigidArea(new Dimension(0, 10)),
                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),

                new Label("Liczba czołgów A: " + Parameters.TANK_A_COUNT, 15, Colors.white),
                new Label("Liczba piechurów A: " + Parameters.SOLDIER_A_COUNT, 15, Colors.white),
                new Label("Liczba artylerzystów A: " + Parameters.GUNNER_A_COUNT, 15, Colors.white),

                new Label("Liczba czołgów B: " + Parameters.TANK_B_COUNT, 15, Colors.white),
                new Label("Liczba piechurów B: " + Parameters.SOLDIER_B_COUNT, 15, Colors.white),
                new Label("Liczba artylerzystów B: " + Parameters.GUNNER_B_COUNT, 15, Colors.white),

                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),

                new Label("Prawdop. dropu paliwa: " + Parameters.FUEL_DROP_PROBABILITY, 15, Colors.white),
                new Label("Prawdop. dropu jedzenia: " + Parameters.FOOD_DROP_PROBABILITY, 15, Colors.white),
                new Label("Prawdop. dropu amunicji: " + Parameters.AMMUNITION_DROP_PROBABILITY, 15, Colors.white),
                new Label("Wartość dropu paliwa: " + Parameters.FUEL_DROP_VALUE, 15, Colors.white),
                new Label("Wartość dropu jedzenia: " + Parameters.FOOD_DROP_VALUE, 15, Colors.white),
                new Label("Wartość dropu amunicji: " + Parameters.AMMUNITION_DROP_VALUE, 15, Colors.white),

                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),
                Box.createRigidArea(new Dimension(0, 5)),

                new Label("Czołg: HP: " + Parameters.TANK_HP, 15, Colors.white),
                new Label("Żołnierz: HP: " + Parameters.SOLDIER_HP, 15, Colors.white),
                new Label("Artylerzysta: HP: " + Parameters.GUNNER_HP, 15, Colors.white),

                new Label("Czołg: siła ataku: " + Parameters.TANK_ATTACK_DAMAGE, 15, Colors.white),
                new Label("Żołnierz: siła ataku: " + Parameters.SOLDIER_ATTACK_DAMAGE, 15, Colors.white),
                new Label("Artylerzysta: siła ataku: " + Parameters.GUNNER_ATTACK_DAMAGE, 15, Colors.white),

                new Label("Czołg: zasięg ataku: " + Parameters.TANK_ATTACK_RANGE, 15, Colors.white),
                new Label("Żołnierz: zasięg ataku: " + Parameters.SOLDIER_ATTACK_RANGE, 15, Colors.white),
                new Label("Artylerzysta: zasięg ataku: " + Parameters.GUNNER_ATTACK_RANGE, 15, Colors.white),

                new Label("Czołg: szybkość ruchu: " + Parameters.TANK_SPEED, 15, Colors.white),
                new Label("Żołnierz: szybkość ruchu: " + Parameters.SOLDIER_SPEED, 15, Colors.white),
                new Label("Artylerzysta: szybkość ruchu: " + Parameters.GUNNER_SPEED, 15, Colors.white),

                new Label("Czołg: amunicja: " + Parameters.TANK_AMMUNITION, 15, Colors.white),
                new Label("Żołnierz: amunicja: " + Parameters.SOLDIER_AMMUNITION, 15, Colors.white),
                new Label("Artylerzysta: amunicja: " + Parameters.GUNNER_AMMUNITION, 15, Colors.white),

                new Label("Czołg: ilość jedzenia: " + Parameters.TANK_FUEL, 15, Colors.white),
                new Label("Żołnierz: ilość jedzenia: " + Parameters.SOLDIER_FOOD, 15, Colors.white),
                Box.createRigidArea(new Dimension(0, 5)),

                new Label("Czołg: spalanie paliwa: " + Parameters.TANK_FUEL_USAGE_PROBABILITY, 15,
                        Colors.white),
                new Label("Żołnierz: szybkość jedzenia: " + Parameters.SOLDIER_FOOD_USAGE_PROBABILITY, 15,
                        Colors.white),
                Box.createRigidArea(new Dimension(0, 5)),

        };

        for (java.awt.Component item : panelItems) {
            panel.add(item);
        }

        /*
         * Utworzenie widoku
         */
        java.awt.Component[] viewItems = {
                // with editable fields

                // Box.createVerticalGlue(),
                // backButton,
                // Box.createRigidArea(new Dimension(0, 40)),
                // title,
                // Box.createRigidArea(new Dimension(0, 20)),
                // description,
                // Box.createRigidArea(new Dimension(0, 20)),
                // panel,
                // Box.createRigidArea(new Dimension(0, 15)),
                // alert,
                // Box.createRigidArea(new Dimension(0, 15)),
                // startButton,
                // Box.createVerticalGlue(),
                // errorOutput,
                // Box.createVerticalGlue(),
                // copyright,
                // Box.createRigidArea(new Dimension(0, 20)),

                // without editable fields

                Box.createVerticalGlue(),
                backButton,
                Box.createRigidArea(new Dimension(0, 40)),
                title,
                Box.createRigidArea(new Dimension(0, 20)),
                description,
                Box.createVerticalGlue(),
                panel,
                Box.createVerticalGlue(),
                startButton,
                Box.createRigidArea(new Dimension(0, 40)),
                errorOutput,
                Box.createRigidArea(new Dimension(0, 40)),
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),

        };

        for (java.awt.Component item : viewItems) {
            this.add(item);
        }
    }

}

/*
 * 
 * 
 * 
 * 
 * 
 * public static Double TANK_FUEL_USAGE_PROBABILITY = 0.3;
 * public static Integer TANK_FUEL = 30;
 * public static Integer TANK_SPEED = 1;
 * public static Integer TANK_ATTACK_RANGE = 6;
 * public static Integer TANK_ATTACK_DAMAGE = 200;
 * public static Integer TANK_HP = 500;
 * public static Integer TANK_AMMUNITION = 200;
 * 
 */