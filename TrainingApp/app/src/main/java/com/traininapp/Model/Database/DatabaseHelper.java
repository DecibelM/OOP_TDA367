package com.traininapp.Model.Database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.traininapp.Model.Planning.Routine;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trainingapp.db";

    //All tables
    private static final String ROUTINE_TABLE = "routine_table";
    private static final String STREX_TABLE = "strex_table";
    private static final String CAREX_TABLE = "carex_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ROUTINE_TABLE +" (NAME TEXT PRIMARY KEY)");
        db.execSQL("create table " + STREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, ROUTINE_NAME TEXT, NAME TEXT, WEIGHT REAL, SETS INTEGER, REPS INTEGER)");
        db.execSQL("create table " + CAREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, ROUTINE_NAME TEXT, NAME TEXT, DISTANCE REAL, TIME INTEGER)");
    }

    //Upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ROUTINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+STREX_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+CAREX_TABLE);

        onCreate(db);
    }



    //Get the names of all tables
    public String getStrexTableName(){
        return STREX_TABLE;
    }

    public String getRoutineTableName(){
        return ROUTINE_TABLE;
    }

    public String getCarExTableName(){
        return CAREX_TABLE;
    }

}
