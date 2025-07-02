package com.example.fitpet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarAdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_admin);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            FitPetDatabase db = FitPetDatabase.getInstance(this);
            if (db.userDao().buscarUsuarioPorCorreo(correo) != null) {
                Toast.makeText(this, "Ese correo ya existe", Toast.LENGTH_SHORT).show();
                return;
            }
            // Pasa todos los parámetros requeridos por el constructor UserEntity
            db.userDao().insertarUsuario(
                    new UserEntity(
                            nombre,
                            correo,
                            password,
                            "",      // teléfono
                            "",      // país
                            "",      // ciudad
                            "admin", // rol
                            true,    // aprobado
                            null     // direcciónLocal
                    )
            );
            Toast.makeText(this, "¡Admin creado!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}