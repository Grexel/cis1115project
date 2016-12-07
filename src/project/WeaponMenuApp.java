/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Jeff
 */
public class WeaponMenuApp {
    Scanner sc;
    PrintStream output;
    FileDAOWeapon weaponList;
    boolean runProgram;
    InputValidator inputValidator;
    
    public WeaponMenuApp() {
        sc = new Scanner(System.in);
        output = System.out;
        weaponList = new FileDAOWeapon();
        runProgram = true;
        inputValidator = new InputValidator(output, sc);
        menuLoop();
    }
    private void menuLoop() {
        int input;
        while(runProgram){
            showMenu();
            input = inputValidator.getInt("Enter choice:", 0,12);
            chooseOption(input);
        }
    }
    public void showMenu(){
        output.println("1: View Weapons");
        output.println("2: Add Weapon");
        output.println("3: Remove Weapon");
        output.println("4: Update Weapon");
        output.println("5: Retrieve Single Weapon");                
        output.println("6: Retrieve all: Order by ID");
        output.println("7: Retrieve all: Order by Type");
        output.println("8: Retrieve all: Order by Item Level");
        output.println("9: Retrieve all: Order by Max damage");
        output.println("10: Retrieve all: Order by Type / Item Level ascending");
        output.println("11: Retrieve all: Order by Type / Item Level descending");
        output.println("12: Statistics");
        output.println("0: Exit");
    }
    public void chooseOption(int choice){
        switch(choice){
            case 0: runProgram = false; break;
            case 1: viewWeapons(); break;
            case 2: addWeapon(); break;
            case 3: removeWeapon();break;
            case 4: updateWeapon();break;
            case 5: retrieveWeapon();break;
            case 6: output.println(weaponList.orderById());break;
            case 7: output.println(weaponList.orderByType());break;
            case 8: output.println(weaponList.orderByItemLevel());break;
            case 9: output.println(weaponList.orderByMaxDamage());break;
            case 10: output.println(weaponList.orderByTypeLevelAscending());break;
            case 11: output.println(weaponList.orderByTypeLevelDescending());break;
            case 12: showStatistics(); break;
            default: runProgram = false; break;
        }
    }
    public void viewWeapons(){
        output.println("Viewing Weapons");
        
        output.println(weaponList.orderById());
        
    }
    public void addWeapon(){
        output.println("Adding Weapon");
        int id;
        String name;
        String type;
        int itemLevel;
        int minDMG;
        int maxDMG;
        double speed;
        int life;
        double size;
        int piercing;
        
        id = inputValidator.getInt("Enter ID #: ");
        name = inputValidator.getLine("Enter name: ");
        type = inputValidator.getLine("Enter type: ");
        itemLevel = inputValidator.getInt("Enter item level: ");
        minDMG = inputValidator.getInt("Enter minimum damage: ");
        maxDMG = inputValidator.getInt("Enter maximum damage: ");
        speed = inputValidator.getDouble("Enter speed(pixels/ms): ");
        life = inputValidator.getInt("Enter lifespan of attack: ");
        size = inputValidator.getDouble("Enter size of attack: ");
        piercing = inputValidator.getInt("Enter piercing depth: ");
        
        Weapon newWeapon = new Weapon(id,name,type,itemLevel,minDMG,maxDMG,speed,life,size,piercing);
        output.println(newWeapon);
        boolean choice = inputValidator.yn("Do you want to add this weapon(y/n)?");
        if(choice){
            weaponList.create(newWeapon);
            output.println("Weapon added.");
        }
        else
            output.println("Weapon not added.");
    }
    public void removeWeapon(){
        output.println("Removing Weapon");
        int id = inputValidator.getInt("Enter ID # of weapon you wish to remove: ");
        Weapon weapon = weaponList.retrieve(id);
        output.println(weapon);
        boolean choice = inputValidator.yn("Delete this weapon(y/n)?");
        if(choice){
            weaponList.delete(weapon);
            output.println("Weapon removed.");
        }
        else
            output.println("Weapon not removed.");
        
    }
    public void updateWeapon(){
        output.println("Updating Weapon");
        int id = inputValidator.getInt("Enter ID # of weapon you wish to update: ");
        Weapon weapon = weaponList.retrieve(id);
        output.println(weapon);
        boolean choice = inputValidator.yn("Update this weapon(y/n)?");
        if(choice){
            updateMenuLoop(weapon);
            weaponList.update(weapon);
        }
        else
            output.println("Weapon not updated.");
    }
    private void updateMenuLoop(Weapon weapon) {
        boolean keepUpdating = true;
        while(keepUpdating){
            showUpdateMenu(weapon);
            int input = inputValidator.getInt("", 0, 9);
            keepUpdating = chooseUpdateOption(input, weapon);
        }
    }
    public boolean chooseUpdateOption(int input, Weapon weapon){
        boolean choice;
        switch(input){
            case 0: return false;
            case 1:
                String name = inputValidator.getLine("Enter new name: ");
                choice = inputValidator.yn("Set " + name + " as new name?(y/n) ");
                if(choice){
                    weapon.setName(name);
                    output.println("Name updated.");
                }
                else
                    output.println("Name not updated.");
                return true;
            case 2:
                String type = inputValidator.getLine("Enter new type: ");
                choice = inputValidator.yn("Set " + type + " as new type?(y/n) ");
                if(choice){
                    weapon.setType(type);
                    output.println("Type updated.");
                }
                else
                    output.println("Type not updated.");
                return true;
            case 3:
                int itemLevel = inputValidator.getInt("Enter new item level: ");
                choice = inputValidator.yn("Set " + itemLevel + " as new item level?(y/n) ");
                if(choice){
                    weapon.setItemLevel(itemLevel);
                    output.println("Item level updated.");
                }
                else
                    output.println("Item level not updated.");
                return true;
            case 4:
                int minDMG = inputValidator.getInt("Enter new minimum damage: ");
                choice = inputValidator.yn("Set " + minDMG + " as new minimum damage?(y/n) ");
                if(choice){
                    weapon.setMinDMG(minDMG);
                    output.println("Minimum damage updated.");
                }
                else
                    output.println("Minimum damage not updated.");
                return true;
            case 5:
                int maxDMG = inputValidator.getInt("Enter new maximum damage: ");
                choice = inputValidator.yn("Set " + maxDMG + " as new maximum damage?(y/n) ");
                if(choice){
                    weapon.setMaxDMG(maxDMG);
                    output.println("Maximum damage updated.");
                }
                else
                    output.println("Maximum damage not updated.");
                return true;
            case 6:
                double speed = inputValidator.getDouble("Enter new speed: ");
                choice = inputValidator.yn("Set " + speed + " as new speed?(y/n) ");
                if(choice){
                    weapon.setSpeed(speed);
                    output.println("Speed updated.");
                }
                else
                    output.println("Speed not updated.");
                return true;
            case 7:
                int life = inputValidator.getInt("Enter new attack life: ");
                choice = inputValidator.yn("Set " + life + " as new attack Life?(y/n) ");
                if(choice){
                    weapon.setLife(life);
                    output.println("Attack life updated.");
                }
                else
                    output.println("Attack life not updated.");
                return true;
            case 8:
                double size = inputValidator.getDouble("Enter new size: ");
                choice = inputValidator.yn("Set " + size + " as new size?(y/n) ");
                if(choice){
                    weapon.setSize(size);
                    output.println("Size updated.");
                }
                else
                    output.println("Size not updated.");
                return true;
            case 9:
                int piercing = inputValidator.getInt("Enter new piercing depth: ");
                choice = inputValidator.yn("Set " + piercing + " as new piercing depth?(y/n) ");
                if(choice){
                    weapon.setPiercing(piercing);
                    output.println("Piercing depth updated.");
                }
                else
                    output.println("Piercing depth not updated.");
                return true;
            default:
                return false;
        }
    }
    public void showUpdateMenu(Weapon weapon){
        output.println(weapon);
        output.println("1: Update Name");
        output.println("2: Update Type");
        output.println("3: Update ItemLevel");
        output.println("4: Update Min Damage");
        output.println("5: Update Max Damage");
        output.println("6: Update Speed");
        output.println("7: Update Life");
        output.println("8: Update Size");
        output.println("9: Update Piercing");
        output.println("0: Exit");
    }
    
