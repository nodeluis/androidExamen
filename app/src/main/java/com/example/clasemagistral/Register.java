package com.example.clasemagistral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText nick,password;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nick=findViewById(R.id.nickReg);
        password=findViewById(R.id.passReg);
        btn=findViewById(R.id.sendReg);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.sendReg){
            Intent in=new Intent(Register.this,MainActivity.class);
            cargar();
            startActivity(in);
        }
    }

    private void cargar() {
        String query="mutation{addUser(nick:\""+nick.getText()+"\",password:\""+password.getText()+"\"){nick,password}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String resp=response.getString("message");
                    Toast.makeText(getApplicationContext(),""+resp,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
