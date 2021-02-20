package com.kryostatic.rubrica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificaContatto extends AppCompatActivity implements View.OnClickListener {

    EditText nomeans,cognomeans,ntelefonoans,emailans,indirizzoans;
    Button confermamodifica;
    static Contatti a;
    static AppDatabase db;
    static ContattiDao contatti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_contatto);
        db = AppDatabase.getInstance(getApplicationContext());
        contatti = db.contattiDao();

        nomeans = findViewById(R.id.nomeans);
        cognomeans = findViewById(R.id.cognomeans);
        ntelefonoans = findViewById(R.id.ntelefonoans);
        emailans = findViewById(R.id.emailans);
        indirizzoans = findViewById(R.id.indirizzoans);
        confermamodifica = findViewById(R.id.confermamodifica);
        confermamodifica.setOnClickListener(this);

        Intent i = getIntent();
        if(i != null){
            a = (Contatti) i.getSerializableExtra("contatto");
            nomeans.setText(a.getNome());
            cognomeans.setText(a.getCognome());
            ntelefonoans.setText(a.getNtelefono());
            emailans.setText(a.getEmail());
            indirizzoans.setText(a.getIndirizzo());;

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confermamodifica){
            a.setNome(nomeans.getText().toString());
            a.setCognome(cognomeans.getText().toString());
            a.setNtelefono(ntelefonoans.getText().toString());
            a.setEmail(emailans.getText().toString());
            a.setIndirizzo(indirizzoans.getText().toString());
            contatti.updateContatto(a);
            Toast.makeText(this,R.string.confermamodifica,
                    Toast.LENGTH_SHORT).show();
            Intent i3 = new Intent(this,Home.class);
            startActivity(i3);
        }

    }
}