    public void retrieveWeapon(){
        output.println("Retrieving Weapon");
        int id = inputValidator.getInt("Enter ID # of weapon you wish to retrieve: ");
        Weapon weapon = weaponList.retrieve(id);
        output.println(weapon);
    }
    
    public void showStatistics(){
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0,squareSum = 0,avg,std;
        int numberOfWeapons = 0;
        for(Weapon weapon : weaponList.weaponList){
            numberOfWeapons++;
            if(weapon.getSpeed() < min){
                min = weapon.getSpeed();
            }
            if(weapon.getSpeed() > max){
                max = weapon.getSpeed();
            }
            sum += weapon.getSpeed();
            squareSum += weapon.getSpeed() * weapon.getSpeed();
        }
        avg = sum/numberOfWeapons;
        std = Math.sqrt((squareSum - (sum * sum) / numberOfWeapons)
                / (numberOfWeapons-1));
        
        output.println("Statistics for Weapon Speed");
        output.println("Minimum:            " + min);
        output.println("Maximum:            " + max);
        output.println("Sum:                " + sum);
        output.println("Average:            " + avg);
        output.println("Standard Deviation: " + std);
        //low,high,sum,average,std deviation
    }
    public static void main(String[] args) {
        WeaponMenuApp weaponMenuApp = new WeaponMenuApp();
    }

}
