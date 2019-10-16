package com.traininapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Routine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_upcoming, R.id.navigation_calendar, R.id.navigation_goals)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Initializing the singleton repo
        Repository repo = Repository.getInstance();

        // Fetching the Users planner
        Planner planner = repo.getUser().getPlanner();


        // TODO Gör detta till en metod endast?
        // Adding Exercises to Planner's exerciseList
        if (planner.getExerciseList().isEmpty()){
            initializeExercises(planner);
        }

        // Adding dummy Routines
        if (repo.getUser().getRoutineList().isEmpty()){
            initializeDummyRoutines(planner);
        }

        // Adding dummy Sessions
        if (planner.getSessionList().isEmpty()){
            initializeDummySessions(planner);
        }

    }

    /**
     * Method to initialize the Planner's list with pre-made exercises
     * @param planner The User's Planner
     */
    private void initializeExercises(Planner planner){

        planner.addExercise(new Exercise("Barbell row"));
        planner.addExercise(new Exercise("Deadlift"));
        planner.addExercise(new Exercise("Squat"));
        planner.addExercise(new Exercise("Bench press"));
        planner.addExercise(new Exercise("Overhead press"));

    }

    private void initializeDummyRoutines(Planner planner) {

        // Creating Routine A and Routine B for StrongLifts 5x5
        Routine routineA = new Routine("5x5 Routine A");
        Routine routineB = new Routine("5x5 Routine B");

        // Adding Exercises to Routine A
        routineA.addExerciseToList(planner.getExercise("Squat"));
        routineA.addExerciseToList(planner.getExercise("Overhead press"));
        routineA.addExerciseToList(planner.getExercise("Deadlift"));

        // Adding Exercises to Routine B
        routineB.addExerciseToList(planner.getExercise("Squat"));
        routineA.addExerciseToList(planner.getExercise("Bench press"));
        routineA.addExerciseToList(planner.getExercise("Barbell row"));

        // Adding Routines to Planner's routineList
        planner.addRoutine(routineA);
        planner.addRoutine(routineB);

    }

    private void initializeDummySessions(Planner planner){

        planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

    }
}
