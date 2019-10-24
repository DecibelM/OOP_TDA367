package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The class responsible for the
 * cardioexercisetable in the database
 */


public class CarExTable {

    private final DatabaseHelper myDb;

    //All the columns in the table
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SESSION_ID";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "TIME";
    private static final String COL_5 = "DISTANCE";

    public CarExTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }

    public void insertData(int session_id, String name, double time, double distance){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, session_id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, time);
        contentValues.put(COL_5, distance);

        db.insert(myDb.getCarExTableName(), null, contentValues);
    }

    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getCarExTableName(), null);
        return res;
    }

    public void clearTable()   {
        SQLiteDatabase db = myDb.getWritableDatabase();

        db.delete(myDb.getCarExTableName(), null,null);
    }

    public boolean updateData(int id, int routineName, String name, double distance, double time ){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, routineName);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, distance);
        contentValues.put(COL_5, time);

        db.update(myDb.getCarExTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getCarExTableName(), "ID = ?", new String[] {id});

    }

}