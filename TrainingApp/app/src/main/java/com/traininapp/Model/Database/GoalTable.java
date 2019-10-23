package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GoalTable {
    private final DatabaseHelper myDb;

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "TARGET";
    private static final String COL_4 = "PROGRESS";


    public GoalTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }

    public void insertData(String name, double target, double progress){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, target);
        contentValues.put(COL_4, progress);

        db.insert(myDb.getGoalTableName(), null, contentValues);
    }

    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getGoalTableName(), null);
        return res;
    }

    public boolean updateData(int id, String name, double target, double progress){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, target);
        contentValues.put(COL_4, progress);

        db.update(myDb.getGoalTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getGoalTableName(), "ID = ?", new String[] {id});

    }
}