package com.luisdavila.practica7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NuevaActivity extends AppCompatActivity {
    private Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva);

    }

    public void Si(View view){
        crear("Iron Man", "10", "15000", "1");
        crear("Viuda Negra", "10", "12000", "2");
        crear("Capitan America", "10", "15000", "3");
        crear("Hulk", "10", "12000", "4");
        crear("Bruja Escarlata", "10", "15000", "5");
        crear("SpiderMan", "10", "10000", "6");
        finish();
    }
    public void No(View v){
        finish();
    }
    public void crear(String n, String ca, String v, String id){
        Basedatos peluche = new Basedatos(this,  "basedatos", null, 1);
        SQLiteDatabase bd = peluche.getWritableDatabase();

        c = bd.rawQuery("select * from peluches where nombre='" + n +"'", null);
        if(c.moveToFirst()==false) {
            ContentValues registro = new ContentValues();

            registro.put("nombre", n);
            registro.put("cantidad", ca);
            registro.put("valor", v);
            registro.put("id", id);

            bd.insert("peluches", null, registro);
            bd.close();
        }
    }
}
