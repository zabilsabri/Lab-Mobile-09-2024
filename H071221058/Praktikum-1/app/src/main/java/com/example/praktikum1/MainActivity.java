package com.example.praktikum1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button submitButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi UI elements
        editTextInput = findViewById(R.id.editTextInput);
        submitButton = findViewById(R.id.submitButton);
        listView = findViewById(R.id.listView);

        // Inisialisasi data list
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, dataList); // Menggunakan layout kustom
        listView.setAdapter(adapter);

        // Set listener untuk tombol Submit
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil teks dari EditText
                String inputText = editTextInput.getText().toString().trim();
                // Pastikan teks tidak kosong
                if (!inputText.isEmpty()) {
                    // Tambahkan teks ke dalam list
                    dataList.add(inputText);
                    // Update tampilan ListView
                    adapter.notifyDataSetChanged();
                    // Kosongkan EditText setelah menambahkan teks
                    editTextInput.setText("");
                }
            }
        });
    }
}
