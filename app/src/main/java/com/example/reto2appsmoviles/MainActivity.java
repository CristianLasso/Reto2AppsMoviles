package com.example.reto2appsmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.widget.Button;
import android.widget.EditText;

import com.example.reto2appsmoviles.util.Constants;
import com.example.reto2appsmoviles.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);

        login.setOnClickListener(
                (v) -> {
                    String trainer = name.getText().toString();
                    Gson gson = new Gson();
                    String json = gson.toJson(trainer);
                    HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
                    new Thread(
                            ()->{
                                https.PUTrequest(Constants.BASEURL+"trainers/"+ trainer +".json", json );
                            }
                    ).start();
                    Intent intent = new Intent(this, ListActivity.class);
                    startActivity(intent);
                }
        );

    }

}