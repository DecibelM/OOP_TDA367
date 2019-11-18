package com.traininapp;

import android.app.Application;
import android.database.Cursor;
import com.traininapp.Database.CarExTable;
import com.traininapp.Database.GoalTable;
import com.traininapp.Database.SessionTable;
import com.traininapp.Database.StrExTable;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/**
 * Code running when the app starts.
 * Creates objects of the data in
 * the database.
 *
 * Author: Isak
 */

public class Startup extends Application {

    //All the variables for Session
    private int sessionID;
    private String sessionName;
    private String sessionDate;
    private int sessionImage;
    private int isFinished;

    //All the variables for StrengthExercise
    private int strExSessionID;
    private String strExName;
    private int sets;
    private int reps;
    private double weight;

    //All the variables for CardioExercise
    private  int carExSessionID;
    private String carExName;
    private double time;
    private double distance;

    //All the variables for Goals
    private String goalName;
    private double target;
    private Repository repository;

    @Override
    public void onCreate(){
        super.onCreate();
        repository = Repository.getInstance();

        SessionTable sessionTable = new SessionTable(this);
        StrExTable strExTable = new StrExTable(this);
        CarExTable carExTable = new CarExTable(this);
        GoalTable goalTable = new GoalTable(this);

        Cursor sessionsInDB = sessionTable.getData();
        Cursor strExInDB = strExTable.getData();
        Cursor carExInDB = carExTable.getData();
        Cursor goalsInDb = goalTable.getData();

        //Goes through all sessions and gives them the correct exercises
        while(sessionsInDB.moveToNext()){
            List<Exercise> exerciseList = new ArrayList<>();
            sessionID = sessionsInDB.getInt(0);

            while(strExInDB.moveToNext()){
                strExSessionID = strExInDB.getInt(1);

                if (sessionID == strExSessionID){
                    strExName = strExInDB.getString(2);
                    sets = strExInDB.getInt(3);
                    reps = strExInDB.getInt(4);
                    weight = strExInDB.getDouble(5);

                    StrengthExercise strengthExercise = new StrengthExercise(strExName, sets, reps, weight);
                    exerciseList.add(strengthExercise);
                }
            }
            while(carExInDB.moveToNext()){
                carExSessionID = carExInDB.getInt(1);
                if (sessionID == carExSessionID){
                    carExName = carExInDB.getString(2);
                    time = carExInDB.getDouble(3);
                    distance = carExInDB.getDouble(4);
                    CardioExercise cardioExercise = new CardioExercise(carExName, time, distance);
                    exerciseList.add(cardioExercise);
                }
            }

            sessionName = sessionsInDB.getString(1);
            sessionDate = sessionsInDB.getString(2);
            sessionImage = sessionsInDB.getInt(3);
            isFinished = sessionsInDB.getInt(4);

            if (isFinished == 0) {
                repository.addSession(sessionName, exerciseList,convert(sessionDate), sessionImage, false);
            } else{
                repository.addSession(sessionName, exerciseList,convert(sessionDate), sessionImage, true);
            }

            strExInDB.moveToFirst();
            carExInDB.moveToFirst();
        }

        //Recreates all goals from the database
        while(goalsInDb.moveToNext()){
            goalName = goalsInDb.getString(1);
            target = goalsInDb.getDouble(2);
            repository.createGoal(goalName, target);
        }
    }

    //Convert string to date
    static LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}