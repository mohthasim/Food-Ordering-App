package com.canadore.foodorderingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<FoodItem> cartList;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize RecyclerView
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve cart list from FoodAdapter (or any singleton/shared class)
        cartList = FoodAdapter.getCartList(); // Assuming the static method in FoodAdapter
        cartAdapter = new CartAdapter(this, cartList);
        cartRecyclerView.setAdapter(cartAdapter);

        // Initialize Checkout Button
        checkoutButton = findViewById(R.id.checkoutButton);

        // Calculate and display total price and item count
        updateCheckoutButton();

        // Set listener for Checkout Button
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartList.isEmpty()) {
                    Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle checkout process here
                    Toast.makeText(CartActivity.this, "Proceeding to checkout...", Toast.LENGTH_SHORT).show();

                    // Clear cart and refresh adapter (optional)
                    cartList.clear();
                    cartAdapter.notifyDataSetChanged();
                    updateCheckoutButton();
                }
            }
        });
    }

    // Method to update Checkout Button text with total items and price
    private void updateCheckoutButton() {
        int totalItems = cartList.size();
        double totalPrice = 0;

        for (FoodItem item : cartList) {
            totalPrice += item.getPrice();
        }

        // Set button text with total items and price
        checkoutButton.setText("Checkout (" + totalItems + " items, $" + String.format("%.2f", totalPrice) + ")");
    }
}
