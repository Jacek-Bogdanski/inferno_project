package com.project.Simulation;

public abstract class MillitaryUnit {
    Integer hp;
    Integer dmg;
    Integer ammo;
    Integer velocity;
    Integer attackRange;
    boolean isAlive=true;
    char team;
    public Position position;
    public MillitaryUnit(char team,int pos_x, int pos_y){
        this.team = team;
        this.position.x=pos_x;
        this.position.y=pos_y;
    }

    public void move(){}
    public void pickUpRation(){}
    public void pickUpAmmo(){}
    public void attack(){};

}
