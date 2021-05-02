package com.example.reto2appsmoviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    private Button pokemonFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        pokemonFree = findViewById(R.id.pokemonFree);

        pokemonFree.setOnClickListener(
                (v) -> {
                    alerta();
                }
        );

    }

    public void alerta(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Espera!");
        alert.setMessage("Si liberas este pokemon ya no lo volveras a ver :(");
        alert.setCancelable(false);
        alert.setPositiveButton("Liberar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                liberar();
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.setCancelable(true);
            }
        });

        alert.show();
    }

    public void liberar(){
        Toast.makeText(getBaseContext(), "Tu pokemon ha sido liberado...", Toast.LENGTH_SHORT).show();
        finish();
    }
}