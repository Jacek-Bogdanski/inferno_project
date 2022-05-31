package com.project;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa rozszerzająca JLabel, odpowiada za formatowanie wyświetlanego tekstu
 */
public class Label extends JLabel {

    /**
     * Konstruktor
     * 
     * @param text  zawartość tekstu
     * @param size  rozmiar czcionki
     * @param color kolor tekstu
     */
    Label(String text, int size, Color color) {
        super(text, SwingConstants.CENTER);
        this.setText(text);
        this.setFont(new Font("", Font.PLAIN, size));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setForeground(color);
    }

}
