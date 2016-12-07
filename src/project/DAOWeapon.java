/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.*;

/**
 *  The Data Access Object to a collection of Weapons
 * @author Jeff
 */
public class DAOWeapon {
    List<Weapon> weaponList;

    @SuppressWarnings("Convert2Diamond")
    public DAOWeapon() {
        weaponList = new ArrayList<>();
    }
    
    //CRUD
    public void create(Weapon weapon){
        weaponList.add(weapon);
    }
    public Weapon retrieve(int id){
        for(Weapon weapon : weaponList){
            if(weapon.getId() == id){
                return weapon;
            }
        }
        return null;
    }
    
    public void update(Weapon weapon){
        Weapon toUpdate = null;
        for(Weapon w : weaponList){
            if(w.getId() == weapon.getId()){
                toUpdate = w;
                break;
            }
        }
        if(toUpdate != null){
            weaponList.remove(toUpdate);
            weaponList.add(weapon);
        }
        Collections.sort(weaponList);
    }
    public void delete(int id){
        Weapon toDelete = null;
        for(Weapon weapon : weaponList){
            if(weapon.getId() == id){
                toDelete = weapon;
                break;
            }
        }
        if(toDelete != null){
            weaponList.remove(toDelete);
        }
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        for(Weapon weapon : weaponList){
            sB.append(weapon).append("\n");
        }
        return sB.toString();
    }
    
}
