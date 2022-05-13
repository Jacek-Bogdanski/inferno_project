package com.project;

import javax.swing.*;
import java.util.Map;

/**
 * Klasa odpowiedzialna za wyświetlenie odpowiedniego widoku w danym momencie
 * działania programu
 */
public class Router {
    private final Window window;

    public void showMainView() {
        JPanel startView = new StartView(this);
        this.window.setContentPane(startView);
        this.window.validate();
    }

    public void showOptionsView() {
        JPanel optionsView = new OptionsView(this);
        this.window.setContentPane(optionsView);
        this.window.validate();
    }

    public void showSimulationView(Map<String, Integer> config) {
        JPanel simulationView = new SimulationView(this, config);
        this.window.setContentPane(simulationView);
        this.window.validate();
    }

    Router() {
        this.window = new Window();
    }
}
