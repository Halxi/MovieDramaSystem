package assignment3.fit4039.monash.mds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by jeric on 7/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MovieDataBase";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movie.CREATE_STATEMENT + ";");
        db.execSQL(Cinema.CREATE_STATEMENT + ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Movie.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + Cinema.TABLE_NAME);
        onCreate(db);
    }

    public void AddCinema(Cinema cinema) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cinema.COLUMN_CINEMANAME, cinema.getCinemaname());
        values.put(Cinema.COLUMN_PRICE, cinema.getPrice());
        values.put(Cinema.COLUMN_ADDRESS, cinema.getCinemaaddress());
        values.put(Cinema.COLUMN_CINEMATAG, cinema.getCinematag());
        db.insert(Cinema.TABLE_NAME, null, values);
        db.close();
    }
    public HashMap<String, Cinema> GetAllCinema() {
        HashMap<String, Cinema> cinemas = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Cinema.TABLE_NAME, null);
        if(cursor.moveToFirst()) {
            do {
                Cinema cinema = new Cinema(cursor.getString(0), cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3));
                cinemas.put(cinema.getCinemaname(), cinema);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return cinemas;
    }
    public void RemoveCinema(Cinema cinema)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Cinema.TABLE_NAME,
                cinema.COLUMN_CINEMANAME + " = ?",
                new String[] {cinema.getCinemaname()});
    }

    public void AddMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_NAME, movie.getMoviename());
        values.put(Movie.COLUMN_VERSION, movie.getMovieversion());
        values.put(Movie.COLUMN_RATE, movie.getMovierate());
        values.put(Movie.COLUMN_SHORTINTRO, movie.getShortintro());
        db.insert(Movie.TABLE_NAME, null, values);
        db.close();
    }
    public HashMap<String, Movie> GetAllMovie() {
        HashMap<String, Movie> movies = new LinkedHashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Movie.TABLE_NAME, null);
        // Add each person to hashmap (Each row has 1 person)
        if(cursor.moveToFirst()) {
            do {
                Movie movie = new Movie(cursor.getString(0), cursor.getString(1), cursor.getDouble(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                movies.put(movie.getMoviename(), movie);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return movies;
    }
    public void RemoveMovie(Movie movie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Movie.TABLE_NAME,
                movie.COLUMN_NAME + " = ?",
                new String[] {movie.getMoviename()});
    }


}
