package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import java.io.Serializable;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates the window and functionalities for the Customization window.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
public class CustomizationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {
    private ImageView customizationPics;
    private ListView removedToppings, addedToppings;
    private TextView priceView;
    private Spinner sizeMenu;
    private Button addButton, removeButton;
    ArrayList<Topping> removedToppingsList, addedToppingsList;
    private final double TOPPING_INCREASE = 1.49;
    private Size boxSize;
    private Pizza pizza;
    private Topping firstToppingAdded;
    private Topping firstToppingRemoved;


    /**
     * Creates the open screen for the customization activity window.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);
        setTitle("Customize Your Order");
        customizationPics = findViewById(R.id.customizationPics);
        removedToppings = findViewById(R.id.removedToppings);
        addedToppings = findViewById(R.id.addedToppings);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        priceView = findViewById(R.id.priceView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            customizationPics.setImageResource(resId);
        }
        setListView(bundle);
        addedToppings.setOnItemClickListener((adapter, v, i, l) -> {
            firstToppingAdded = (Topping) addedToppings.getItemAtPosition(i);
            removedToppingsList.add(firstToppingAdded);
            addedToppingsList.remove(firstToppingAdded);
            if (pizza.price() - TOPPING_INCREASE >= pizza.defaultPrice()) {
                pizza.toppingDecrease();
            }
        });
        removedToppings.setOnItemClickListener((adapter, v, i, l) -> {
            firstToppingRemoved = (Topping) removedToppings.getItemAtPosition(i);
            addedToppingsList.add(firstToppingRemoved);
            removedToppingsList.remove(firstToppingRemoved);
            pizza.toppingIncrease();
        });
        sizeMenu = findViewById(R.id.sizeMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CustomizationActivity.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sizes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeMenu.setAdapter(adapter);
        spinnerSetUp(sizeMenu);
        setSizeMenu();
    }

    /**
     * Sets the spinner.
     * @param spinner Spinner object.
     */
    private void spinnerSetUp(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSizeMenu();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Converts the String size to size object.
     * @param size String size.
     * @return the Size enum associated with the String size.
     */
    private Size stringToSize(String size) {
        if (size.equalsIgnoreCase("Small")) {
            return Size.SMALL;
        } else if (size.equalsIgnoreCase("Medium")) {
            return Size.MEDIUM;
        } else if (size.equalsIgnoreCase("Large")) {
            return Size.LARGE;
        }
        return null;
    }

    /**
     * Sets the pizza size based on which size is clicked.
     */
    public void setSizeMenu() {
        Size currentSize = boxSize;
        boxSize = stringToSize((String) sizeMenu.getSelectedItem());
        if (currentSize == Size.SMALL || currentSize == null) {
            if (boxSize == Size.MEDIUM) {
                pizza.sizeIncrease();
            } else if (boxSize == Size.LARGE) {
                pizza.sizeIncrease();
                pizza.sizeIncrease();
            }
        }
        if (currentSize == Size.MEDIUM) {
            if (boxSize == Size.SMALL) {
                pizza.sizeDecrease();
            } else if (boxSize == Size.LARGE) {
                pizza.sizeIncrease();
            }
        }
        if (currentSize == Size.LARGE) {
            if (boxSize == Size.MEDIUM) {
                pizza.sizeDecrease();
            } else if (boxSize == Size.SMALL) {
                pizza.sizeDecrease();
                pizza.sizeDecrease();
            }
        }
        priceView.setText(String.format("%.2f",pizza.price()));
        pizza.setSize(boxSize);
    }

    /**
     * Sets the list view of topping.
     * @param bundle
     */
    public void setListView(Bundle bundle) {
        if (bundle != null) {
            removedToppingsList = (ArrayList<Topping>) bundle.getSerializable("setToppingsId");
            Deluxe deluxe = new Deluxe();
            Pepperoni pepperoni = new Pepperoni();
            Hawaiian hawaiian = new Hawaiian();
            if (removedToppingsList.equals(deluxe.getSetToppings())) {
                pizza = new Deluxe();
            } else if (removedToppingsList.equals(pepperoni.getSetToppings())) {
                pizza = new Pepperoni();
            } else if (removedToppingsList.equals(hawaiian.getSetToppings())) {
                pizza = new Hawaiian();
            }
            ArrayAdapter<Topping> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    removedToppingsList
            );
            removedToppings.setAdapter(arrayAdapter);
            addedToppingsList = (ArrayList<Topping>) bundle.getSerializable("extraToppingsId");
            ArrayAdapter<Topping> arrayAdapter2 = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    addedToppingsList
            );
            addedToppings.setAdapter(arrayAdapter2);
        }
    }

    /**
     * Method for when item is selected (required in order to implement AdapterView.OnItemSelectedListener).
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * Method for when nothing is selected (required in order to implement AdapterView.OnItemSelectedListener).
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Adds toppings to a pizza based on topping selected.
     * @param view
     */
    public void addClicked(View view) {
        //had first selected item first
        removedToppings.setOnItemClickListener((adapter, v, i, l) -> {
            Topping topping = (Topping) removedToppings.getItemAtPosition(i);
            addedToppingsList.add(topping);
            removedToppingsList.remove(topping);
            pizza.toppingIncrease();
        });
        ArrayAdapter<Topping> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                addedToppingsList
        );
        ArrayAdapter<Topping> arrayAdapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                removedToppingsList
        );
        removedToppings.setAdapter(arrayAdapter2);
        arrayAdapter2.notifyDataSetChanged();
        addedToppings.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        pizza.setToppings(addedToppingsList);
        priceView.setText(String.format("%.2f",pizza.price()));
    }

    /**
     * Removes toppings from a pizza based on topping selected.
     * @param view
     */
    public void removeClicked(View view) {
        addedToppings.setOnItemClickListener((adapter, v, i, l) -> {
            Topping topping = (Topping) addedToppings.getItemAtPosition(i);
            removedToppingsList.add(topping);
            addedToppingsList.remove(topping);
            System.out.println(pizza.defaultPrice());
            if (pizza.price() - TOPPING_INCREASE >= pizza.defaultPrice()) {
                pizza.toppingDecrease();
            }
        });
        ArrayAdapter<Topping> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                removedToppingsList
        );
        ArrayAdapter<Topping> arrayAdapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                addedToppingsList
        );
        addedToppings.setAdapter(arrayAdapter2);
        arrayAdapter2.notifyDataSetChanged();
        removedToppings.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        pizza.setToppings(addedToppingsList);
        priceView.setText(String.format("%.2f",pizza.price()));
    }

    /**
     * Submits an order for the pizza that the order has selected.
     * @param view
     * @throws IOException
     */
    public void submitCustomerButton(View view) throws IOException {
        MainActivity.hawaiianButton.setChecked(false);
        MainActivity.deluxeButton.setChecked(false);
        MainActivity.pepperoniButton.setChecked(false);
        MainActivity.numberInput.getText().clear();
        Intent i = getIntent();
        Long phoneNumber = i.getLongExtra("phoneNumber", 0);
        Order order = new Order(phoneNumber, pizza);

        MainActivity.orders.add(order);
        MainActivity.orderArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.orders
        );
        MainActivity.orderArrayAdapter.notifyDataSetChanged();
        Toast.makeText(CustomizationActivity.this, R.string.order_submit,
                Toast.LENGTH_LONG).show();
    }
}