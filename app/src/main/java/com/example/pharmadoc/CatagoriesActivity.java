package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CatagoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);

        Button btnOtcMedicine = findViewById(R.id.btn_otc_medicine);
        Button btnDiabeticCare = findViewById(R.id.btn_diabetic_care);
        Button btnDentalCare = findViewById(R.id.btn_dental_care);
        Button btnMentalHealth = findViewById(R.id.btn_mental_health);
        Button btnWomensHealth = findViewById(R.id.btn_womens_health);
        Button btnFirstAid = findViewById(R.id.btn_first_aid);

        btnOtcMedicine.setOnClickListener(view -> openCategory("OTC Medicine"));
        btnDiabeticCare.setOnClickListener(view -> openCategory("Diabetic Care"));
        btnDentalCare.setOnClickListener(view -> openCategory("Dental Care"));
        btnMentalHealth.setOnClickListener(view -> openCategory("Mental Health"));
        btnWomensHealth.setOnClickListener(view -> openCategory("Women's Health"));
        btnFirstAid.setOnClickListener(view -> openCategory("First Aid"));
    }

    private void openCategory(String category) {
        Intent intent = new Intent(this, CategoryProductsActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
