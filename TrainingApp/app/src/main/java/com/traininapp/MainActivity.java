package com.traininapp;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;

import com.traininapp.Model.Planning.StrengthExercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The activity in which the application starts
 */
public class MainActivity extends AppCompatActivity {

    Repository repo = Repository.getInstance();

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

        // Adding dummy Sessions
        if (repo.getSessionList().isEmpty()){
            initializeDummySessions();
        }
    }

    /**
     * Method to add dummy sessions to the list
     */
    private void initializeDummySessions(){
        List<Exercise> eList = new ArrayList<>();
        eList.add(new CardioExercise("Spring",10,10));
        eList.add(new StrengthExercise("Lyft",10,100,10));
        eList.add(new CardioExercise("Spring2",100,10));
        eList.add(new StrengthExercise("Lyft2",10,10,10));

        repo.addSession("Löpning", eList, LocalDate.now(),R.drawable.workout_5);
        repo.addSession("Yoga", eList, LocalDate.now().plusDays(1), R.drawable.workout_2);
        repo.addSession("Armträning", eList, LocalDate.now().plusDays(2),R.drawable.workout_4);
        repo.addSession("Hjärngympa", eList,LocalDate.now().plusDays(3),R.drawable.workout_1);
    }
}
