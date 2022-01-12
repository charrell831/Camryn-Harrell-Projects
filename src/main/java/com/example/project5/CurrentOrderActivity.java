package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Creates the window and functionalities for the Current Order window.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private ListView singleOrderList;
    private ArrayList<Order> singleOrder;
    private TextView phoneNumber, taxSpace, orderTotalField, subtotalField;
    private final double TAX_RATE = 0.06625;
    private final int ARRAY_SIZE = 1;
    private double subTotal;


    /**
     * Displays the opening page for the CurrentOrder window.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentorder);
        setTitle("Current Order");
        singleOrderList = findViewById(R.id.singleOrderList);
        taxSpace = findViewById(R.id.taxSpace);
        subtotalField = findViewById(R.id.subtotalField);
        orderTotalField = findViewById(R.id.orderTotalField);
        phoneNumber = findViewById(R.id.phoneNumber);
        singleOrder = new ArrayList<>();
        if (MainActivity.orders.size() >= ARRAY_SIZE) {
            singleOrder.add(MainActivity.orders.get(MainActivity.orders.size()-1));
            phoneNumber.setText(Long.toString(singleOrder.get(0).getPhoneNumber()));
            subTotal = singleOrder.get(0).getPizza().getPrice();
            setSingleOrderList(singleOrder);
            subtotalField.setText(String.format("%.2f",subTotal));
            orderTotalField.setText(String.format("%.2f", calculateTotal()));
        } else {
            Toast.makeText(CurrentOrderActivity.this, R.string.no_orders,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Calculates the price total and sets the text field.
     * @return total price
     */
    public double calculateTotal() {
        //put int orders in an array list and say no orders yet if empty
        double add = TAX_RATE * subTotal;
        taxSpace.setText(String.format("%.2f", add));
        return Math.round((subTotal + add) * 100.0) / 100.0;
    }

    /**
     * Sets the arraylist for a single order.
     * @param orders ArrayList of Order objects.
     */
    public void setSingleOrderList(ArrayList<Order> orders) {
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                orders
        );
        singleOrderList.setAdapter(arrayAdapter);
    }

    /**
     * Removes an order from the Order list when removeButton is clicked.
     * @param view
     */
    public void removeButtonClicked(View view) {
        MainActivity.orders.remove(MainActivity.orders.size()-1);
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.orders
        );
        taxSpace.setText("");
        subtotalField.setText("");
        phoneNumber.setText("");
        orderTotalField.setText("");
        singleOrderList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();Toast.makeText(CurrentOrderActivity.this, R.string.removed_order,
                Toast.LENGTH_LONG).show();

    }

    /**
     * Notifies the user that their order has been placed.
     * @param view
     */
    public void placeOrderClicked(View view) {
        Toast.makeText(CurrentOrderActivity.this, R.string.order_submit,
                Toast.LENGTH_LONG).show();
    }
}
