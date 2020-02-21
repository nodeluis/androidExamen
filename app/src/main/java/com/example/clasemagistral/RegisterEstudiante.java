package com.example.clasemagistral;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class RegisterEstudiante extends AppCompatActivity implements View.OnClickListener {
    EditText nom,ap,co,dir,fe;
    Button btn;
    DatePickerDialog datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_estudiante);
        btn=findViewById(R.id.btnReRgEst);
        btn.setOnClickListener(this);
        nom=findViewById(R.id.nomReRgEst);
        ap=findViewById(R.id.apReRgEst);
        co=findViewById(R.id.coReRgEst);
        dir=findViewById(R.id.diReRgEst);
        fe=findViewById(R.id.feReRgEst);
        fe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnReRgEst){
            Intent in=new Intent(RegisterEstudiante.this,principal.class);
            mandar();
            startActivity(in);
        }else if(view.getId()==R.id.feReRgEst){
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            datepicker = new DatePickerDialog(RegisterEstudiante.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            //Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                            String filtro=(monthOfYear +1)+ "/" + dayOfMonth + "/" + year;
                            fe.setText(filtro);

                        }
                    }, year, month, day);
            datepicker.show();
        }
    }

    private void mandar() {
        String query="mutation{addEstudiante(nombre:\""+nom.getText()
                +"\",apellido:\""+ap.getText()
                +"\",direccion:\""+dir.getText()
                +"\",correo:\""+co.getText()
                +"\",fecha_nacimiento:\""
                +fe.getText()+"\"){nombre,apellido,direccion,correo,fecha_nacimiento}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String re=response.getString("message");
                    Toast.makeText(getApplicationContext(),""+re, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
