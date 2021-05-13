package com.arthurpasqualon.influencerlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class InfluencerDAO {

    public static void insert (Influencer influencer, Context context){
        ContentValues values = new ContentValues();
        values.put("name", influencer.name);
        values.put("username", influencer.username);
        values.put("category", influencer.category);

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        sqLiteDatabase.insert("influencers", null, values);
    }

    public static void delete(int id, Context context) {
        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.delete("influencers", "id ="+id, null);

    }

    public static List<Influencer> getInfluencers(Context context) {
        List<Influencer> list = new ArrayList<>();

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM influencers ORDER BY name", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Influencer influencer = new Influencer();
                influencer.id = cursor.getInt(0);
                influencer.name = cursor.getString(1);
                influencer.username = cursor.getString(2);
                influencer.category = cursor.getString(3);

                list.add(influencer);
            }while (cursor.moveToNext());
        }
        return list;
    }
}