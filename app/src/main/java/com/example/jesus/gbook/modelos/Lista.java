package com.example.jesus.gbook.modelos;

public class Lista {
    private int idLista ;
    private String nombre = "" ;

    public Lista(int id) { this.idLista = id ; }


    public Lista(int id, String name) {
        idLista   = id ;
        nombre    = name ;

    }


    public int getIdLista() {
        return idLista;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

