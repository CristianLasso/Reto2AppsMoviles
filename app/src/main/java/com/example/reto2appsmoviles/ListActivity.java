package com.example.reto2appsmoviles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reto2appsmoviles.util.Constants;
import com.example.reto2appsmoviles.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    private Button capture;
    private EditText pokemonCapture;
    private PokemonAdapter adapter;
    private String trainer;
    private Pokemon poke;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        capture = findViewById(R.id.capture);
        pokemonCapture = findViewById(R.id.pokemonCapure);
        adapter = new PokemonAdapter();

        capture.setOnClickListener(
                (v) -> {
                    String poke = pokemonCapture.getText().toString();
                    addPokemon();
                    Toast.makeText(getBaseContext(), "Gotcha!", Toast.LENGTH_SHORT).show();
                }
        );

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addPokemon() {
        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
        Gson gson = new Gson();

        new Thread(
                () -> {
                    String response = https.GETrequest(Constants.POKEURL + pokemonCapture.getText().toString());
                    try {
                        JSONObject pokemon = new JSONObject(response);

                        String name = pokemon.getString("name");

                        JSONObject sprites = pokemon.getJSONObject("sprites");
                        String sprite = sprites.getString("front_default");

                        JSONArray types = pokemon.getJSONArray("types");
                        String type = "";
                        for(int i=0; i<types.length(); i++){
                            JSONObject typejson = types.getJSONObject(i);
                            JSONObject tipo = typejson.getJSONObject("type");
                            type = type + tipo.getString("name")  + ". ";
                        }

                        JSONArray stats = pokemon.getJSONArray("stats");
                        JSONObject hpJ = stats.getJSONObject(0);
                        String hp = hpJ.getString("base_stat");

                        JSONObject attackJ = stats.getJSONObject(1);
                        String attack = attackJ.getString("base_stat");

                        JSONObject defenseJ = stats.getJSONObject(2);
                        String defense = defenseJ.getString("base_stat");

                        JSONObject speedJ = stats.getJSONObject(5);
                        String speed = speedJ.getString("base_stat");

                        poke = new Pokemon(name, sprite, type, attack, defense, speed, hp);

                        trainer = getIntent().getExtras().getString("trainer");
                        Gson gson2 = new Gson();
                        String json = gson.toJson(poke);
                        HTTPSWebUtilDomi https2 = new HTTPSWebUtilDomi();

                        new Thread(
                                ()->{
                                    https2.PUTrequest(Constants.BASEURL+"trainers/" + trainer + "/" + poke.getName() + ".json", json );
                                }
                        ).start();

                        adapter.addPokemon(poke);

                        runOnUiThread(() -> adapter.notifyDataSetChanged());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
        ).start();

    }

}