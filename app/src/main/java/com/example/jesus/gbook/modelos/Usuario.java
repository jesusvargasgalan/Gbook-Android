package com.example.jesus.gbook.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    @Expose
    @SerializedName("idUsuario")
    private String idUsuario ;

    @Expose
    @SerializedName("usuario")
    private String usuario ;

    @Expose
    @SerializedName("contrasenia")
    private String contrasenia ;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario() { }
    public Usuario(String idUsuario, String usuario,
                   String contrasenia) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;

    }


}


