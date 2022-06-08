package com.project;

import com.project.Simulation.*;

import javax.swing.*;
import java.awt.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.project.Parameters.ITERATION_COUNT;

/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {
    SimulationMap map;
    Router parent;
    String textOutput="";

    JTextPane mapArea;


    /**
     * Metoda wykonująca zapis danych wyjściowych do pliku
     */
    public void writeOutput(){
        new FileWrite("output/output.csv",textOutput);
    }

    /**
     * Metoda wykonująca ponownie symulację
     */
    public void repeat(){
        this.prepareSimulation();
        this.startSimulation();
    }


    private void prepareSimulation(){
        /*
         * Utworzenie mapy
         */
        this.map = new SimulationMap(mapArea);
        this.map.printMapToMapArea();

    }

    private void startSimulation(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    String outputCsvLine = "";
                    String outputCsvHeaders = "";
                    @Override
                    public void run() {
                        map.runSimulation(ITERATION_COUNT);

                        Map<String,String> outputData = map.getOutputData();
                        if(outputData.size()==0) return;
                        outputData.forEach((a,b)->{
                            System.out.println(a+" "+b);
                            this.outputCsvHeaders = this.outputCsvHeaders+a+";";
                            this.outputCsvLine = this.outputCsvLine+b+";";
                        });
                        if(Objects.equals(textOutput, "")) textOutput = this.outputCsvHeaders+"\n";
                        textOutput =  textOutput+this.outputCsvLine+"\n";
                    }
                },
                1000
        );

    }
    /**
     * Konstruktor symulacji
     */
    SimulationView(Router parent) {
        this.parent = parent;
        this.mapArea = new JTextPane();
        this.mapArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
        this.prepareLayout();
        this.prepareSimulation();
        this.startSimulation();
    }

    /**
     * Metoda przygotowująca layout aplikacji
     */
    private void prepareLayout() {
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*
         * Napisy na ekranie
         */
        Label title = new Label("TRWA SYMULACJA", 35, Colors.white);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Przyciski
         */
        Button endButton = new Button("Koniec", 20, Colors.black);
        endButton.addActionListener(e -> parent.showOptionsView());

        Button continueButton = new Button("Następna iteracja", 20, Colors.black);
        continueButton.addActionListener(e -> map.nextIteration());

        Button refreshButton = new Button("Odśwież wydruk mapy", 20, Colors.black);
        refreshButton.addActionListener(e -> map.printMapToMapArea());

        Button repeatButton = new Button("Powtórz symulację", 20, Colors.black);
        repeatButton.addActionListener(e -> this.repeat());

        Button saveButton = new Button("Zapisz dane do pliku", 20, Colors.black);
        saveButton.addActionListener(e -> this.writeOutput());

        java.awt.Component[] panelButtons = {
                refreshButton,continueButton,repeatButton,endButton,saveButton
        };
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 3));
        for (java.awt.Component item : panelButtons) {
            buttonPanel.add(item);
        }

        /*
         * Utworzenie widoku
         */
        Component[] viewItems = {
                Box.createVerticalGlue(),
                this.mapArea,
                Box.createRigidArea(new Dimension(0, 10)),
                buttonPanel,
                Box.createRigidArea(new Dimension(0, 10)),
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),
        };

        for (Component item : viewItems) {
            this.add(item);
        }
    }

}
