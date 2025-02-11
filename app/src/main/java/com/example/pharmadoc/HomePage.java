package com.example.pharmadoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize UI elements
        greetingText = findViewById(R.id.greetingText);
        //ImageView profileIcon = findViewById(R.id.profileIcon);

        // Set Profile Icon Click Listener
//        profileIcon.setOnClickListener(v -> {
//            Intent intent = new Intent(HomePage.this, ProfileActivity.class);
//            startActivity(intent);
//        });

        // Fetch and display user name
        updateGreetingText();

        // Set Click Listeners for Cards
        findViewById(R.id.orderMedicineCard).setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, HomePage2.class);
            startActivity(intent);
        });

        findViewById(R.id.consultDoctorsCard).setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, ConsultDoctor.class);
            startActivity(intent);
        });
    }

    private void updateGreetingText() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userName = user.getDisplayName();
            if (userName != null && !userName.isEmpty()) {
                greetingText.setText("Welcome " + userName + ",");
            } else {
                greetingText.setText("Welcome User,");
            }
        } else {
            greetingText.setText("Welcome User,");
        }
    }

    // Inflate the menu (adds items to the action bar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu); // Make sure the menu is inflated
        return true;
    }

    // Handle item clicks in the action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profile_icon) {
            // If the profile menu item is clicked, navigate to ProfileActivity
            Intent intent = new Intent(HomePage.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        } else {
            // Logout user
            auth.signOut();
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
    }

    // Handle the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}