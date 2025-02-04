package com.example.pharmadoc;



import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductAdapter extends CursorAdapter {

    private boolean isAdmin;

    public ProductAdapter(Context context, Cursor cursor, boolean isAdmin) {
        super(context, cursor, 0);
        this.isAdmin = isAdmin;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Load different layouts for admin and user
        if (isAdmin) {
            return inflater.inflate(R.layout.list_item_product, parent, false);
        } else {
            return inflater.inflate(R.layout.list_item_product_user, parent, false);
        }
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.text_view_product_name);
        TextView priceTextView = view.findViewById(R.id.text_view_product_price);
        TextView quantityTextView = view.findViewById(R.id.text_view_product_quantity);
        ImageView productImageView = view.findViewById(R.id.image_view_product);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_NAME));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_PRICE));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_QUANTITY));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PRODUCT_IMAGE_URI));

        nameTextView.setText(name);
        priceTextView.setText("Price: tk" + price);
        quantityTextView.setText("Quantity: " + quantity);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        productImageView.setImageBitmap(bitmap);

        // Only add Order button for users
        if (!isAdmin) {
            Button orderButton = view.findViewById(R.id.button_order);
            orderButton.setOnClickListener(v -> {
                Toast.makeText(context, "Ordered: " + name, Toast.LENGTH_SHORT).show();
            });
        }
    }
}

