package com.example.fitpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Button btnAgregarAdmin = findViewById(R.id.btnAgregarAdmin);
        btnAgregarAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarAdminActivity.class);
            startActivity(intent);
        });

        Button btnRegistrarTienda = findViewById(R.id.btnRegistrarTienda);
        btnRegistrarTienda.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistrarTiendaActivity.class);
            startActivity(intent);
        });

        Button btnAprobarVeterinarios = findViewById(R.id.btnAprobarVeterinarios);
        btnAprobarVeterinarios.setOnClickListener(v -> {
            Intent intent = new Intent(this, AprobarVeterinariosActivity.class);
            startActivity(intent);
        });

        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
            prefs.edit().clear().apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}