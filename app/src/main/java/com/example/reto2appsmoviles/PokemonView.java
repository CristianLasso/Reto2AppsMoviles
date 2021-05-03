package com.example.reto2appsmoviles;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonView extends RecyclerView.ViewHolder{

    private ConstraintLayout root;
    private TextView nombrePokemon;
    private ImageView imagenPokemon;


    public PokemonView(ConstraintLayout root) {
        super(root);
        nombrePokemon = root.findViewById(R.id.nombrepokemon);
        imagenPokemon = root.findViewById(R.id.imagenpokemon);

    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public TextView getNombrePokemon() {
        return nombrePokemon;
    }

    public ImageView getImagenPokemon() {
        return imagenPokemon;
    }
}
