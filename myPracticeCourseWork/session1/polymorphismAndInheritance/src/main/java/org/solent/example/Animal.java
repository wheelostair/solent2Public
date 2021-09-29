package org.solent.example;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ally Cox
 */
public abstract class Animal {

    protected String name;

    public Animal(String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }

    public abstract String speak();
}
