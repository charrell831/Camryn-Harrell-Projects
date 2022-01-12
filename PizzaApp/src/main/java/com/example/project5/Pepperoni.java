package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates an instance of the Pepperoni class.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class Pepperoni extends Pizza implements Serializable {
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private ArrayList<Topping> additionalToppings = new ArrayList<Topping>();
    private ArrayList<Topping> setToppings = new ArrayList<Topping>();
    private final double SMALL_PRICE = 8.99;
    private Size size;
    private long phoneNumber;

    /**
     * Constructor for pepperoni class.
     */
    public Pepperoni() {
        List<Topping> pepperoniToppings = Arrays.asList(Topping.PEPPERONI);
        toppings.addAll(pepperoniToppings);
        setToppings.addAll(pepperoniToppings);
        List<Topping> extraToppings = Arrays.asList(Topping.PINAPPLE, Topping.SAUSAGE,
                Topping.HAM, Topping.ONION, Topping.MUSHROOM, Topping.PEPPERS);
        additionalToppings.addAll(extraToppings);
        price = SMALL_PRICE;
    }

    /**
     * Overridden constructor for Pepperoni class.
     * @param toppings ArrayList of toppings.
     * @param size Size enum.
     */
    public Pepperoni(ArrayList<Topping> toppings, Size size) {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * Returns the default price of pepperoni pizzas.
     * @return default price.
     */
    public double defaultPrice() {
        return SMALL_PRICE;
    }

    /**
     * Returns the set toppings for pepperoni pizzas.
     * @param topping ArrayList of toppings.
     */
    public void setToppings(ArrayList<Topping> topping) {
        this.toppings = topping;
    }

    /**
     * Gets set toppings of pepperoni pizza.
     * @return ArrayList of set toppings of pepperoni pizza.
     */
    public ArrayList<Topping> getSetToppings() {
        return setToppings;
    }

    /**
     * Sets phone number as for pizza order.
     * @param phoneNumber input phone number.
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone number for order.
     * @return phone number associated with order.
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Gets ArrayList of toppings.
     * @return
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets additional toppings added by user for pepperoni pizza.
     * @return ArrayList of additional toppings.
     */
    public ArrayList<Topping> getAdditionalToppings() {
        return additionalToppings;
    }

    /**
     * Gets price of a small pepperoni pizza.
     * @return price of a small pepperoni pizza
     */
    public double getSMALL_PRICE() {
        return SMALL_PRICE;
    }

    /**
     * Sets the size for the pizza order.
     * @param size Size enum inputted by the user.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Override of price method in pizza abstract class.
     * @return price of pizza.
     */
    @Override
    public double price() {
        return price;
    }

    /**
     * Gets String of pepperoni pizza object.
     * @return String of pepperoni pizza object.
     */
    public String toString() {
        return size + " :: Pepperoni :: " + toppings + " :: " + Double.valueOf(price);
    }

}

