package com.irene.saludos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloActivity extends Activity {
    private static final int ACTIVITY_NOMBRE = 1;
    public static final String TAG_NOMBRE = "NOMBRE";
    private String nombre = null;
    private Button buttonLanzarActivity;
    private TextView textViewSaludo;
    private SharedPreferences prefs;
    private static String PREFS_NAME = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);
        textViewSaludo = (TextView) findViewById(R.id.textViewSaludo);
        buttonLanzarActivity = (Button) findViewById(R.id.buttonLanzarActivity);
        buttonLanzarActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ElegirNombreActivity.class);
                startActivityForResult(intent, ACTIVITY_NOMBRE);
            }
        });
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if(savedInstanceState != null){
            nombre = savedInstanceState.getString(TAG_NOMBRE);
        }else{
            nombre = prefs.getString(TAG_NOMBRE, null);
        }
        if (nombre != null){
            actualizarPantalla();
        }
    }

    private void actualizarPantalla(){
        if (nombre != null){
            textViewSaludo.setText("¡Hola, " + nombre + "!");
            buttonLanzarActivity.setText(getText(R.string.cambiar_nombre));
        }else{
            textViewSaludo.setText(getString(R.string.nombre_no_introducido));
            buttonLanzarActivity.setText((getString(R.string.introducir_nombre)));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ACTIVITY_NOMBRE){
            if (requestCode == RESULT_OK){
                nombre = data.getStringExtra(TAG_NOMBRE);
            }else if (resultCode == RESULT_CANCELED){
                nombre = null;
            }
            actualizarPantalla();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString(TAG_NOMBRE, nombre);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        nombre = savedInstanceState.getString(TAG_NOMBRE);
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TAG_NOMBRE, nombre);
        editor.apply(); //lo hace en background
        //editor.commit(); lo hace de inmediato
        super.onStop();
    }
}
