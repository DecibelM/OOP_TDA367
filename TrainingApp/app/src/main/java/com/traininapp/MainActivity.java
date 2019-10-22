package com.traininapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traininapp.Model.*;
import com.traininapp.Model.Planning.Exercise; // Samma här som nedanför. Vet att du tog bort den Maria men behövs tillfälligt för test.
import com.traininapp.Model.Planning.Planner;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session; // Borde kanske tas bort innan inlämning, används mest för test nu

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

        Repository repo = Repository.getInstance();


        Planner planner = repo.getUser().getPlanner();


        // Adding dummy Sessions
        if (planner.getSessionList().isEmpty()){
            planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
            planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
            planner.addSession("Yoga2", LocalDate.now().plusDays(1),R.drawable.workout_2);
            planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
            planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);
            System.out.println(planner.getSessionList().get(1).toString() + " Activity");
            Session session = planner.getSessionList().get(0);
            session.addCardioExercise("Spring",10,10);
            session.addStrengthExercise("Lyft",10,100,10);
            session.addCardioExercise("Spring2",100,10);
            session.addStrengthExercise("Lyft2",10,10,10);
            session.addCardioExercise("Spring3",100,100);
            session.addStrengthExercise("Lyft3",100,100,100);

        }

/*        planner.addSession("Löpning", LocalDate.of(2019,10,7));
        planner.addSession("Styrketräning", LocalDate.of(2019,10,7));
        planner.addSession("Yoga", LocalDate.of(2019,10,8));
        planner.addSession("Armträning", LocalDate.of(2019,10,9));
        planner.addSession("Ben 1", LocalDate.of(2019,10,11));
        planner.addSession("Ben 2", LocalDate.of(2019,10,11));
        planner.addSession("Ben 3", LocalDate.of(2019,10,11));*/

        Session session = new Session("Spring", LocalDate.now(),R.drawable.workout_5);
        session.addCardioExercise("Spring",1,1);

        printSessionDetails(planner);
            initializeDummySessions(planner);
        }





    private void initializeDummySessions(Planner planner){

        planner.addSession("Löpning", LocalDate.now(),R.drawable.workout_5);
        planner.addSession("Yoga", LocalDate.now().plusDays(1),R.drawable.workout_2);
        planner.addSession("Armträning", LocalDate.now().plusDays(2),R.drawable.workout_4);
        planner.addSession("Hjärngympa", LocalDate.now().plusDays(3),R.drawable.workout_1);

    }



    public void printSessionDetails(Planner planner){

        int i = 1;

        for (Session session : planner.getSessionList()){

            System.out.println("");
            System.out.print(i + ". ");
            System.out.println("Name of session: " + session.getName());
            System.out.println("Date of session: " + session.getDate().toString());

            if (session.getExerciseList().isEmpty()) {
                System.out.println("There are no exercises in session " + session.getName());
            } else {
                System.out.println("Exercises in session: ");
                for (Exercise exercise : session.getExerciseList()){
                    System.out.println("   " + exercise.getName());
                }
            }
            i++;
        }
    }
}
