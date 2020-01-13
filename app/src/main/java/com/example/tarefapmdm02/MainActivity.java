package com.example.tarefapmdm02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    AdminSQLiteOpenHelper dbhelper;
    SQLiteDatabase bd;
    EditText usuario,contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        bd = dbhelper.getWritableDatabase();
        usuario=findViewById((R.id.user));
        contra=findViewById(R.id.passwd);
    }
    public void login(View vista){
        Intent i=null;
        String nombre= String.valueOf(usuario.getText());
        String contraseña= String.valueOf(contra.getText());


        Cursor cursor= bd.rawQuery("select * from usuarios where usuario="+"\""+nombre+"\"",null);

        if(cursor.moveToFirst()) {
            if (cursor.getString(6).equals(contraseña)) {
                i=new Intent(this, Usuario.class);
                i.putExtra("idUser",cursor.getInt(0));
                startActivity(i);}
            else
                Toast.makeText(this, "Constraseña errónea", Toast.LENGTH_LONG).show();
        } else
                Toast.makeText(this,"Usuario no existe",Toast.LENGTH_LONG).show();

    }
    public void registro_usuario(View vista){
        Intent i=new Intent(this,RegistroActivity.class);
        startActivity(i);

    }
}
