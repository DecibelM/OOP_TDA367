package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RoutineTable {
    private final DatabaseHelper myDb;

    private static final String COL1 = "ID";


    public RoutineTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }

    public void insertRoutineData(String name){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        db.insert(myDb.getRoutineTableName(), null, contentValues);
    }


    public Cursor getRoutineData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getRoutineTableName(), null);
        return res;
    }

    public boolean updateData(String name){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        db.update(myDb.getRoutineTableName(), contentValues,
                "name = ?",new String[] {name});
        return true;
    }

    public Integer deleteData(String name){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getRoutineTableName(), "NAME = ?", new String[] {name});

    }
}
