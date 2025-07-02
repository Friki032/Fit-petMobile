package com.example.fitpet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitpet.R;
import com.example.fitpet.UserEntity;
import java.util.List;

public class AprobarVeterinariosAdapter extends RecyclerView.Adapter<AprobarVeterinariosAdapter.VetViewHolder> {
    private List<UserEntity> veterinarios;
    private OnAprobarClickListener listener;

    public interface OnAprobarClickListener {
        void onAprobarClick(UserEntity veterinario, int position);
    }

    public AprobarVeterinariosAdapter(List<UserEntity> veterinarios, OnAprobarClickListener listener) {
        this.veterinarios = veterinarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_veterinario_pendiente, parent, false);
        return new VetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VetViewHolder holder, int position) {
        UserEntity vet = veterinarios.get(position);
        holder.tvNombre.setText(vet.nombre);
        holder.tvCorreo.setText(vet.correo);

        holder.btnAprobar.setOnClickListener(view -> {
            if (listener != null) listener.onAprobarClick(vet, position);
        });
    }

    @Override
    public int getItemCount() {
        return veterinarios.size();
    }

    public void removeAt(int position) {
        veterinarios.remove(position);
        notifyItemRemoved(position);
    }

    static class VetViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo;
        Button btnAprobar;
        VetViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            btnAprobar = itemView.findViewById(R.id.btnAprobar);
        }
    }
}