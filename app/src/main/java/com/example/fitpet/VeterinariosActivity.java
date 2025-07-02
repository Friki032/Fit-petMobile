package com.example.fitpet;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VeterinariosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarios);

        RecyclerView recyclerVeterinarios = findViewById(R.id.recyclerVeterinarios);
        recyclerVeterinarios.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los veterinarios desde la base de datos
        List<UserEntity> veterinarios = FitPetDatabase.getInstance(this)
                .userDao()
                .obtenerVeterinarios(); // Este método debe existir en UserDao

        if (veterinarios == null || veterinarios.isEmpty()) {
            Toast.makeText(this, "No hay veterinarios registrados.", Toast.LENGTH_SHORT).show();
        }

        // Usa un adapter para mostrar los veterinarios
        VeterinarioAdapter adapter = new VeterinarioAdapter(veterinarios);
        recyclerVeterinarios.setAdapter(adapter);
    }
}