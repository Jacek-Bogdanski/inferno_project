package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends JPanel {
    StartView(Router parent){
        this.setBackground(new Color(0x2a2b2d));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label title = new Label("INFERNO PROJECT",35,new Color(0xffffff));
        Label description = new Label("Symulacja pola walki.",25,new Color(0xcccccc));
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.",10,new Color(0xcccccc));

        Button startButton = new Button("START",30,new Color(0x000000));
        startButton.addActionListener(e -> parent.showOptionsView());

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(description);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(startButton);
        this.add(Box.createVerticalGlue());
        this.add(copyright);
        this.add(Box.createRigidArea(new Dimension(0,20)));
    }

}
