package com.project.Simulation;

public class Tank extends MillitaryUnit{
    int fuel;
    public Tank(char team,int x,int y){
        super(team,x,y);
        fuel=50;
        this.ammo=10;
        this.attackRange = 7;
        this.dmg=50;        //maksymalny dmg, pozniej podczas ataku bedzie losowa wartosc w takim zakresie
        this.hp=100;
        this.velocity=3;
    }
}
