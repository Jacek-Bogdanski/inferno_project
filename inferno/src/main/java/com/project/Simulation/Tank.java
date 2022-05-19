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
    public Position move(Field[][] map, Position pos, int mapSize){
        int direction = rand.nextInt(3); // 0-gora 1-prawo 2-dol 3-prawo
        int moveSpeed = rand.nextInt(2)+1; // losowanie ilosci kratek do przebycia dla testow niech bedzie 1-3 kratki
        Tank tank = (Tank) map[pos.x][pos.y].units.get(0); //tu bedzie trzeba jakos obslugiwac wiecej indexow
        map[pos.x][pos.y].units.remove(tank);
        switch (direction) {
            case 0 -> {
                System.out.println("Przesuwam w gore");
                if (pos.y - 1 >= 0) {
                    pos.y -= 1;
                    map[pos.x][pos.y].units.add(tank);
                } else {
                    map[pos.x][pos.y].units.add(tank);
                    return new Position(pos.x,pos.y);
                }
            }
            case 1 -> {
                System.out.println("Przesuwam w prawo");
                if (pos.x + 1 <= mapSize - 1) {
                    pos.x += 1;
                    map[pos.x][pos.y].units.add(tank);
                } else {
                    map[pos.x][pos.y].units.add(tank);
                    return new Position(pos.x,pos.y);
                }
            }
            case 2 -> {
                System.out.println("Przesuwam w dol");
                if (pos.y + 1 <= mapSize - 1) {
                    pos.y += 1;
                    map[pos.x][pos.y].units.add(tank);
                } else {
                    map[pos.x][pos.y].units.add(tank);
                    return new Position(pos.x,pos.y);
                }
            }
            case 3 -> {
                System.out.println("Przesuwam w lewo");
                if (pos.x - 1 >= 0) {
                    pos.x -= 1;
                    map[pos.x][pos.y].units.add(tank);
                } else {
                    map[pos.x][pos.y].units.add(tank);
                    return new Position(pos.x,pos.y);
                }
            }
        }

        return pos;
    }


}
