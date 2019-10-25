package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.traininapp.Model.Planning.Session;


/**
 * The class responsible for the
 * sessiontable in the database
 */

public class SessionTable {
    private final DatabaseCreator myDb;

    private Context context;

    //All the columns in the table
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "DATE";
    private static final String COL_4 = "IMAGE";
    private static final String COL_5 = "IS_FINISHED";

    public SessionTable(Context context) {
        this.context = context;
        this.myDb = new DatabaseCreator(context);
    }



    //Method for inserting data into table
    public void insertData(String name, String DATE, int image, int isFinished){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, DATE);
        contentValues.put(COL_4, image);
        contentValues.put(COL_5, isFinished);

        db.insert(myDb.getSessionTableName(), null, contentValues);
    }

    //Method for accessing data in the table
    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getSessionTableName(), null);
        return res;
    }

    //Method for removing all data in the table
    public void clearTable()   {
        SQLiteDatabase db = myDb.getWritableDatabase();

        db.delete(myDb.getSessionTableName(), null,null);
    }


    //Method for getting the ID of the most recently created sessiontable
    public int getLatestTable(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        String query = "SELECT MAX(id) AS max_id FROM "+ myDb.getSessionTableName();
        Cursor res = db.rawQuery(query, null);
        int id = 0;
        if (res.moveToFirst())
        {
            do
            {
                id = res.getInt(0);
            } while(res.moveToNext());
        }
        return id;
    }

    public boolean updateData(int id, String name, String date){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, date);

        db.update(myDb.getSessionTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getSessionTableName(), "ID = ?", new String[] {id});

    }
}