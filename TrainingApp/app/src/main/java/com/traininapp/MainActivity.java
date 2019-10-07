package com.traininapp;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.app.Fragment;
import com.traininapp.Model.*;
import com.traininapp.Model.Session;
import com.traininapp.viewModel.CalendarViewModel;
import com.traininapp.viewModel.TrainingAppViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Model model;
    CalendarViewModel cvm;

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

        model = new Model();
        cvm = new CalendarViewModel(model);

        Planner planner = model.getUser().getPlanner();
        planner.addSession("Löpning", LocalDate.of(2019,10,7));
        planner.addSession("Yoga", LocalDate.of(2019,10,8));
        planner.addSession("Armträning", LocalDate.of(2019,10,9));

    }

    public CalendarViewModel getCvm() {
        return cvm;
    }
}
