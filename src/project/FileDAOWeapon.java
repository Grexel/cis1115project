/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeff
 */
public class FileDAOWeapon {
    List<Weapon> weaponList;
    String fileName = "weaponData.txt";
    @SuppressWarnings("Convert2Diamond")
    public FileDAOWeapon() {
        weaponList = new ArrayList<>();
        try{
            Files.createFile(Paths.get(fileName));
        } catch(FileAlreadyExistsException fae){
            //YAYYY we don't have to make the file!
        } catch(IOException ex){
            //The Path is probably broken, please don't tell daddy.
            Logger.getLogger(FileDAOWeapon.class.getName()).log(Level.SEVERE, fileName, ex);
        }
        loadFile(fileName);
        //load from file
    }
    
    //SORTS
    public String orderById(){
        weaponList.sort(Comparator.comparing(Weapon::getId));
        return this.toString();
    }
    public String orderByName(){
        weaponList.sort(Comparator.comparing(Weapon::getName));
        return this.toString();
    }
    public String orderByType(){
        weaponList.sort(Comparator.comparing(Weapon::getType));
        return this.toString();
    }
    public String orderByItemLevel(){
        weaponList.sort(Comparator.comparing(Weapon::getItemLevel));
        return this.toString();
    }
    public String orderByMaxDamage(){
        weaponList.sort(Comparator.comparing(Weapon::getMaxDMG));
        return this.toString();
    }
    public String orderByTypeLevelAscending(){
        weaponList.sort(Comparator.comparing(Weapon::getType).thenComparing(Weapon::getItemLevel));
        return this.toString();
    }
    public String orderByTypeLevelDescending(){
        weaponList.sort(Comparator.comparing(Weapon::getType).thenComparing(Weapon::getItemLevel).reversed());
        return this.toString();
    }
    
    
    public void create(Weapon weapon){
        weaponList.add(weapon);
        saveFile(fileName);
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
        saveFile(fileName);
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
        saveFile(fileName);
    }
    public void delete(Weapon toDelete){
        weaponList.remove(toDelete);
        saveFile(fileName);
    }
    
    public void loadFile(String txtFile){
        Path path = Paths.get(txtFile);
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                //0 = id, 1 = species, 2 = price
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String type = data[2];
                int itemLevel = Integer.parseInt(data[3]);
                int minDMG = Integer.parseInt(data[4]);
                int maxDMG = Integer.parseInt(data[5]);
                double speed = Double.parseDouble(data[6]);
                int life = Integer.parseInt(data[7]);
                double size = Double.parseDouble(data[8]);
                int piercing = Integer.parseInt(data[9]);
                create(new Weapon(id,name,type,itemLevel,minDMG,maxDMG,speed,life,size,piercing));                
            }
        } catch(FileNotFoundException fnf){
            Logger.getLogger(FileDAOWeapon.class.getName()).log(Level.SEVERE, fileName, fnf);
        } catch(IOException ioe){
            Logger.getLogger(FileDAOWeapon.class.getName()).log(Level.SEVERE, fileName, ioe);
        }
    }
    public void saveFile(String txtFile){
        Path path = Paths.get(txtFile);
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for (Weapon weapon : weaponList){
                String weaponDetails = 
                    String.format("%d,%s,%s,%d,%d,%d,%.2f,%d,%.2f,%d\n",
                    weapon.getId(),weapon.getName(),weapon.getType(),
                    weapon.getItemLevel(),weapon.getMinDMG(),weapon.getMaxDMG(),
                    weapon.getSpeed(),weapon.getLife(),weapon.getSize(),weapon.getPiercing());
                writer.append(weaponDetails);
            }
        } catch(FileNotFoundException fnf){
            Logger.getLogger(FileDAOWeapon.class.getName()).log(Level.SEVERE, fileName, fnf);
        } catch(IOException ioe){
            Logger.getLogger(FileDAOWeapon.class.getName()).log(Level.SEVERE, fileName, ioe);
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
