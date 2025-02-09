package com.example.pharmadoc;

import android.content.Intent;
import android.database.Cursor;
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
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_doctor);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this);

        listViewDoctors = findViewById(R.id.list_view_doctors);
        btnLogout = findViewById(R.id.btn_logout);

        // Load products from SQLite for user
        loadDoctors();

        // Logout button functionality
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(ConsultDoctor.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ConsultDoctor.this, MainActivity.class));
            finish();
        });
    }

   public void loadDoctors() {
        Cursor cursor = databaseHelper.getAllDoctors();
        DoctorAdapter adapter = new DoctorAdapter(this, cursor, false); // User mode
        listViewDoctors.setAdapter(adapter);
    }

}