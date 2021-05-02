package com.example.reto2appsmoviles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter() {
        pokemons = new ArrayList<>();
    }


    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemonrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);
        return pokemonView;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        holder.getNombrePokemon().setText(pokemons.get(position).getName());

        //La imagen del pokemon
        String carpeta = "/storage/emulated/0/Android/data/com.example.reto2appsmoviles/files/Pictures/";
        Bitmap bitmap = BitmapFactory.decodeFile(carpeta + pokemons.get(position).getImg());
        holder.getImagenPokemon().setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


}
