package com.project.Simulation;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static com.project.Parameters.*;

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
    private Map<String, Integer> config;

    public Integer alliveA = 0;
    public Integer alliveB = 0;

    JTextPane mapArea;

    public Field[][] map;
    int mapSize;
    Integer[] dropProbability = new Integer[3]; //0-fuel  1-ammo 2-food

    // numer aktualnej iteracji
    int iterationNumber = 1;

    // liczba wszystkich iteracji do wykonania
    int ITERATION_NUMBER = 10;

    private final Random rand = new Random();

    public SimulationMap(Map<String, Integer> config, JTextPane mapArea) {
        this.config = config;
        this.mapArea = mapArea;
//        this.mapSize = config.get("mapSize");
        this.mapSize = MAP_SIZE;
        this.dropProbability[0] = config.get("fuelProbability");
        this.dropProbability[1] = config.get("ammunitionProbability");
        this.dropProbability[2] = config.get("foodProbability");
        this.map = this.generateMap();
        this.fillMap();
        // this.printMapToConsole(this.map);
        this.printMapToMapArea();
        this.runSimulation();
    }

    public void runSimulation() {
//        for (int i = 0; i < ITERATION_NUMBER; i++) {
//            // this.handleIteration();
//        }


        // Po odkomentowaniu kodu poniżej, symulacja trwa do momentu, aż jedna druzyna wyginie

//        while(alliveA>0&&alliveB>0){
//            this.handleIteration();
//        }
    }

    public void handleIteration() {
        this.iterationNumber++;

        int randomNumber = rand.nextInt(101);
        if (dropProbability[0]>randomNumber){
            dropFuelOnMap();
            System.out.println("Zrzucam paliwo na mape");
        }
        if (dropProbability[1]>randomNumber){
            dropFuelOnMap();
            System.out.println("Zrzucam amunicje na mape");
        }
        if (dropProbability[2]>randomNumber){
            dropFuelOnMap();
            System.out.println("Zrzucam jedzenie na mape");
        }


        // PRZESUNIĘCIE OBIEKTÓW
        // 1. wejście do danego pola i wyciągnięcie obiektów
        // 2. wylosowanie nowych wspolrzednych
        // 3. aktualizacja obiektu position
        // 4. przekopiowanie obiektu do nowego pola
        // 5. usunięcie obiektu ze starego pola
        // 6. inkrementacja wartości iterationNumber - do sprawdzenia czy juz sie
        // poruszył

        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                for (int i = 0; i < units.size(); i++) {
                    MilitaryUnit unit = units.get(i);
                    if (!unit.isAlive)
                        continue;
                    if (unit.iterationNumber == this.iterationNumber)
                        continue;
                    unit.iterationNumber = this.iterationNumber;
                    unit.move(this);
                }
            }
        }

        // WYKONANIE ATAKÓW

        // kazdy obiekt atakuje tylko jeden, najblizczy obiekt przeciwnika

        // Iteracja wykonująca atak przez każdy żywy obiekt
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> attackingUnits = map[x][y].units;
                for (MilitaryUnit attackingUnit : attackingUnits) {
                    if (!attackingUnit.isAlive)
                        continue;
                    attackingUnit.attack(this);
                }
            }
        }

        // Oznaczenie zniszczonych obiektów za zabite
        this.alliveA = 0;
        this.alliveB = 0;
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                for (MilitaryUnit unit : units) {
                    if (unit.hp <= 0)
                        unit.isAlive = false;
                    if (unit.isAlive && unit.team == 'A')
                        this.alliveA++;
                    if (unit.isAlive && unit.team == 'B')
                        this.alliveB++;
                }
            }
        }

//usuwanie pustych dropow
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<Drop> drops = map[x][y].drops;
                for (Drop drop : drops) {
                    if (drop.getValue()<=0){
                        drops.remove(drop);
                    }
                }
            }
        }
