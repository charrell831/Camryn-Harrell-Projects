package com.example.project5;

import android.content.Context;
import android.os.Environment;

import java.io.*;
import java.util.ArrayList;

/**
 * Keeps track of all orders that have been place.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class StoreOrders {
    private ArrayList<Order> orders = new ArrayList<>();
    private Order order;
    private String stringOrder;

    /**
     * Empty constructor for StoreOrders class.
     */
    public StoreOrders() {
    }
    /**
     * Searches through the orders to see if a phone number already exists.
     * @param order Order object that contains the phone number, price and pizza description.
     * @return true if the phone number has already ordered and false if not.
     */
    public boolean numberExists(Order order) throws IOException {
        String orderNum = "";
        String fileName = "order-track.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        long phoneNumber;
        while((line = br.readLine()) != null) {
            //process the line
            String[] orderAttributes = line.split(" :: ");
            if (!orderAttributes[0].equals("")) { //list is empty
                phoneNumber = Long.parseLong(orderAttributes[0]);
                if (order.getPhoneNumber() == phoneNumber) {
                    return true;
                }
            }
        }
        fr.close();
        br.close();
        return false;
    }

    /**
     * Adds the current order to the list of store orders.
     * @param order current order inputted by user.
     */
    public void addStoreOrdersToList(Order order) {
        orders.add(order);
    }

    /**
     * Sets input order.
     * @param order Order object that contains the phone number, price and pizza description.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Sets input String order to stringOrder instance variable.
     * @param order String of order.
     */
    public void setStringOfOrder(String order) {
        this.stringOrder = order;
    }
    /**
     * Exports orders into "order-track.txt" file.
     * @throws IOException
     */
    public void export() throws IOException {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "project5");
            if (!root.exists()) {
                root.mkdir();
            }
            File filepath = new File(root, "order-track.txt");

            FileWriter writer = new FileWriter(filepath);
            writer.append(stringOrder);
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
    }
/*
File file = new File("order-track.txt");
        FileWriter fr = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fr);
        pw.write(stringOrder + "\n");
        pw.close();
 */
    /**
     * Export the user's current order into "single-order.txt" file for display.
     * @param order String of user's current order.
     * @throws IOException
     */
    public void exportSingleOrder(String order) throws IOException {
        File file = new File("single-order.txt");
        FileWriter fr = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fr);
        pw.write(order + "\n");
        pw.close();
    }
}
