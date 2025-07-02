package com.example.fitpet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VeterinarioAdapter extends RecyclerView.Adapter<VeterinarioAdapter.VetViewHolder> {
    private final List<UserEntity> veterinarios;

    public VeterinarioAdapter(List<UserEntity> veterinarios) {
        this.veterinarios = veterinarios;
    }

    @NonNull
    @Override
    public VetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_veterinarios, parent, false); // Usa el layout correcto
        return new VetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VetViewHolder holder, int position) {
        UserEntity vet = veterinarios.get(position);
        holder.tvNombre.setText(vet.getNombre());
        holder.tvCorreo.setText(vet.getCorreo());
        holder.tvTelefono.setText("Teléfono: " + vet.getTelefono());
        holder.tvPais.setText("País: " + vet.getPais());
        holder.tvCiudad.setText("Ciudad: " + vet.getCiudad());
        holder.tvDireccionLocal.setText("Dirección: " + (vet.getDireccionLocal() == null ? "" : vet.getDireccionLocal()));
    }

    @Override
    public int getItemCount() {
        return veterinarios.size();
    }

    static class VetViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo, tvTelefono, tvPais, tvCiudad, tvDireccionLocal;
        VetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreVeterinario);
            tvCorreo = itemView.findViewById(R.id.tvCorreoVeterinario);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvPais = itemView.findViewById(R.id.tvPais);
            tvCiudad = itemView.findViewById(R.id.tvCiudad);
            tvDireccionLocal = itemView.findViewById(R.id.tvDireccionLocal);
        }
    }
}