package com.example.fitpet;

import java.util.List;

public class Rutina {
    public String nombre;
    public String descripcion;
    public String dificultad;
    public int duracionMin;
    public int imagenResId;
    public List<Ejercicio> ejercicios;

    public Rutina(String nombre, String descripcion, String dificultad, int duracionMin, int imagenResId, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.duracionMin = duracionMin;
        this.imagenResId = imagenResId;
        this.ejercicios = ejercicios;
    }
}