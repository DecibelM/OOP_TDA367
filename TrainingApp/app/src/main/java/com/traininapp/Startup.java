package com.traininapp;

import android.app.Application;
import android.database.Cursor;
import android.widget.Toast;

import com.traininapp.Model.Database.CarExTable;
import com.traininapp.Model.Database.SessionTable;
import com.traininapp.Model.Database.StrExTable;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
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

        SessionTable sessionTable = new SessionTable(this);
        StrExTable strExTable = new StrExTable(this);
        CarExTable carExTable = new CarExTable(this);

        Cursor sessionsInDB = sessionTable.getData();
        Cursor strExInDB = strExTable.getData();
        Cursor carExInDB = carExTable.getData();


        while(sessionsInDB.moveToNext()){
            sessionsInDB.getString(0);
            List<Exercise> exerciseList = new ArrayList<>();

            while(strExInDB.moveToNext()){

                if (sessionsInDB.getInt(0) == (strExInDB.getInt(1))){

                    StrengthExercise strengthExercise = new StrengthExercise(strExInDB.getString(2), strExInDB.getInt(3), strExInDB.getInt(4), strExInDB.getInt(5));
                    exerciseList.add(strengthExercise);
                }
            }
            while(carExInDB.moveToNext()){

                if (sessionsInDB.getInt(0) == carExInDB.getInt(1)){

                    CardioExercise cardioExercise = new CardioExercise(carExInDB.getString(2), carExInDB.getInt(3), carExInDB.getInt(4));
                    exerciseList.add(cardioExercise);
                }
            }

            if(convert(sessionsInDB.getString(2)).isAfter(LocalDate.now().minusDays(1))){
                repository.getUser().getPlanner().addSession(sessionsInDB.getString(1), convert(sessionsInDB.getString(2)),exerciseList, sessionsInDB.getInt(3));
            } else{
                sessionTable.deleteData(String.valueOf(sessionsInDB.getString(0)));
            }


            System.out.println("Sessiontable: " + sessionsInDB.getString(1));
            strExInDB.moveToFirst();
            carExInDB.moveToFirst();
        }


    }

    static LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}