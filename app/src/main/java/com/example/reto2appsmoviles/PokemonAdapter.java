package com.example.reto2appsmoviles;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> originalPokemons;
    private Activity activity;
    private String trainer;

    public PokemonAdapter(Activity activity, String trainer) {
        pokemons = new ArrayList<>();
        originalPokemons = new ArrayList<>();
        this.activity = activity;
        this.trainer = trainer;
    }


    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        originalPokemons.add(pokemon);
    }

    public void setPokemons(ArrayList<Pokemon> originalPokemons, ArrayList<Pokemon> pokemons){
        this.originalPokemons = originalPokemons;
        this.pokemons = pokemons;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
    public ArrayList<Pokemon> getOriginalPokemons() { return originalPokemons; }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemonrow, parent, false);
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

    public void filter(String search){
        if(search.length()==0){
            pokemons.clear();
            pokemons.addAll(originalPokemons);
        }else {
            List<Pokemon> collect = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                pokemons.clear();
                collect = originalPokemons.stream().filter(i -> i.getName().toLowerCase().contains(search)).collect(Collectors.toList());
                pokemons.addAll(collect);
            }

        }
    }


}
