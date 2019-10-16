package com.traininapp;

import android.app.Application;
import android.database.Cursor;

import com.traininapp.Model.DatabaseHelper;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class Startup extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        DatabaseHelper myDb;
        Repository repository;
        repository = Repository.getInstance();

        myDb = new DatabaseHelper(this);

        Cursor routinesInDB = myDb.getRoutineData();
        Cursor strExInDB = myDb.getStrExData();
        Cursor carExInDB = myDb.getCarExData();

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