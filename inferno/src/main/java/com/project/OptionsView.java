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
        Label title = new Label("Konfiguracja symulacji", 25, Colors.white);
        Label alert = new Label("Wprowadzono nieprawidłowe wartości!", 18, Colors.red);
        alert.setVisible(false);
        Label errorOutput = new Label("", 14, Colors.red);
        Label description = new Label("Zmień parametry lub pozostaw domyślne", 15, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Edytowalne pola: Rozmiar mapy, losowość itemków
         */
        NumberField sizeField = new NumberField(30);
        NumberField buildingCountField = new NumberField(15);
        NumberField iterationCountField = new NumberField(1000);
        NumberField EParameterField = new NumberField(50);
        NumberField FParameterField = new NumberField(50);
        NumberField GParameterField = new NumberField(50);

        /*
         * Edytowalne pola: Liczby czołgów w danych drużynach
         */
        NumberField A1CountField = new NumberField(20);
        NumberField A2CountField = new NumberField(20);

        /*
         * Edytowalne pola: Liczby piechurów w danych drużynach
         */
        NumberField B1CountField = new NumberField(40);
        NumberField B2CountField = new NumberField(40);

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
        panel.setLayout(new GridLayout(0, 2));
        panel.setBackground(Colors.darkerGrey);
        panel.setPreferredSize(new Dimension(800, 300));
        panel.setMaximumSize(panel.getPreferredSize());
        panel.setMinimumSize(panel.getPreferredSize());

        java.awt.Component[] panelItems = {
                new Label("Liczba czołgów A", 15, Colors.white),
                new Label("Liczba czołgów B", 15, Colors.white),
                A1CountField,
                A2CountField,
                new Label("Liczba piechurów A", 15, Colors.white),
                new Label("Liczba piechurów B", 15, Colors.white),
                B1CountField,
                B2CountField,
                new Label("Liczba artylerzystów A", 15, Colors.white),
                new Label("Liczba artylerzystów B", 15, Colors.white),
                C1CountField,
                C2CountField,
                new Label("Rozmiar mapy", 15, Colors.white),
                new Label("Liczba budynków", 15, Colors.white),
                sizeField,
                buildingCountField,
                new Label("Szybkość dostaw jedzenia [%]", 15, Colors.white),
                new Label("Szybkość dostaw paliwa [%]", 15, Colors.white),
                FParameterField,
                GParameterField,
                new Label("Szybkość dostaw amunicji [%]", 15, Colors.white),
                new Label("Liczba iteracji", 15, Colors.white),
                EParameterField,
                iterationCountField,
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
                Box.createRigidArea(new Dimension(0, 20)),
                panel,
                Box.createRigidArea(new Dimension(0, 15)),
                alert,
                Box.createRigidArea(new Dimension(0, 15)),
                startButton,
                Box.createVerticalGlue(),
                errorOutput,
                Box.createVerticalGlue(),
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),
        };

        for (java.awt.Component item : viewItems) {
            this.add(item);
        }
    }

}
