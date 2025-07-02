package com.example.fitpet;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RutinaDetalleActivity extends AppCompatActivity {
    private TextView txtTitulo;
    private TextView txtDescripcion;
    private ImageView imgTitulo;
    private RecyclerView rvEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_detalle);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        imgTitulo = findViewById(R.id.imgTitulo);
        rvEjercicios = findViewById(R.id.rvEjercicios);

        String rutinaId = getIntent().getStringExtra("archivoRutina");
        Rutina rutina = RutinaData.rutinas.get(rutinaId);

        if (rutina != null) {
            txtTitulo.setText(rutina.nombre);
            txtDescripcion.setText(rutina.descripcion);
            imgTitulo.setImageResource(rutina.imagenResId);

            if (rutina.ejercicios != null && !rutina.ejercicios.isEmpty()) {
                EjercicioAdapter adapter = new EjercicioAdapter(this, rutina.ejercicios);
                rvEjercicios.setLayoutManager(new LinearLayoutManager(this));
                rvEjercicios.setAdapter(adapter);
            }
        } else {
            txtTitulo.setText("Rutina no encontrada");
        }
    }
}