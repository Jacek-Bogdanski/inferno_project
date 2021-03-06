package com.project.Simulation;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

import static com.project.Parameters.*;

/**
 * Klasa mapy symulacji
 */
public class SimulationMap {
    private Integer alliveA = 0;
    private Integer alliveB = 0;

    private long startTime;
    private long endTime;

    JTextPane mapArea;

    public Field[][] map;
    int mapSize;

    // numer aktualnej iteracji
    private int iterationNumber = 1;

    private final Random rand = new Random();
    private String outputCsvContent = "";
    private String outputCsvLine = "";
    private String outputCsvHeaders = "";

    /**
     * Konstruktor
     *
     * @param mapArea obiekt JTextPane do wydruku mapy
     */
    public SimulationMap(JTextPane mapArea) {
        this.mapArea = mapArea;
        this.mapSize = MAP_SIZE;
        this.map = this.generateMap();
        this.fillMap();
        this.printMapToMapArea();
        writeCsvLine();
    }

    /**
     * Funkcja automatycznie wykonująca symulację
     *
     * @param iterationCount liczba iteracji do wykonania
     *                       liczba mniejsza od 0 oznacza wykonywanie symulacji do
     *                       śmierci wszystkich obiektów jednej druzyny
     *                       w pozostałych przypadkach wykona się zadana liczba
     *                       iteracji
     */
    public void runSimulation(int iterationCount) {
        this.startTime = System.currentTimeMillis();
        this.endTime = System.currentTimeMillis();
        System.out.println("### START SYMULACJI ###");
        if (iterationCount == -1) {
            while (alliveA > 0 && alliveB > 0) {
                this.handleIteration();
                // wydruk mapy
                if (PRINT_MAP_WHILE_SIMULATION)
                    this.printMapToMapArea();
                if (this.iterationNumber > MAX_ITERATION_COUNT)
                    break;
            }
        }
        if (iterationCount == -2) {
            Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    handleIteration();
                    // wydruk mapy
                    if (PRINT_MAP_WHILE_SIMULATION)
                        printMapToMapArea();
                    if (iterationNumber > MAX_ITERATION_COUNT)
                        this.cancel();
                    if (!(alliveA > 0 && alliveB > 0))
                        this.cancel();
                }
            };
            t.schedule(tt, new Date(), ITERATION_DURATION);
        }
        for (int i = 0; i < iterationCount; i++) {
            this.handleIteration();
            // wydruk mapy
            if (PRINT_MAP_WHILE_SIMULATION)
                this.printMapToMapArea();
        }
        this.endTime = System.currentTimeMillis();
        this.printMapToMapArea();
    }

    /**
     * Metoda czyszcząca mapę z dropów
     */
    public void removeAllDrops() {
        // usuwanie pustych dropow
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<Drop> drops = map[x][y].drops;
                drops.removeIf(drop -> true);
            }
        }
    }

    /**
     * Metoda wykonująca wszystkie operacje dla kazdej iteracji
     */
    public void handleIteration() {
        this.iterationNumber++;
        this.dropItemsOnMap();

        // usunięcie zabitych wcześniej obiektów

        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                units.removeIf(unit -> unit.hp <= 0);
            }
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

        // usuwanie pustych dropow
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<Drop> drops = map[x][y].drops;
                drops.removeIf(drop -> drop.getValue() <= 0);
            }
        }

        // podnoszenie dropów
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                ArrayList<MilitaryUnit> units = map[x][y].units;
                for (MilitaryUnit unit : units) {
                    if (unit.isAlive) {
                        unit.pickUpDrop(this);
                    }
                }
            }
        }

        if (PRINT_DEBUG_TO_CONSOLE)
            System.out.println("Iteracja nr " + this.iterationNumber);

        writeCsvLine();
    }

    private void writeCsvLine() {
        this.outputCsvHeaders = "";
        this.outputCsvLine = "";
        this.getOutputData().forEach((a, b) -> {
            this.outputCsvHeaders = this.outputCsvHeaders + a + ";";
            this.outputCsvLine = this.outputCsvLine + b + ";";
        });
        if (Objects.equals(outputCsvContent, ""))
            outputCsvContent = this.outputCsvHeaders + "\n";

        this.outputCsvContent = this.outputCsvContent + this.outputCsvLine + "\n";
    }

    /**
     * Metoda zwracająca rodzaj zadanego pola
     *
     * @param position obiekt pozycji
     * @return field type from [-1,0,1]
     */
    public int getFieldType(Position position) {
        try {
            Field field = this.map[position.x][position.y];
            return field.type;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Metoda przeprowadzająca generowanie mapy
     *
     * @return wygenerowana mapa
     */
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

    /**
     * Metoda wypełniająca mapę obiektami według parametrów
     */
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
        Integer tankACounter = TANK_A_COUNT;
        while (tankACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B') {
                map[x][y].units.add(new Tank('A', new Position(x, y)));
                tankACounter--;
            }
        }

        // Dodanie czołgów drużyny B
        Integer tankBCounter = TANK_B_COUNT;
        while (tankBCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B' && map[x][y].units.size() == 0) {
                map[x][y].units.add(new Tank('B', new Position(x, y)));
                tankBCounter--;
            }
        }

        // Dodanie piechurów drużyny A
        Integer soldierACounter = SOLDIER_A_COUNT;
        while (soldierACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].units.add(new Soldier('A', new Position(x, y)));
            soldierACounter--;
        }

        // Dodanie piechurów drużyny B
        Integer soldierBCounter = SOLDIER_B_COUNT;
        while (soldierBCounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].units.add(new Soldier('B', new Position(x, y)));
            soldierBCounter--;
        }

        // Dodanie artylerzystów drużyny A
        Integer gunnerACounter = GUNNER_A_COUNT;
        while (gunnerACounter != 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            if (map[x][y].type != 'B') {
                map[x][y].units.add(new Gunner('A', new Position(x, y)));
                gunnerACounter--;
            }
        }

        // Dodanie artylerzystów drużyny B
        Integer gunnerBCounter = GUNNER_B_COUNT;
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

    /**
     * Metoda dopisująca tekst to textPane
     *
     * @param textpane textpane to add text
     * @param message  message to append
     * @param color    color of the text
     */
    private void appendToPane(JTextPane textpane, String message, Color color) {
        StyleContext scope = StyleContext.getDefaultStyleContext();
        AttributeSet attrSet = scope.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attrSet = scope.addAttribute(attrSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        attrSet = scope.addAttribute(attrSet, StyleConstants.FontFamily, "MONOSPACED");
        int length = textpane.getDocument().getLength();
        textpane.setCaretPosition(length);
        textpane.setCharacterAttributes(attrSet, false);
        textpane.replaceSelection(message);
    }

    /**
     * Metoda drukująca pojedynczą komórkę mapy (wysokość 1 linia, szerokość 3
     * znaki)
     *
     * @param field pole mapy
     */
    public void printFieldToMapArea(Field field, JTextPane pane) {

        switch (field.type) {
            case 1:
                appendToPane(pane, "##", new Color(160, 160, 160));
                break;
            case -1:
                break;
            default:
                if (field.units.size() == 0) {
                    if (field.drops.size() > 0)
                        appendToPane(pane, "D ", Color.GREEN);
                    else
                        appendToPane(pane, "  ", Color.BLACK);
                    break;
                }

                MilitaryUnit unit = field.units.get(0);

                Color color;
                switch (unit.team) {
                    case 'A':
                        color = new Color(255, 0, 0);
                        if (!unit.isAlive)
                            color = new Color(80, 40, 0);
                        break;
                    case 'B':
                        color = new Color(0, 0, 255);
                        if (!unit.isAlive)
                            color = new Color(0, 40, 80);
                        break;
                    default:
                        color = new Color(0, 0, 0);
                        if (!unit.isAlive)
                            color = new Color(80, 80, 80);
                        break;
                }
                appendToPane(pane, unit.symbol, color);

                if (field.units.size() == 1) {
                    if (field.drops.size() > 0)
                        appendToPane(pane, "D", Color.GREEN);
                    else
                        appendToPane(pane, " ", Color.BLACK);
                    break;
                }

                MilitaryUnit unit2 = field.units.get(1);
                Color color2;
                switch (unit2.team) {
                    case 'A':
                        color2 = new Color(255, 0, 0);
                        if (!unit2.isAlive)
                            color2 = new Color(60, 20, 20);
                        break;
                    case 'B':
                        color2 = new Color(0, 0, 255);
                        if (!unit2.isAlive)
                            color2 = new Color(20, 20, 60);
                        break;
                    default:
                        color2 = new Color(0, 0, 0);
                        if (!unit2.isAlive)
                            color2 = new Color(20, 20, 20);
                        break;
                }
                appendToPane(pane, unit2.symbol, color2);

                break;
        }
        appendToPane(pane, " ", Color.BLACK);
    }

    /**
     * Metoda aktywująca wydruk mapy w okienku
     */
    public void printMapToMapArea() {
        JTextPane pane = new JTextPane();
        appendToPane(pane, "", Color.BLACK);

        pane.setText("");

        double time = (double) (this.endTime - this.startTime) / 1000;

        appendToPane(pane, "Iteracja " + this.iterationNumber + " | żywych A: " + alliveA + " | żywych B: "
                + alliveB + " | CZERWONY A, NIEBIESKI B | \n\n", Color.BLACK);

        for (Field[] row : this.map) {
            appendToPane(pane, "               |", Color.BLACK);
            for (Field field : row) {
                this.printFieldToMapArea(field, pane);

            }
            appendToPane(pane, "|\n", Color.BLACK);
        }

        this.mapArea.setDocument(pane.getDocument());
    }

    /**
     * Metoda aktywująca rozrzut dropów
     */
    void dropItemsOnMap() {
        if (this.iterationNumber % 10 == 1) {
            this.removeAllDrops();
            int randomNumber = rand.nextInt(101);
            if (FUEL_DROP_PROBABILITY * 100 < randomNumber) {
                dropFuelOnMap();
            }
            randomNumber = rand.nextInt(101);
            if (AMMUNITION_DROP_PROBABILITY * 100 < randomNumber) {
                dropAmmoOnMap();
            }
            randomNumber = rand.nextInt(101);
            if (FOOD_DROP_PROBABILITY * 100 < randomNumber) {
                dropFoodOnMap();
            }
        }

    }

    /**
     * Metoda rozmieszczająca dropy paliwa po mapie
     */
    void dropFuelOnMap() {
        if (PRINT_DEBUG_TO_CONSOLE)
            System.out.println("Zrzucam paliwo na mape");
        int fuelCounter = (int) (FUEL_DROP_PROBABILITY * mapSize) * 3;
        while (fuelCounter > 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].drops.add(new Drop("fuel", FUEL_DROP_VALUE));
            fuelCounter--;
        }
    }

    /**
     * Metoda rozmieszczająca dropy amunicji po mapie
     */
    void dropAmmoOnMap() {
        if (PRINT_DEBUG_TO_CONSOLE)
            System.out.println("Zrzucam amunicje na mape");
        int ammunitionCounter = (int) (AMMUNITION_DROP_PROBABILITY * mapSize) * 3;
        while (ammunitionCounter > 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].drops.add(new Drop("ammo", AMMUNITION_DROP_VALUE));
            ammunitionCounter--;
        }
    }

    /**
     * Metoda rozmieszczająca dropy jedzenia po mapie
     */
    void dropFoodOnMap() {
        if (PRINT_DEBUG_TO_CONSOLE)
            System.out.println("Zrzucam jedzenie na mape");
        int foodCounter = (int) (FOOD_DROP_PROBABILITY * mapSize) * 3;
        System.out.println("zrzucam jedzenia: " + foodCounter);
        while (foodCounter > 0) {
            int x = rand.nextInt(mapSize - 1);
            int y = rand.nextInt(mapSize - 1);
            map[x][y].drops.add(new Drop("food", FOOD_DROP_VALUE));
            foodCounter--;
        }

    }

    /**
     * Dodatkowa metoda wykonująca wydruk mapy na zlecenie przycisku
     */
    public void nextIteration() {
        this.handleIteration();
        this.printMapToMapArea();
        this.endTime = System.currentTimeMillis();
    }

    /**
     * Metoda zwracająca dane z mapy
     *
     * @return map with map data
     */
    public Map<String, String> getOutputData() {
        Map<String, String> map = new HashMap<>();

        int alliveTanksA = 0;
        int alliveTanksB = 0;
        int alliveSoldiersA = 0;
        int alliveSoldiersB = 0;
        int alliveGunnersA = 0;
        int alliveGunnersB = 0;

        int kills = 0;

        for (Field[] fields : this.map) {
            for (Field field : fields) {
                for (MilitaryUnit unit : field.units) {

                    if (unit.isAlive)
                        switch (unit.team) {
                            case 'A':
                                if (unit instanceof Soldier)
                                    alliveSoldiersA++;
                                if (unit instanceof Tank)
                                    alliveTanksA++;
                                if (unit instanceof Gunner)
                                    alliveGunnersA++;

                                break;
                            case 'B':
                                if (unit instanceof Soldier)
                                    alliveSoldiersB++;
                                if (unit instanceof Tank)
                                    alliveTanksB++;
                                if (unit instanceof Gunner)
                                    alliveGunnersB++;
                                break;
                            default:
                                break;
                        }
                    else
                        kills++;
                }
            }
        }

        map.put("alliveTanksA", String.valueOf(alliveTanksA));
        map.put("alliveTanksB", String.valueOf(alliveTanksB));
        map.put("alliveSoldiersA", String.valueOf(alliveSoldiersA));
        map.put("alliveSoldiersB", String.valueOf(alliveSoldiersB));
        map.put("alliveGunnersA", String.valueOf(alliveGunnersA));
        map.put("alliveGunnersB", String.valueOf(alliveGunnersB));

        map.put("initialTanksA", String.valueOf(TANK_A_COUNT));
        map.put("initialTanksB", String.valueOf(TANK_B_COUNT));
        map.put("initialSoldiersA", String.valueOf(SOLDIER_A_COUNT));
        map.put("initialSoldiersB", String.valueOf(SOLDIER_B_COUNT));
        map.put("initialGunnersA", String.valueOf(GUNNER_A_COUNT));
        map.put("initialGunnersB", String.valueOf(GUNNER_B_COUNT));

        map.put("kills", String.valueOf(kills));

        map.put("alliveA", String.valueOf(this.alliveA));
        map.put("alliveB", String.valueOf(this.alliveB));
        map.put("iterationCount", String.valueOf(this.iterationNumber));

        map.put("mapSize", String.valueOf(MAP_SIZE));

        return map;
    }

    public String getOutputCsvContent() {
        return outputCsvContent;
    }
}
