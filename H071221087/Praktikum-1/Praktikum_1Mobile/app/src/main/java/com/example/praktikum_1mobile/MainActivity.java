package com.example.praktikum_1mobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edit_text);
        Button submitButton = findViewById(R.id.submit_button);
        TextView textView = findViewById(R.id.text_view);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String inputText = editText.getText().toString();
                String input = textView.getText().toString();
                textView.setText(inputText);
                if (input.isEmpty()){
                    textView.setText(inputText);
                }else {
                    String katabaru = input + "\n" + inputText;
                    textView.setText(katabaru);
                }
                editText.setText("");
            }
        });
    }
}