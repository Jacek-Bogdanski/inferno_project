package com.project;

import com.project.Simulation.Field;
import com.project.Simulation.Position;
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
    private Map<String,Integer> config = null;
    private final Random rand = new Random();     // by nie powtarzac
    int mapSize=0;
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

        this.mapSize=config.get("mapSize");
        this.map = generateMap();
        fillMap();
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

// Wydruk mapy do konsoli X-budynek, A-tankA, B-tankB
        System.out.println("");System.out.println("");
        System.out.println("Wydruk mapy:");
        for(Field[] row:this.map){
            System.out.print("|");
            for(Field field:row){
                switch(field.type){
                    case 1:System.out.print("X");break;
                    default:
                    {
                        if (field.units.size()>0){
                            if(field.units.get(0).team=='A'){
                                System.out.print("A");
                            }else{
                                System.out.print("B");
                            }
                        }else{
                            System.out.print(" ");
                        }
                        break;
                    }
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

    private Field[][] generateMap(){

        Field[][] map = new Field[mapSize][mapSize];

        /*
        Wypelnianie mapy typami pola
         */
        for (int x = 0 ; x<mapSize; x++)
        {
            for (int y = 0 ; y<mapSize; y++)
            {
                map[x][y] = new Field(0);
            }
            int buildingsCount = mapSize/10;
            while (buildingsCount!=0){               // jeden budynek generowany na 10 pol
                int ypos= rand.nextInt(mapSize-1);
                if(map[x][ypos].type == 0){
                    map[x][ypos].type = 1;
                    buildingsCount--;
                }
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

//        CZOŁG ANI ARTYLERZYSTA NIE MOGĄ STAC TAM GDZIE BUDYNEK
        // W budynku moze byc tylko piechur


        Tank[] tanksA = new Tank[config.get("tankCountA")];
        Integer tankACounter=config.get("tankCountA");
        int counter=0;
        while(tankACounter!=0){
            int x = rand.nextInt(mapSize-1);
            int y = rand.nextInt(mapSize-1);
            if(map[x][y].type!='B'){
                Tank tank = new Tank('A',new Position(x,y));
                map[x][y].units.add(tank);
                tanksA[counter]= tank;
                counter++;
                tankACounter--;
//                System.out.println("Dodaje czołg A");
            }

        }
        Tank[] tanksB = new Tank[config.get("tankCountB")];
        Integer tankBCounter=config.get("tankCountB");
        counter=0;
        while(tankBCounter!=0){
            int x = rand.nextInt(mapSize-1);
            int y = rand.nextInt(mapSize-1);
            if(map[x][y].type!='B' && map[x][y].units.size()==0){
                Tank tank = new Tank('B',new Position(x,y));
                map[x][y].units.add(tank);
                tanksB[counter]= tank;
                counter++;
                tankBCounter--;
//                System.out.println("Dodaje czołg B");
            }

        }



    }



}
