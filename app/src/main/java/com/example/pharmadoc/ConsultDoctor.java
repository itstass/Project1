package com.example.pharmadoc;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class ConsultDoctor extends AppCompatActivity {

    private ListView listViewDoctors;
    private DatabaseHelper databaseHelper;
    private FirebaseAuth mAuth;

    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_doctor);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this);

        listViewDoctors = findViewById(R.id.list_view_doctors);

        mapButton = findViewById(R.id.button_map_location);

        // Load products from SQLite for user
        loadDoctors();

        mapButton.setOnClickListener(v -> openMapLocation());



    }

   public void loadDoctors() {
        Cursor cursor = databaseHelper.getAllDoctors();
        DoctorAdapter adapter = new DoctorAdapter(this, cursor, false); // User mode
        listViewDoctors.setAdapter(adapter);
    }

    private void openMapLocation() {
        // Example: replace with actual location coordinates
        String uri = "geo:24.899105,91.862685?q=24.899105,91.862685(Pharmacy+Location)\n";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // Ensure it opens in Google Maps
        startActivity(intent);
    }

}