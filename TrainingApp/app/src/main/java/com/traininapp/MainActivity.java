package com.traininapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    Repository model;
    CalendarViewModel cvm;
    UpcomingSessionsViewModel upcomingSessionsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);




        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_upcoming, R.id.navigation_calendar, R.id.navigation_goals)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //model = new Repository();
        //cvm = new CalendarViewModel(model);
        //upcomingSessionsViewModel = new UpcomingSessionsViewModel(model);
        Repository repo = Repository.getInstance();

        Planner planner = repo.getUser().getPlanner();
        planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);
        planner.addSession("Löpning", LocalDate.of(2019,10,7));
        planner.addSession("Styrketräning", LocalDate.of(2019,10,7));
        planner.addSession("Yoga", LocalDate.of(2019,10,8));
        planner.addSession("Armträning", LocalDate.of(2019,10,9));
        planner.addSession("Ben 1", LocalDate.of(2019,10,11));
        planner.addSession("Ben 2", LocalDate.of(2019,10,11));
        planner.addSession("Ben 3", LocalDate.of(2019,10,11));

        Cursor routinesInDB = myDb.getRoutineData();
        Cursor strExInDB = myDb.getStrExData();
        Cursor carExInDB = myDb.getCarExData();

        /////
        if(routinesInDB.getCount() == 0){
            //message
            Toast.makeText(this, "No routines", Toast.LENGTH_SHORT).show();
        }


        while(routinesInDB.moveToNext()){

            routinesInDB.getString(1);
            List<Exercise> exerciseList = new ArrayList<>();

            while(strExInDB.moveToNext()){

                if (routinesInDB.getString(1).equals(strExInDB.getString(1))){

                    StrengthExercise strengthExercise = new StrengthExercise(strExInDB.getString(2), strExInDB.getInt(3), strExInDB.getInt(4), strExInDB.getInt(5));
                    exerciseList.add(strengthExercise);
                }
            }

            while(carExInDB.moveToNext()){

                if (routinesInDB.getString(1).equals(carExInDB.getString(1))){

                    CardioExercise cardioExercise = new CardioExercise(carExInDB.getString(2), carExInDB.getInt(3), carExInDB.getInt(4));
                    exerciseList.add(cardioExercise);
                }
            }

            repo.getUser().addRoutine(routinesInDB.getString(1), exerciseList);
            strExInDB.moveToFirst();
            carExInDB.moveToFirst();

        }

/////


    }

    public CalendarViewModel getCvm() {
        return cvm;
    }

    public UpcomingSessionsViewModel getUpcomingSessionsViewModel(){
        return upcomingSessionsViewModel;
    }
}
