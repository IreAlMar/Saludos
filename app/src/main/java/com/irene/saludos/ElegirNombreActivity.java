package com.irene.saludos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ElegirNombreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_nombre);
        Button buttonVolver =(Button) findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
                String nombre = editTextNombre.getText().toString();
                //nombre = nombre.isEmpty() ? null : nombre;
                nombre = "Un nombre";
                Intent resultado = new Intent();
                resultado.putExtra(HelloActivity.TAG_NOMBRE, nombre);
                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }
}
