package com.luisdavila.practica7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eNombre;
    EditText eCantidad;
    EditText eValor;
    static int ganancias=0;
    static int id=1;
    TextView tT1;
    private Cursor c;
    private Cursor c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inicializar();
        eNombre = (EditText) findViewById(R.id.e1);
        eCantidad = (EditText) findViewById(R.id.e2);
        eValor = (EditText) findViewById(R.id.e3);
        tT1 = (TextView) findViewById(R.id.t1);


    }

    public void inicializar(){
        Basedatos estudiante = new Basedatos(this, "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();
        c = bd.rawQuery("select * from peluches", null);
        if (c.moveToFirst() != true) {
            Intent intent = new Intent(this, NuevaActivity.class);
//
            startActivityForResult(intent, 1234);
        }

    }



    public void guardar(View view){
        Basedatos peluche = new Basedatos(this,  "basedatos", null, 1);
        SQLiteDatabase bd = peluche.getWritableDatabase();

        String nombre = eNombre.getText().toString();
        String valor = eValor.getText().toString();
        String cantidad = eCantidad.getText().toString();

        c = bd.rawQuery("select * from peluches where nombre='" + nombre +"'", null);
        if(c.moveToFirst()==true){
            Toast.makeText(this, "Actualmente existe este peluche", Toast.LENGTH_SHORT).show();
        }else{
            if (eNombre.length() > 0 ) {
                if (eValor.length() > 0 && Integer.valueOf(eValor.getText().toString()) > 0) {
                    if ((eCantidad.length() > 0) && Integer.valueOf(eCantidad.getText().toString()) > 0) {
                        ContentValues registro = new ContentValues();
                        registro.put("nombre", nombre);
                        registro.put("valor", valor);
                        registro.put("cantidad", cantidad);
                        dispoid();
                        registro.put("id", id);
                        bd.insert("peluches", null, registro);
                        bd.close();

                        eNombre.setText("");
                        eCantidad.setText("");
                        eValor.setText("");
                        Toast.makeText(this, "Peluche guardado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Ingrese cantidad correcta de peluches", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Ingrese un valor correcto a los peluches", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Ingrese un nombre correcto a los peluches", Toast.LENGTH_SHORT).show();
            }

        }



    }

    public void dispoid(){
        Basedatos estudiante = new Basedatos(this, "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();
        c = bd.rawQuery("select * from peluches", null);
        if (c.moveToFirst() == true) {
            do {
                if (Integer.valueOf(c.getString(3))==id) {
                    id+=1;
                }else{
                    id =Integer.valueOf(c.getString(3))+1;
                }
            } while (c.moveToNext());

        }
        if(id<7){
            id=7;
        }

    }

    public void consultar (View view){
        Basedatos estudiante = new Basedatos(this, "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();
        if (eNombre.length() > 0 ) {
            String nombre = eNombre.getText().toString();


            c = bd.rawQuery("select * from peluches where nombre='" + nombre + "'", null);
            if (c.moveToFirst() == true) {
                eNombre.setText(c.getString(0));
                eCantidad.setText(c.getString(1));
                eValor.setText(c.getString(2));
            } else {
                Toast.makeText(this, "No existe este peluche", Toast.LENGTH_SHORT).show();
            }

            bd.close();
        }else{
            Toast.makeText(this, "Ingrese nombre del peluche a buscar", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar (View view){
        Basedatos estudiante = new Basedatos(this,  "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        ContentValues registro = new ContentValues();
        if (eNombre.length() > 0 ) {
            if ((eCantidad.length() > 0) && Integer.valueOf(eCantidad.getText().toString()) > 0) {
                c = bd.rawQuery("select cantidad from peluches where nombre='" + nombre + "'", null);
                if (c.moveToFirst() == true) {
                    registro.put("cantidad", String.valueOf(Integer.valueOf(cantidad) + Integer.valueOf(c.getString(0))));
                    int cant = bd.update("peluches", registro, "nombre='" + nombre + "'", null);
                    bd.close();
                    if (cant == 1) {
                        Toast.makeText(this, "Se aumentó la cantidad de peluches", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "No existe este peluche", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ingrese cantidad correcta de peluches a aumentar", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Ingrese un nombre correcto del peluche a aumentar su cantidad", Toast.LENGTH_SHORT).show();
        }

    }

    public void eliminar(View v){
        Basedatos admin = new Basedatos(this, "basedatos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = eNombre.getText().toString();
        if (eNombre.length() > 0 ) {
            int cant = bd.delete("peluches", "nombre='" + nombre + "'", null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, "Peluche borrado", Toast.LENGTH_SHORT).show();
                eCantidad.setText("");
                eNombre.setText("");
                eValor.setText("");
            } else {
                Toast.makeText(this, "No existe este peluche", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Ingrese nombre del peluche a borrar", Toast.LENGTH_SHORT).show();
        }
    }

    public void venta (View view){
        Basedatos estudiante = new Basedatos(this,  "basedatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        ContentValues registro = new ContentValues();
        if (eNombre.length() > 0 ) {
            if ((eCantidad.length() > 0) && Integer.valueOf(eCantidad.getText().toString()) > 0) {
                c = bd.rawQuery("select cantidad, valor from peluches where nombre='" + nombre + "'", null);

                    if (c.moveToFirst() == true) {
                        if(Integer.valueOf(c.getString(0))>0) {
                            if (Integer.valueOf(c.getString(0)) - Integer.valueOf(cantidad) >= 0) {
                                registro.put("cantidad", String.valueOf(Integer.valueOf(c.getString(0)) - Integer.valueOf(cantidad)));
                                int cant = bd.update("peluches", registro, "nombre='" + nombre + "'", null);
                                bd.close();
                                if (cant == 1) {
                                    ganancias += Integer.valueOf(c.getString(1)) * Integer.valueOf(cantidad);
                                    if(Integer.valueOf(c.getString(0))-Integer.valueOf(cantidad)<=5) {
                                        Toast.makeText(this, "Quedan pocos peluches\nSe vendieron " + cantidad + " peluches de " + nombre, Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(this, "Se vendieron " + cantidad + " peluches de" + nombre, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(this, "No hay suficientes peluches", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "No hay peluches disponibles", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                            Toast.makeText(this, "No existe este peluche", Toast.LENGTH_SHORT).show();
                    }

            } else {
                Toast.makeText(this, "Ingrese cantidad correcta de peluches a vender", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Ingrese un nombre correcto del peluche a vender", Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiar(View view){
        eCantidad.setText("");
        eNombre.setText("");
        eValor.setText("");
    }

    public void mostrar(View view){

        Intent intent = new Intent(this, MostrarActivity.class);
//
        startActivityForResult(intent, 1234);

    }

    public void Ganancias(View view){
        Intent intent = new Intent(this, GananciasActivity.class);
        intent.putExtra("pGanancias", ganancias);
        startActivityForResult(intent, 1234);
    }

}
