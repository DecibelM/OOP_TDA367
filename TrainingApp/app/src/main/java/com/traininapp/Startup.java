package com.traininapp;

import android.app.Application;
import android.database.Cursor;

import com.traininapp.Model.Database.CarExTable;
import com.traininapp.Model.Database.DatabaseHelper;
import com.traininapp.Model.Database.SessionTable;
import com.traininapp.Model.Database.StrExTable;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class Startup extends Application {

    /**
     * Code running when the app starts.
     * Creates objects of the data in
     * the database.
     */

    @Override
    public void onCreate(){
        super.onCreate();
        Repository repository;
        repository = Repository.getInstance();

       // myDb = new DatabaseHelper(this);
        SessionTable sessionTable = new SessionTable(this);
        StrExTable strExTable = new StrExTable(this);
        CarExTable carExTable = new CarExTable(this);

        Cursor routinesInDB = sessionTable.getData();
        Cursor strExInDB = strExTable.getData();
        Cursor carExInDB = carExTable.getData();

        while(routinesInDB.moveToNext()){
            routinesInDB.getString(0);
            List<Exercise> exerciseList = new ArrayList<>();

            while(strExInDB.moveToNext()){

                if (routinesInDB.getString(0).equals(strExInDB.getString(1))){

                    StrengthExercise strengthExercise = new StrengthExercise(strExInDB.getString(2), strExInDB.getInt(3), strExInDB.getInt(4), strExInDB.getInt(5));
                    exerciseList.add(strengthExercise);
                }
            }
            while(carExInDB.moveToNext()){

                if (routinesInDB.getString(0).equals(carExInDB.getString(1))){

                    CardioExercise cardioExercise = new CardioExercise(carExInDB.getString(2), carExInDB.getInt(3), carExInDB.getInt(4));
                    exerciseList.add(cardioExercise);
                }
            }
            repository.getUser().addRoutine(routinesInDB.getString(0), exerciseList);
            strExInDB.moveToFirst();
            carExInDB.moveToFirst();
        }
    }
}