package com.example.jesus.gbook.modelos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jesus.gbook.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ListAdapter extends FirestoreRecyclerAdapter<Videojuego, ListAdapter.ListHolder> {


    public ListAdapter(@NonNull FirestoreRecyclerOptions<Videojuego> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListHolder holder, int position, @NonNull Videojuego model) {
        holder.textviewNombre.setText(model.getNombre());
        holder.textviewGenero.setText(model.getGenero());
        holder.textviewSinopsis.setText(model.getSinopsis());
        holder.textviewFinalizado.setText(model.getFinalizado());
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ListHolder(v);
    }



    class ListHolder extends RecyclerView.ViewHolder {
             TextView textviewNombre;
             TextView textviewGenero;
             TextView textviewSinopsis;
             TextView  textviewFinalizado;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            textviewNombre = itemView.findViewById(R.id.textview_nombre);
            textviewGenero = itemView.findViewById(R.id.textview_genero);
            textviewSinopsis = itemView.findViewById(R.id.textview_sinopsis);
            textviewFinalizado = itemView.findViewById(R.id.textview_finalizado);
        }
    }
}
