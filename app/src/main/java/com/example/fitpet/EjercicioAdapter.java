package com.example.fitpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.ViewHolder> {

    private final List<Ejercicio> ejercicios;
    private final Context context;

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        this.context = context;
        this.ejercicios = ejercicios;
    }

    @NonNull
    @Override
    public EjercicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ejercicio, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioAdapter.ViewHolder holder, int position) {
        Ejercicio ejercicio = ejercicios.get(position);
        holder.txtNombre.setText(ejercicio.nombre);

        if (ejercicio.repeticiones > 0) {
            holder.txtDetalle.setText("Repeticiones: " + ejercicio.repeticiones);
        } else if (ejercicio.duracionSegundos > 0) {
            holder.txtDetalle.setText("Duración: " + ejercicio.duracionSegundos + " s");
        } else {
            holder.txtDetalle.setText("");
        }
        holder.txtInstrucciones.setText(ejercicio.instrucciones);

        if (ejercicio.imagenResId != 0) {
            holder.imgEjercicio.setImageResource(ejercicio.imagenResId);
        } else {
            holder.imgEjercicio.setImageResource(R.drawable.ic_launcher_foreground); // default
        }
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEjercicio;
        TextView txtNombre, txtDetalle, txtInstrucciones;

        ViewHolder(View itemView) {
            super(itemView);
            imgEjercicio = itemView.findViewById(R.id.imgEjercicio);
            txtNombre = itemView.findViewById(R.id.txtNombreEjercicio);
            txtDetalle = itemView.findViewById(R.id.txtDetalleEjercicio);
            txtInstrucciones = itemView.findViewById(R.id.txtInstruccionesEjercicio);
        }
    }
}
