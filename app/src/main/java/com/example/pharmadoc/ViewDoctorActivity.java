package com.example.pharmadoc;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewDoctorActivity extends AppCompatActivity {

    private ListView listViewDoctors;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);

        // Initialize views
        listViewDoctors = findViewById(R.id.list_view_doctors);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Load doctors from the database
        loadDoctors();
    }

    public void loadDoctors() {
        // Get all doctors from the database
        Cursor cursor = databaseHelper.getAllDoctors();

        // Since this is the admin page, pass 'true' to show the delete button
        DoctorAdapter adapter = new DoctorAdapter(this, cursor, true);  // true for admin mode

        listViewDoctors.setAdapter(adapter);
    }


}
