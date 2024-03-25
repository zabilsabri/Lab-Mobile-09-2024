package com.example.tugasprak1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnoke;
    EditText editText;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnoke = (Button) findViewById(R.id.button_ok);
        editText = (EditText) findViewById(R.id.editTextt);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayy);

        btnoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = editText.getText().toString();
                if (!newText.isEmpty()) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(newText);
                    textView.setTextSize(18);
                    linearLayout.addView(textView);
                    editText.getText().clear();
                }
            }
        });
    }
}
