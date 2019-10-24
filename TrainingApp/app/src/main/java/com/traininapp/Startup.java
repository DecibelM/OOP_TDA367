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
            List<Exercise> exerciseList = new ArrayList<>();
            int sessionID = sessionsInDB.getInt(0);

            while(strExInDB.moveToNext()){
                int strExSessionID = strExInDB.getInt(1);

                if (sessionID == strExSessionID){
                    String strExName = strExInDB.getString(2);
                    int sets = strExInDB.getInt(3);
                    int reps = strExInDB.getInt(4);
                    double weight = strExInDB.getInt(5);

                    StrengthExercise strengthExercise = new StrengthExercise(strExName, sets, reps, weight);
                    exerciseList.add(strengthExercise);
                }
            }
            while(carExInDB.moveToNext()){
                int carExSessionID = carExInDB.getInt(1);
                if (sessionID == carExSessionID){
                    String carExName = carExInDB.getString(2);
                    double time = carExInDB.getInt(3);
                    double distance = carExInDB.getInt(4);
                    CardioExercise cardioExercise = new CardioExercise(carExName, time, distance);
                    exerciseList.add(cardioExercise);
                }
            }

            String sessionName = sessionsInDB.getString(1);
            String sessionDate = sessionsInDB.getString(2);
            int sessionImage = sessionsInDB.getInt(3);
            if(convert(sessionDate).isAfter(LocalDate.now().minusDays(1))){
                repository.addSession(sessionName, exerciseList,convert(sessionDate), sessionImage);
            }// else{
             //   sessionTable.deleteData(String.valueOf(sessionsInDB.getString(0)));
            //}


            strExInDB.moveToFirst();
            carExInDB.moveToFirst();
        }
     /*   sessionsInDB.moveToFirst();
        carExInDB.moveToFirst();

        while(carExInDB.moveToNext()){
            isHere = false;
            while(sessionsInDB.moveToNext()){

                if (sessionsInDB.getInt(0) == (carExInDB.getInt(1))){
                    isHere = true;
                }
            }

            if(isHere == false){
                carExTable.deleteData(String.valueOf(carExInDB.getString(0)));
                Toast.makeText(this, "aaaa", Toast.LENGTH_SHORT).show();
            }

            sessionsInDB.moveToFirst();

        }*/


    }

    static LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}