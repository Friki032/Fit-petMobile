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

public class VeterinariosPendientesAdapter extends RecyclerView.Adapter<VeterinariosPendientesAdapter.ViewHolder> {

    public interface OnAprobarClickListener {
        void onAprobarClick(UserEntity veterinario);
    }

    private List<UserEntity> veterinarios;
    private OnAprobarClickListener listener;

    public VeterinariosPendientesAdapter(List<UserEntity> veterinarios, OnAprobarClickListener listener) {
        this.veterinarios = veterinarios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veterinario_pendiente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEntity veterinario = veterinarios.get(position);
        holder.tvNombre.setText(veterinario.getNombre());
        holder.tvCorreo.setText(veterinario.getCorreo());
        holder.btnAprobar.setOnClickListener(v -> {
            if (listener != null) listener.onAprobarClick(veterinario);
        });
    }

    @Override
    public int getItemCount() {
        return veterinarios.size();
    }

    public void removeVeterinario(UserEntity veterinario) {
        int pos = veterinarios.indexOf(veterinario);
        if (pos != -1) {
            veterinarios.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo;
        Button btnAprobar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            btnAprobar = itemView.findViewById(R.id.btnAprobar);
        }
    }
}