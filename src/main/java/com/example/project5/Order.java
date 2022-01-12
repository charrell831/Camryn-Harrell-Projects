package com.example.project5;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
/**
 * Create order objects for each user.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class Order implements Serializable{
    private long phoneNumber;
    private Pizza pizza;

    /**
     * Constructor for pizza order.
     * @param phoneNumber input phone number for pizza order.
     * @param pizza input pizza for pizza order.
     */
    public Order(long phoneNumber, Pizza pizza) {
        this.phoneNumber = phoneNumber;
        this.pizza = pizza;
    }

    /**
     * Gets phone number associated with pizza order.
     * @return
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public Pizza getPizza() {
        return this.pizza;
    }
    /**
     * Converts order to a string.
     * @return order as a string.
     */
    public String toString() {
        return String.valueOf(phoneNumber) + " :: " + pizza;
    }


}

