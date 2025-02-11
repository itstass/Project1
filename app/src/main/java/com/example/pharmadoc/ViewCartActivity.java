package com.example.pharmadoc;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewCartActivity extends AppCompatActivity {

    private ListView cartListView;
    private SimpleCursorAdapter adapter;  // Declare adapter at class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        cartListView = findViewById(R.id.cart_list_view);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.getPaymentItems();


        if (cursor == null) {
            Toast.makeText(this, "Cursor is NULL. Database issue?", Toast.LENGTH_LONG).show();
            return;
        }

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No items in cart!", Toast.LENGTH_LONG).show();
            cursor.close();  // Close cursor if no items
            return;
        }

        String[] fromColumns = {
               // DatabaseHelper.COL_CART_PRODUCT_NAME,
                //DatabaseHelper.COL_CART_PRODUCT_PRICE,
                DatabaseHelper.COL_PAYMENT_FULL_NAME,      // Use correct column names
                DatabaseHelper.COL_PAYMENT_PHONE_NUMBER,
                DatabaseHelper.COL_PAYMENT_EMAIL,
                DatabaseHelper.COL_PAYMENT_SHIPPING_ADDRESS,
                DatabaseHelper.COL_PAYMENT_DELIVERY_NOTE,
                DatabaseHelper.COL_PAYMENT_METHOD
        };

        int[] toViews = {
               // R.id.cart_product_name,
               // R.id.cart_product_price,
                R.id.cart_full_name,
                R.id.cart_phone_number,
                R.id.cart_email,
                R.id.cart_shipping_address,
                R.id.cart_delivery_note,
                R.id.cart_payment_method
        };

        try {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this, R.layout.cart_item_layout, cursor, fromColumns, toViews, 0);

            cartListView.setAdapter(adapter);  // Set adapter inside try block

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
