package com.project;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa rozszerzająca JButton, odpowiada za formatowanie przycisków
 */
public class Button extends JButton {

    Button(String text, int size, Color color) {
        this.setText(text);
        this.setFont(new Font("", Font.PLAIN, size));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setForeground(color);

    }
}
