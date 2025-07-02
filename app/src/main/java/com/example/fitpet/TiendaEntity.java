package com.example.fitpet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tiendas")
public class TiendaEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String descripcion;
    public String direccion;
    public String telefono;
    public String imagenUrl;
}