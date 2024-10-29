package com.canadore.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView statusTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        statusTextView = findViewById(R.id.statusTextView);


        // Set login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username and password input
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate login
                if (username.equals("admin") && password.equals("admin")) {
                    // Successful login
                    statusTextView.setText("Login Successful");
                    statusTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                    Toast.makeText(AdminActivity.this, "Welcome, Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminActivity.this, AddFood.class);
                    startActivity(intent);
                    // TODO: Navigate to the admin dashboard or main admin functionality
                } else {
                    // Failed login
                    statusTextView.setText("Invalid Username or Password");
                    statusTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

    }
}