//podnoszenie dropu
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                ArrayList<Drop> drops = map[x][y].drops;
                if (map[x][y].drops.size()==0) continue;
                for (MilitaryUnit unit : units) {
                    if (unit.isAlive) {
                        switch (unit.symbol) {
                            case "T":
                                Tank tank = (Tank) unit;
                                for (Drop drop : drops) {
                                    switch (drop.type) {
                                        case "fuel":
                                            tank.addFuel(drop.collect(TANK_FUEL - tank.getFuel()));
                                            break;
                                        case "ammo":
                                            tank.addAmmo(drop.collect(TANK_AMMUNITION - tank.ammunition));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                break;

                            case "G":
                                Gunner gunner = (Gunner) unit;
                                for (Drop drop : drops) {
                                    switch (drop.type) {
                                        case "ammo":
                                            gunner.addAmmo(drop.collect(GUNNER_AMMUNITION - gunner.ammunition));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                break;
                            case "S":
                                Soldier soldier = (Soldier) unit;
                                for (Drop drop : drops) {
                                    switch (drop.type) {
                                        case "ammo":
                                            soldier.addAmmo(drop.collect(SOLDIER_AMMUNITION - soldier.ammunition));
                                            break;
                                        case "food":
                                            soldier.eat(drop.collect(SOLDIER_FOOD - soldier.getFood()));
                                        default:
                                            break;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }

        this.printMapToMapArea();
        // this.printMapToConsole(this.map);
    }

    public int getFieldType(Position position) {
        try {
            Field field = this.map[position.x][position.y];
            return field.type;
        } catch (Exception e) {
            return -1;
        }
    }

    private Field[][] generateMap() {

        Field[][] map = new Field[mapSize][mapSize];

        /*
         * Wypelnianie mapy typami pola
         */
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                map[x][y] = new Field(0);
            }
            int buildingsCount = mapSize / 10;
            while (buildingsCount != 0) { // jeden budynek generowany na 10 pol
                int ypos = rand.nextInt(mapSize - 1);
                if (map[x][ypos].type == 0) {
                    map[x][ypos].type = 1;
                    buildingsCount--;
                }
            }
        }

        return map;
    }

    private void fillMap() {
        dropItemsOnMap();
        /*
         * 1. wylosowanie pozycji x,y
         * 2. sprawdzenie, czy obiekt moze tam stanąć
         * 3. wstawienie obiektu do wylosowanego pola
         */

        // CZOŁG ANI ARTYLERZYSTA NIE MOGĄ STAC TAM GDZIE BUDYNEK
        // W budynku moze byc tylko piechur

        // Dodanie czołgów drużyny A
        Integer tankACounter = config.get("tankCountA");
        while (tankACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B') {
                map[x][y].units.add(new Tank('A', new Position(x, y)));
                tankACounter--;
            }
        }

        // Dodanie czołgów drużyny B
        Integer tankBCounter = config.get("tankCountB");
        while (tankBCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B' && map[x][y].units.size() == 0) {
                map[x][y].units.add(new Tank('B', new Position(x, y)));
                tankBCounter--;
            }
        }

        // Dodanie piechurów drużyny A
        Integer soldierACounter = config.get("soldierCountA");
        while (soldierACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].units.add(new Soldier('A', new Position(x, y)));
            soldierACounter--;
        }

        // Dodanie piechurów drużyny B
        Integer soldierBCounter = config.get("soldierCountB");
        while (soldierBCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].units.add(new Soldier('B', new Position(x, y)));
            soldierBCounter--;
        }

        // Dodanie artylerzystów drużyny A
        Integer gunnerACounter = config.get("gunnerCountA");
        while (gunnerACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B') {
                map[x][y].units.add(new Gunner('A', new Position(x, y)));
                gunnerACounter--;
            }
        }

        // Dodanie artylerzystów drużyny B
        Integer gunnerBCounter = config.get("gunnerCountB");
        while (gunnerBCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B') {
                map[x][y].units.add(new Gunner('B', new Position(x, y)));
                gunnerBCounter--;
            }
        }

        // Obliczenie liczby zywych na poczatku
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                for (MilitaryUnit unit : units) {
                    if (unit.isAlive && unit.team == 'A')
                        this.alliveA++;
                    if (unit.isAlive && unit.team == 'B')
                        this.alliveB++;
                }
            }
        }
    }

    public void printMapToConsole(Field[][] map) {
        // Wydruk mapy do konsoli X-budynek, A-tankA, B-tankB

        System.out.println(" ");
        System.out.println("Iteracja: " + this.iterationNumber);
        System.out.println("Wydruk mapy:");
        for (Field[] row : map) {
            System.out.print("|");
            for (Field field : row) {
                switch (field.type) {
                    case 1:
                        System.out.print("X");
                        break;
                    case -1:
                        break;
                    default:
                        if (field.units.size() == 0) {
                            System.out.print(" ");
                            break;
                        }
                        System.out.print(field.units.get(0).team);
                        break;
                }
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println(" ");
        }
    }

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "MONOSPACED");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public void printMapToMapArea() {
        // Wydruk mapy do konsoli X-budynek, A-tankA, B-tankB
        appendToPane(this.mapArea, "", Color.BLACK);
        this.mapArea.setText("");
//        this.mapArea.setDocument(new PlainDocument());
        appendToPane(this.mapArea, "Iteracja " + this.iterationNumber + " | żywych A: " + alliveA + " | żywych B: " + alliveB + " | CZERWONY A, NIEBIESKI B\n\n", Color.BLACK);

        for (Field[] row : this.map) {
            appendToPane(this.mapArea, "      |", Color.BLACK);
            for (Field field : row) {
                //tymczasowe
                if (field.drops.size()>0){
                    appendToPane(this.mapArea,"D",Color.GREEN);
                }

                switch (field.type) {
                    case 1:
                        appendToPane(this.mapArea, "##", new Color(160,160,160));
                        break;
                    case -1:
                        break;
                    default:
                        if (field.units.size() == 0) {
                            appendToPane(this.mapArea, "  ", Color.BLACK);
                            break;
                        }

                        MilitaryUnit unit = field.units.get(0);
                        // team chwilowo zastąpiony symbolem jednostki
                        String team= unit.symbol;
                        Color color;
                        switch (unit.team) {
                            case 'A':
                                color = new Color(255, 0, 0);
                                if(!unit.isAlive) color = new Color(80,40, 0);
                                break;
                            case 'B':
                                color = new Color(0,0,255);
                                if(!unit.isAlive) color = new Color(0,40,80);
                                break;
                            default:
                                color = new Color(0,0,0);
                                if(!unit.isAlive) color = new Color(80,80,80);
                                break;
                        }
                        appendToPane(this.mapArea, team,color );

                        if(field.units.size()==1) {
                            appendToPane(this.mapArea, " ",Color.BLACK );
                            break;
                        }

                        MilitaryUnit unit2 = field.units.get(1);
                        String team2= String.valueOf(unit2.team);
                        Color color2;
                        switch (unit2.team) {
                            case 'A':
                                color2 = new Color(255, 0, 0);
                                if(!unit2.isAlive) color2 = new Color(60,20,20);
                                break;
                            case 'B':
                                color2 = new Color(0,0,255);
                                if(!unit2.isAlive) color2 = new Color(20,20,60);
                                break;
                            default:
                                color2 = new Color(0,0,0);
                                if(!unit2.isAlive) color2 = new Color(20,20,20);
                                break;
                        }
                        appendToPane(this.mapArea, team2, color2 );

                        break;
                }
                appendToPane(this.mapArea, " ", Color.BLACK);

            }
            appendToPane(this.mapArea, "|\n", Color.BLACK);
        }
    }

    void dropItemsOnMap(){
        dropFuelOnMap();
        dropAmmoOnMap();
        dropFoodOnMap();
    }

    void dropFuelOnMap() {
        Integer fuelCounter = config.get("fuelProbability") * mapSize / 100;
        while (fuelCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].drops.add(new Drop("fuel", 100));
            fuelCounter--;
        }
    }

        void dropAmmoOnMap() {
            Integer ammunitionCounter = config.get("ammunitionProbability") * mapSize / 100;
            while (ammunitionCounter != 0) {
                int x = rand.nextInt(mapSize - 1);
                int y = rand.nextInt(mapSize - 1);
                map[x][y].drops.add(new Drop("ammo", 100));
                ammunitionCounter--;
            }
        }
    void dropFoodOnMap(){
        Integer foodCouner = config.get("foodProbability")*mapSize/100;
        while (foodCouner != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].drops.add(new Drop("food", 100));
            foodCouner--;
        }

    }
}
