package com.example.prak2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> galleryLauncher;

    private ImageView image;
    private EditText name;
    private EditText username;

    // Variabel untuk menyimpan URI foto yang dipilih
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi variabel dengan menggunakan findViewById()
        Button btnSubmit = findViewById(R.id.buttonSubmit);
        image = findViewById(R.id.profilImage);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                // Tangkap URI gambar yang dipilih oleh pengguna
                selectedImageUri = result.getData().getData();
                // Set gambar pada ImageView
                image.setImageURI(selectedImageUri);
            }
        });

        // Menambahkan OnClickListener ke ImageView
        image.setOnClickListener(view -> {
            // Buat Intent untuk membuka galeri
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Mulai aktivitas galeri menggunakan launcher
            galleryLauncher.launch(galleryIntent);
        });

        // Menambahkan OnClickListener ke button
        btnSubmit.setOnClickListener(view -> {
            // Ambil nilai teks dari EditText
            String nameValue = name.getText().toString();
            String usernameValue = username.getText().toString();

            if (selectedImageUri == null && nameValue.isEmpty() && usernameValue.isEmpty()) {
                Toast.makeText(this, "Please set your profile", Toast.LENGTH_SHORT).show();
                return;
            }

            // Periksa apakah gambar telah dipilih
            if (selectedImageUri == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                return; // Keluar dari metode jika gambar belum dipilih
            }

            // Periksa apakah nama telah dimasukkan
            if (nameValue.isEmpty()) {
                Toast.makeText(this, "Please set your name", Toast.LENGTH_SHORT).show();
                return; // Keluar dari metode jika nama kosong
            }

            if (usernameValue.isEmpty()) {
                Toast.makeText(this,"Please set your username", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buat Intent untuk memulai SecondActivity
            Intent intent = new Intent(this, SecondActivity.class);

            // Masukkan data ke Intent
            intent.putExtra("NAME", nameValue);
            intent.putExtra("USERNAME", usernameValue);
            intent.putExtra("IMAGE_URI", selectedImageUri.toString());

            // Mulai SecondActivity
            startActivity(intent);
        });
    }
}

