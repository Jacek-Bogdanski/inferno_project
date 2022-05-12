package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsView extends JPanel {
    OptionsView(){
        this.setBackground(new Color(0x2a2b2d));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label hello = new Label("Widok konfiguracji symulacji",25,new Color(0xffffff));
        Label description = new Label("Tutaj znajdą się pola konfiguracyjne",20,new Color(0xcccccc));
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved",10,new Color(0xcccccc));

        Button startButton = new Button("Rozpocznij symulację",20,new Color(0x000000));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                description.setText("Nie tak szybko ;)  Symulacja jeszcze nie jest gotowa");
            }
        });

        this.add(Box.createVerticalGlue());
        this.add(hello);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(description);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(startButton);
        this.add(Box.createVerticalGlue());
        this.add(copyright);
        this.add(Box.createRigidArea(new Dimension(0,20)));
    }

}
