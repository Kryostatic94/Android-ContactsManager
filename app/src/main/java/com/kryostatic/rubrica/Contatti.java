package com.kryostatic.rubrica;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Contatti")
public class Contatti implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "cognome")
    private String cognome;
    @ColumnInfo(name = "numero_telefono")
    private String ntelefono;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "indirizzo")
    private String indirizzo;

    public Contatti(String nome, String cognome, String ntelefono, String email, String indirizzo) {
        this.nome = controlloevuoto(nome);
        this.cognome = controlloevuoto(cognome);
        this.ntelefono = controlloevuoto(ntelefono);
        this.email = controlloevuoto(email);
        this.indirizzo = controlloevuoto(indirizzo);
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setNome(String nome) {
        this.nome = controlloevuoto(nome);
    }

    public void setCognome(String cognome) {
        this.cognome = controlloevuoto(cognome);
    }

    public void setNtelefono(String ntelefono) {
        this.ntelefono = controlloevuoto(ntelefono);
    }

    public void setEmail(String email) {
        this.email = controlloevuoto(email);
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = controlloevuoto(indirizzo);
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNtelefono() {
        return ntelefono;
    }

    public String getEmail() {
        return email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String controlloevuoto(String x){
        if (x.length() == 0){
            x = " ";
            return x;
        }
        return x;
    }

    @Override
    public String toString() {
        return "Contatti{" +
                "uid=" + uid +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ntelefono='" + ntelefono + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
