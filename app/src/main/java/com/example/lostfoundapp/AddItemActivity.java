package com.example.lostfoundapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    Spinner spinnerCategory;

    EditText etName,
            etPhone,
            etDescription,
            etDate,
            etLocation;

    RadioGroup radioGroupType;

    Button btnSave,
            btnUpload;

    ImageView imageView;

    Uri imageUri;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        spinnerCategory =
                findViewById(R.id.spinnerCategory);

        etName =
                findViewById(R.id.etName);

        etPhone =
                findViewById(R.id.etPhone);

        etDescription =
                findViewById(R.id.etDescription);

        etDate =
                findViewById(R.id.etDate);

        etLocation =
                findViewById(R.id.etLocation);

        radioGroupType =
                findViewById(R.id.radioGroupType);

        btnSave =
                findViewById(R.id.btnSave);

        btnUpload =
                findViewById(R.id.btnUpload);

        imageView =
                findViewById(R.id.imageView);

        dbHelper =
                new DBHelper(this);

        String[] categories = {
                "Electronics",
                "Pets",
                "Wallets",
                "Others"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                );

        spinnerCategory.setAdapter(adapter);

        btnUpload.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_PICK);

            intent.setType("image/*");

            startActivityForResult(intent, 1);
        });

        btnSave.setOnClickListener(v -> {

            int selectedId =
                    radioGroupType.getCheckedRadioButtonId();

            RadioButton radioButton =
                    findViewById(selectedId);

            String type =
                    radioButton.getText().toString();

            String name =
                    etName.getText().toString();

            String phone =
                    etPhone.getText().toString();

            String description =
                    etDescription.getText().toString();

            String date =
                    etDate.getText().toString();

            String location =
                    etLocation.getText().toString();

            String category =
                    spinnerCategory
                            .getSelectedItem()
                            .toString();

            String timestamp =
                    new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm",
                            Locale.getDefault()
                    ).format(new Date());

            boolean inserted =
                    dbHelper.insertItem(
                            type,
                            name,
                            phone,
                            description,
                            date,
                            location,
                            category,
                            timestamp
                    );

            if (inserted) {

                Toast.makeText(
                        this,
                        "Item Saved",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Save Failed",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == 1 &&
                resultCode == RESULT_OK &&
                data != null) {

            imageUri = data.getData();

            imageView.setImageURI(imageUri);
        }
    }
}