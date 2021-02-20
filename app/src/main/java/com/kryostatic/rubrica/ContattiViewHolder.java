package com.kryostatic.rubrica;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class ContattiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView titolo,iniziali,fullscreen,modifica,elimina;
    Contatti x;
    AlertDialog.Builder builder;
    static AppDatabase db;
    static Home m;
    static QueryRicerca y;

    public ContattiViewHolder(@NonNull View itemView) {
        super(itemView);
        titolo = itemView.findViewById(R.id.titolo);
        iniziali = itemView.findViewById(R.id.iniziali);
        fullscreen = itemView.findViewById(R.id.fullscreen);
        modifica = itemView.findViewById(R.id.modifica);
        elimina = itemView.findViewById(R.id.elimina);

        fullscreen.setOnClickListener(this);
        modifica.setOnClickListener(this);
        elimina.setOnClickListener(this);
    }

    public void setP(Contatti x) {
        this.x = x;
        titolo.setText(x.getNome()+" "+x.getCognome());
        iniziali.setText(x.getNome().substring(0,1)+x.getCognome().substring(0,1));
    }


    @Override
    public void onClick(View v) {
        if(v.getContext() instanceof Home){
            m = (Home) v.getContext();
            db = AppDatabase.getInstance(m);
            ContattiDao contatti = db.contattiDao();
            if(v.getId() == R.id.modifica){
                Intent i2 = new Intent(m,ModificaContatto.class);
                i2.putExtra("contatto",x);
                m.startActivity(i2);
            }else if(v.getId() == R.id.fullscreen){
                Intent i = new Intent(m,MainActivity.class);
                i.putExtra("contatto",x);
                m.startActivity(i);
            }else if(v.getId() == R.id.elimina) {
                builder = new AlertDialog.Builder(m,R.style.MyDialog);
                builder.setMessage(R.string.eliminare)
                        .setCancelable(false)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contatti.deleteContatto(x);
                                Toast.makeText(m.getApplicationContext(),R.string.confermaelimina,
                                        Toast.LENGTH_SHORT).show();
                                Intent i3 = new Intent(m,Home.class);
                                m.startActivity(i3);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.titoloelimina);
                alert.show();
            }
        }else{
            y = (QueryRicerca) v.getContext();
            db = AppDatabase.getInstance(y);
            ContattiDao contatti = db.contattiDao();
            if(v.getId() == R.id.modifica){
                Intent i2 = new Intent(y,ModificaContatto.class);
                i2.putExtra("contatto",x);
                y.startActivity(i2);
            }else if(v.getId() == R.id.fullscreen){
                Intent i = new Intent(y,MainActivity.class);
                i.putExtra("contatto",x);
                y.startActivity(i);
            }else if(v.getId() == R.id.elimina) {
                builder = new AlertDialog.Builder(y,R.style.MyDialog);
                builder.setMessage(R.string.eliminare)
                        .setCancelable(false)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contatti.deleteContatto(x);
                                Toast.makeText(y.getApplicationContext(),R.string.confermaelimina,
                                        Toast.LENGTH_SHORT).show();
                                Intent i3 = new Intent(y,Home.class);
                                y.startActivity(i3);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.titoloelimina);
                alert.show();
            }
        }
    }
}
