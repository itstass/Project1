package com.example.pharmadoc;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private EditText fullNameEditText, phoneNumberEditText, shippingAddressEditText, emailEditText, deliveryNoteEditText;
    private Spinner paymentMethodSpinner;
    private Button placeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize EditText and Spinner views
        fullNameEditText = findViewById(R.id.editTextFullName);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        emailEditText = findViewById(R.id.editTextEmail);
        deliveryNoteEditText = findViewById(R.id.editTextDeliveryNote);
        shippingAddressEditText = findViewById(R.id.editTextShippingAddress); // Address as EditText
        paymentMethodSpinner = findViewById(R.id.spinnerPaymentMethod);
        placeOrderButton = findViewById(R.id.buttonPlaceOrder);

        // Set up the Payment Method Spinner directly
        String[] paymentMethods = {"Select Payment Method", "Credit Card", "Bkash", "Cash on Delivery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        // Handle Place Order button click event
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String fullName = fullNameEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String deliveryNote = deliveryNoteEditText.getText().toString().trim();
                String shippingAddress = shippingAddressEditText.getText().toString().trim(); // Get address from EditText

                // Get selected payment method
                String selectedPaymentMethod = paymentMethodSpinner.getSelectedItem().toString();

                // Simple validation
                if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || shippingAddress.isEmpty() || selectedPaymentMethod.equals("Select Payment Method")) {
                    Toast.makeText(PaymentActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    // Order summary for user
                    String orderSummary = "Order placed successfully!\n" +
                            "Name: " + fullName + "\n" +
                            "Phone: " + phoneNumber + "\n" +
                            "Email: " + email + "\n" +
                            "Address: " + shippingAddress + "\n" +
                            "Payment Method: " + selectedPaymentMethod + "\n" +
                            "Delivery Note: " + deliveryNote;

                    Toast.makeText(PaymentActivity.this, orderSummary, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}