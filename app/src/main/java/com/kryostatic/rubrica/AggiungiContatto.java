package com.kryostatic.rubrica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AggiungiContatto extends AppCompatActivity implements View.OnClickListener {
    EditText nomeans,cognomeans,ntelefonoans,emailans,indirizzoans;
    Button confermamodifica;
    static Contatti a;
    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_contatto);
        db = AppDatabase.getInstance(getApplicationContext());



        nomeans = findViewById(R.id.nomeans);
        cognomeans = findViewById(R.id.cognomeans);
        ntelefonoans = findViewById(R.id.ntelefonoans);
        emailans = findViewById(R.id.emailans);
        indirizzoans = findViewById(R.id.indirizzoans);
        confermamodifica = findViewById(R.id.confermamodifica);
        confermamodifica.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ContattiDao contatti = db.contattiDao();
        Contatti x = new Contatti(nomeans.getText().toString(),cognomeans.getText().toString(),
                ntelefonoans.getText().toString(),emailans.getText().toString(),indirizzoans.getText().toString());
        contatti.insertContatto(x);
        Toast.makeText(this,R.string.addmessage,
                Toast.LENGTH_SHORT).show();
        Intent i3 = new Intent(this,Home.class);
        startActivity(i3);
    }
}