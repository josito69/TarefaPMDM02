package com.example.tarefapmdm02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {
    EditText nombre,apellido1,apellido2,email,usuario,contraseña;
    RadioButton cliente,administrador;
    AdminSQLiteOpenHelper bdhelper;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        bdhelper=new AdminSQLiteOpenHelper(this,"administracion",null, 1);
        bd = bdhelper.getWritableDatabase();

        nombre=(EditText)findViewById(R.id.regnome);
        apellido1=(EditText)findViewById(R.id.regnome);
        apellido2=(EditText)findViewById(R.id.regapellido2);
        email=(EditText)findViewById(R.id.regmail);
        usuario=(EditText)findViewById(R.id.regusuario);
        contraseña=(EditText)findViewById(R.id.password);
        cliente=(RadioButton)findViewById(R.id.regcliente);
        administrador=(RadioButton)findViewById(R.id.regadmin);
    }

    public void registro(View vista){
        boolean existe=comprobarUsuario();
        if(!existe) {
            ContentValues newUser = new ContentValues();
            newUser.put("nombre", nombre.getText().toString());
            newUser.put("apellido1", apellido1.getText().toString());
            newUser.put("apellido2", apellido2.getText().toString());
            newUser.put("email", email.getText().toString());
            newUser.put("Usuario", usuario.getText().toString());
            newUser.put("contraseña", contraseña.getText().toString());
            if (cliente.isChecked())
                newUser.put("tipoUsuario", "cliente");
            else
                newUser.put("tipoUsuario", "administrador");
            try {
                bd.insert("usuarios", null, newUser);
                Toast.makeText(this, "Se cargaron los datos del nuevo Usuario", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } catch (Exception ex) {
                Toast.makeText(this, "ERROR al cargar los datos del nuevo Usuario", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(this, "Nombre de Usuario ya existente en la base de datos", Toast.LENGTH_LONG).show();


    }
    public boolean comprobarUsuario(){
        boolean check=false;
        String nombreUsuario=nombre.getText().toString();
        Cursor cursor= bd.rawQuery("select * from usuarios where usuario="+"\""+nombreUsuario+"\"",null);
        if (cursor.getCount()>0)
            check=true;
     return check;
    }
}
