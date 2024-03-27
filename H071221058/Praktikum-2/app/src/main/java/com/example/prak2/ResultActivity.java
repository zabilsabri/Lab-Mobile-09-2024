package com.example.prak2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi views
        ImageView img = findViewById(R.id.img);
        TextView name = findViewById(R.id.name);
        TextView username = findViewById(R.id.username);
        TextView title = findViewById(R.id.titleNoted);
        TextView noted = findViewById(R.id.containedNoted);

        // Tangkap data yang dikirimkan dari SecondActivity
        Intent result = getIntent();
        if (result != null) {

            // Ambil URI gambar dari intent
            String imageUriString = result.getStringExtra("IMAGE_URI");
            if (imageUriString != null) {
                // Konversi URI string menjadi objek Uri
                Uri imageUri = Uri.parse(imageUriString);
                // Set gambar ke ImageView
                img.setImageURI(imageUri);
            }

            // Tampilkan nama, username, title, dan noted
            name.setText(result.getStringExtra("NAME"));
            username.setText(result.getStringExtra("USERNAME"));
            title.setText(result.getStringExtra("TITLE"));
            noted.setText(result.getStringExtra("NOTED"));
        }
    }

}


