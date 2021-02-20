package com.kryostatic.rubrica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {
    RecyclerView lista;
    Button aggiungi;
    ImageButton cerca;
    AlertDialog.Builder builder;
    private String queryricerca = "";
    static AppDatabase db;
    static ContattiDao contatti;
    static List<Contatti> contattirl,contattifiltrati;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        aggiungi = findViewById(R.id.aggiungi);
        cerca = findViewById(R.id.cerca);
        aggiungi.setOnClickListener(this);
        cerca.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lista = findViewById(R.id.lista);
        db = AppDatabase.getInstance(getApplicationContext());
        contatti = db.contattiDao();
        contattirl = contatti.getAll();
        ContattiAdapter adp = new ContattiAdapter(contattirl);
        lista.setAdapter(adp);
        lista.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.aggiungi){
            Home m = (Home) v.getContext();
            Intent i3 = new Intent(this,AggiungiContatto.class);
            m.startActivity(i3);
        }else if(v.getId() == R.id.cerca){
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setTextColor(getResources().getColor(R.color.white));
            builder = new AlertDialog.Builder(this,R.style.MyDialog);
            builder.setMessage(R.string.lookup)
                    .setView(input)
                    .setCancelable(false)
                    .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            queryricerca = input.getText().toString()+"%";
                            if(contatti.listaRicercati(queryricerca).isEmpty()){
                                contattifiltrati= contattirl;
                            }else{
                                contattifiltrati= contatti.listaRicercati(queryricerca);
                            }
                            Intent i3 = new Intent(Home.this,QueryRicerca.class);
                            i3.putExtra("risultati",(ArrayList<Contatti>)contattifiltrati);
                            startActivity(i3);

                        }
                    })
                    .setNegativeButton(R.string.anulla, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle(R.string.titolocerca);
            alert.show();
        }
    }
}