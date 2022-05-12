package com.project;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za formatowanie okna
 */
public class Window extends JFrame {
    Window() {
        this.setTitle("Inferno Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 680);
        this.setVisible(true);
        this.getContentPane().setBackground(Colors.darkerGrey);
    }
}
