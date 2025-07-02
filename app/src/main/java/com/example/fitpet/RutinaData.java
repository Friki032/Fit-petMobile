package com.example.fitpet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RutinaData {
    public static Map<String, Rutina> rutinas = new HashMap<>();

    static {
        rutinas.put("perro_cardio_basico", new Rutina(
                "Cardio en casa (Perros)",
                "Rutina divertida para perros llenos de energía dentro del hogar.",
                "Fácil",
                15,
                R.drawable.cardio,
                Arrays.asList(
                        new Ejercicio("Perseguir la pelota", "Lanza una pelota y anima a tu mascota a traerla varias veces.", 5, 0, R.drawable.pelota),
                        new Ejercicio("Saltos sobre cojines", "Coloca cojines en el suelo y anima a tu mascota a saltar sobre ellos.", 10, 0, R.drawable.salto),
                        new Ejercicio("Buscar premios", "Esconde pequeñas golosinas y deja que tu mascota las encuentre usando su olfato.", 0, 180, R.drawable.busca)
                )
        ));
        rutinas.put("perro_relajacion", new Rutina(
                "Relajación y masaje (Perros)",
                "Ejercicios suaves para que tu perro se relaje después de jugar.",
                "Básico",
                10,
                R.drawable.relajacion,
                Arrays.asList(
                        new Ejercicio("Estiramiento suave", "Ayuda a tu mascota a estirar sus patas delanteras y traseras con movimientos cuidadosos.", 0, 60, R.drawable.estiramiento),
                        new Ejercicio("Masaje en la barriga", "Haz masajes circulares suaves en la barriga y el lomo de tu mascota.", 0, 120, R.drawable.masaje)
                )
        ));
        rutinas.put("gato_juego_olfato", new Rutina(
                "Juegos de olfato (Gatos)",
                "Estimula la mente y el cuerpo de tu gato con juegos de búsqueda.",
                "Intermedio",
                12,
                R.drawable.olfato,
                Arrays.asList(
                        new Ejercicio("Encuentra la croqueta", "Esconde croquetas bajo tazas o cajas pequeñas y deja que tu mascota las descubra.", 0, 120, R.drawable.croqueta),
                        new Ejercicio("Caza el láser", "Mueve un puntero láser por el suelo para motivar a tu mascota a perseguirlo.", 0, 90, R.drawable.laser),
                        new Ejercicio("Atrapa la cuerda", "Juega con una cuerda o cinta y deja que tu mascota la atrape.", 7, 0, R.drawable.cuerda)
                )
        ));
        rutinas.put("gato_agilidad", new Rutina(
                "Circuito de agilidad (Gatos)",
                "Rutina para mejorar la agilidad y reflejos de tu gato.",
                "Avanzado",
                18,
                R.drawable.agilidad,
                Arrays.asList(
                        new Ejercicio("Saltos a la caja", "Coloca una caja y motiva a tu mascota a saltar dentro y fuera varias veces.", 6, 0, R.drawable.caja),
                        new Ejercicio("Sube y baja estantes", "Guía a tu mascota a subir y bajar por diferentes estantes o muebles seguros.", 0, 120, R.drawable.estantes),
                        new Ejercicio("Atrapa el plumero", "Haz movimientos rápidos con un plumero y deja que tu mascota lo alcance.", 8, 0, R.drawable.plumero)
                )
        ));
        // Puedes seguir agregando más rutinas para perros y gatos...
    }
}