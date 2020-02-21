package com.example.clasemagistral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class principal extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btn1=findViewById(R.id.addPrin);
        btn2=findViewById(R.id.listarPrin);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addPrin){
            Intent in=new Intent(principal.this,RegisterEstudiante.class);
            startActivity(in);
        }else if(view.getId()==R.id.listarPrin){
            Intent in=new Intent(principal.this,listar.class);
            startActivity(in);
        }
    }
}
