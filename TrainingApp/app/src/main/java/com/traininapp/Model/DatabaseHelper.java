package com.traininapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "trainingapp.db";

    public static final String ROUTINE_TABLE = "routine_table";
    public static final String RCOL1 = "NAME";


    public static final String STREX_TABLE = "strex_table";
    public static final String SCOL_1 = "ID";
    public static final String SCOL_2 = "ROUTINE_NAME";
    public static final String SCOL_3 = "NAME";
    public static final String SCOL_4 = "WEIGHT";
    public static final String SCOL_5 = "SETS";
    public static final String SCOL_6 = "REPS";

    public static final String CAREX_TABLE = "carex_table";
    public static final String CCOL_1 = "ID";
    public static final String CCOL_2 = "ROUTINE_NAME";
    public static final String CCOL_3 = "NAME";
    public static final String CCOL_4 = "DISTANCE";
    public static final String CCOL_5 = "TIME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ROUTINE_TABLE +" (NAME TEXT PRIMARY KEY)");
        db.execSQL("create table " + STREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, ROUTINE_NAME TEXT, NAME TEXT, WEIGHT REAL, SETS INTEGER, REPS INTEGER)");
        db.execSQL("create table " + CAREX_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, ROUTINE_NAME TEXT, NAME TEXT, DISTANCE REAL, TIME INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ROUTINE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+STREX_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+CAREX_TABLE);

        onCreate(db);
    }

    public boolean insertRoutineData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RCOL1, name);
        long result =  db.insert(ROUTINE_TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else{
            return true;
        }
    }


    public boolean insertStrExData(String routine_name, String name,double weight, int sets, int reps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCOL_2, routine_name);
        contentValues.put(SCOL_3, name);
        contentValues.put(SCOL_4, weight);
        contentValues.put(SCOL_5, sets);
        contentValues.put(SCOL_6, reps);

        long result =  db.insert(STREX_TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean insertCarExData(String routine_name, String name, double distance, double time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CCOL_2, routine_name);
        contentValues.put(CCOL_3, name);
        contentValues.put(CCOL_4, distance);
        contentValues.put(CCOL_5, time);

        long result =  db.insert(CAREX_TABLE, null, contentValues);
        if (result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor getRoutineData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ROUTINE_TABLE, null);
        return res;
    }

    public Cursor getStrExData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+STREX_TABLE, null);
        return res;
    }

    public Cursor getCarExData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+CAREX_TABLE, null);
        return res;
    }
}
