package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        findViewById(R.id.orderMedicineCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(HomePage.this, HomePage2.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.consultDoctorsCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(HomePage.this, ConsultDoctor.class);
                startActivity(intent);
            }
        });
    }

    // 🔹 Default Back Button Handling
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Go back to the previous activity
    }
}
