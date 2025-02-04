package com.example.pharmadoc;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CatagoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);

        // Switch tabs functionality (dummy behavior)
        TextView tabMedicine = findViewById(R.id.tabMedicine);
        TextView tabDoctor = findViewById(R.id.tabDoctor);

        tabMedicine.setOnClickListener(v -> {
            tabMedicine.setTextColor(getResources().getColor(R.color.black));
            tabDoctor.setTextColor(getResources().getColor(R.color.gray));
        });

        tabDoctor.setOnClickListener(v -> {
            tabDoctor.setTextColor(getResources().getColor(R.color.black));
            tabMedicine.setTextColor(getResources().getColor(R.color.gray));
        });
    }
}