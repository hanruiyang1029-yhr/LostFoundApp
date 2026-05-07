package com.example.lostfoundapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    TextView tvType, tvName, tvPhone,
            tvDescription, tvDate, tvLocation;

    Button btnRemove;

    DBHelper dbHelper;

    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        tvType = findViewById(R.id.tvDetailType);
        tvName = findViewById(R.id.tvDetailName);
        tvPhone = findViewById(R.id.tvDetailPhone);
        tvDescription = findViewById(R.id.tvDetailDescription);
        tvDate = findViewById(R.id.tvDetailDate);
        tvLocation = findViewById(R.id.tvDetailLocation);

        btnRemove = findViewById(R.id.btnRemove);

        dbHelper = new DBHelper(this);

        itemId = getIntent().getIntExtra("id", -1);

        tvType.setText(getIntent().getStringExtra("type"));
        tvName.setText("Name: " + getIntent().getStringExtra("name"));
        tvPhone.setText("Phone: " + getIntent().getStringExtra("phone"));
        tvDescription.setText("Description: " + getIntent().getStringExtra("description"));
        tvDate.setText("Date: " + getIntent().getStringExtra("date"));
        tvLocation.setText("Location: " + getIntent().getStringExtra("location"));

        btnRemove.setOnClickListener(v -> {

            dbHelper.deleteItem(itemId);

            Toast.makeText(this,
                    "Item Removed",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}