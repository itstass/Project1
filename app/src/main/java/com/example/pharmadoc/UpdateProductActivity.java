package com.example.pharmadoc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText editTextProductName, editTextProductPrice, editTextProductQuantity;
    private ImageView imageViewProduct;
    private Button buttonUpdateProduct;

    private DatabaseHelper databaseHelper;
    private String productId;
    private byte[] productImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        // Initialize views
        editTextProductName = findViewById(R.id.edit_text_product_name);
        editTextProductPrice = findViewById(R.id.edit_text_product_price);
        editTextProductQuantity = findViewById(R.id.edit_text_product_quantity);
        imageViewProduct = findViewById(R.id.image_view_product);
        buttonUpdateProduct = findViewById(R.id.button_update_product);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get product details from Intent
        Intent intent = getIntent();
        productId = intent.getStringExtra("product_id");
        String productName = intent.getStringExtra("product_name");
        double productPrice = intent.getDoubleExtra("product_price", 0);
        int productQuantity = intent.getIntExtra("product_quantity", 0);
        productImageByteArray = intent.getByteArrayExtra("product_image");

        // Set the data to the EditText fields and ImageView
        editTextProductName.setText(productName);
        editTextProductPrice.setText(String.valueOf(productPrice));
        editTextProductQuantity.setText(String.valueOf(productQuantity));

        // If you want to display the image, decode the byte array to a bitmap
        if (productImageByteArray != null) {
            imageViewProduct.setImageBitmap(BitmapFactory.decodeByteArray(productImageByteArray, 0, productImageByteArray.length));
        }

        // Set the update button functionality
        buttonUpdateProduct.setOnClickListener(v -> {
            // Get updated data from the input fields
            String updatedProductName = editTextProductName.getText().toString();
            double updatedProductPrice = Double.parseDouble(editTextProductPrice.getText().toString());
            int updatedProductQuantity = Integer.parseInt(editTextProductQuantity.getText().toString());

            // Optionally, you could add code for updating the product image here

            // Update the product in the database
            updateProduct(updatedProductName, updatedProductPrice, updatedProductQuantity);
        });
    }

    private void updateProduct(String updatedProductName, double updatedProductPrice, int updatedProductQuantity) {
        // Call the update method from DatabaseHelper to update the product in the database
        databaseHelper.updateProduct(
                Integer.parseInt(productId), // Convert productId to int
                updatedProductName,
                updatedProductPrice,
                updatedProductQuantity,
                productImageByteArray  // You can update the image if needed
        );

        // Show a toast and finish the activity to go back to the list
        Toast.makeText(this, "Product updated successfully!", Toast.LENGTH_SHORT).show();
        finish();  // Close the activity and return to the previous screen
    }
}