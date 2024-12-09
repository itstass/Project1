package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";  // Username: min 3 chars, alphanumeric, dots, dashes, underscores
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";  // Simple email pattern

    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views
        EditText etUsername = findViewById(R.id.email);
        EditText etPassword = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.loginButton);
        TextView btnRegister = findViewById(R.id.registerLink);
        progressBar = findViewById(R.id.progressBar);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Go to register activity on button click
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Login validation
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (validateForm(username, password)) {
                progressBar.setVisibility(View.VISIBLE);  // Show progress bar during login process

                // Proceed with login if the form is valid
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);  // Hide progress bar after task completion

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    // Navigate to the next activity if login is successful
                                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    // Function to validate form fields
    private boolean validateForm(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(EMAIL_PATTERN, username) && !Pattern.matches(USERNAME_PATTERN, username)) {
            Toast.makeText(MainActivity.this, "Please enter a valid email or username", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // All validations passed
    }
}
