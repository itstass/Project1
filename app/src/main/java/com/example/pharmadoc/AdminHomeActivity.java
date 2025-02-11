package com.example.pharmadoc;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Button btnInsertProduct =findViewById(R.id.btn_insert_product);
        Button btnViewProduct =findViewById(R.id.btn_view_product);
        Button btnInsertDoctor =findViewById(R.id.btn_insert_doctor);
        Button btnViewDoctor =findViewById(R.id.btn_view_doctor);
        Button btnViewOrder = findViewById(R.id.btn_view_order);
        Button btnLogout  = findViewById(R.id.btn_logout);;

        btnInsertProduct.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, InsertProductActivity.class);
            startActivity(intent);
        });


        btnViewProduct.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewProductActivity.class);
            startActivity(intent);
        });

        btnInsertDoctor.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, InsertDoctorActivity.class);
            startActivity(intent);
        });


        btnViewDoctor.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewDoctorActivity.class);
            startActivity(intent);
        });

        btnViewOrder.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomeActivity.this, ViewCartActivity.class);
            startActivity(intent);
        });

        // Logout button functionality
        btnLogout.setOnClickListener(v -> {

            Toast.makeText(AdminHomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminHomeActivity.this, MainActivity.class));
            finish();
        });

    }
}