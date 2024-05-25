package com.example.l5_20180805.dto;

public class usuario{
    private String nombre;
    private String correo;
    private String contrasena;
    private String imagen;


    public usuario(String nombre, String correo, String contrasena, String imagen) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.imagen = imagen;

    }

    public usuario() {
        // Constructor vacío necesario para Firebase
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
