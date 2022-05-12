package com.project;

import javax.swing.*;

/**
 * Klasa odpowiadająca za formatowanie i obsługę pól tekstowych
 */
public class NumberField extends JTextField {
    private void config(){
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setSize(200, 50);
        this.setBackground(Colors.darkGrey);
        this.setForeground(Colors.white);
    }

    NumberField() {
        this.config();
    }

    NumberField(String value) {
        super(value);
        this.config();
    }

    NumberField(int value) {
        super(String.valueOf(value));
        this.config();
    }
}
