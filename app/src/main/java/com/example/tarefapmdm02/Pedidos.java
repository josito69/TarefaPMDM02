package com.example.tarefapmdm02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pedidos extends AppCompatActivity {
    AdminSQLiteOpenHelper dbhelper;
    SQLiteDatabase bd;
    Bundle data;
    RecyclerView lista;
    ArrayList <Pedido> pedidos;
    int idUser;
    String tipoPedido;
    String tipoUsuario;
    TextView cabecera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pedidos=new ArrayList<Pedido>();
        data=getIntent().getExtras();
        idUser=data.getInt("idUser");
        tipoPedido=data.getString("tipoPedido");
        tipoUsuario=data.getString("tipoUsuario");
        lista=findViewById(R.id.ListaPedidos);
        cabecera=(TextView)findViewById(R.id.cabecera);
        dbhelper=new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        bd = dbhelper.getWritableDatabase();


        Cursor cursor=null;
        if(tipoUsuario.equals("cliente"))
            cursor = bd.rawQuery("select codigo,articulo,cantidad from pedidos where idUser="+this.idUser+" and estado=\""+this.tipoPedido+"\";" ,null);
        if(tipoUsuario.equals("administrador"))
             cursor = bd.rawQuery("select codigo,articulo,cantidad from pedidos where estado=\""+this.tipoPedido+"\";" ,null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            pedidos.add(new Pedido(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }
        bd.close();

        RecyclerViewAdapter adaptador=new RecyclerViewAdapter(pedidos,tipoPedido,tipoUsuario,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        lista.setLayoutManager(layoutManager);
        lista.setAdapter(adaptador);

       switch(tipoPedido) {
            case "aceptado":
                cabecera.setText(R.string.activity_compras);
                break;
            case "rechazado":
                cabecera.setText(R.string.activity_rechazados);
                break;
            case "tramite":
                cabecera.setText(R.string.activity_tramites);
                if (tipoUsuario.equals("administrador")) {
                    ((ImageView)findViewById(R.id.acept)).setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.rech)).setVisibility(View.VISIBLE);
                }
                break; }

        }

    public boolean onOptionsItemSelected(MenuItem item) {
             switch (item.getItemId()) {
                 case android.R.id.home:
                     Intent i=new Intent(this, Usuario.class);
                     i.putExtra("idUser",idUser);
                     i.putExtra("tipoPedido",tipoPedido);
                     i.putExtra("tipoUsuario",tipoUsuario);
                     startActivity(i);
                     finish();
                     return true;

                 default:
                     return super.onOptionsItemSelected(item);
             }
         }
}

