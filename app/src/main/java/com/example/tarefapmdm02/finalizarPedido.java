package com.example.tarefapmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class finalizarPedido extends AppCompatActivity {
     Bundle data;
     Button bt1;
     EditText direccion,codigoPostal,ciudad;
     String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);

        bt1=findViewById(R.id.bt1);
        direccion=findViewById(R.id.address);
        codigoPostal=findViewById(R.id.Postal_code);
        ciudad=findViewById(R.id.city);
        data=getIntent().getExtras();
    }
    public void mostrarPedido(View vista){
        texto=getString(R.string.familia)+": "+data.getString("categoria")+"\n"+
              getString(R.string.articulo)+": "+data.getString("producto")+"\n"+
              getString(R.string.cantidad)+": "+data.getString("cantidad")+"\n"+
              getString(R.string.direccion)+": "+direccion.getText().toString()+"\n"+
              getString(R.string.ciudad)+": "+ciudad.getText().toString()+"\n"+
              getString(R.string.codigo_postal)+": "+codigoPostal.getText().toString();
        Toast.makeText(this,texto,Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity.class));

    }
}
