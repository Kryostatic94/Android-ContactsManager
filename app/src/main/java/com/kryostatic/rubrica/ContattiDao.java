package com.kryostatic.rubrica;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContattiDao {
    @Query("SELECT * FROM Contatti")
    List<Contatti> getAll();

    @Query("SELECT * FROM Contatti WHERE cognome LIKE :filtro")
    List<Contatti> listaRicercati(String filtro);

    @Insert
    void insertContatto(Contatti user1);

    @Delete
    void deleteContatto(Contatti user1);

    @Update
    void updateContatto(Contatti user1);


}
