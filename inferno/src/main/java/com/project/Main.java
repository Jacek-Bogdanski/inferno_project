package com.project;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x2a2b2d));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Label hello = new Label("INFERNO PROJECT",35,new Color(0xffffff));
        Label description = new Label("Symulacja pola walki.",25,new Color(0xcccccc));
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved",10,new Color(0xcccccc));
        Button startButton = new Button("START",30,new Color(0x000000));

        panel.add(Box.createVerticalGlue());
        panel.add(hello);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(description);
        panel.add(Box.createRigidArea(new Dimension(0,40)));
        panel.add(startButton);
        panel.add(Box.createVerticalGlue());
        panel.add(copyright);
        panel.add(Box.createRigidArea(new Dimension(0,20)));

        window.add(panel);
        window.validate();
    }
}