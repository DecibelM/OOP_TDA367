package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//Todo: ADD BOOLEAN


public class SessionTable {
    private final DatabaseHelper myDb;

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "DATE";
    private static final String COL_4 = "IMAGE";

    public SessionTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }

    public void insertData(String name, String DATE, int image){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, DATE);
        contentValues.put(COL_4, image);

        db.insert(myDb.getSessionTable(), null, contentValues);
    }

    public Cursor getData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getSessionTable(), null);
        return res;
    }

    public int getLatestTable(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        String query = "SELECT MAX(id) AS max_id FROM "+ myDb.getSessionTable();
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

        db.update(myDb.getSessionTable(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getSessionTable(), "ID = ?", new String[] {id});

    }
}