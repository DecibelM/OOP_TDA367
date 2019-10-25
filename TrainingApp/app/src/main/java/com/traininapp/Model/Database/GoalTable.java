package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GoalTable {

    /**
     * The class responsible for the
     * goaltable in the database
     * Author: Isak
     */

    private final DatabaseCreator myDb;

    //All the columns in the table
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "TARGET";


    public GoalTable(Context context) {
        this.myDb = new DatabaseCreator(context);
    }

    //Method for inserting data into table
    public void insertData(String name, double target){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, target);

        db.insert(myDb.getGoalTableName(), null, contentValues);
    }

    //Method for accessing data in the table
    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getGoalTableName(), null);
        return res;
    }
}