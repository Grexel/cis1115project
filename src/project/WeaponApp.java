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
public class WeaponApp {
    public static void main(String[] args) {
        Weapon weapon1 = new Weapon(0, "Chakram", "Boomerang", 10, 25, 45, 0.2, 1000, 20, 1);
        Weapon weapon2 = new Weapon(1, "Iron Spear", "Spear", 5, 12, 25, 0.2, 500, 125, 99);
        Weapon weapon3 = new Weapon(2, "Gladius", "Sword", 25, 62, 88, 0.25, 500, 75, 99);
        Weapon weapon4 = new Weapon(3, "Blaster", "Gun", 50, 140, 175, 0.3, 1250, 18.5, 1);
        Weapon weapon5 = new Weapon(4, "Machine Gun", "Gun", 20, 20, 40, 0.33, 1000, 8.5, 1);
        //System.out.println(weapon1);
       // System.out.println(weapon2);
       // System.out.println(weapon3);
        //System.out.println(weapon4);
        
        DAOWeapon weaponList = new DAOWeapon();
        //create
        weaponList.create(weapon1);
        weaponList.create(weapon2);
        weaponList.create(weapon3);
        weaponList.create(weapon4);
        weaponList.create(weapon5);
        System.out.println(weaponList);
        //delete
        weaponList.delete(1);
        System.out.println(weaponList);
        //retrieve
        System.out.println(weaponList.retrieve(3));
        System.out.println("");
        //update
        weapon4.setItemLevel(75);
        weapon4.setMaxDMG(999);
        weaponList.update(weapon4);
        System.out.println(weaponList);
        
    }
}
