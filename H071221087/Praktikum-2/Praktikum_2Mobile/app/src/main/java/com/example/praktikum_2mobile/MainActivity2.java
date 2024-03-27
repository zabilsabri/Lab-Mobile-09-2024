package com.example.praktikum_2mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.praktikum_2mobile.MainActivity3;
import com.example.praktikum_2mobile.R;

public class MainActivity2 extends AppCompatActivity {
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        saveBtn = findViewById(R.id.button2);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.editText3);
                EditText contentEditText = findViewById(R.id.editText4);
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                String nama = getIntent().getStringExtra("nama");
                String username = getIntent().getStringExtra("username");
                String imageUri = getIntent().getStringExtra("imageUri");

                if (titleEditText.getText().toString().trim().isEmpty()) {
                    titleEditText.setError("Field ini tidak boleh kosong");
                } else if (contentEditText.getText().toString().trim().isEmpty()) {
                    contentEditText.setError("Field ini tidak boleh kosong");
                } else {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("nama", nama);
                    intent.putExtra("username", username);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    intent.putExtra("imageUri", imageUri);
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
}