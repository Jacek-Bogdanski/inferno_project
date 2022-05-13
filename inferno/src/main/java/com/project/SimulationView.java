package com.project;

import com.project.Simulation.Field;
import com.project.Simulation.Tank;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;


/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {
    /**
     * Map config:
     * Tablica zawierająca dane konfiguracyjne.
     * Dostępne pola:
     * mapSize, buildingCount, iterationCount,
     * tankCountA, tankCountB,
     * soldierCountA, soldierCountB,
     * gunnerCountA, gunnerCountB,
     * fuelProbability, ammunitionProbability, foodProbability
     */

    SimulationView(Router parent,Map<String,Number> config) {
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Field[][] map = generateMap(config.get("mapSize").intValue(), config);


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


    private Field[][] generateMap(int mapSize, Map<String,Number> config){
        Field[][] map = new Field[mapSize][mapSize];
        Tank[] tanksA = new Tank[config.get("tankCountA").intValue()];
        Tank[] tanksB =new Tank[config.get("tankCountB").intValue()];


        Random rand = new Random();
        for (int x = 0 ; x<mapSize; x++)
        {
            for (int y = 0 ; y<mapSize; y++)
            {
                map[x][y] = new Field(null,0);
            }
            for (int z = 0; z < mapSize/10;z++){               // jeden budynek generowany na 10 pol
                map[x][rand.nextInt(mapSize-1)].type = 1;
            }
        }

        return map;
    }



}
