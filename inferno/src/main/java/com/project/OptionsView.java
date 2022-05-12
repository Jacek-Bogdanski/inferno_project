package com.project;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class OptionsView extends JPanel {
    OptionsView(Router parent){
        this.setBackground(new Color(0x2a2b2d));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Label title = new Label("Widok konfiguracji symulacji",25,new Color(0xffffff));
        Label description = new Label("Tutaj znajdą się pola konfiguracyjne",20,new Color(0xcccccc));
        Label copyright = new Label("Copyright © 2022, INFERNO PROJECT. All right reserved.",10,new Color(0xcccccc));

        Button backButton = new Button("Powrót",20,new Color(0x333333));
        backButton.addActionListener(e -> parent.showMainView());

        Button startButton = new Button("Rozpocznij symulację",20,new Color(0x000000));
        startButton.addActionListener(e -> description.setText("Nie tak szybko ;)  Symulacja jeszcze nie jest gotowa"));

//        NumberFormat longFormat = NumberFormat.getIntegerInstance();
//        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
//        numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
//        numberFormatter.setAllowsInvalid(false); //this is the key!!
//        numberFormatter.setMinimum(0l); //Optional

        // Te wszystkie pola poniżej zamienić na tablicę

        JTextField sizeField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField EParameterField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField FParameterField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField GParameterField = new JTextField(100);
        sizeField.setSize(200,40);

        JTextField A1CountField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField A2CountField = new JTextField(100);
        sizeField.setSize(200,40);

        JTextField B1CountField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField B2CountField = new JTextField(100);
        sizeField.setSize(200,40);

        JTextField C1CountField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField C2CountField = new JTextField(100);
        sizeField.setSize(200,40);

        JTextField D1CountField = new JTextField(100);
        sizeField.setSize(200,40);
        JTextField D2CountField = new JTextField(100);
        sizeField.setSize(200,40);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        panel.setBackground(new Color(0x2a2b2d));
        panel.setPreferredSize(new Dimension(800, 300));
        panel.setMaximumSize(panel.getPreferredSize());
        panel.setMinimumSize(panel.getPreferredSize());

        panel.add(new Label("Liczba czołgów A",15,new Color(0xffffff)));
        panel.add(new Label("Liczba czołgów B",15,new Color(0xffffff)));
        panel.add(A1CountField);
        panel.add(A2CountField);
        panel.add(new Label("Liczba piechurów A",15,new Color(0xffffff)));
        panel.add(new Label("Liczba piechurów B",15,new Color(0xffffff)));
        panel.add(B1CountField);
        panel.add(B2CountField);
        panel.add(new Label("Liczba artylerzystów A",15,new Color(0xffffff)));
        panel.add(new Label("Liczba artylerzystów B",15,new Color(0xffffff)));
        panel.add(C1CountField);
        panel.add(C2CountField);
        panel.add(new Label("Liczba zwiadowców A",15,new Color(0xffffff)));
        panel.add(new Label("Liczba zwiadowców B",15,new Color(0xffffff)));
        panel.add(D1CountField);
        panel.add(D2CountField);

        panel.add(new Label("Rozmiar mapy",15,new Color(0xffffff)));
        panel.add(new Label("Szybkość dostaw amunicji",15,new Color(0xffffff)));
        panel.add(sizeField);
        panel.add(EParameterField);
        panel.add(new Label("Szybkość dostaw jedzenia",15,new Color(0xffffff)));
        panel.add(new Label("Szybkość dostaw paliwa",15,new Color(0xffffff)));
        panel.add(FParameterField);
        panel.add(GParameterField);

        this.add(Box.createVerticalGlue());
        this.add(backButton);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(description);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(panel);
        this.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(startButton);
        this.add(Box.createVerticalGlue());
        this.add(copyright);
        this.add(Box.createRigidArea(new Dimension(0,20)));
    }

}
