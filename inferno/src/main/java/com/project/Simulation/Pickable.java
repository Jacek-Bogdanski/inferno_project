package com.project.Simulation;

public interface Pickable {
    /**
     * Zobaczenie przechowywanej wartości
     * @return wartość przechowywana
     */
    Integer getValue();

    /**
     * Pobranie dostępnej ilości materiału. Zwróci albo oczekiwaną wartośc, albo maksymalną dostępną wartosć.
     * @param value oczekiwana wartość
     * @return wartość oddana
     */
    Integer collect(Integer value);
}
