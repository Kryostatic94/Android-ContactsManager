package com.kryostatic.rubrica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContattiAdapter extends RecyclerView.Adapter<ContattiViewHolder>  {
    List<Contatti> contatti;

    public ContattiAdapter(List<Contatti> contatti) {
        this.contatti = contatti;
    }

    @NonNull
    @Override
    public ContattiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.contattomockup,parent,false);
        return new ContattiViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ContattiViewHolder holder, int position) {
        Contatti x = contatti.get(position);
        holder.setP(x);
    }

    @Override
    public int getItemCount() {
        return contatti.size();
    }
}
