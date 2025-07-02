package com.example.fitpet;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitpet.FitPetDatabase;
import com.example.fitpet.R;
import com.example.fitpet.UserEntity;
import java.util.List;

public class AprobarVeterinariosActivity extends AppCompatActivity implements VeterinariosPendientesAdapter.OnAprobarClickListener {

    private VeterinariosPendientesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprobar_veterinarios);

        RecyclerView recyclerView = findViewById(R.id.recyclerVeterinarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarVeterinariosPendientes(recyclerView);
    }

    private void cargarVeterinariosPendientes(RecyclerView recyclerView) {
        FitPetDatabase db = FitPetDatabase.getInstance(this);
        List<UserEntity> lista = db.userDao().obtenerVeterinariosPendientes();

        adapter = new VeterinariosPendientesAdapter(lista, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAprobarClick(UserEntity veterinario) {
        veterinario.setAprobado(true);
        FitPetDatabase db = FitPetDatabase.getInstance(this);
        db.userDao().updateUser(veterinario);
        Toast.makeText(this, "Veterinario aprobado", Toast.LENGTH_SHORT).show();
        // Recarga la lista
        adapter.removeVeterinario(veterinario);
    }
}