package com.example.prak2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private EditText title;
    private EditText noted;
    private String nameValue;
    private String usernameValue;
    private String imageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi variabel dengan menggunakan findViewById()
        title = findViewById(R.id.titleNoted);
        noted = findViewById(R.id.containedNoted);
        Button btnSubmit = findViewById(R.id.btnToThirdActivity);

        // Tangkap data yang dikirim dari MainActivity
        Intent dataMain = getIntent();
        if (dataMain != null) {
            nameValue = dataMain.getStringExtra("NAME");
            usernameValue = dataMain.getStringExtra("USERNAME");
            imageValue = dataMain.getStringExtra("IMAGE_URI");
        }

        // Menambahkan OnClickListener ke button
        btnSubmit.setOnClickListener(view -> {
            String titleNoted = title.getText().toString();
            String containedNoted = noted.getText().toString();

            if (titleNoted.isEmpty() && containedNoted.isEmpty()) {
                Toast.makeText(this,"Please set your noted",Toast.LENGTH_SHORT).show();
                return;
            }

            if (titleNoted.isEmpty()) {
                Toast.makeText(this,"Please set your title noted",Toast.LENGTH_SHORT).show();
                return;
            }

            if (containedNoted.isEmpty()) {
                Toast.makeText(this,"Please set your content noted",Toast.LENGTH_SHORT).show();
                return;
            }

            // Buat Intent untuk memulai ResultActivity
            Intent resultIntent = new Intent(this, ResultActivity.class);

            // Menambahkan data yang akan dikirim ke ResultActivity
            resultIntent.putExtra("TITLE", titleNoted);
            resultIntent.putExtra("NOTED", containedNoted);
            resultIntent.putExtra("NAME", nameValue);
            resultIntent.putExtra("USERNAME", usernameValue);
            resultIntent.putExtra("IMAGE_URI", imageValue);

            // Memulai ResultActivity
            startActivity(resultIntent);
        });
    }
}


