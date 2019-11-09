package com.example.tarefapmdm02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario,contra;
    CheckBox tipo_cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=findViewById((R.id.user));
        contra=findViewById(R.id.passwd);
        tipo_cliente=findViewById(R.id.cliente);
    }
    public void registro(View vista){
        Intent i=null;
        String nombre= String.valueOf(usuario.getText());
        String contraseña= String.valueOf(contra.getText());

        if (tipo_cliente.isChecked() && nombre.equals("admin") && contraseña.equals("abc123.")) {
            i = new Intent(this, Administrador.class);
            startActivity(i); }
        else if(!tipo_cliente.isChecked() && nombre.equals("cliente1") && contraseña.equals("abc123.")) {
                i = new Intent(this, cliente.class);
                startActivity(i); }
        else if(!tipo_cliente.isChecked() && nombre.equals("admin") && contraseña.equals("abc123.")){
            Toast notification=Toast.makeText(this,"Tipo de usuario erróneo",Toast.LENGTH_LONG);
            notification.show(); }
        else if(tipo_cliente.isChecked() && nombre.equals("cliente1") && contraseña.equals("abc123.")) {
            Toast notification = Toast.makeText(this, "Tipo de usuario erróneo", Toast.LENGTH_LONG);
            notification.show(); }
        else{
            Toast notification=Toast.makeText(this,"Usuario y/o contraseña erróneos",Toast.LENGTH_LONG);
            notification.show();
        }
    }
}
