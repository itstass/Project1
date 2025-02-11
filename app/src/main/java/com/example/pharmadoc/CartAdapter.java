package com.example.pharmadoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> cartProducts;
    private TextView totalPriceTextView;// Added for displaying total price

    // Constructor
    public CartAdapter(Context context, ArrayList<Product> cartProducts, TextView totalPriceTextView) {
        this.context = context;
        this.cartProducts = cartProducts;
        this.totalPriceTextView = totalPriceTextView; // Initialize total price TextView

    }

    @Override
    public int getCount() {
        return cartProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return cartProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart_product, parent, false);
        }

        // Get the current product
        Product product = cartProducts.get(position);

        // Initialize views
        TextView nameTextView = convertView.findViewById(R.id.cartItemName);
        TextView priceTextView = convertView.findViewById(R.id.cartItemPrice);
        Button removeButton = convertView.findViewById(R.id.removeButton);

        // Set product details to the views
        nameTextView.setText(product.getName());
        priceTextView.setText("tk" + product.getPrice());

        // Set the Remove button functionality
        removeButton.setOnClickListener(v -> {
            // Remove the product from the cart
            removeProductFromCart(position);
        });

        return convertView;
    }

    // Method to remove product from the cart
    private void removeProductFromCart(int position) {
        // Remove the product from the cartProducts list
        cartProducts.remove(position);

        // Notify the adapter that the data set has changed
        notifyDataSetChanged();

        // Recalculate the total price after removal
        updateTotalPrice();

        // You may also need to update the cart in your database or shared preferences
        saveCartToDatabase();
    }

    // Method to save the updated cart (optional)
    private void saveCartToDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        // Loop through cart products and insert them into the database
        for (Product product : cartProducts) {
            // Use the addToCart method to save the product to the cart table
            boolean isAdded = dbHelper.addToCart(product.getName(), product.getPrice());
            if (!isAdded) {
                Toast.makeText(context, "Error saving cart item", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to update the total price
    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartProducts) {
            totalPrice += product.getPrice(); // Sum up all product prices
        }

        // Update the total price TextView
        totalPriceTextView.setText("Total: tk" + totalPrice);
    }


}