package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
// singleton
abstract public class WordDataBase extends RoomDatabase {

    static private WordDataBase INSTANCE; //单例

    static public WordDataBase getDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();
}
