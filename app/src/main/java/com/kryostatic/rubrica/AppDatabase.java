package com.kryostatic.rubrica;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contatti.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContattiDao contattiDao();
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context c) {
        if (instance == null) {
            instance = Room.databaseBuilder(c,AppDatabase.class,"Contatti")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
