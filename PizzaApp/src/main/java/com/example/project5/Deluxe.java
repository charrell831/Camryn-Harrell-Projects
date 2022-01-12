package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class will be used to create an instance of a Deluxe pizza.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class Deluxe extends Pizza implements Serializable {
    private final double SMALL_PRICE = 12.99;
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private ArrayList<Topping> extraToppings = new ArrayList<Topping>();
    private ArrayList<Topping> setToppings = new ArrayList<Topping>();
    private Size size;
    private long phoneNumber;
    private int toppingsSize;

    /**
     * Constructor for deluxe class.
     */
    public Deluxe() {
        List<Topping> deluxeToppings = Arrays.asList(Topping.SAUSAGE, Topping.MUSHROOM, Topping.ONION, Topping.PEPPERS, Topping.PEPPERONI);
        toppings.addAll(deluxeToppings);
        setToppings.addAll(deluxeToppings);
        List<Topping> additionalToppings = Arrays.asList(Topping.PINAPPLE, Topping.HAM);
        extraToppings.addAll(additionalToppings);
        price = SMALL_PRICE;
    }

    /**
     * Overloaded constructor for deluxe class.
     * @param toppings ArrayList of toppings.
     * @param size Size enum.
     */
    public Deluxe(ArrayList<Topping> toppings, Size size) {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * Returns the default price of deluxe pizzas.
     * @return default price.
     */
    public double defaultPrice() {
        return SMALL_PRICE;
    }

    /**
     * Returns the set toppings for deluxe pizzas.
     * @param topping ArrayList of toppings.
     */
    public void setToppings(ArrayList<Topping> topping) {
        this.toppings = topping;
    }

    /**
     * Gets the set toppings for an instance of deluxe pizzas.
     * @return set toppings for an instance of deluxe pizzas.
     */
    public ArrayList<Topping> getSetToppings() {
        return setToppings;
    }

    public double getSMALL_PRICE() {
        return SMALL_PRICE;
    }
    /**
     * Gets ArrayList of toppings.
     * @return ArrayList of toppings.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets ArrayList of additional toppings.
     * @return ArrayList of additional toppings.
     */
    public ArrayList<Topping> getAdditionalToppings() {
        return extraToppings;
    }

    /**
     * Sets pizza size.
     * @param size Size enum.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Gets phone number associated with order.
     * @return phone number integer.
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets phone number that will be associated with order.
     * @param phoneNumber input phone number to be associated with order.
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Method sets the topping size for a pizza.
     *
     * @param size is the number of toppings.
     */
    public void setToppingsSize(int size){
        toppingsSize = size;
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
        return size + " :: Deluxe :: " + toppings + " :: " + Double.valueOf(price);
    }
}
