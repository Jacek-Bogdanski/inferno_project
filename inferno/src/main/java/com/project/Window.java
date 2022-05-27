package com.project;

import javax.swing.*;

/**
 * Klasa odpowiedzialna za formatowanie okna
 */
public class Window extends JFrame {
    Window() {
        this.setTitle("Inferno Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setVisible(true);
        this.getContentPane().setBackground(Colors.darkerGrey);
    }
}
