package com.arthurpasqualon.influencerlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "InfluencersList";

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS influencers (" +
                "" +
                "   id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "   name TEXT NOT NULL , " +
                "  username TEXT NOT NULL, " +
                "category TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO
    }
}
