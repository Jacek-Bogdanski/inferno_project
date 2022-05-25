package com.project;

import com.project.Simulation.*;

import javax.swing.*;
import java.awt.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * Klasa odpowiadająca za wyświetlenie symulacji
 */
public class SimulationView extends JPanel {
    SimulationMap map;
    Router parent;

    JTextArea mapArea;

    /**
     * Konstruktor symulacji
     */
    SimulationView(Router parent, Map<String, Integer> config) {
        this.mapArea = new JTextArea("");
        this.mapArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 9));
        this.prepareLayout();
        this.parent = parent;
        /*
         * Utworzenie mapy
         */
        this.map = new SimulationMap(config, mapArea);
    }

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
        Label description = new Label("Dane wyjściowe znajdują się w konsoli.", 25, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Przyciski
         */
        Button endButton = new Button("KONIEC", 30, Colors.black);
        endButton.addActionListener(e -> parent.showMainView());

        Button continueButton = new Button("Następna iteracja", 20, Colors.black);
        continueButton.addActionListener(e -> map.handleIteration());

        /*
         * Ikony
         */
        ImageIcon dirt = new ImageIcon("dirt.jpg");
        ImageIcon brick = new ImageIcon("brick.jpg");

        /*
         * Utworzenie widoku
         */
        Component[] viewItems = {
                Box.createVerticalGlue(),
                title,
//                Box.createRigidArea(new Dimension(0, 20)),
//                description,
                Box.createRigidArea(new Dimension(0, 40)),
                this.mapArea,
                Box.createRigidArea(new Dimension(0, 40)),
                continueButton,
                Box.createRigidArea(new Dimension(0, 40)),
                endButton,
                Box.createRigidArea(new Dimension(0, 40)),
                copyright,
                Box.createRigidArea(new Dimension(0, 20)),
        };
        for (Component item : viewItems) {
            this.add(item);
        }
    }

}
