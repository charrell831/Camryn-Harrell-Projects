package com.example.project5;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates the window and functionalities for the Store Orders window.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class StoreOrdersActivity extends AppCompatActivity {
    private ListView storeOrdersList;
    private Button cancelOrderButton;
    private Order selectedOrder;
    private TextView phoneNumber, totalPrice;

    /**
     * Creates page for StoreOrders window.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorders);
        setTitle("Store Orders");
        totalPrice = findViewById(R.id.totalPrice);
        phoneNumber = findViewById(R.id.customerNumber);
        storeOrdersList = findViewById(R.id.storeOrdersList);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);
        setStoreOrdersList(MainActivity.orders);
        storeOrdersList.setOnItemClickListener((adapter, v, i, l) -> {
            selectedOrder = (Order) storeOrdersList.getItemAtPosition(i);
            phoneNumber.setText(Long.toString(selectedOrder.getPhoneNumber()));
            totalPrice.setText(Double.toString(selectedOrder.getPizza().price()));
            cancelOrderButton.setOnClickListener(view -> setCancelOrderButton(selectedOrder));
        });

    }

    /**
     * Removes selected order from Order list when Cancel Order button is
     * clicked.
     * @param order Order object.
     */
    private void setCancelOrderButton(Order order) {
        MainActivity.orders.remove(order);
        setStoreOrdersList(MainActivity.orders);
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.orders
        );
        storeOrdersList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        phoneNumber.setText("");
        totalPrice.setText("");
    }

    /**
     * Sets the list for pizza orders.
     * @param orders List of Order objecgts.
     */
    private void setStoreOrdersList(ArrayList<Order> orders) {
        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                orders
        );
        storeOrdersList.setAdapter(arrayAdapter);
    }

}
