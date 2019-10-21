package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CarExTable {
    private final DatabaseHelper myDb;

    private static final String COL_1 = "ID";
    private static final String COL_2 = "ROUTINE_NAME";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "DISTANCE";
    private static final String COL_5 = "TIME";

    public CarExTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }

    public void insertCarExData(String routine_name, String name, double distance, double time){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, routine_name);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, distance);
        contentValues.put(COL_5, time);

        db.insert(myDb.getCarExTableName(), null, contentValues);
    }

    public Cursor getCarExData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getCarExTableName(), null);
        return res;
    }

    public boolean updateData(int id, String routineName, String name, double distance, double time ){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, routineName);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, distance);
        contentValues.put(COL_5, time);

        db.update(myDb.getRoutineTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getRoutineTableName(), "ID = ?", new String[] {id});


    }

}
