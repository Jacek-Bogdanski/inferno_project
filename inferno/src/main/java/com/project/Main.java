package com.project;

/**
 * Klasa odpowiadająca za uruchomienie okienka z interfejsem użytkownika
 */
public class Main {
    public static void main(String[] args) {
        Router router = new Router();
        router.showMainView();
    }
}