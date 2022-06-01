package com.project.Simulation;

public interface Pickable {
    /**
     * Metoda zwracająca wartość dropu bez jego zmiany
     * 
     * @return wartość przechowywana
     */
    Integer getValue();

    /**
     * Metoda zwracająca typ obiektu
     * @return typ dropu
     */
    String getType();

    /**
     * Pobranie dostępnej ilości materiału. Zwróci albo oczekiwaną wartośc, albo
     * maksymalną dostępną wartosć.
     * 
     * @param value oczekiwana wartość
     * @return wartość oddana
     */
    Integer collect(Integer value);
}
