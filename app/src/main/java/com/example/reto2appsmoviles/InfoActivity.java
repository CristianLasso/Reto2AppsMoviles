package com.example.reto2appsmoviles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.reto2appsmoviles.util.Constants;
import com.example.reto2appsmoviles.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;

public class InfoActivity extends AppCompatActivity {

    private Button pokemonFree;
    private TextView name;
    private ImageView img;
    private TextView type;
    private TextView attack;
    private TextView defense;
    private TextView speed;
    private TextView hp;
    private String trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.pokemonName);
        img = findViewById(R.id.pokemonImg);
        type = findViewById(R.id.pokemonType);
        attack = findViewById(R.id.pokemonAttack);
        defense = findViewById(R.id.pokemonDefense);
        speed = findViewById(R.id.pokemonSpeed);
        hp = findViewById(R.id.pokemonHp);

        name.setText(getIntent().getExtras().getString("name"));
        type.setText("Tipo: " + getIntent().getExtras().getString("type"));
        attack.setText("Ataque: " + getIntent().getExtras().getString("attack"));
        defense.setText("Defensa: " + getIntent().getExtras().getString("defense"));
        speed.setText("Velocidad: " + getIntent().getExtras().getString("speed"));
        hp.setText("Vida: " + getIntent().getExtras().getString("hp"));

        trainer = getIntent().getExtras().getString("url");

        String url = getIntent().getExtras().getString("img");
        Glide.with(this).load(url).fitCenter().into(img);

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
        System.out.println(Constants.BASEURL+ "trainers/" + trainer + "/" + name + ".json");

        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

        new Thread(
                ()->{
                    https.DELETErequest(Constants.BASEURL+ "trainers/" + trainer + "/" + name.getText().toString() + ".json");
                }
        ).start();

        Toast.makeText(getBaseContext(), "Tu pokemon ha sido liberado...", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK, null);

        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("trainer", trainer);
        startActivity(intent);

        finish();
    }
}