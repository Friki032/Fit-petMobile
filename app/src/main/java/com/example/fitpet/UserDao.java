package com.example.fitpet;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertarUsuario(UserEntity usuario);

    @Query("SELECT * FROM users WHERE correo = :correo LIMIT 1")
    UserEntity buscarUsuarioPorCorreo(String correo);

    // Obtener veterinarios PENDIENTES (no aprobados)
    @Query("SELECT * FROM users WHERE rol = 'veterinario' AND aprobado = 0")
    List<UserEntity> obtenerVeterinariosPendientes();

    // Obtener veterinarios APROBADOS
    @Query("SELECT * FROM users WHERE rol = 'veterinario' AND aprobado = 1")
    List<UserEntity> obtenerVeterinariosAprobados();

    // Obtener todos los veterinarios (aprobados o no)
    @Query("SELECT * FROM users WHERE rol = 'veterinario'")
    List<UserEntity> obtenerVeterinarios();

    // Aprobar veterinario por id
    @Query("UPDATE users SET aprobado = 1 WHERE id = :id")
    void aprobarVeterinario(int id);

    @Update
    void updateUser(UserEntity usuario);

    // Obtener usuario por id
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    UserEntity obtenerUsuarioPorId(int id);

    // Obtener todos los usuarios (cualquier rol)
    @Query("SELECT * FROM users")
    List<UserEntity> obtenerTodosLosUsuarios();

    // Eliminar usuario por id
    @Query("DELETE FROM users WHERE id = :id")
    void eliminarUsuarioPorId(int id);
}