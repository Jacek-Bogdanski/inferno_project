package com.project;

import javax.swing.*;
import java.awt.*;

import java.util.Map;
import java.util.Random;


/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {


    SimulationView(Router parent,Map<String,Number> config) {
        /**
         * Tablica zawierająca dane konfiguracyjne.
         * Dostępne pola:
         * mapSize, buildingCount, iterationCount,
         * tankCountA, tankCountB,
         * soldierCountA, soldierCountB,
         * gunnerCountA, gunnerCountB,
         * fuelProbability, ammunitionProbability, foodProbability
         */
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        int[][] map = new int[config.get("mapSize").intValue()][config.get("mapSize").intValue()];

        for (int x = 0 ; x< config.get("mapSize").intValue(); x++)
        {
            for (int y = 0; y< config.get("mapSize").intValue(); y++)
            {
                Random rand = new Random();
                int answer = rand.nextInt(2);
                map[x][y] = answer;
            }
        }


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
         * Ikony
         */
        ImageIcon dirt = new ImageIcon("dirt.jpg");
        ImageIcon brick = new ImageIcon("brick.jpg");

        JPanel dirtPanel = new JPanel();
        dirtPanel.setBounds(20,20,64,64);


//        dirtPanel.setBackground(new Color (78,53,36));
//        this.add(dirtPanel);

         /* Utworzenie widoku
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
