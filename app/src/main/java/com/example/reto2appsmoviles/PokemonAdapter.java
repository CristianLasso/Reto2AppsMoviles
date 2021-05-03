package com.example.reto2appsmoviles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter() {
        pokemons = new ArrayList<>();
    }


    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
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

        System.out.println(pokemons.get(position).getImg());

        //La imagen del pokemon
        /*
        try {
            InputStream srt = new java.net.URL(pokemons.get(position).getImg()).openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(srt);
            holder.getImagenPokemon().setImageBitmap(bitmap);
            System.out.println("SIIIIIIIIIIIIIIIIIIIIIIII");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FFFFFFFFFF");
        }*/

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


}
