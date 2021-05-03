package com.example.reto2appsmoviles;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Type;
import android.view.SurfaceControl;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reto2appsmoviles.util.Constants;
import com.example.reto2appsmoviles.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private Button login;
    private String trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE
        }, 11);


        login.setOnClickListener(
                (v) -> {
                    trainer = name.getText().toString();
                    Gson gson = new Gson();
                    String json = gson.toJson(trainer);
                    HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
                    new Thread(
                            ()->{
                                https.PUTrequest(Constants.BASEURL+ "trainers/" + trainer + ".json", json );
                            }
                    ).start();
                    Intent intent = new Intent(this, ListActivity.class);
                    intent.putExtra("trainer", trainer);
                    startActivity(intent);
                }
        );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 11){
            boolean allGrant = true;
            for(int i=0; i<grantResults.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allGrant = false;
                    break;
                }
            }
            if(allGrant) {
                Toast.makeText(getBaseContext(), "Todos los permisos concedidos", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(), "Alerta, no todos los permisos fueron concedidos", Toast.LENGTH_LONG).show();
            }

        }

    }

    public String getTrainer() {
        return trainer;
    }
}