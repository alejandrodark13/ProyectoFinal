package com.example.alejandro.proyectofinal.BasesDeDatos;

public class Usuario {
        int id;
        public String Titulo;
        public String Descripcion;
        public  String TareaoNota;
        public  String Fecha;
        public  String Hora;

    public Usuario(int id, String titulo, String descripcion, String tareaoNota, String fecha, String hora) {
        this.id = id;
        Titulo = titulo;
        Descripcion = descripcion;
        TareaoNota = tareaoNota;
        Fecha = fecha;
        Hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTareaoNota() {
        return TareaoNota;
    }

    public void setTareaoNota(String tareaoNota) {
        TareaoNota = tareaoNota;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}
