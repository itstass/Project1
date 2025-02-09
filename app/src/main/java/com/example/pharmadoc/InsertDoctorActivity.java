package com.example.pharmadoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertDoctorActivity extends AppCompatActivity {

    private EditText editTextDoctorName, editTextDoctorSpecialization, editTextDoctorContact, editTextDoctorEmail;
    private Button buttonInsertDoctor;
    private DatabaseHelper databaseHelper;  // Assuming you have a DatabaseHelper class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor);

        // Initialize views
        editTextDoctorName = findViewById(R.id.edit_text_doctor_name);
        editTextDoctorSpecialization = findViewById(R.id.edit_text_doctor_specialization);
        editTextDoctorContact = findViewById(R.id.edit_text_doctor_contact);
        editTextDoctorEmail = findViewById(R.id.edit_text_doctor_email);
        buttonInsertDoctor = findViewById(R.id.button_insert_doctor);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set button click listener
        buttonInsertDoctor.setOnClickListener(v -> insertDoctor());
    }

    private void insertDoctor() {
        String name = editTextDoctorName.getText().toString().trim();
        String specialization = editTextDoctorSpecialization.getText().toString().trim();
        String contact = editTextDoctorContact.getText().toString().trim();
        String email = editTextDoctorEmail.getText().toString().trim();

        // Validate input
        if (name.isEmpty() || specialization.isEmpty() || contact.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate the contact and email formats (you can add more validations as necessary)
        if (!contact.matches("^[0-9]{11}$")) {
            Toast.makeText(this, "Invalid contact number! Must be 11 digits.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+$")) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert doctor into database
        boolean isInserted = databaseHelper.insertDoctor(name, specialization, contact, email);

        if (isInserted) {
            Toast.makeText(this, "Doctor inserted successfully", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity after insertion
        } else {
            Toast.makeText(this, "Error inserting doctor", Toast.LENGTH_SHORT).show();
        }
    }
}
