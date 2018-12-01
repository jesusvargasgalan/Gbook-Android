package com.example.jesus.gbook.modelos;

import java.io.Serializable;

public class Videojuego extends UserId implements Serializable {

    private String nombre;
    private String genero;
    private String plataforma;
    private String sinopsis;
    private String finalizado;


    public Videojuego(String nombre){

    }
    public Videojuego(String nombre, String genero,String plataforma, String sinopsis, String finalizado) {

        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.sinopsis = sinopsis;
        this.finalizado = finalizado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }
}
