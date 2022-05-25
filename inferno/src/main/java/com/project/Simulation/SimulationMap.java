package com.project.Simulation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class SimulationMap {
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
    private Map<String,Integer> config;

    JTextArea mapArea;

    public Field[][] map;
    int mapSize=0;

    // numer aktualnej iteracji
    int iterationNumber = 1;

    // liczba wszystkich iteracji do wykonania
    int ITERATION_NUMBER = 10;

    private final Random rand = new Random();

    public SimulationMap(Map<String,Integer> config, JTextArea mapArea){
        this.config = config;
        this.mapArea = mapArea;
        this.mapSize=config.get("mapSize");
        this.map = this.generateMap();
        this.fillMap();
//        this.printMapToConsole(this.map);
        this.printMapToMapArea();
        this.runSimulation();
    }

    public void runSimulation(){
        for(int i=0;i<ITERATION_NUMBER;i++) {
//            this.handleIteration();
        }
    }

    public void handleIteration(){
        this.iterationNumber++;
        // 1. wejście do danego pola i wyciągnięcie obiektów
        // 2. wylosowanie nowych wspolrzednych
        // 3. aktualizacja obiektu position
        // 4. przekopiowanie obiektu do nowego pola
        // 5. usunięcie obiektu ze starego pola
        // 6. inkrementacja wartości iterationNumber - do sprawdzenia czy juz sie poruszył






        //problem jest taki ze czolg ktory sie przesunie w kolejnej iteracji moze byc przesuniety jeszcze raz
// trzeba jakos odznaczac przesuniete juz czolgi
// i nie wiem czemu po niby wylosowaniu przesuniecia w prawo przesuwa sie w lewo


        System.out.println("Liczba obiektów: " + MilitaryUnit.instanceCount);
        System.out.println();

        for (int x = 0 ; x<mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                    for(int i=0; i<units.size(); i++){
                        MilitaryUnit unit = units.get(i);
                        if(unit.iterationNumber==this.iterationNumber)
                            continue;
                        unit.iterationNumber = this.iterationNumber;
                        unit.move(this);
                    }

//                units.forEach((unit)->{
//
//                });


//                if (!map[x][y].units.isEmpty()) {
//                    MilitaryUnit unit = map[x][y].units.get(0);
//                    Position newpos = unit.move(map, new Position(x, y), mapSize);
//                    System.out.println("przesuwam czolg [id="+unit.id+"] z (" + x + ", " + y +") do (" + newpos.x + ", " + newpos.y +")");
//                }
            }
        }
        this.printMapToMapArea();
        this.printMapToConsole(this.map);
    }

    public int getFieldType(Position position){
        try{
            Field field = this.map[position.x][position.y];
            return field.type;
        }
        catch(Exception e){
            return -1;
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


        Integer tankACounter=config.get("tankCountA");
        while(tankACounter!=0){
            int x = rand.nextInt(mapSize-1);
            int y = rand.nextInt(mapSize-1);
            if(map[x][y].type!='B'){
                Tank tank = new Tank('A',new Position(x,y));
                map[x][y].units.add(tank);
                tankACounter--;
            }
        }

        Integer tankBCounter=config.get("tankCountB");

        while(tankBCounter!=0){
            int x = rand.nextInt(mapSize-1);
            int y = rand.nextInt(mapSize-1);
            if(map[x][y].type!='B' && map[x][y].units.size()==0){
                Tank tank = new Tank('B',new Position(x,y));
                map[x][y].units.add(tank);
                tankBCounter--;
            }
        }

    }

    public void printMapToConsole(Field[][] map){
        // Wydruk mapy do konsoli X-budynek, A-tankA, B-tankB

        System.out.println("");
        System.out.println("Iteracja: "+this.iterationNumber);
        System.out.println("Wydruk mapy:");
        for(Field[] row:map){
            System.out.print("|");
            for(Field field:row){
                switch(field.type){
                    case 1:
                        System.out.print("X");
                        break;

                    default:
                        if (field.units.size()==0) {
                            System.out.print(" ");
                            break;
                        }
                        System.out.print(field.units.get(0).team);
                        break;
                }
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println("");
        }
    }

    public void printMapToMapArea(){
        // Wydruk mapy do konsoli X-budynek, A-tankA, B-tankB

        this.mapArea.setText("");
        this.mapArea.append("Iteracja "+this.iterationNumber+":\n\n");
        for(Field[] row:this.map){
            this.mapArea.append("      |");
            for(Field field:row){
                switch(field.type){
                    case 1:
                        this.mapArea.append("XX");
                        break;

                    default:
                        if (field.units.size()==0) {
                            this.mapArea.append("  ");
                            break;
                        }
                        if (field.units.size()==1) {
                            this.mapArea.append(String.valueOf(field.units.get(0).team)+" ");
                            break;
                        }
                        this.mapArea.append(String.valueOf(field.units.get(0).team));
                        this.mapArea.append(String.valueOf(field.units.get(1).team));
                        break;
                }
                this.mapArea.append(" ");

            }
            this.mapArea.append("|\n");
        }
    }
}
