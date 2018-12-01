package com.example.jesus.gbook;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jesus.gbook.modelos.Videojuego;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {

    MainActivity mainActivity;
    ArrayList<Videojuego> videojuegoArrayList;
    private Context contexto;
    private static int posicion;





    public MyRecyclerViewAdapter(MainActivity mainActivity, ArrayList<Videojuego> videojuegoArrayList) {
        this.mainActivity = mainActivity;
        this.videojuegoArrayList = videojuegoArrayList;

    }

    @NonNull

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);

        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, final int i) {

        holder.nombre.setText(videojuegoArrayList.get(i).getNombre());
        holder.genero.setText(videojuegoArrayList.get(i).getGenero());
        holder.plataforma.setText(videojuegoArrayList.get(i).getPlataforma());
        holder.sinopsis.setText(videojuegoArrayList.get(i).getSinopsis());
        holder.finalizado.setText(videojuegoArrayList.get(i).getFinalizado());


    }

    @Override
    public int getItemCount() {
        return videojuegoArrayList.size();
    }

    public static int getPosicion() {
        return posicion;
    }


    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView genero;
        public TextView plataforma;
        public TextView sinopsis;
        public TextView finalizado;
        public TextView detalle;


        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.textview_nombre);
            genero = itemView.findViewById(R.id.textview_genero);
            plataforma = itemView.findViewById(R.id.textview_plataforma);
            sinopsis = itemView.findViewById(R.id.textview_sinopsis);
            finalizado = itemView.findViewById(R.id.textview_finalizado);

            detalle = itemView.findViewById(R.id.textview_plataforma);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    posicion = getAdapterPosition();
                    return false;
                }
            });
        }

    }

}








