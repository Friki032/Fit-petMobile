package com.example.fitpet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.ViewHolder> {

    private final List<Rutina> lista;
    private final Context context;

    public RutinaAdapter(Context context, List<Rutina> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public RutinaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_rutina, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaAdapter.ViewHolder holder, int position) {
        Rutina rutina = lista.get(position);
        holder.txtNombre.setText(rutina.nombre);
        holder.imgRutina.setImageResource(rutina.imagenResId);
        holder.txtDificultad.setText("Nivel: " + rutina.dificultad);
        holder.txtDuracion.setText("Duración: " + rutina.duracionMin + " min");
        holder.txtResumenEjercicios.setText(rutina.ejercicios.size() + " ejercicios");

        holder.btnVerDetalles.setOnClickListener(view -> {
            Intent intent = new Intent(context, RutinaDetalleActivity.class);
            intent.putExtra("archivoRutina", getRutinaKey(rutina));
            context.startActivity(intent);
        });
    }

    // Encuentra el key de la rutina (según tu estructura, puedes mejorarlo)
    private String getRutinaKey(Rutina rutina) {
        for (Map.Entry<String, Rutina> entry : RutinaData.rutinas.entrySet()) {
            if (entry.getValue() == rutina || entry.getValue().equals(rutina)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return lista.size();
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