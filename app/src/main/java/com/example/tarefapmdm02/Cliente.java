package com.example.tarefapmdm02;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Cliente extends AppCompatActivity {
    TextView nombre,apellido1,apellido2;
    Bundle data;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        data=getIntent().getExtras();
        nombre=(TextView)findViewById(R.id.nombre);
        apellido1=(TextView)findViewById(R.id.apellido1);
        apellido2=(TextView)findViewById(R.id.apellido2);
        nombre.setText(data.getString("nombre"));
        apellido1.setText(data.getString("apellido1"));
        apellido2.setText(data.getString("apellido2"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.op1) {
            ver_pedidos_tramite(item.getActionView()); }
        if (id==R.id.op2) {
           facerPedido(item.getActionView()); }
        if (id==R.id.op3) {
            ver_compras_realizads(item.getActionView());}
        if (id==R.id.op4) {
            finalizar(item.getActionView());
        }
        return super.onOptionsItemSelected(item);
    }

    public void  facerPedido(View vista){
        Intent i=new Intent(this,Facer_Pedido.class);
        startActivity(i);
    }
    public void finalizar(View vista){
        finish();
    }
    public void ver_pedidos_tramite(View vista){
        Intent i=new Intent(this,Pedidos_Tramite.class);
        startActivity(i);
    }
    public void ver_compras_realizads(View vista){
        Intent i=new Intent(this,Compras_realizadas.class);
        startActivity(i);
    }

}
