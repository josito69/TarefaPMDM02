package com.example.tarefapmdm02;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Facer_Pedido extends AppCompatActivity {
    String [] informatica,electronica,moviles,categorias,cantidades;
    Spinner familia,articulo,cantidad;
    int idUser;
    String tipoUsuario;
    Bundle data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facer__pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        data=getIntent().getExtras();
        idUser=data.getInt("idUser");
        tipoUsuario=data.getString("tipoUsuario");

        //*************inicializamos los arrays para los spinners extrayéndolos de strings.xml***************************
        informatica=getResources().getStringArray(R.array.informatica);
        electronica=getResources().getStringArray(R.array.electronica);
        moviles=getResources().getStringArray(R.array.moviles);
        cantidades=getResources().getStringArray(R.array.cantidades);
        categorias=getResources().getStringArray(R.array.categorias);

        //*************inicializamos las vistas de la app ****************************************************************
        familia = findViewById(R.id.spinner);
        articulo = findViewById(R.id.spinner2);
        cantidad = findViewById(R.id.spinner3);

        //****************rellenamos los items de los spinners con entradas
        familia.setAdapter(new ArrayAdapter<>(this, R.layout.spinner, categorias));
        producto_selection();
        cantidad.setAdapter(new ArrayAdapter<>(this,R.layout.spinner,cantidades));
        idUser=getIntent().getExtras().getInt("idUser");
    }
    public void producto_selection(){
        familia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String opcion=familia.getSelectedItem().toString();
                ArrayAdapter <String> adaptador;

                        if(opcion.equals("Informática")||opcion.equals("Informatic")){
                           adaptador=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner,informatica);
                           articulo.setAdapter(adaptador);}
                        else if(opcion.equals("Electrónica")||opcion.equals("Electronic")){
                           adaptador=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner,electronica);
                           articulo.setAdapter(adaptador);}
                        else if(opcion.equals("Móviles")||opcion.equals("Mobiles")){
                           adaptador=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner,moviles);
                           articulo.setAdapter(adaptador);}
                        else
                           throw new IllegalStateException("Unexpected value: " + opcion);

            }
            public void onNothingSelected(AdapterView<?> spn) {
            }
        });
    }
    public void enviarPedido(View view){
       Intent i=new Intent(this,finalizarPedido.class);
       i.putExtra("categoria",familia.getSelectedItem().toString());
       i.putExtra("producto",articulo.getSelectedItem().toString());
       i.putExtra("cantidad",cantidad.getSelectedItem().toString());
       i.putExtra("idUser",idUser);
       startActivity(i);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i=new Intent(this, Usuario.class);
                i.putExtra("idUser",idUser);
                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
