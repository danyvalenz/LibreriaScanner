package com.cedisropa.libreriascanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.cedisropa.sdk.DatosGenerales;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PantallaDosActivity extends AppCompatActivity {
    private static final String EXTRA_DATOS_GENERALES = "datosGenerales";


    @BindView(R.id.txt_nombre)
    TextView txtNombre;

    @BindView(R.id.txt_ip)
    TextView txtIp;


    @OnClick(R.id.btn_mostrar_datos)
    void showDatosGenerales(){
        recibirDatos();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);
        ButterKnife.bind(this);





    }

    private void recibirDatos(){
        Intent intent = getIntent();
        DatosGenerales datosGenerales = (DatosGenerales) intent.getSerializableExtra(EXTRA_DATOS_GENERALES);
        txtNombre.setText(datosGenerales.getNomEmpleado());
        txtIp.setText(datosGenerales.getIpBodega());

    }
}
