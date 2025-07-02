package com.example.fitpet;

public class Ejercicio {
    public String nombre;
    public String instrucciones;
    public int repeticiones;
    public int duracionSegundos;
    public int imagenResId;

    public Ejercicio(String nombre, String instrucciones, int repeticiones, int duracionSegundos, int imagenResId) {
        this.nombre = nombre;
        this.instrucciones = instrucciones;
        this.repeticiones = repeticiones;
        this.duracionSegundos = duracionSegundos;
        this.imagenResId = imagenResId;
    }
}