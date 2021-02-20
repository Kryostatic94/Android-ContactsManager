package com.kryostatic.rubrica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

public class QueryRicerca extends AppCompatActivity{
    RecyclerView lista;
    Button aggiungi;
    ImageButton cerca;
    static List<Contatti> contattifiltrati;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        lista = findViewById(R.id.lista);
        Intent i = getIntent();
        if(i != null){
            contattifiltrati = (List<Contatti>) i.getSerializableExtra("risultati");

        }
        ContattiAdapter adp = new ContattiAdapter(contattifiltrati);
        lista.setAdapter(adp);
        lista.setLayoutManager(new LinearLayoutManager(this));

        aggiungi = findViewById(R.id.aggiungi);
        cerca = findViewById(R.id.cerca);
        aggiungi.setVisibility(View.GONE);
        cerca.setVisibility(View.GONE);
    }
}