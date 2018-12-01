package com.example.jesus.gbook.modelos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesus.gbook.R;

import org.w3c.dom.Text;

import java.util.List;

public class videojuegoAdapter extends RecyclerView.Adapter<videojuegoAdapter.ViewHolder> {

    public List<Videojuego> videojuegoList;
    public Context context;

    public videojuegoAdapter( Context context,List<Videojuego> videojuegoList){

         this.videojuegoList = videojuegoList;
         this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Videojuego videojuego = videojuegoList.get(i);

        viewHolder.nombreVideojuego.setText(videojuegoList.get(i).getNombre());
        viewHolder.generoVideojuego.setText(videojuegoList.get(i).getGenero());
        viewHolder.sinopsisVideojuego.setText(videojuegoList.get(i).getSinopsis());
        viewHolder.finalizadoVideojuego.setText(videojuegoList.get(i).getFinalizado());

        final String user_id = videojuegoList.get(i).userId;

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"user id" + user_id,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videojuegoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TextView nombreVideojuego;
        public TextView generoVideojuego;
        public TextView sinopsisVideojuego;
        public TextView finalizadoVideojuego;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            nombreVideojuego = mView.findViewById(R.id.textview_nombre);
            generoVideojuego = mView.findViewById(R.id.textview_genero);
            sinopsisVideojuego = mView.findViewById(R.id.textview_sinopsis);
            finalizadoVideojuego = mView.findViewById(R.id.textview_finalizado);
        }
    }
}
