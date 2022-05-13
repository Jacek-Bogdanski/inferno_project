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

    private Field[][] map;
    private Map<String,Integer> config=null;
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

    SimulationView(Router parent,Map<String,Integer> config) {
        this.config=config;
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.map = generateMap(config);

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


        System.out.println("");System.out.println("");
        System.out.println("Wydruk mapy:");
        for(Field[] row:this.map){
            System.out.print("|");
            for(Field field:row){
                switch(field.type){
                    case 1:System.out.print("B");break;
                    default:System.out.print(" ");break;
                }

            }
            System.out.print("|");
            System.out.println("");
        }

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
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),
        };
        for (Component item : viewItems) {
            this.add(item);
        }
    }

    private Field[][] generateMap(Map<String,Integer> config){
        int mapSize = config.get("mapSize").intValue();
        Field[][] map = new Field[mapSize][mapSize];
        Random rand = new Random();
        /*
        Wypelnianie mapy typami pola
         */
        for (int x = 0 ; x<mapSize; x++)
        {
            for (int y = 0 ; y<mapSize; y++)
            {
                map[x][y] = new Field(0);
            }
            for (int z = 0; z < mapSize/10;z++){               // jeden budynek generowany na 10 pol
                map[x][rand.nextInt(mapSize-1)].type = 1;
            }
        }
        return map;
    }

    private void fillMap(){

        /*
        wypelnianie mapy jednostkami narazie tylko czolgi
         */


        /*
         * 1. wylosowanie pozycji x,y
         * 2. sprawdzenie, czy obiekt moze tam stanąć
         * 3. wstawienie obiektu do wylosowanego pola
         */

        Integer tankACounter=0;
        Integer tankBCounter=0;

        for(int i=0;i<this.config.get("tankCountA");i++){
            System.out.println("Dodaje czołg A");
        }



//        Tank[] tanksA = new Tank[config.get("tankCountA").intValue()];
//        Tank[] tanksB = new Tank[config.get("tankCountB").intValue()];
//
//        int q = mapSize/(tanksA.length+1);
//        for (int x=0; x<tanksA.length;x++){
//            if (x%q==1){
//                //map[0][x+q].unit=new Tank('A',0,x+q);
//            }
//        }
    }



}
