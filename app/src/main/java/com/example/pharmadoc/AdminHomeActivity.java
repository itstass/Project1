package com.example.pharmadoc;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

    }
}