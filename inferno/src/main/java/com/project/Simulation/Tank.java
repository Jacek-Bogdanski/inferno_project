package com.project.Simulation;

import java.util.Random;

public class Tank extends MilitaryUnit{
    private Integer fuel;
    private final Random rand = new Random();
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

    @Override
    public void move(Field[][] map,Position pos, int id,int mapSize){
        int direction = rand.nextInt(3); // 0-gora 1-prawo 2-dol 3-prawo
        int moveSpeed = rand.nextInt(2)+1; // losowanie ilosci kratek do przebycia dla testow niech bedzie 1-3 kratki
        switch (direction){
            case 0:{

            }
            case 1:{

            }
            case 2:{

            }
            case 3:{

            }
        }

    }


}
