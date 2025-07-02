package com.example.fitpet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Muestra el catálogo de rutinas usando las claves de RutinaData.
 * Al hacer click en una rutina, pasa la clave para mostrar el detalle real y los ejercicios.
 */
public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        RecyclerView recyclerView = findViewById(R.id.recyclerRutinas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Usar las claves del mapa de rutinas para evitar el problema de "Rutina no encontrada"
        List<String> rutinaKeys = new ArrayList<>(RutinaData.rutinas.keySet());
        RutinaAdapter adapter = new RutinaAdapter(this, rutinaKeys);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Adaptador para mostrar la lista de rutinas por clave, con botón de ver detalles.
     */
    public static class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.ViewHolder> {
        private final List<String> rutinaKeys;
        private final Context context;

        public RutinaAdapter(Context context, List<String> rutinaKeys) {
            this.context = context;
            this.rutinaKeys = rutinaKeys;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_rutina, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String rutinaKey = rutinaKeys.get(position);
            Rutina rutina = RutinaData.rutinas.get(rutinaKey);

            if (rutina == null) return; // Seguridad (no debería pasar)

            holder.txtNombre.setText(rutina.nombre);
            holder.imgRutina.setImageResource(rutina.imagenResId);
            holder.txtDificultad.setText("Nivel: " + rutina.dificultad);
            holder.txtDuracion.setText("Duración: " + rutina.duracionMin + " min");
            holder.txtResumenEjercicios.setText(rutina.ejercicios.size() + " ejercicios");

            holder.btnVerDetalles.setOnClickListener(view -> {
                Intent intent = new Intent(context, RutinaDetalleActivity.class);
                intent.putExtra("archivoRutina", rutinaKey); // PASA LA CLAVE
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return rutinaKeys.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgRutina;
            TextView txtNombre, txtDificultad, txtDuracion, txtResumenEjercicios;
            Button btnVerDetalles;

            ViewHolder(View itemView) {
                super(itemView);
                imgRutina = itemView.findViewById(R.id.imgRutina);
                txtNombre = itemView.findViewById(R.id.txtNombreRutina);
                txtDificultad = itemView.findViewById(R.id.txtDificultad);
                txtDuracion = itemView.findViewById(R.id.txtDuracion);
                txtResumenEjercicios = itemView.findViewById(R.id.txtResumenEjercicios);
                btnVerDetalles = itemView.findViewById(R.id.btnVerDetalles);
            }
        }
    }
}