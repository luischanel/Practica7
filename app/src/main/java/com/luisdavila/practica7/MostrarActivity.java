package com.luisdavila.practica7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarActivity extends AppCompatActivity {
    TextView tT1;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        String cadena="";
        Basedatos estudiante = new Basedatos(this, "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();
        tT1 = (TextView) findViewById(R.id.t1);
        c = bd.rawQuery("select * from peluches", null);
        if (c.moveToFirst() == true) {
            do {
                cadena += "Id: " + c.getString(3) + "\n";
                cadena += "Nombre: " + c.getString(0) + "\n";
                cadena += "Cantidad: "+c.getString(1) + "\n";
                cadena += "Precio: " +c.getString(2) + "\n";
            } while (c.moveToNext());

        }

        tT1.setText(String.valueOf(cadena));

        bd.close();
    }
}
