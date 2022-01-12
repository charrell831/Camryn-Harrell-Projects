package com.example.project5;

import java.io.Serializable;

/**
 * "Factory design" class for creating pizza flavors.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class PizzaMaker implements Serializable {
    /**
     * Creates instances of pizza flavors based on String input of flavor.
     * @param flavor input of pizza flavor.
     * @return instance of pizza subclass based on flavor input.
     */
    public static Pizza createPizza(String flavor) {
        if (flavor.equalsIgnoreCase("Deluxe")) {
            return new Deluxe();
        }else if (flavor.equalsIgnoreCase("Pepperoni")) {
            return new Pepperoni();
        } else if (flavor.equalsIgnoreCase("Hawaiian")) {
            return new Hawaiian();
        }
        return null;
    }
}

