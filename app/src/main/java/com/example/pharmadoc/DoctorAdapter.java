package com.example.pharmadoc;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorAdapter extends CursorAdapter {

    private DatabaseHelper databaseHelper;
    private boolean isAdmin; // Flag to check if user is admin

    public DoctorAdapter(Context context, Cursor cursor, boolean isAdmin) {
        super(context, cursor, 0);
        this.databaseHelper = new DatabaseHelper(context);
        this.isAdmin = isAdmin; // Set user role
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Load different layouts for admin and user
        if (isAdmin) {
            return inflater.inflate(R.layout.list_item_doctor, parent, false);  // Admin layout
        } else {
            return inflater.inflate(R.layout.list_item_doctor_user, parent, false);  // User layout
        }
    }

    @Override

    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.text1);
        TextView specializationTextView = view.findViewById(R.id.text2);
        TextView contactTextView = view.findViewById(R.id.text3);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DOCTOR_NAME));
        String specialization = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DOCTOR_SPECIALIZATION));
        String contact = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_DOCTOR_CONTACT));

        nameTextView.setText(name);
        specializationTextView.setText("Specialization: " + specialization);
        contactTextView.setText("Contact: " + contact);

        // Show delete button only for admin
        if (isAdmin) {
            Button deleteButton = view.findViewById(R.id.button_delete);
            final int doctorId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));

            deleteButton.setVisibility(View.VISIBLE); // Ensure it's visible
            deleteButton.setOnClickListener(v -> {
                boolean isDeleted = databaseHelper.deleteDoctorById(doctorId);
                if (isDeleted) {
                    Toast.makeText(context, "Doctor deleted successfully", Toast.LENGTH_SHORT).show();
                    ((ViewDoctorActivity) context).loadDoctors();  // Refresh the list
                } else {
                    Toast.makeText(context, "Failed to delete doctor", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
