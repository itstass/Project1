package com.example.pharmadoc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

public class ViewDoctorActivity extends AppCompatActivity {

    private ListView listViewDoctors;
    private DatabaseHelper databaseHelper;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);

        // Initialize views
        listViewDoctors = findViewById(R.id.list_view_doctors);
        mapButton = findViewById(R.id.button_map_location);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Load doctors from the database
        loadDoctors();

        // Set an onClickListener for the map button
        mapButton.setOnClickListener(v -> openMapLocation());
    }

    public void loadDoctors() {
        // Get all doctors from the database
        Cursor cursor = databaseHelper.getAllDoctors();

        // Since this is the admin page, pass 'true' to show the delete button
        DoctorAdapter adapter = new DoctorAdapter(this, cursor, true);  // true for admin mode

        listViewDoctors.setAdapter(adapter);
    }

    // Method to open Google Maps with a location
    private void openMapLocation() {
        // Example: replace with actual location coordinates
        String uri = "geo:24.899105,91.862685?q=24.899105,91.862685(Pharmacy+Location)\n";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // Ensure it opens in Google Maps
        startActivity(intent);
    }
}
