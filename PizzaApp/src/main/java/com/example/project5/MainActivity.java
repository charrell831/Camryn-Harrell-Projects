package com.example.project5;

import static com.example.project5.PizzaMaker.createPizza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates the window and functionalities for the Main Activity window.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class MainActivity extends AppCompatActivity implements Serializable{
    private TextView phoneNumber;
    protected static RadioButton deluxeButton, hawaiianButton, pepperoniButton;
    private Button viewCurrentOrder, viewStoreOrderButton;
    protected static EditText numberInput;
    protected static ArrayList<Order> orders;
    protected static ArrayAdapter<Order> orderArrayAdapter;
    private final int PHONE_NUMBER_SIZE = 10;

    /**
     * Creates the open screen for the main activity window.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome to RU Pizzeria!");
        orders = new ArrayList<>();
        orderArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                orders
        );
        numberInput = findViewById(R.id.editTextPhone);
        deluxeButton = findViewById(R.id.deluxeButton);
        hawaiianButton = findViewById(R.id.hawaiianButton);
        pepperoniButton = findViewById(R.id.pepperoniButton);
        viewCurrentOrder = findViewById(R.id.viewCurrentOrder);
        viewStoreOrderButton = findViewById(R.id.viewStoreOrderButton);

        viewStoreOrderButton.setOnClickListener(v -> setViewStoreOrdersClicked());
        deluxeButton.setOnClickListener(v -> setDeluxe());
        hawaiianButton.setOnClickListener(v -> setHawaiian());
        pepperoniButton.setOnClickListener(v -> setPepperoni());
        viewCurrentOrder.setOnClickListener(v -> setViewCurrentOrder());
    }

    /**
     * Starts the activity for StoreOrdersActivity.
     */
    public void setViewStoreOrdersClicked() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the activity for CurrentOrderActivity.
     */
    public void setViewCurrentOrder() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the customization page for a deluxe pizza.
     */
    public void setDeluxe() {
       try {
           if (numberInput.getText().length() != PHONE_NUMBER_SIZE || numberInputExists(Long.parseLong(numberInput.getText().toString()))) {
               Toast.makeText(MainActivity.this, R.string.error_number,
                       Toast.LENGTH_LONG).show();
               deluxeButton.setChecked(false);
               pepperoniButton.setChecked(false);
               hawaiianButton.setChecked(false);
               numberInput.getText().clear();
           } else {
               Intent intent = new Intent(this, CustomizationActivity.class);
               Bundle extras = new Bundle();

               Pizza deluxe = createPizza("Deluxe");
               ArrayList<Topping> toppings = deluxe.getSetToppings();
               ArrayList<Topping> additional = deluxe.getAdditionalToppings();

               extras.putSerializable("extraToppingsId", additional);
               extras.putSerializable("setToppingsId", (Serializable)toppings); //first extras addition
               extras.putInt("resId", R.drawable.deluxe); //extras image addition
               extras.putLong("phoneNumber", Long.parseLong(numberInput.getText().toString()));
               extras.putSerializable("storeOrdersId", orders);
               intent.putExtras(extras);

               startActivity(intent);
           }
       } catch (NumberFormatException npe) {
           Toast.makeText(MainActivity.this, R.string.error_number,
                   Toast.LENGTH_LONG).show();
           deluxeButton.setChecked(false);
           pepperoniButton.setChecked(false);
           hawaiianButton.setChecked(false);
           numberInput.getText().clear();
       }
   }

    /**
     * Opens the customization page for a hawaiian pizza.
     */
    public void setHawaiian() {
        try {
            if (numberInput.getText().length() != PHONE_NUMBER_SIZE || numberInputExists(Long.parseLong(numberInput.getText().toString()))) {
                Toast.makeText(MainActivity.this, R.string.error_number,
                        Toast.LENGTH_LONG).show();
                deluxeButton.setChecked(false);
                pepperoniButton.setChecked(false);
                hawaiianButton.setChecked(false);
                numberInput.getText().clear();
            } else {
                Intent intent = new Intent(this, CustomizationActivity.class);
                Bundle extras = new Bundle();

                Pizza deluxe = createPizza("Deluxe");
                ArrayList<Topping> toppings = deluxe.getSetToppings();
                ArrayList<Topping> additional = deluxe.getAdditionalToppings();

                extras.putSerializable("extraToppingsId", additional);
                extras.putSerializable("setToppingsId", toppings); //first extras addition
                extras.putInt("resId", R.drawable.deluxe); //extras image addition
                extras.putLong("phoneNumber", Long.parseLong(numberInput.getText().toString()));
                extras.putSerializable("storeOrdersId", orders);
                intent.putExtras(extras);

                startActivity(intent);
            }
        } catch (NumberFormatException npe) {
            Toast.makeText(MainActivity.this, R.string.error_number,
                    Toast.LENGTH_LONG).show();
            deluxeButton.setChecked(false);
            pepperoniButton.setChecked(false);
            hawaiianButton.setChecked(false);
            numberInput.getText().clear();
        }
    }

    /**
     * Checks for duplicate phone numbers in Orders list.
     * @param number Phone number for customer.
     * @return true if phone number exists in Order list and false otherwise.
     */
    public boolean numberInputExists(long number) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getPhoneNumber() == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens the customization page for a pepperoni pizza.
     */
    public void setPepperoni() {
        try {
            if (numberInput.getText().length() != PHONE_NUMBER_SIZE || numberInputExists(Long.parseLong(numberInput.getText().toString()))) {
                Toast.makeText(MainActivity.this, R.string.error_number,
                        Toast.LENGTH_LONG).show();
                deluxeButton.setChecked(false);
                pepperoniButton.setChecked(false);
                hawaiianButton.setChecked(false);
                numberInput.getText().clear();
            } else {
                Intent intent = new Intent(this, CustomizationActivity.class);
                Bundle extras = new Bundle();

                Pizza pepperoni = createPizza("Pepperoni");
                ArrayList<Topping> toppings = pepperoni.getSetToppings();
                ArrayList<Topping> additional = pepperoni.getAdditionalToppings();

                extras.putSerializable("extraToppingsId", additional);
                extras.putSerializable("setToppingsId", (Serializable) toppings); //first extras addition
                extras.putInt("resId", R.drawable.pepperoni); //extras image addition
                extras.putLong("phoneNumber", Long.parseLong(numberInput.getText().toString()));
                extras.putSerializable("storeOrdersId", orders);
                intent.putExtras(extras);

                startActivity(intent);
            }
        } catch (NumberFormatException npe) {
            Toast.makeText(MainActivity.this, R.string.error_number,
                    Toast.LENGTH_LONG).show();
            deluxeButton.setChecked(false);
            pepperoniButton.setChecked(false);
            hawaiianButton.setChecked(false);
            numberInput.getText().clear();
        }
    }
}