package com.example.fitpet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder> {

    private List<TiendaEntity> tiendaList;
    private Context context;

    public TiendaAdapter(Context context, List<TiendaEntity> tiendaList) {
        this.context = context;
        this.tiendaList = tiendaList;
    }

    @NonNull
    @Override
    public TiendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tienda, parent, false);
        return new TiendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaViewHolder holder, int position) {
        TiendaEntity tienda = tiendaList.get(position);
        holder.tvNombreTienda.setText(tienda.nombre);
        holder.tvDescripcionTienda.setText(tienda.descripcion);
        holder.tvDireccionTienda.setText(tienda.direccion);
        holder.tvTelefonoTienda.setText(tienda.telefono);

        // Carga la imagen desde almacenamiento interno o el recurso por defecto
        if (tienda.imagenUrl != null && !tienda.imagenUrl.isEmpty()) {
            Uri uri = Uri.parse(tienda.imagenUrl);
            File file = new File(uri.getPath());
            if (file.exists()) {
                // Usa BitmapFactory para forzar la carga correcta
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    holder.ivImagenTienda.setImageBitmap(bitmap);
                } else {
                    holder.ivImagenTienda.setImageResource(R.drawable.logofitpet);
                }
            } else {
                holder.ivImagenTienda.setImageResource(R.drawable.logofitpet);
            }
        } else {
            holder.ivImagenTienda.setImageResource(R.drawable.logofitpet);
        }
    }

    @Override
    public int getItemCount() {
        return tiendaList.size();
    }

    public void setTiendaList(List<TiendaEntity> tiendaList) {
        this.tiendaList = tiendaList;
        notifyDataSetChanged();
    }

    static class TiendaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagenTienda;
        TextView tvNombreTienda, tvDescripcionTienda, tvDireccionTienda, tvTelefonoTienda;

        public TiendaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenTienda = itemView.findViewById(R.id.ivImagenTienda);
            tvNombreTienda = itemView.findViewById(R.id.tvNombreTienda);
            tvDescripcionTienda = itemView.findViewById(R.id.tvDescripcionTienda);
            tvDireccionTienda = itemView.findViewById(R.id.tvDireccionTienda);
            tvTelefonoTienda = itemView.findViewById(R.id.tvTelefonoTienda);
        }
    }
}