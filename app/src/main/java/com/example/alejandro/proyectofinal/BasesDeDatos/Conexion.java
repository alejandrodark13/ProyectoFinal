package com.example.alejandro.proyectofinal.BasesDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conexion extends SQLiteOpenHelper {

    public static final String [] COLUMNS_USUARIOS = {"_id", "Titulo", "Descripcion", "TareaoNota", "Fecha" ,"Hora" };
    public static final String [] TABLES_DB = {"usuarios"};

    private String SCRIPT_DB  = "create table usuarios " +
            "(_id integer primary key autoincrement, " +
            "Titulo text not null, " +
            "Descripcion text not null," +
            "TareaoNota text not null," +
            "Fecha text not null, " +
            "Hora text not null);";


    public Conexion (Context ctx){

        super(ctx, "midb", null, 1);
    }

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

