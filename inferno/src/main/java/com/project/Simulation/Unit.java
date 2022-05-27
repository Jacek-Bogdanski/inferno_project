package com.project.Simulation;

/**
 * Interfejs główny
 */
public interface Unit {
    void pickUpDrop(SimulationMap map);
    void attack(SimulationMap map);
    void takeDamage(Integer dmg);
    void move(SimulationMap map);

}
