package com.luisdavila.practica7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 04/05/2016.
 */
public class Basedatos extends SQLiteOpenHelper {

    public Basedatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table peluches(nombre text primary key, cantidad integer, valor integer, id integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist peluches");
        db.execSQL("create table peluches(nombre text primary key, cantidad integer, valor integer, id integer)");
    }


}
