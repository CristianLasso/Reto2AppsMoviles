package com.example.reto2appsmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ListActivity extends AppCompatActivity {

    private Button capture;
    private EditText pokemonCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        capture = findViewById(R.id.capture);
        pokemonCapture = findViewById(R.id.pokemonCapure);

        capture.setOnClickListener(
                (v) -> {
                    String poke = pokemonCapture.getText().toString();
                }
        );

    }
}