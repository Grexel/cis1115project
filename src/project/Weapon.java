/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Jeff
 */
public class Weapon implements Comparable<Weapon>{
    private int id;
    private String name;
    private String type;
    private int itemLevel;
    private int minDMG;
    private int maxDMG;
    private double speed;
    private int life;
    private double size;
    private int piercing;

    //CONSTRUCTORS
    public Weapon() { 
        this.id = -1;
        this.name = "Null";
        this.type = "Null";
        this.itemLevel = 0;
        this.minDMG = 0;
        this.maxDMG = 0;
        this.speed = 0;
        this.life = 0;
        this.size = 0;
        this.piercing = 0;
        
    }
    public Weapon(int id, String name, String type, int itemlevel, int minDMG, int maxDMG, double speed, int life, double size, int piercing) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.itemLevel = itemlevel;
        this.minDMG = minDMG;
        this.maxDMG = maxDMG;
        this.speed = speed;
        this.life = life;
        this.size = size;
        this.piercing = piercing;
    }
    
    //GETTER AND SETTERS
    public int getPiercing() {
        return piercing;
    }

    public void setPiercing(int piercing) {
        this.piercing = piercing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemlevel) {
        this.itemLevel = itemlevel;
    }

    public int getMinDMG() {
        return minDMG;
    }

    public void setMinDMG(int minDMG) {
        this.minDMG = minDMG;
    }

    public int getMaxDMG() {
        return maxDMG;
    }

    public void setMaxDMG(int maxDMG) {
        this.maxDMG = maxDMG;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        String str = String.format("id:%3d, name:%20s, type:%20s, level:%3d, damage:%4d - %4d, speed:%6.2f, life:%5d, size:%6.2f, pierce:%3d",getId(),getName(),getType(),getItemLevel(),getMinDMG(),getMaxDMG(),getSpeed(),getLife(),getSize(),getPiercing());
        return str;
    }

    @Override
    public int compareTo(Weapon otherWeapon) {
        if(this.getId() > otherWeapon.getId())
        {
            return 1;
        }
        else if(this.getId() == otherWeapon.getId())
            return 0;
        else
            return -1;
    }
}
