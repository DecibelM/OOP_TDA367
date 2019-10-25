package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The class responsible for the
 * cardioexercisetable in the database
 * Author: Isak
 */


public class CarExTable {

    private final DatabaseCreator myDb;

    //All the columns in the table
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SESSION_ID";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "TIME";
    private static final String COL_5 = "DISTANCE";

    public CarExTable(Context context) {
        this.myDb = new DatabaseCreator(context);
    }

    //Method for inserting data into table
    public void insertData(int session_id, String name, double time, double distance){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, session_id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, time);
        contentValues.put(COL_5, distance);

        db.insert(myDb.getCarExTableName(), null, contentValues);
    }

    //Method for accessing data in the table
    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getCarExTableName(), null);
        return res;
    }

    //Method for removing all data in the table
    public void clearTable()   {
        SQLiteDatabase db = myDb.getWritableDatabase();

        db.delete(myDb.getCarExTableName(), null,null);
    }
}