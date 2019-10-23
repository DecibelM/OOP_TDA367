package com.traininapp.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * The class which names and creates all
 * the tables in the database
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Name of the database
    private static final String DATABASE_NAME = "trainingapp.db";

    //All tables
    private static final String STREX_TABLE = "strex_table";
    private static final String CAREX_TABLE = "carex_table";
    private static final String GOAL_TABLE = "goal_table";
    private static final String SESSION_TABLE = "session_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + STREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SESSION_ID INTEGER, NAME TEXT, SETS INTEGER, REPS INTEGER, WEIGHT REAL)");
        db.execSQL("create table " + CAREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SESSION_ID INTEGER, NAME TEXT, DISTANCE REAL, TIME INTEGER)");
        db.execSQL("create table " + GOAL_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TARGET REAL, PROGRESS REAL)");
        db.execSQL("create table " + SESSION_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DATE TEXT, IMAGE INTEGER)");

    }

    //Upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+STREX_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+CAREX_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+GOAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+SESSION_TABLE);

        onCreate(db);
    }

    //Get the names of all tables
    public String getStrexTableName(){
        return STREX_TABLE;
    }

    public String getCarExTableName(){
        return CAREX_TABLE;
    }

    public String getGoalTableName(){
        return GOAL_TABLE;
    }

    public String getSessionTable(){
        return SESSION_TABLE;
    }

}
