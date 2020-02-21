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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nick,pass;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        nick=findViewById(R.id.nickLog);
        pass=findViewById(R.id.passLog);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button2){
            Intent in=new Intent(MainActivity.this,Register.class);
            startActivity(in);
        }else if(view.getId()==R.id.button){
            Intent in=new Intent(MainActivity.this,principal.class);
            validar(in);
        }
    }

    private void validar(final Intent in) {
        String query="{validation(nick:\""+nick.getText()+"\",password:\""+pass.getText()+"\"){nick,password}}";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams r=new RequestParams();
        r.put("query",query);
        client.post(ip.ip,r,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                    JSONObject re=response.getJSONObject("data").getJSONObject("validation");
                    if(re!=null){
                        startActivity(in);
                    }else{
                        Toast.makeText(getApplicationContext(),"error datos incorrctos",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"error datos incorrctos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
