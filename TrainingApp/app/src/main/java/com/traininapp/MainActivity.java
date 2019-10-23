package com.traininapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        //TODO Javadoc, imports, SPACE! Flytta alla dummys hit.
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

        // Adding dummy Sessions
        if (planner.getSessionList().isEmpty()){
            initializeDummySessions(planner);
        }
    }

    private void initializeDummySessions(Planner planner){
        List<Exercise> eList = new ArrayList<>();
        planner.addSession(new Session("Löpning", LocalDate.now(), eList,R.drawable.workout_5));
        planner.addSession(new Session("Yoga", LocalDate.now().plusDays(1),eList, R.drawable.workout_2));
        planner.addSession(new Session("Armträning", LocalDate.now().plusDays(2), eList,R.drawable.workout_4));
        planner.addSession(new Session("Hjärngympa", LocalDate.now().plusDays(3),eList,R.drawable.workout_1));
    }
}
