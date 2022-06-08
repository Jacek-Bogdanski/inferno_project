package com.project;

import javax.swing.*;
import java.awt.*;

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
        Label description = new Label("Po uruchomieniu symulacji, mapa wyświetli się dopiero po wykonaniu obiczeń.", 15, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Przyciski
         */
        Button backButton = new Button("Powrót", 20, Colors.darkGrey);
        backButton.addActionListener(e -> parent.showMainView());
        Button startButton = new Button("Rozpocznij", 20, Colors.black);
        startButton.addActionListener(e -> {

            try {
                alert.setVisible(false);
                parent.showSimulationView();
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
