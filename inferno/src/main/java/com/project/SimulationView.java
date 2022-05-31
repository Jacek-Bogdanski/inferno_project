package com.project;

import com.project.Simulation.*;

import javax.swing.*;
import java.awt.*;

import java.util.Map;

/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {
    SimulationMap map;
    Router parent;

    JTextPane mapArea;

    /**
     * Konstruktor symulacji
     */
    SimulationView(Router parent, Map<String, Integer> config) {
        this.mapArea = new JTextPane();
        this.mapArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 8));
        this.prepareLayout();
        this.parent = parent;
        /*
         * Utworzenie mapy
         */
        this.map = new SimulationMap(mapArea);
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
        Button endButton = new Button("KONIEC", 30, Colors.black);
        endButton.addActionListener(e -> parent.showMainView());

        Button continueButton = new Button("Następna iteracja", 20, Colors.black);
        continueButton.addActionListener(e -> map.handleIteration());

        /*
         * Utworzenie widoku
         */
        Component[] viewItems = {
                Box.createVerticalGlue(),
                title,
                Box.createRigidArea(new Dimension(0, 10)),
                this.mapArea,
                Box.createRigidArea(new Dimension(0, 10)),
                continueButton,
                Box.createRigidArea(new Dimension(0, 10)),
                endButton,
                Box.createRigidArea(new Dimension(0, 10)),
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),
        };

        for (Component item : viewItems) {
            this.add(item);
        }
    }

}
