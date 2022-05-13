package com.project.Simulation;

public abstract class MillitaryUnit {
    Integer hp;
    Integer dmg;
    Integer ammo;
    Integer velocity;
    Integer attackRange;
    boolean isAlive=true;
    char team;

    public void move(){}
    public void pickUpRation(){}
    public void pickUpAmmo(){}
    public void attack(){};

}
