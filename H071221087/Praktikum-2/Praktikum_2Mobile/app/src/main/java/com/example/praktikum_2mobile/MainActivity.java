package com.example.praktikum_2mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private boolean isImageChanged = false;
    private Uri selectedImageUri;

    private ActivityResultLauncher<Intent> openGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            selectedImageUri = data.getData();
                            if (selectedImageUri != null) {
                                ImageView imageView = findViewById(R.id.EditImage);
                                imageView.setImageURI(selectedImageUri);
                                isImageChanged = true;
                            }
                        }
                    }
                }
            }
    );
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        submitBtn = findViewById(R.id.button);

        ImageView image = findViewById(R.id.EditImage);
        image.setOnClickListener(this);

        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                EditText namaEditText = findViewById(R.id.EditText1);
                EditText usernameEditText = findViewById(R.id.EditText2);
                String nama = namaEditText.getText().toString();
                String username = usernameEditText.getText().toString();

                if (!isImageChanged) {
                    Toast.makeText(MainActivity.this, "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else if (namaEditText.getText().toString().trim().isEmpty()) {
                    namaEditText.setError("Field ini tidak boleh kosong");
                } else if (usernameEditText.getText().toString().trim().isEmpty()) {
                    usernameEditText.setError("Field ini tidak boleh kosong");
                } else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("username", username);
                    if (selectedImageUri != null) {
                        intent.putExtra("imageUri", selectedImageUri.toString());
                    }
                    startActivity(intent);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onClick (View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        openGallery.launch(Intent.createChooser(intent, "Pilih gambar"));
    }
}