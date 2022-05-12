package com.project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {

    /**
     * Tablica zawierająca dane konfiguracyjne.
     * Dostępne pola:
     * mapSize, buildingCount, iterationCount,
     * tankCountA, tankCountB,
     * soldierCountA, soldierCountB,
     * gunnerCountA, gunnerCountB,
     * fuelProbability, ammunitionProbability, foodProbability
     */
    private Map<String,Number> config = new HashMap<>();

    SimulationView(Router parent,Map<String,Number> config) {
        this.config = config;
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*
         * Napisy na ekranie
         */
        Label title = new Label("TRWA SYMULACJA", 35, Colors.white);
        Label description = new Label("Żarcik, jeszcze nie ;).", 25, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Przyciski
         */
        Button startButton = new Button("KONIEC", 30, Colors.black);
        startButton.addActionListener(e -> parent.showMainView());

        /*
         * Utworzenie widoku
         */
        Component[] viewItems = {
                Box.createVerticalGlue(),
                title,
                Box.createRigidArea(new Dimension(0, 20)),
                description,
                Box.createRigidArea(new Dimension(0, 40)),
                startButton,
                Box.createVerticalGlue(),
                copyright, Box.createRigidArea(new Dimension(0, 20)),
        };

        for (Component item : viewItems) {
            this.add(item);
        }
    }

}
