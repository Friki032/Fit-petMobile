package com.example.fitpet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RegistrarTiendaActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tienda);

        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        String rol = prefs.getString("rol", null);

        if (!"admin".equals(rol)) {
            Toast.makeText(this, "Solo el admin puede registrar tiendas", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        EditText etNombre = findViewById(R.id.etNombreTienda);
        EditText etDescripcion = findViewById(R.id.etDescripcionTienda);
        EditText etDireccion = findViewById(R.id.etDireccionTienda);
        EditText etTelefono = findViewById(R.id.etTelefonoTienda);
        ImageView ivLogoPreview = findViewById(R.id.ivLogoPreview);
        Button btnElegirLogo = findViewById(R.id.btnElegirLogo);
        Button btnRegistrar = findViewById(R.id.btnRegistrarTienda);

        btnElegirLogo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (imageUri == null) {
                Toast.makeText(this, "Debes seleccionar un logo para la tienda", Toast.LENGTH_SHORT).show();
                return;
            }

            TiendaEntity tienda = new TiendaEntity();
            tienda.nombre = nombre;
            tienda.descripcion = descripcion;
            tienda.direccion = direccion;
            tienda.telefono = telefono;
            tienda.imagenUrl = imageUri.toString();

            new Thread(() -> {
                FitPetDatabase db = FitPetDatabase.getInstance(this);
                db.tiendaDao().insertarTienda(tienda);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Tienda registrada exitosamente", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri tempUri = data.getData();
            // Copia la imagen al almacenamiento interno
            Uri copiedUri = copyImageToInternalStorage(tempUri, "logo_" + System.currentTimeMillis() + ".jpg");
            if (copiedUri != null) {
                imageUri = copiedUri;
                ImageView ivLogoPreview = findViewById(R.id.ivLogoPreview);
                ivLogoPreview.setImageURI(imageUri);
            } else {
                Toast.makeText(this, "No se pudo copiar la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Uri copyImageToInternalStorage(Uri sourceUri, String fileName) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(sourceUri);
            File file = new File(getFilesDir(), fileName);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return Uri.fromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}