package com.project;

/**
 * Klasa odpowiadająca za uruchomienie okienka z interfejsem użytkownika
 */
public class Main {
    /**
     * Konstruktor programu
     * 
     * @param args argumenty tekstowe
     */
    public static void main(String[] args) {
        Router router = new Router();
        router.showMainView();
    }
}