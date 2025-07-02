package com.example.fitpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fitpet.R;
import java.util.List;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.CarruselViewHolder> {

    private final Context context;
    private final List<String> urls;

    public CarruselAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public CarruselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_carrusel, parent, false);
        return new CarruselViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarruselViewHolder holder, int position) {
        int actualPosition = position % urls.size();
        Glide.with(context)
                .load(urls.get(actualPosition))
                .placeholder(R.drawable.rounded_img)
                .error(R.drawable.rounded_img)
                .into(holder.imgCarrusel);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; // Para que nunca se acabe
    }

    static class CarruselViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCarrusel;
        CarruselViewHolder(View itemView) {
            super(itemView);
            imgCarrusel = itemView.findViewById(R.id.imgCarrusel);
        }
    }
}