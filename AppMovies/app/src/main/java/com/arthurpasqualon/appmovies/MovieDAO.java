package com.arthurpasqualon.appmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public static void insert (Movie movie, Context context){
        ContentValues values = new ContentValues();
        values.put("name", movie.name);
        values.put("year", movie.getYear());

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        sqLiteDatabase.insert("movie", null, values);
    }

    public static void edit(Movie movie, Context context){
        ContentValues values = new ContentValues();
        values.put("name", movie.name);
        values.put("year", movie.getYear());

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        sqLiteDatabase.update("movie", values, "id ="+movie.id, null);

    }

    public static void delete(int id, Context context) {
        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        sqLiteDatabase.delete("movie", "id ="+id, null);

    }

    public static List<Movie> getMovies(Context context) {
        List<Movie> list = new ArrayList<>();

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM movie ORDER BY name", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Movie movie = new Movie();
                movie.id = cursor.getInt(0);
                movie.name = cursor.getString(1);
                movie.setYear(cursor.getInt(2));

                list.add(movie);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public static Movie getMovieById(Context context, int id) {

        Database database = new Database(context);
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM movie WHERE id = "+ id, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Movie movie = new Movie();
            movie.id = cursor.getInt(0);
            movie.name = cursor.getString(1);
            movie.setYear(cursor.getInt(2));
            return  movie;
        }
        else {
            return null;
        }
    }
}
