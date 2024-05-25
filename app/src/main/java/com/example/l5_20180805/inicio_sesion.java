package com.example.l5_20180805;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.l5_20180805.SuperadminActivity;
import com.example.l5_20180805.dto.usuario;

import java.util.ArrayList;
import java.util.List;

public class inicio_sesion extends AppCompatActivity {

    private List<usuario> usuarios;
    private EditText correoEditText;
    private EditText contrasenaEditText;
    private Button iniciarSesionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_cuenta);

        // Inicializar lista de usuarios
        usuarios = new ArrayList<>();
        generarUsuarios();

        correoEditText = findViewById(R.id.editUsuario);
        contrasenaEditText = findViewById(R.id.editContrasena);
        iniciarSesionButton = findViewById(R.id.buttonInicioSesion);

        // Acción del botón de inicio de sesión
        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

    }


    private void generarUsuarios() {
        usuarios.add(new usuario("Carlos Minaya", "20180805", "123456", "xxxxx"));
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        String correo = correoEditText.getText().toString();
        String contrasena = contrasenaEditText.getText().toString();

        for (usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                // Inicio de sesión exitoso
                redirigirSegunRol(usuario.getCorreo());
                return;
            }
        }

        Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    private void redirigirSegunRol(String correo) {
        Intent intent;
        switch (correo) {
            case "20180805":
                intent = new Intent(this, SuperadminActivity.class);
                intent.putExtra("nombre", usuarios.get(0).getNombre());
                break;
            default:
                throw new IllegalArgumentException("Rol no válido: " + correo);
        }
        startActivity(intent);
        finish();
    }

}
