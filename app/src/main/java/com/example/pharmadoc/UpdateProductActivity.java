package com.example.pharmadoc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText editTextProductName, editTextProductPrice, editTextProductQuantity, productDescriptionEditText;
    private Spinner categorySpinner;
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
        productDescriptionEditText = findViewById(R.id.edit_text_product_description);
        categorySpinner = findViewById(R.id.spinner_category);
        imageViewProduct = findViewById(R.id.image_view_product);
        buttonUpdateProduct = findViewById(R.id.button_update_product);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get product details from Intent
        Intent intent = getIntent();
        productId = intent.getStringExtra("product_id");
        String productName = intent.getStringExtra("product_name");
        String productDescription = intent.getStringExtra("product_description");
        String productCategory = intent.getStringExtra("product_category");
        double productPrice = intent.getDoubleExtra("product_price", 0);
        int productQuantity = intent.getIntExtra("product_quantity", 0);
        productImageByteArray = intent.getByteArrayExtra("product_image");

        // Set the data to the EditText fields and ImageView
        editTextProductName.setText(productName);
        productDescriptionEditText.setText(productDescription);
        editTextProductPrice.setText(String.valueOf(productPrice));
        editTextProductQuantity.setText(String.valueOf(productQuantity));

        // Populate category spinner
        String[] categories = {"Medicine", "Supplements", "Personal Care", "Medical Equipment"};
        categorySpinner.setSelection(Arrays.asList(categories).indexOf(productCategory));

        // Display image if available
        if (productImageByteArray != null) {
            imageViewProduct.setImageBitmap(BitmapFactory.decodeByteArray(productImageByteArray, 0, productImageByteArray.length));
        }

        // Set the update button functionality
        buttonUpdateProduct.setOnClickListener(v -> {
            // Get updated data from the input fields
            String updatedProductName = editTextProductName.getText().toString();
            String updatedProductDescription = productDescriptionEditText.getText().toString();
            String updatedProductCategory = categorySpinner.getSelectedItem().toString();
            double updatedProductPrice = Double.parseDouble(editTextProductPrice.getText().toString());
            int updatedProductQuantity = Integer.parseInt(editTextProductQuantity.getText().toString());

            // Update the product in the database
            updateProduct(updatedProductName, updatedProductDescription, updatedProductCategory, updatedProductPrice, updatedProductQuantity);
        });
    }

    private void updateProduct(String name, String description, String category, double price, int quantity) {
        databaseHelper.updateProduct(Integer.parseInt(productId), name, description, category, price, quantity, productImageByteArray);
        Toast.makeText(this, "Product updated successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
