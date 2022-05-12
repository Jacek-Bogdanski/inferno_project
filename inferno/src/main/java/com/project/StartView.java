package com.project;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadająca za wyświetlenie widoku głównego programu
 */
public class StartView extends JPanel {
    StartView(Router parent) {
        /*
         * Konfiguracja panelu
         */
        this.setBackground(Colors.darkerGrey);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /*
         * Napisy na ekranie
         */
        Label title = new Label("INFERNO PROJECT", 35, Colors.white);
        Label description = new Label("Symulacja pola walki.", 25, Colors.veryLightGrey);
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.", 10, Colors.veryLightGrey);

        /*
         * Przyciski
         */
        Button startButton = new Button("START", 30, Colors.black);
        startButton.addActionListener(e -> parent.showOptionsView());

        /*
         * Utworzenie widoku
         */
        java.awt.Component[] viewItems = {
                Box.createVerticalGlue(),
                title,
                Box.createRigidArea(new Dimension(0, 20)),
                description,
                Box.createRigidArea(new Dimension(0, 40)),
                startButton,
                Box.createVerticalGlue(),
                copyright, Box.createRigidArea(new Dimension(0, 20)),
        };

        for (java.awt.Component item : viewItems) {
            this.add(item);
        }
    }

}
