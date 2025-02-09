package com.example.pharmadoc;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView totalPriceTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        cartListView = findViewById(R.id.cartListView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        checkoutButton = findViewById(R.id.checkoutButton);



        ArrayList<Product> cartProducts = CartManager.getInstance().getCart();


        CartAdapter adapter = new CartAdapter(this, cartProducts, totalPriceTextView);

        cartListView.setAdapter(adapter);


        updateTotalPrice();


        checkoutButton.setOnClickListener(v -> {
            if (cartProducts.isEmpty()) {
                Toast.makeText(CartActivity.this, "Your cart is empty.", Toast.LENGTH_SHORT).show();
            } else {

                CartManager.getInstance().clearCart();
                adapter.notifyDataSetChanged();
                updateTotalPrice();
                Toast.makeText(CartActivity.this, "Checkout completed!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void updateTotalPrice() {
        double total = CartManager.getInstance().getTotalPrice();
        totalPriceTextView.setText("Total: tk" + total);
    }
}