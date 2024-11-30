package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    // Firebase Authentication instance
    private FirebaseAuth mAuth;

    // Regex patterns for validation
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private static final String MOBILE_PATTERN = "^\\d{10}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth

        EditText etUsername = findViewById(R.id.et_register_username);
        EditText etEmail = findViewById(R.id.et_register_email);
        EditText etPassword = findViewById(R.id.et_register_password);
        EditText etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        EditText etMobile = findViewById(R.id.et_register_mobile);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            String mobile = etMobile.getText().toString();

            if (validateForm(username, email, password, confirmPassword, mobile)) {
                // If form is valid, create a new user with Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // User registered successfully
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    sendEmailVerification(user);  // Send email verification
                                }
                            } else {
                                // If registration fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Send email verification
    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        // Redirect to login screen or ask the user to verify their email
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Function to validate form fields
    private boolean validateForm(String username, String email, String password, String confirmPassword, String mobile) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobile.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(USERNAME_PATTERN, username)) {
            Toast.makeText(RegisterActivity.this, "Invalid username: Must be at least 3 characters, alphanumeric", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }



        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(MOBILE_PATTERN, mobile)) {
            Toast.makeText(RegisterActivity.this, "Invalid mobile number: Must be 10 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // All validations passed
    }
}