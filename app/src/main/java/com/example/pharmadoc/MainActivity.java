package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    // Regex patterns for validation
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,}$";  // Username: min 3 chars, alphanumeric, dots, dashes, underscores
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";  // Simple email pattern
     // Password: At least 6 characters, contains letters and numbers
    FirebaseAuth mAuth;
    ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);




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
                // Proceed with login if the form is valid
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                // Add logic to handle successful login (e.g., open new activity)
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



        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        return true; // All validations passed
    }
}