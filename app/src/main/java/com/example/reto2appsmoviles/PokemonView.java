package com.example.reto2appsmoviles;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonView extends RecyclerView.ViewHolder{

    private ConstraintLayout root;
    private TextView nombrePokemon;
    private ImageButton imagenPokemon;
    private Pokemon pokemon;

    Activity activity;

    public PokemonView(ConstraintLayout root) {
        super(root);
        nombrePokemon = root.findViewById(R.id.nombrepokemon);
        imagenPokemon = root.findViewById(R.id.imagenpokemon);

        imagenPokemon.setOnClickListener(
                (v) -> {
                    Intent intent = new Intent(activity, InfoActivity.class);
                    intent.putExtra("name",pokemon.getName());
                    intent.putExtra("img",pokemon.getImg());
                    intent.putExtra("type",pokemon.getType());
                    intent.putExtra("attack",pokemon.getAttack());
                    intent.putExtra("defense",pokemon.getDefense());
                    intent.putExtra("speed",pokemon.getSpeed());
                    intent.putExtra("hp",pokemon.getHp());
                    activity.startActivity(intent);
                }
        );

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

    public Pokemon getPokemon() { return pokemon; }

    public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }

    public void setActivity(Activity activity) { this.activity = activity; }
}
