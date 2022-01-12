package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to create an instance of Hawaiian pizza.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class Hawaiian extends Pizza implements Serializable {
    private final double SMALL_PRICE = 10.99;
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private ArrayList<Topping> additionalToppings = new ArrayList<Topping>();
    private ArrayList<Topping> setToppings = new ArrayList<Topping>();
    private Size size;
    private long phoneNumber;

    /**
     * Constructor for Hawaiian class.
     */
    public Hawaiian() {
        List<Topping> hawaiianToppings = Arrays.asList(Topping.PINAPPLE, Topping.HAM);
        toppings.addAll(hawaiianToppings);
        setToppings.addAll(hawaiianToppings);
        List<Topping> extraToppings = Arrays.asList(Topping.PEPPERONI, Topping.PEPPERS,
                Topping.MUSHROOM, Topping.ONION, Topping.SAUSAGE);
        additionalToppings.addAll(extraToppings);
        price = SMALL_PRICE;
    }

    /**
     * Override constructor for Hawaiian class.
     * @param toppings ArrayList of toppings for a pizza.
     * @param size enum for Pizza size.
     */
    public Hawaiian(ArrayList<Topping> toppings, Size size) {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * Returns the default price of hawaiian pizzas.
     * @return default price.
     */
    public double defaultPrice() {
        return SMALL_PRICE;
    }

    /**
     * Returns the set toppings for hawaiian pizzas.
     * @param topping ArrayList of toppings.
     */
    public void setToppings(ArrayList<Topping> topping) {
        this.toppings = topping;
    }

    /**
     * Gets phone number associated with order.
     * @return phone number integer.
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number associated with order.
     * @param phoneNumber Input phone number associated with order.
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets pizza toppings.
     * @return ArrayList of toppings.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets the price of a small order of a Hawaiian pizza.
     * @return price of a small Hawaiian pizza.
     */
    public double getSMALL_PRICE() {
        return SMALL_PRICE;
    }

    /**
     * Gets the set toppings for an instance of hawaiian pizzas.
     * @return set toppings for an instance of hawaiian pizzas.
     */
    public ArrayList<Topping> getSetToppings() {
        return setToppings;
    }
    /**
     * Gets any additional toppings that have been added.
     * @return ArrayList of additional toppings.
     */
    public ArrayList<Topping> getAdditionalToppings() {
        return additionalToppings;
    }

    /**
     * Sets size for pizza.
     * @param size enum size for pizza order.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Overrides abstract method in Pizza class.
     * @return price of pizza.
     */
    @Override
    public double price() {
        return price;
    }

    /**
     * Return pizza description as a string.
     * @return Return pizza description as a string.
     */
    public String toString() {
        return size + " :: Hawaiian :: " + toppings + " :: " + Double.valueOf(price);
    }
}
