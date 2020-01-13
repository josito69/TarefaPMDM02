package com.example.tarefapmdm02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class finalizarPedido extends AppCompatActivity {
     Bundle data;
     Button bt1;
     EditText direccion,codigoPostal,ciudad;
     int idUser;
     String tipoUsuario;
    AdminSQLiteOpenHelper bdhelper;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bt1=findViewById(R.id.bt1);
        direccion=findViewById(R.id.address);
        codigoPostal=findViewById(R.id.Postal_code);
        ciudad=findViewById(R.id.city);

        data=getIntent().getExtras();
        bdhelper=new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        bd = bdhelper.getWritableDatabase();
        idUser=data.getInt("idUser");
        idUser=data.getInt("idUser");
        tipoUsuario=data.getString("tipoUsuario");

    }
    public void almacenarPedido(View vista){
        try{
            ContentValues newUser = new ContentValues();
            newUser.put("categoria", data.getString("categoria"));
            newUser.put("articulo", data.getString("producto"));
            newUser.put("cantidad", Integer.parseInt(data.getString("cantidad")));
            newUser.put("estado", "tramite");
            newUser.put("direccion",direccion.getText().toString());
            newUser.put("ciudad", ciudad.getText().toString());
            newUser.put("codigoPostal", Integer.parseInt(codigoPostal.getText().toString()));
            newUser.put("idUser",idUser);

            bd.insert("pedidos", null, newUser);
            Toast.makeText(this,"Datos guardados correctamente", Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            Intent i=new Intent(this,Usuario.class);
            i.putExtra("idUser",idUser);
            startActivity(i);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i=new Intent(this,Facer_Pedido.class);
                i.putExtra("idUser",idUser);
                i.putExtra("tipoUsuario",tipoUsuario);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
