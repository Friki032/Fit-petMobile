package com.example.fitpet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.Nullable;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;
    public String correo;
    public String password;
    public String telefono;
    public String pais;
    public String ciudad;
    public String rol; // "usuario", "veterinario" o "admin"
    public boolean aprobado; // true si fue aprobado (solo relevante para veterinario/admin)
    @Nullable
    public String direccionLocal; // Solo para veterinario

    // Constructor completo
    public UserEntity(String nombre, String correo, String password, String telefono,
                      String pais, String ciudad, String rol, boolean aprobado, @Nullable String direccionLocal) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.pais = pais;
        this.ciudad = ciudad;
        this.rol = rol;
        this.aprobado = aprobado;
        this.direccionLocal = direccionLocal;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    @Nullable
    public String getDireccionLocal() { return direccionLocal; }
    public void setDireccionLocal(@Nullable String direccionLocal) { this.direccionLocal = direccionLocal; }
}