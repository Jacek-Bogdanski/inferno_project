package com.project.Simulation;

public class Tank extends MilitaryUnit{
    private Integer fuel;

    /**
     * Konstruktor
     * @param team nazwa drużyny [char]
     * @param position pozycja początkowa
     */
    public Tank(char team,Position position){
        super(team,position);
        this.setParams();
    }

    /**
     * Metoda ustawiająca parametry początkowe obiektu
     */
    private void setParams(){
        this.fuel=50;
        this.ammunition =10;
        this.attackRange = 7;
        this.damage =50;        //maksymalny dmg, pozniej podczas ataku bedzie losowa wartosc w takim zakresie
        this.hp=100;
        this.speed=3;
    }
}
