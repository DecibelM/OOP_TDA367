package com.traininapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.Planner;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    Repository model;
    CalendarViewModel cvm;
    UpcomingSessionsViewModel upcomingSessionsViewModel;

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

        //model = new Repository();
        //cvm = new CalendarViewModel(model);
        //upcomingSessionsViewModel = new UpcomingSessionsViewModel(model);
        Repository repo = Repository.getInstance();

        Planner planner = repo.getUser().getPlanner();
        planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

    }

    public CalendarViewModel getCvm() {
        return cvm;
    }

    public UpcomingSessionsViewModel getUpcomingSessionsViewModel(){
        return upcomingSessionsViewModel;
    }
}
