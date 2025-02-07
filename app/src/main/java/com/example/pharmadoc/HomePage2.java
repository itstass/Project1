package com.example.pharmadoc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage2 extends AppCompatActivity {

    private ListView listViewProducts;
    private DatabaseHelper databaseHelper;
    private FirebaseAuth mAuth;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this);

        listViewProducts = findViewById(R.id.product_list);
        btnLogout = findViewById(R.id.btn_logout);

        // Load products from SQLite for user
        loadProducts();

        // Logout button functionality
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(HomePage2.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomePage2.this, MainActivity.class));
            finish();
        });
    }

    private void loadProducts() {
        Cursor cursor = databaseHelper.getAllProducts();
        ProductAdapter adapter = new ProductAdapter(this, cursor, false); // User mode
        listViewProducts.setAdapter(adapter);
    }
}
