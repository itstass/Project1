package com.example.pharmadoc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage2 extends AppCompatActivity {

    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;
    private FirebaseAuth mAuth;
    private Button btnLogout;
    private Button viewCartButton;
    private Button btnCatagory;
    private SearchView searchView;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this);

        listViewProducts = findViewById(R.id.product_list);
        btnLogout = findViewById(R.id.btn_logout);
        viewCartButton = findViewById(R.id.button_cart);
        btnCatagory=findViewById(R.id.btn_catagory);
        searchView = findViewById(R.id.search_view);

        // Load products from SQLite for user
        loadProducts();

        // Setup search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the action when search is submitted
                searchProduct(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter products as the user types
                searchProduct(newText);
                return true;
            }
        });
        // Logout button functionality
        btnLogout.setOnClickListener(v -> {
           // mAuth.signOut();
            Toast.makeText(HomePage2.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomePage2.this, MainActivity.class));
            finish();
        });

        // View Cart button functionality
        viewCartButton.setOnClickListener(v -> {
            // Open CartActivity when "View Cart" is clicked
            Intent intent = new Intent(HomePage2.this, CartActivity.class);
            startActivity(intent);
        });

        btnCatagory.setOnClickListener(v -> {
            // Open CartActivity when "View Cart" is clicked
            Intent intent = new Intent(HomePage2.this, CatagoriesActivity.class);
            startActivity(intent);
        });
    }

    private void loadProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ProductAdapter adapter = new ProductAdapter(this, cursor, false); // User mode
        listViewProducts.setAdapter(adapter);
    }
    private void searchProduct(String query) {
        Cursor cursor = databaseHelper.getProductByName(query); // Search by name
        productAdapter = new ProductAdapter(this, cursor, true);
        listViewProducts.setAdapter(productAdapter);
    }
}