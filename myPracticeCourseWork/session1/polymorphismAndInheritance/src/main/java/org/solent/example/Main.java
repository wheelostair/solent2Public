package org.solent.example;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ally Cox
 */
public class Main {

    public static void main(String[] args) {
        Dog d1 = new Dog("Fido");
        Dog d2 = new Dog("Lucky");
        Cat c1 = new Cat("Wiskers");
        Cat c2 = new Cat("Socks");
        Horse h1 = new Horse("Trigger");
        Horse h2 = new Horse("Daisy");

        //Create an Array
        Animal[] animalList = new Animal[6];

        //Adding the objects
        animalList[0] = d1;
        animalList[1] = c1;
        animalList[2] = h1;
        animalList[3] = d2;
        animalList[4] = c2;
        animalList[5] = h2;

        for (int i = 0; i < animalList.length; i++ ) {
        System.out.println(animalList[i].getName() + " says " +
                animalList[i].speak());
}
    }
    
        
}
