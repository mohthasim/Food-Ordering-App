package com.canadore.foodorderingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<FoodItem> foodList;
    private static List<FoodItem> cartList = new ArrayList<>(); // Static cart list

    public FoodAdapter(Context context, List<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.foodNameTextView.setText(foodItem.getName());
        holder.foodPriceTextView.setText("Price: $" + foodItem.getPrice());
        holder.foodDescriptionTextView.setText(foodItem.getDescription());

        // Load image with Picasso
        Picasso.get().load(foodItem.getImageURL()).into(holder.foodImageView);

        // Set click listener for Add to Cart button
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.add(foodItem); // Add item to cart list
                Toast.makeText(context, foodItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static List<FoodItem> getCartList() {
        return cartList; // Getter to retrieve cart items in the next activity
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView, foodPriceTextView, foodDescriptionTextView;
        ImageView foodImageView;
        Button addToCartButton;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
            foodDescriptionTextView = itemView.findViewById(R.id.foodDescriptionTextView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
