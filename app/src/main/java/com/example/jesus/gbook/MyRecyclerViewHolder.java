package com.example.jesus.gbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre;
    public TextView genero;
    public TextView sinopsis;
    public TextView finalizado;
    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        nombre = itemView.findViewById(R.id.textview_nombre);
       genero = itemView.findViewById(R.id.textview_genero);
        sinopsis = itemView.findViewById(R.id.textview_sinopsis);
        finalizado = itemView.findViewById(R.id.textview_finalizado);
    }
}
