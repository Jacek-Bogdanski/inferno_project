package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        JPanel panel = new StartView();


        window.add(panel);
        window.validate();
    }
}