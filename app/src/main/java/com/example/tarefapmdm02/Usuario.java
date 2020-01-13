package com.example.tarefapmdm02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Usuario extends AppCompatActivity {
    TextView nombre,apellido1,apellido2;
    ImageView userImage;
    int idUser;
    String tipoUsuario;
    Bundle data;
    AdminSQLiteOpenHelper dbhelper;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        nombre=(TextView)findViewById(R.id.nombre);
        apellido1=(TextView)findViewById(R.id.apellido1);
        apellido2=(TextView)findViewById(R.id.apellido2);
        userImage=(ImageView)findViewById(R.id.userImage);


        dbhelper=new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        bd = dbhelper.getWritableDatabase();
        data=getIntent().getExtras();
        idUser = data.getInt("idUser");

        Cursor cursor= bd.rawQuery("select * from usuarios where codigo="+idUser,null);

        try {
            cursor.moveToFirst();
            nombre.setText(cursor.getString(1));
            apellido1.setText(cursor.getString(2));
            apellido2.setText(cursor.getString(3));
            tipoUsuario=cursor.getString(7);
        }catch(Exception ex){
            Toast.makeText(this,"No se ha podido acceder a la based de datos",Toast.LENGTH_LONG).show();
        }
        if(tipoUsuario.equals("cliente"))
            userImage.setImageResource(R.drawable.donald_duck);
        else
            userImage.setImageResource(R.drawable.obama);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        MenuItem menuitem=null;
        if(tipoUsuario.equals("cliente"))
            menuitem = menu.findItem(R.id.op3);
        if(tipoUsuario.equals("administrador"))
            menuitem = menu.findItem(R.id.op5);
         menuitem.setVisible(false);
        this.invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.op1)
            gestionar_pedidos(item.getActionView(),"tramite");
        if (id==R.id.op2)
            gestionar_pedidos(item.getActionView(),"aceptado");
        if (id==R.id.op3)
            gestionar_pedidos(item.getActionView(),"rechazado");
        if (id==R.id.op4)
            finalizar(item.getActionView());
        if (id==R.id.op5)
                facer_pedidos(item.getActionView());

        return super.onOptionsItemSelected(item);
    }

    private void facer_pedidos(View actionView) {
        Intent i=new Intent(this, Facer_Pedido.class);
        i.putExtra("idUser",idUser);
        i.putExtra("tipoUsuario",tipoUsuario);
        startActivity(i); }

    public void finalizar(View vista){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i); }

    public void gestionar_pedidos(View vista,String tipoPedido){
        Intent i=new Intent(this, Pedidos.class);
        i.putExtra("idUser",idUser);
        i.putExtra("tipoPedido",tipoPedido);
        i.putExtra("tipoUsuario",tipoUsuario);
        startActivity(i); }
}
