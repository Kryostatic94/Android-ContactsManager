package com.kryostatic.rubrica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton chiama, mandaemail, mandasms, trovaindirizzo;
    TextView ans1, ans2,iniziali;
    static Contatti a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chiama = findViewById(R.id.chiamare);
        mandaemail = findViewById(R.id.email);
        mandasms = findViewById(R.id.sms);
        trovaindirizzo = findViewById(R.id.address);

        chiama.setOnClickListener(this);
        mandaemail.setOnClickListener(this);
        mandasms.setOnClickListener(this);
        trovaindirizzo.setOnClickListener(this);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        iniziali = findViewById(R.id.iniziali);

        Intent i = getIntent();
        if(i != null){
            a = (Contatti) i.getSerializableExtra("contatto");
            ans1.setText(a.getNome()+" "+a.getCognome());
            ans2.setText(a.getNtelefono());
            iniziali.setText(a.getNome().substring(0,1)+a.getCognome().substring(0,1));
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ a.getNtelefono()));
                startActivity(intent);
            }else{
                Toast.makeText(this,R.string.nocallpermesso,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.chiamare) {
            if(a.getNtelefono().length() == 0 || a.getNtelefono().equals(" ")){
                Toast.makeText(this,R.string.nonumber,Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ a.getNtelefono()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                return;
            }
            startActivity(intent);
        }else if(v.getId() == R.id.email){
            if(a.getEmail().length() == 0 || a.getEmail().equals(" ")){
                Toast.makeText(this,R.string.noemail,Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+a.getEmail()));
            startActivity(intent);

        }else if(v.getId() == R.id.sms){
            if(a.getNtelefono().length() == 0 || a.getNtelefono().equals(" ")){
                Toast.makeText(this,R.string.nonumber,Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+a.getNtelefono()));
            startActivity(intent);

        }else if(v.getId() == R.id.address){
            if(a.getIndirizzo().length() == 0 || a.getIndirizzo().equals(" ")){
                Toast.makeText(this,R.string.noaddrss,Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+a.getIndirizzo()));
            startActivity(intent);
        }
    }
}