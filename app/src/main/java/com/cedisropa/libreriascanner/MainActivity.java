package com.cedisropa.libreriascanner;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cedisropa.scanner.BrAppCompatActivity;
import com.cedisropa.scanner.OnScanListener;
import com.cedisropa.scanner.ScannableEditText;
import com.cedisropa.sdk.DatosGenerales;
import com.cedisropa.sdk.WebServices;
import com.cedisropa.sdk.Webservice;
import com.cedisropa.sdk.WebserviceNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BrAppCompatActivity {

    private static final String EXTRA_DATOS_GENERALES = "datosGenerales";


    @BindView(R.id.txt_nombre)
    ScannableEditText txtNombre;

    @BindView(R.id.lbl_titulo)
    TextView lblTitulo;

    @BindView(R.id.btn_aceptar)
    Button  btnAceptar;

    DatosGenerales datosGenerales;

    @OnClick(R.id.btn_aceptar)
    void abrirSegundaPantalla(){
        abrirPantallaDos();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        try {
            Webservice webservice;
            webservice = WebServices.getService(48);
            String url = webservice.getUrl().toString();
            //url = "";
            lblTitulo.setText(url);

        }catch (WebserviceNotFoundException e){

            e.printStackTrace();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        datosGenerales = new DatosGenerales();
        setDatosGenerales();



        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);






        this.setOnScanListener(new OnScanListener() {
            @Override
            public void onScan(String s) {
                lblTitulo.setText(s);
            }
        });

        txtNombre.setOnScanListener(new OnScanListener() {
            @Override
            public void onScan(String s) {
                txtNombre.setText(s);
            }
        });




    }


    private void setDatosGenerales(){
        datosGenerales.setNomCiudad("Culiacan");
        datosGenerales.setIpBodega("10.28.114.110");
        datosGenerales.setNomEmpleado("Daniel Valenzuela");
        datosGenerales.setNumEmpleado("94271331");
        datosGenerales.setNumIdentificador(31);
    }

    private void abrirPantallaDos(){
        Intent intent = new Intent(this, PantallaDosActivity.class);
        intent.putExtra(EXTRA_DATOS_GENERALES,datosGenerales);
        startActivity(intent);
    }
}
