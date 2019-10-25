package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The class responsible for the
 * strengthexercisetable in the database
 */

public class StrExTable {
    private final DatabaseCreator myDb;

    //All the columns in the table
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SESSION_ID";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "SETS";
    private static final String COL_5 = "REPS";
    private static final String COL_6 = "WEIGHT";


    public StrExTable(Context context) {
        this.myDb = new DatabaseCreator(context);
    }

    //Method for inserting data into table
    public void insertData(int session_id, String name,int sets, int reps, double weight){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, session_id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, sets);
        contentValues.put(COL_5, reps);
        contentValues.put(COL_6, weight);

        db.insert(myDb.getStrexTableName(), null, contentValues);
    }

    //Method for accessing data in the table
    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getStrexTableName(), null);
        return res;
    }

    //Method for removing all data in the table
    public void clearTable()   {
        SQLiteDatabase db = myDb.getWritableDatabase();

        db.delete(myDb.getStrexTableName(), null,null);
    }


    public boolean updateData(int id, String routineName, String name, double weight, int sets, int reps ){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, routineName);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, weight);
        contentValues.put(COL_5, sets);
        contentValues.put(COL_6, reps);

        db.update(myDb.getStrexTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getStrexTableName(), "ID = ?", new String[] {id});

    }
}
