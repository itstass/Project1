package com.example.pharmadoc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryProductsActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listViewProducts;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        dbHelper = new DatabaseHelper(this);
        listViewProducts = findViewById(R.id.listViewProducts);

        // Get selected category from intent
        selectedCategory = getIntent().getStringExtra("CATEGORY");
        if (selectedCategory != null) {
            loadProducts(); // No need to pass category since it's already stored in selectedCategory
        } else {
            Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProducts() {
        Cursor cursor = dbHelper.getProductsByCategory(selectedCategory); // Fetch products by category
        ProductAdapter adapter = new ProductAdapter(this, cursor, false); // User mode
        listViewProducts.setAdapter(adapter);
    }
}
