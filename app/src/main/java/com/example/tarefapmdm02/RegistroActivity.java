package com.example.tarefapmdm02;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.FileUtils.*;
import static com.example.tarefapmdm02.FileUtils.getPath;

public class RegistroActivity extends AppCompatActivity {
    EditText nombre,apellido1,apellido2,email,usuario,contraseña,recontraseña;
    RadioButton cliente,administrador,foto,file;
    AdminSQLiteOpenHelper bdhelper;
    SQLiteDatabase bd;
    Button upload;
    File imageFile;
    public final int REQUEST_GRAVACION_IMAXE = 0;
    private final int CHOOSSER_FILE=1;

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
        foto=(RadioButton)findViewById(R.id.foto);
        file=(RadioButton)findViewById(R.id.file);
        upload=(Button)findViewById(R.id.upload);
        recontraseña=(EditText)findViewById(R.id.repassword);

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

    public void hacerFoto(View vista) {
        if (foto.isChecked()) {
            Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intento, REQUEST_GRAVACION_IMAXE); }

        else{
            String type="*/*";
            Intent intento=new Intent(Intent.ACTION_GET_CONTENT);
            intento.setType(type);
            startActivityForResult(Intent.createChooser(intento,"Selecciona foto") ,CHOOSSER_FILE); }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        com.example.tarefapmdm02.FileUtils
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_GRAVACION_IMAXE:
                    Bitmap bmp1 = (Bitmap) data.getExtras().get("data");
                    guardaImagen(bmp1);
                    break;

                case CHOOSSER_FILE:
                    Uri uri = data.getData();
                    File file = new File(FileUtils.getPath(this,uri));
                    Bitmap bmp2 = BitmapFactory.decodeFile(file.getAbsolutePath());
                    guardaImagen(bmp2);
                    break;
            }
        }
    }

    public void guardaImagen(Bitmap bmp){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        try {
            FileOutputStream outputStream = getApplicationContext().openFileOutput("nombre1.png", Context.MODE_PRIVATE);
            outputStream.write(byteArray);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
