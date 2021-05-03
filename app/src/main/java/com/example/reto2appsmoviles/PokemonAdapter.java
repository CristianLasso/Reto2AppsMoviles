package com.example.reto2appsmoviles;

import android.app.Activity;
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

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;
    private Activity activity;
    private String trainer;

    public PokemonAdapter(Activity activity, String trainer) {
        pokemons = new ArrayList<>();
        this.activity = activity;
        this.trainer = trainer;
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

        holder.setActivity(activity);

        holder.getNombrePokemon().setText(pokemons.get(position).getName());

        System.out.println(pokemons.get(position).getImg());

        //La imagen del pokemon
        String url = pokemons.get(position).getImg();
        Glide.with(activity).load(url).fitCenter().into(holder.getImagenPokemon());

        holder.setPokemon(pokemons.get(position));
        holder.setTrainer(trainer);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


}
