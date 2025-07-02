package com.example.fitpet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CatalogoTiendaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalago_tiendas);

        RecyclerView recyclerTiendas = findViewById(R.id.recyclerTienda);
        recyclerTiendas.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            FitPetDatabase db = FitPetDatabase.getInstance(this);
            List<TiendaEntity> tiendas = db.tiendaDao().obtenerTodasTiendas();

            runOnUiThread(() -> {
                TiendaAdapter adapter = new TiendaAdapter(this, tiendas);
                recyclerTiendas.setAdapter(adapter);
            });
        }).start();
    }
}
