package com.luisdavila.practica7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GananciasActivity extends AppCompatActivity {
    TextView tganancias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganancias);
        tganancias = (TextView) findViewById(R.id.tGanancias);
        Bundle extras = getIntent().getExtras();
        tganancias.setText(String.valueOf(extras.getInt("pGanancias")));
    }
}
