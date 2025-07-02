package com.example.fitpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final List<String> CARRUSEL_URLS = Arrays.asList(
            "https://images.unsplash.com/photo-1518717758536-85ae29035b6d",
            "https://images.unsplash.com/photo-1558788353-f76d92427f16",
            "https://images.unsplash.com/photo-1543852786-1cf6624b9987?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1568393691622-c7ba131d63b4?q=80&w=1931&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1509205477838-a534e43a849f?q=80&w=2078&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    );

    private int tutorialStep = 0;
    private FrameLayout tutorialOverlay;
    private ImageView tutorialArrow;
    private TextView tutorialText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NO redirigir nunca a AdminMenuActivity aquí (ni a ningún menú especial)
        // NO borrar sesión aquí tampoco

        // Carrusel de imágenes
        RecyclerView recycler = findViewById(R.id.recyclerCarrusel);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycler.setAdapter(new CarruselAdapter(this, CARRUSEL_URLS));

        // Botones principales
        LinearLayout btnRutinas = findViewById(R.id.btnRutinas);
        LinearLayout btnVeterinarios = findViewById(R.id.btnVeterinarios);
        LinearLayout btnTienda = findViewById(R.id.btnTienda);

        btnRutinas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
            startActivity(intent);
        });

        btnVeterinarios.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VeterinariosActivity.class);
            startActivity(intent);
        });

        btnTienda.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogoTiendaActivity.class);
            startActivity(intent);
        });

        // Login/registro/bienvenida/cerrar sesión
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvBienvenido = findViewById(R.id.tvBienvenido);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Controlar visibilidad según sesión
        actualizarVistaSesion(btnLogin, btnRegister, tvBienvenido, btnCerrarSesion);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        btnCerrarSesion.setOnClickListener(v -> {
            new android.app.AlertDialog.Builder(this)
                    .setTitle("Cerrar sesión")
                    .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        getSharedPreferences("sesion", MODE_PRIVATE)
                                .edit()
                                .clear()
                                .apply();
                        // Recarga la actividad para actualizar los botones y mensaje
                        recreate();
                        android.widget.Toast.makeText(this, "Sesión cerrada", android.widget.Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // Botón Empezar
        Button btnEmpezar = findViewById(R.id.btnEmpezar);
        btnEmpezar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
            startActivity(intent);
        });

        // Tutorial overlay
        tutorialOverlay = findViewById(R.id.tutorialOverlay);
        tutorialArrow = findViewById(R.id.tutorialArrow);
        tutorialText = findViewById(R.id.tutorialText);

        SharedPreferences tutorialPrefs = getSharedPreferences("tutorial", MODE_PRIVATE);
        boolean tutorialVisto = tutorialPrefs.getBoolean("tutorial_main_visto", false);

        if (!tutorialVisto) {
            findViewById(R.id.rootFrame).post(() -> mostrarTutorialPaso(0));
            tutorialOverlay.setOnClickListener(v -> {
                tutorialStep++;
                mostrarTutorialPaso(tutorialStep);
            });
        }
    }

    private void actualizarVistaSesion(Button btnLogin, Button btnRegister, TextView tvBienvenido, Button btnCerrarSesion) {
        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        String nombre = prefs.getString("nombre", null);

        if (nombre != null) {
            btnLogin.setVisibility(View.GONE);
            btnRegister.setVisibility(View.GONE);
            tvBienvenido.setText("¡Bienvenido " + nombre + "!");
            tvBienvenido.setVisibility(View.VISIBLE);
            btnCerrarSesion.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            tvBienvenido.setVisibility(View.GONE);
            btnCerrarSesion.setVisibility(View.GONE);
        }
    }

    private void mostrarTutorialPaso(int paso) {
        // Elementos clave de la UI para el tutorial
        View[] vistasObjetivo = new View[]{
                findViewById(R.id.recyclerCarrusel),      // Carrusel
                findViewById(R.id.btnRutinas),            // Rutinas
                findViewById(R.id.btnVeterinarios),       // Veterinarios
                findViewById(R.id.btnTienda),             // Tienda
                findViewById(R.id.btnLogin),              // Login
                findViewById(R.id.btnRegister),           // Registro
                findViewById(R.id.btnEmpezar),            // Empezar
                findViewById(R.id.tvTestimonios)          // Testimonios
        };
        String[] textosTutorial = new String[]{
                "Aquí puedes ver las fotos\ny momentos FitPet.",
                "Explora Rutinas Saludables\npara tu mascota.",
                "Consulta o agenda con\nveterinarios certificados.",
                "Visita la tienda de mascotas\npara productos de calidad.",
                "Inicia sesión para\npersonalizar tu experiencia.",
                "Regístrate si aún\nno tienes cuenta.",
                "¡Presiona aquí para comenzar!",
                "Aquí puedes leer los testimonios\nde otros usuarios."
        };

        if (paso < vistasObjetivo.length) {
            mostrarOverlaySobre(vistasObjetivo[paso], textosTutorial[paso]);
        } else {
            tutorialOverlay.setVisibility(View.GONE);
            SharedPreferences tutorialPrefs = getSharedPreferences("tutorial", MODE_PRIVATE);
            tutorialPrefs.edit().putBoolean("tutorial_main_visto", true).apply();
        }
    }

    private void mostrarOverlaySobre(View objetivo, String texto) {
        if (objetivo == null || objetivo.getVisibility() != View.VISIBLE) {
            // Si la vista no existe o está oculta, pasa al siguiente paso
            tutorialStep++;
            mostrarTutorialPaso(tutorialStep);
            return;
        }
        objetivo.post(() -> {
            int[] location = new int[2];
            objetivo.getLocationOnScreen(location);
            int viewX = location[0];
            int viewY = location[1];

            // Posición del overlay
            int[] overlayLoc = new int[2];
            tutorialOverlay.getLocationOnScreen(overlayLoc);

            // Centra la flecha encima del objetivo y el texto debajo
            float arrowX = viewX + objetivo.getWidth() / 2f - tutorialArrow.getWidth() / 2f - overlayLoc[0];
            float arrowY = viewY - tutorialArrow.getHeight() - 20 - overlayLoc[1];
            float textX = viewX + objetivo.getWidth() / 2f - tutorialText.getWidth() / 2f - overlayLoc[0];
            float textY = viewY + objetivo.getHeight() + 20 - overlayLoc[1];

            tutorialArrow.setX(arrowX);
            tutorialArrow.setY(arrowY);
            tutorialArrow.setVisibility(View.VISIBLE);
            tutorialText.setText(texto);
            tutorialText.setX(textX);
            tutorialText.setY(textY);
            tutorialText.setVisibility(View.VISIBLE);
            tutorialOverlay.setVisibility(View.VISIBLE);
        });
    }

    // Actualiza la vista de sesión al volver a MainActivity
    @Override
    protected void onResume() {
        super.onResume();
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvBienvenido = findViewById(R.id.tvBienvenido);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        actualizarVistaSesion(btnLogin, btnRegister, tvBienvenido, btnCerrarSesion);
    }
}