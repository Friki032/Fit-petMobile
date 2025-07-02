package com.example.fitpet;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TiendaDao {
    @Insert
    void insertarTienda(TiendaEntity tienda);

    @Query("SELECT * FROM tiendas")
    List<TiendaEntity> obtenerTodasTiendas();
}