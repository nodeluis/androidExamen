package com.example.clasemagistral;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class listar extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5;
    RecyclerView rec1,rec2,rec3,rec4,rec5;
    LinearLayoutManager ln1;
    LinearLayoutManager ln2;
    LinearLayoutManager ln3;
    LinearLayoutManager ln4;
    LinearLayoutManager ln5;
    adapterNombre adpN;
    adapterApellido adpA;
    adapterCorreo adpC;
    adapterDireccion adpD;
    adapterFecha adpF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        ln1=new LinearLayoutManager(this);
        ln2=new LinearLayoutManager(this);
        ln3=new LinearLayoutManager(this);
        ln4=new LinearLayoutManager(this);
        ln5=new LinearLayoutManager(this);
        rec1=findViewById(R.id.columnNombre);
        rec2=findViewById(R.id.columnAp);
        rec3=findViewById(R.id.columnCo);
        rec4=findViewById(R.id.columnDir);
        rec5=findViewById(R.id.columnFe);
        rec1.setLayoutManager(ln1);
        rec2.setLayoutManager(ln2);
        rec3.setLayoutManager(ln3);
        rec4.setLayoutManager(ln4);
        rec5.setLayoutManager(ln5);
        adpN=new adapterNombre(this);
        adpA=new adapterApellido(this);
        adpC=new adapterCorreo(this);
        adpD=new adapterDireccion(this);
        adpF=new adapterFecha(this);
        rec1.setAdapter(adpN);
        rec2.setAdapter(adpA);
        rec3.setAdapter(adpC);
        rec4.setAdapter(adpD);
        rec5.setAdapter(adpF);
        btn1=findViewById(R.id.btnNombre);
        btn2=findViewById(R.id.btnAp);
        btn3=findViewById(R.id.btnCo);
        btn4=findViewById(R.id.btnDir);
        btn5=findViewById(R.id.btnFe);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNombre){
            if(adpN.getItemCount()>0){
                adpN.eliminar();
            }else{
                llenarN();
            }
        }else if(view.getId()==R.id.btnAp){
            if(adpA.getItemCount()>0){
                adpA.eliminar();
            }else{
                llenarA();
            }
        }else if(view.getId()==R.id.btnCo){
            if(adpC.getItemCount()>0){
                adpC.eliminar();
            }else{
                llenarC();
            }
        }else if(view.getId()==R.id.btnDir){
            if(adpD.getItemCount()>0){
                adpD.eliminar();
            }else{
                llenarD();
            }
        }else if(view.getId()==R.id.btnFe){
            if(adpF.getItemCount()>0){
                adpF.eliminar();
            }else{
                llenarF();
            }
        }
    }

    private void llenarN() {
        String query="{estudiantes{nombre}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray re=response.getJSONObject("data").getJSONArray("estudiantes");
                    for (int i=0;i<re.length();i++){
                        JSONObject ob=re.getJSONObject(i);
                        String tipo=ob.getString("nombre");
                        item it=new item(tipo);
                        adpN.add(it);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void llenarA() {
        String query="{estudiantes{apellido}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray re=response.getJSONObject("data").getJSONArray("estudiantes");
                    for (int i=0;i<re.length();i++){
                        JSONObject ob=re.getJSONObject(i);
                        String tipo=ob.getString("apellido");
                        item it=new item(tipo);
                        adpA.add(it);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void llenarC() {
        String query="{estudiantes{correo}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray re=response.getJSONObject("data").getJSONArray("estudiantes");
                    for (int i=0;i<re.length();i++){
                        JSONObject ob=re.getJSONObject(i);
                        String tipo=ob.getString("correo");
                        item it=new item(tipo);
                        adpC.add(it);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void llenarD() {
        String query="{estudiantes{direccion}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray re=response.getJSONObject("data").getJSONArray("estudiantes");
                    for (int i=0;i<re.length();i++){
                        JSONObject ob=re.getJSONObject(i);
                        String tipo=ob.getString("direccion");
                        item it=new item(tipo);
                        adpD.add(it);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void llenarF() {
        String query="{estudiantes{fecha_nacimiento}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    JSONArray re=response.getJSONObject("data").getJSONArray("estudiantes");
                    for (int i=0;i<re.length();i++){
                        JSONObject ob=re.getJSONObject(i);
                        String tipo=ob.getString("fecha_nacimiento");
                        item it=new item(tipo);
                        adpF.add(it);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
