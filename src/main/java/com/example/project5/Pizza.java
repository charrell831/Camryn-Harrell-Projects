package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Abstract class for Pizza object.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public abstract class Pizza implements Serializable {
    private static final double ADDITIONAL_TOPPING = 1.49;
    private static final double SIZE_INCREASE = 2.00;
    protected double price;

    /**
     * Abstract method for price for all pizza subclasses.
     * @return price in each subclass.
     */
    public abstract double price();

    /**
     * Gets the set toppings for a specified pizza.
     * @param toppings ArrayList of toppings.
     */
    public abstract void setToppings(ArrayList<Topping> toppings);
    /**
     * Gets toppings from each pizza subclasses.
     * @return ArrayList of toppings from each pizza subclasses.
     */
    public abstract ArrayList<Topping> getToppings() ;

    /**
     * Gets additional toppings from each pizza subclasses.
     * @return ArrayList of additional toppings from each pizza subclasses.
     */
    public abstract ArrayList<Topping> getAdditionalToppings();

    /**
     * Returns the default price of a pizza before any sizes or toppings are changed
     * or added.
     * @return default pizza price.
     */
    public abstract double defaultPrice();

    /**
     * Sets phone number of the order in each subclass.
     * @param phoneNumber of order.
     */
    public abstract void setPhoneNumber(long phoneNumber);

    /**
     * Gets phone number associated with each order. Implemented in
     * each pizza subclass.
     *
     * @return phone number associated with order in each pizza subclass.
     */
    public abstract long getPhoneNumber();

    /**
     * Sets the size for a pizza order.
     * @param size of order.
     */
    public abstract void setSize(Size size);

    /**
     * Increases the price of pizza order when a topping is added.
     */
    public void toppingIncrease() {
        price += ADDITIONAL_TOPPING;
    }

    /**
     * Increases the price of pizza order when size in increased.
     */
    public void sizeIncrease() {
        price += SIZE_INCREASE;
    }

    /**
     * Decreases the price of a pizza order when a topping is removed.
     */
    public void toppingDecrease() {
        price -= ADDITIONAL_TOPPING;
    }

    /**
     * Gets set toppings from each pizza subclass.
     * @return ArrayList of set toppings from pizza subclass.
     */
    public abstract ArrayList<Topping> getSetToppings();

    /**
     * Gets price of smallest size of pizza order. Implemented in subclass.
     * @return price of small pizza from each subclass.
     */
    public abstract double getSMALL_PRICE();

    /**
     * Decreases the price of pizza order when size is decreased.
     */
    public void sizeDecrease() {
        price -= SIZE_INCREASE;
    }

    /**
     * Gets the price of a pizza order.
     * @return price of pizza order.
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * Abstract method of toString() method that is implemented in each subclass.
     * @return toString of pizza instance.
     */
    public abstract String toString();
}

