package com.canadore.foodorderingapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {


    private EditText foodNameEditText, foodPriceEditText, foodDescriptionEditText, foodImageURLEditText;
    private Button addFoodButton, logoutButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");

        // Initialize views
        foodNameEditText = findViewById(R.id.foodNameEditText);
        foodPriceEditText = findViewById(R.id.foodPriceEditText);
        foodDescriptionEditText = findViewById(R.id.foodDescriptionEditText);
        foodImageURLEditText = findViewById(R.id.foodImageURLEditText);
        addFoodButton = findViewById(R.id.addFoodButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Set button click listener
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodToFirebase();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log out and navigate to MainActivity
                Intent intent = new Intent(AddFood.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();  // Finish the current activity to prevent going back
            }
        });
    }

    private void addFoodToFirebase() {
        String name = foodNameEditText.getText().toString().trim();
        String priceText = foodPriceEditText.getText().toString().trim();
        String description = foodDescriptionEditText.getText().toString().trim();
        String imageURL = foodImageURLEditText.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(priceText) || TextUtils.isEmpty(description) || TextUtils.isEmpty(imageURL)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate unique ID for each food item
        String id = databaseReference.push().getKey();

        // Create food item object
        FoodItem foodItem = new FoodItem(id, name, price, description, imageURL);

        // Store the item in Firebase
        if (id != null) {
            databaseReference.child(id).setValue(foodItem)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddFood.this, "Food added successfully", Toast.LENGTH_SHORT).show();
                            // Clear the input fields
                            foodNameEditText.setText("");
                            foodPriceEditText.setText("");
                            foodDescriptionEditText.setText("");
                            foodImageURLEditText.setText("");
                        } else {
                            Toast.makeText(AddFood.this, "Failed to add food", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
