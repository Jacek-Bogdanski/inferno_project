package com.project;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    Window() {
        this.setTitle("Inferno Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1200, 680);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0x2a2b2d));
    }
}
