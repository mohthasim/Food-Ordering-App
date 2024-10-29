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

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<FoodItem> cartList;

    public CartAdapter(Context context, List<FoodItem> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FoodItem foodItem = cartList.get(position);
        holder.cartItemNameTextView.setText(foodItem.getName());
        holder.cartItemPriceTextView.setText("Price: $" + foodItem.getPrice());

        // Load image with Picasso
        Picasso.get().load(foodItem.getImageURL()).into(holder.cartItemImageView);

        // Set click listener for Remove button
        holder.removeFromCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.remove(position); // Remove item from list
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartList.size()); // Update list range
                Toast.makeText(context, foodItem.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView cartItemNameTextView, cartItemPriceTextView;
        ImageView cartItemImageView;
        Button removeFromCartButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemNameTextView = itemView.findViewById(R.id.cartItemNameTextView);
            cartItemPriceTextView = itemView.findViewById(R.id.cartItemPriceTextView);
            cartItemImageView = itemView.findViewById(R.id.cartItemImageView);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
        }
    }
}
