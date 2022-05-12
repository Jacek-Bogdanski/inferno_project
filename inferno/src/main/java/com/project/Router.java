package com.project;

import javax.swing.*;


public class Router {
    private final Window window ;

    public void showMainView(){
        JPanel startView = new StartView(this);
        this.window.setContentPane(startView);
        this.window.validate();
    }

    public void showOptionsView(){
        JPanel optionsView = new OptionsView(this);
        this.window.setContentPane(optionsView);
        this.window.validate();
    }

    Router(){
        this.window = new Window();
        this.showMainView();
    }
}
