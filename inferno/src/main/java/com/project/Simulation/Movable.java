package com.project.Simulation;

/**
 * Interfejs główny
 */
public interface Movable {
    /**
     * Metoda podnosząca itemek z pola, na którym sie znajduje obiekt
     * 
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    void pickUpDrop(SimulationMap map);

    /**
     * Metoda wykonująca atak
     * 1. jednostka przeszukuje mapę, aby znaleźć pola możliwe do zaatakowania
     * 2. spośród tych pól wybiera najbliższy obiekt
     * 3. Jeśli taki istnieje, atakuje go
     * 
     * @param map obiekt mapy potrzebny do podniesienia obiektu
     */
    void attack(SimulationMap map);

    /**
     * Metoda przyjmująca atak
     * Określenie śmierci postaci następuje po wykonaniu wszystkich ataków w
     * iteracji
     * 
     * @param dmg liczba otrzymanych punktów obrażeń
     */
    void takeDamage(Integer dmg);

    /**
     * Metoda wykonująca ruch obiektu na podstawie parametru speed
     * Parametr speed określa maksymalną liczbę pól do przemieszczenia się w jednej
     * iteracji
     * Np. speed=5 oznacza maksymalny ruch 5 pionowo i 5 poziomo
     * 
     * @param map obiekt mapy potrzebny do przeniesienia obiektu w inne miejsce
     */
    void move(SimulationMap map);
}
