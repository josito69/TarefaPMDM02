package com.example.tarefapmdm02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(codigo integer primary key autoincrement," +
                                         "nombre text," +
                                         "apellido1 text," +
                                         "apellido2 text,"+
                                         "email text," +
                                         "usuario text unique," +
                                         "contraseña text, " +
                                         "tipoUsuario text)");
        db.execSQL("create table pedidos(codigo integer primary key autoincrement," +
                                        "categoria text," +
                                        "articulo text," +
                                        "cantidad tinyint," +
                                        "estado text,"+
                                        "direccion text," +
                                        "ciudad text," +
                                        "codigoPostal integer,"+
                                        "idUser integer)");

        db.execSQL("insert into usuarios(nombre,apellido1,apellido2,email,usuario,contraseña,tipoUsuario)" +
                               "values('NameA','Apellido1A','Apellido2A','NameA@dam.com','cliente1','abc123.','cliente')");
        db.execSQL("insert into usuarios(nombre,apellido1,apellido2,email,usuario,contraseña,tipoUsuario)" +
                        "values('NameB','Apellido1B','Apellido2B','NameB@dam.com','admin','abc123.','administrador')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}