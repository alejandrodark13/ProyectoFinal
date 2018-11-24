package com.example.alejandro.proyectofinal.BasesDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOusuario {

    private SQLiteDatabase base;

    public DAOusuario(Context ctx) {
        base =
                new Conexion(ctx).getWritableDatabase();
    }

    public long add(Usuario u)
    {
        ContentValues cv = new ContentValues();
        cv.put(Conexion.COLUMNS_USUARIOS[1], u.getTitulo() );
        cv.put(Conexion.COLUMNS_USUARIOS[2], u.getDescripcion() );
        cv.put(Conexion.COLUMNS_USUARIOS[3], u.getTareaoNota());
        cv.put(Conexion.COLUMNS_USUARIOS[4], u.getFecha() );
        cv.put(Conexion.COLUMNS_USUARIOS[5], u.getHora() );
        long result =base.insert(Conexion.TABLES_DB[0],null,cv);
        return result;
    }

    public long  update (Usuario u){
        ContentValues cv = new ContentValues();
        cv.put(Conexion.COLUMNS_USUARIOS[1], u.getTitulo() );
        cv.put(Conexion.COLUMNS_USUARIOS[2], u.getDescripcion() );
        cv.put(Conexion.COLUMNS_USUARIOS[3], u.getTareaoNota());
        cv.put(Conexion.COLUMNS_USUARIOS[4], u.getFecha() );
        cv.put(Conexion.COLUMNS_USUARIOS[5], u.getHora() );

        base.update(Conexion.TABLES_DB[0],
                cv,
                "_id=?",
                new String[]{String.valueOf( u.getId())}
        );
        return 0;
    }

    public List<Usuario > getAll(){
        List <Usuario> lst = new ArrayList<Usuario>();
        Cursor c = base.query(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );
        while(c.moveToNext()){

            lst.add(
                    new Usuario(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3),
                            c.getString(4),c.getString(5) )
            );
        }
        return lst;
    }

    public Cursor getAllC(){
        Cursor c = base.query(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );
        return c;
    }
    public Cursor getBusqueda(Usuario u){
        String whe="Titulo='"+ u.getTitulo()+"'";
        Cursor c = base.query(Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                whe,
                null,
                null,
                null,
                null);
        //_ad.close();
        return c;
    }
    public int getBorrar(Usuario u){

        return base.delete(Conexion.TABLES_DB[0],"titulo='"+u.getTitulo()+"'",null);
    }

/*//Adaptador para la base de datos
private SQLiteDatabase adaptador;

    public DAOusuario(Context context){
        //Hace que el adaptador pueda leer y exribir en la base de datos
        adaptador = new Conexion(context).getWritableDatabase();

    }

    public long Agregar(Usuario objUsuario){
        ContentValues content = new ContentValues();
        //Decimos que vamos a agregar
        //primer parametro = columa
        //segundo parametro = valor
        content.put(Conexion.COLUMNS_USUARIOS[1],objUsuario.getTitulo());
        content.put(Conexion.COLUMNS_USUARIOS[2],objUsuario.getDescripcion());
        content.put(Conexion.COLUMNS_USUARIOS[4],objUsuario.getTareaoNota());
        content.put(Conexion.COLUMNS_USUARIOS[5],objUsuario.getFecha());
        content.put(Conexion.COLUMNS_USUARIOS[6],objUsuario.getHora());

        //insertamos
        //si inserta devuelve el id insertado si no devuelve -1


        return adaptador.insert(Conexion.TABLES_DB[0],null,content);

    }

    public long Eliminar(Usuario n){
        return adaptador.delete(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS[1]+"='"+n.getTitulo()+"'&&" +
                        Conexion.COLUMNS_USUARIOS[2]+"='"+n.getDescripcion()+"'&&" +
                        Conexion.COLUMNS_USUARIOS[3]+"='"+n.getTareaoNota()+"'&&" +
                        Conexion.COLUMNS_USUARIOS[4]+"='"+n.getFecha()+"'&&" +
                        Conexion.COLUMNS_USUARIOS[5]+"='"+n.getHora()+"'&&" ,
                null);
    }

    public Cursor Buscar(String Titulo){
        String sql="Titulo='"+ Titulo+"'";
        Cursor c = adaptador.query(Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                sql,
                null,
                null,
                null,
                null);
        return c;
    }

    public Cursor Buscar(int id){
        String sql="_id="+ id ;
        Cursor c = adaptador.query(Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                sql,
                null,
                null,
                null,
                null);
        return c;
    }
    public List<Usuario> getAll(){
        List <Usuario> lst = new ArrayList<>();



        Cursor c = adaptador.query(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );

        while(c.moveToNext()){

            lst.add(
                    new Usuario(c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5))
            );

        }


        return lst;
    }

    public Cursor getAllC(){
        Cursor c = adaptador.query(
                Conexion.TABLES_DB[0],
                Conexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );

        return c;

    }*/
}
