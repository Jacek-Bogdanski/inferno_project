package com.project;

import javax.swing.*;

/**
 * Klasa odpowiadająca za obsługę pól tekstowych
 */
public class NumberField extends JTextField {

    /**
     * Konstruktor domyślny
     */
    NumberField() {
        this.config();
    }

    /**
     * Konstruktor z wartością początkową
     * 
     * @param value wartość początkowa typu String
     */
    NumberField(String value) {
        super(value);
        this.config();
    }

    /**
     * Konstruktor z wartością początkową
     * 
     * @param value wartość początkowa typu int
     */
    NumberField(int value) {
        super(String.valueOf(value));
        this.config();
    }

    /**
     * Metoda konfigurująca pole tekstowe
     */
    private void config() {
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setSize(200, 50);
        this.setBackground(Colors.darkGrey);
        this.setForeground(Colors.white);
    }
}
