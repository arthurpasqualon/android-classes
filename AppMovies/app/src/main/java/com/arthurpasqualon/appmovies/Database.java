package com.arthurpasqualon.appmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "AppMovies";

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS movie (" +
                    "" +
                    "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "   name TEXT NOT NULL , " +
                    " year INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /* if( i == 1 && i1 == 2) {
            sqLiteDatabase.execSQL("ALTER TABLE movie ADD COLUMN director TEXT");
        } */

    }
}
