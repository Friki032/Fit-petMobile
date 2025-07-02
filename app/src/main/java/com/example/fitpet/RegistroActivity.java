package com.example.fitpet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etTelefono = findViewById(R.id.etTelefono);
        EditText etPais = findViewById(R.id.etPais);
        EditText etCiudad = findViewById(R.id.etCiudad);
        EditText etDireccionLocal = findViewById(R.id.etDireccionLocal);

        RadioGroup rgRol = findViewById(R.id.rgRol);
        RadioButton rbUsuario = findViewById(R.id.rbUsuario);
        RadioButton rbVeterinario = findViewById(R.id.rbVeterinario);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        // Mostrar/ocultar dirección local según el rol seleccionado
        rgRol.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbVeterinario) {
                etDireccionLocal.setVisibility(View.VISIBLE);
            } else {
                etDireccionLocal.setVisibility(View.GONE);
                etDireccionLocal.setText(""); // Limpia el campo si se cambia a usuario
            }
        });

        // Inicializa correctamente el estado del campo al abrir la pantalla
        if (rbVeterinario.isChecked()) {
            etDireccionLocal.setVisibility(View.VISIBLE);
        } else {
            etDireccionLocal.setVisibility(View.GONE);
            etDireccionLocal.setText("");
        }

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString();
            String telefono = etTelefono.getText().toString().trim();
            String pais = etPais.getText().toString().trim();
            String ciudad = etCiudad.getText().toString().trim();
            String direccionLocal = etDireccionLocal.getText().toString().trim();
            String rol = rbUsuario.isChecked() ? "usuario" : "veterinario";
            boolean aprobado = rol.equals("usuario"); // Veterinario necesita aprobación

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() ||
                    telefono.isEmpty() || pais.isEmpty() || ciudad.isEmpty() ||
                    (rol.equals("veterinario") && direccionLocal.isEmpty())) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            FitPetDatabase db = FitPetDatabase.getInstance(this);
            if (db.userDao().buscarUsuarioPorCorreo(correo) != null) {
                Toast.makeText(this, "Ese correo ya está registrado", Toast.LENGTH_SHORT).show();
                return;
            }

            db.userDao().insertarUsuario(
                    new UserEntity(
                            nombre,
                            correo,
                            password,
                            telefono,
                            pais,
                            ciudad,
                            rol,
                            aprobado,
                            rol.equals("veterinario") ? direccionLocal : null
                    )
            );

            if (rol.equals("veterinario")) {
                Toast.makeText(this, "Registro enviado. Espera aprobación como veterinario.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}