package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView fullNameTextView, emailTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullNameTextView = findViewById(R.id.fullName);
        emailTextView = findViewById(R.id.email);
        logoutButton = findViewById(R.id.logoutButton);

        // Get the current user from FirebaseAuth
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Fetch user information
            String fullName = user.getDisplayName(); // Display name (can be null if not set)
            String email = user.getEmail(); // Email

            // Display the data in the profile activity
            fullNameTextView.setText("Full Name: " + (fullName != null ? fullName : "No Name Available"));
            emailTextView.setText("Email: " + (email != null ? email : "Email not available"));
        } else {
            // No user is signed in
            Toast.makeText(this, "No user is signed in.", Toast.LENGTH_SHORT).show();
        }

        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Sign out from Firebase
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class); // Redirect to MainActivity (Login)
            startActivity(intent);
            finish(); // Close ProfileActivity
        });
    }
}