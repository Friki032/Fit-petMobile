package com.example.fitpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Consulta en background para evitar bloqueo del UI
            new Thread(() -> {
                FitPetDatabase db = FitPetDatabase.getInstance(this);
                UserEntity usuario = db.userDao().buscarUsuarioPorCorreo(correo);

                runOnUiThread(() -> {
                    if (usuario == null) {
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!usuario.getPassword().equals(password)) {
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!usuario.isAprobado()) {
                        Toast.makeText(this, "Tu cuenta aún no ha sido aprobada", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // SOLO aquí se guarda la sesión, después de login exitoso
                    SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
                    prefs.edit()
                            .putString("rol", usuario.getRol())
                            .putString("correo", usuario.getCorreo())
                            .putString("nombre", usuario.getNombre())
                            .apply();

                    Intent intent;
                    switch (usuario.getRol()) {
                        case "admin":
                            intent = new Intent(this, AdminMenuActivity.class);
                            break;
                        case "veterinario":
                        case "usuario":
                            intent = new Intent(this, MainActivity.class);
                            break;
                        default:
                            Toast.makeText(this, "Rol desconocido", Toast.LENGTH_SHORT).show();
                            return;
                    }

                    // Mensaje de bienvenida solo después de login
                    if ("veterinario".equals(usuario.getRol())) {
                        Toast.makeText(this, "Bienvenido, veterinario", Toast.LENGTH_SHORT).show();
                    } else if ("usuario".equals(usuario.getRol())) {
                        Toast.makeText(this, "Bienvenido, usuario", Toast.LENGTH_SHORT).show();
                    } else if ("admin".equals(usuario.getRol())) {
                        Toast.makeText(this, "Bienvenido, administrador", Toast.LENGTH_SHORT).show();
                    }

                    startActivity(intent);
                    finish();
                });
            }).start();
        });
    }
}