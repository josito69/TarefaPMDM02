package com.example.tarefapmdm02;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Administrador extends AppCompatActivity {
    TextView nombre,apellido1,apellido2;
    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        data=getIntent().getExtras();
        nombre=(TextView)findViewById(R.id.nombre);
        apellido1=(TextView)findViewById(R.id.apellido1);
        apellido2=(TextView)findViewById(R.id.apellido2);
        nombre.setText(data.getString("nombre"));
        apellido1.setText(data.getString("apellido1"));
        apellido2.setText(data.getString("apellido2"));
    }
}
