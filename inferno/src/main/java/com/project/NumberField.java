package com.project;

import javax.swing.*;

/**
 * Klasa odpowiadająca za formatowanie i obsługę pól tekstowych
 */
public class NumberField extends JTextField {
    NumberField() {
        this.setSize(200, 50);
    }

    NumberField(String value) {
        super(value);
        this.setSize(200, 50);
    }

    NumberField(int value) {
        super(String.valueOf(value));
        this.setSize(200, 50);
    }
}
