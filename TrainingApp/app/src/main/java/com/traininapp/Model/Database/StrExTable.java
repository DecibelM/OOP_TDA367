package com.traininapp.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StrExTable {
    private final DatabaseHelper myDb;

    private static final String COL_1 = "ID";
    private static final String COL_2 = "ROUTINE_NAME";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "WEIGHT";
    private static final String COL_5 = "SETS";
    private static final String COL_6 = "REPS";


    public StrExTable(Context context) {
        this.myDb = new DatabaseHelper(context);
    }


    public void insertStrExData(String routine_name, String name,double weight, int sets, int reps){
        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, routine_name);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, weight);
        contentValues.put(COL_5, sets);
        contentValues.put(COL_6, reps);

        db.insert(myDb.getStrexTableName(), null, contentValues);
    }

    public Cursor getStrExData(){
        SQLiteDatabase db = myDb.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+myDb.getStrexTableName(), null);
        return res;
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

        db.update(myDb.getRoutineTableName(), contentValues,
                "ID = ?",new String[] {String.valueOf(id)});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = myDb.getWritableDatabase();
        return db.delete(myDb.getRoutineTableName(), "ID = ?", new String[] {id});


    }
}
