package com.project;

import javax.swing.*;
import java.util.Map;

/**
 * Klasa odpowiedzialna za wyświetlenie odpowiedniego widoku w danym momencie
 * działania programu
 */
public class Router {
    private final Window window;

    /**
     * Konstruktor
     */
    Router() {
        this.window = new Window();
    }

    /**
     * Metoda wyśwyetlająca widok początkowy
     */
    public void showMainView() {
        JPanel startView = new StartView(this);
        this.window.setContentPane(startView);
        this.window.validate();
    }

    /**
     * Metoda wyśwyetlająca widok konfiguracji symulacji
     */
    public void showOptionsView() {
        JPanel optionsView = new OptionsView(this);
        this.window.setContentPane(optionsView);
        this.window.validate();
    }

    /**
     * Metoda wyśwyetlająca widok wymulacji
     */
    public void showSimulationView() {
        JPanel simulationView = new SimulationView(this);
        this.window.setContentPane(simulationView);
        this.window.validate();
    }

}
