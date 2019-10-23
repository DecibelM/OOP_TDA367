package com.traininapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
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

        // Print details of session (for checking only)
        planner.printSessionDetails();

    }

    private void initializeDummySessions(Planner planner){

        planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);



        Session session = planner.getSessionList().get(0);
        session.addCardioExercise("Spring",10,10);
        session.addStrengthExercise("Lyft",10,100,10);
        session.addCardioExercise("Spring2",100,10);
        session.addStrengthExercise("Lyft2",10,10,10);
        session.addCardioExercise("Spring3",100,100);
        session.addStrengthExercise("Lyft3",100,100,100);

    }
}
