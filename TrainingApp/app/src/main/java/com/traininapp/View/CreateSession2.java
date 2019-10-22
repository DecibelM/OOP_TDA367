package com.traininapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateSession2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // Declaring elements
    private EditText txtEnterSessionName;
    private TextView txtSelectedDate, txtAddCarExercise, txtAddStrExercise;
    private FloatingActionButton btnDone;

    // Lists for created fragments
    private List<FragStrRow> listStrFrag = new ArrayList<>();
    private List<FragCarRow> listCarFrag = new ArrayList<>();

    // List for exercises
    private List<Exercise> exerciseList = new ArrayList<>();

    // Date of session, set to today's date by default
    private LocalDate selectedDate = LocalDate.now();

    // Repo
    private Repository repository;


    private FragmentTransaction fragmentTransaction;
    private boolean control;

    // Database stuff, currently not in use
/*    private DatabaseHelper myDb = new DatabaseHelper(this);
    private StrExTable strExTable;
    private CarExTable carExTable;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session_2);

        // Initializing the repository
        repository = Repository.getInstance();

        // Initializing elements
        btnDone = findViewById(R.id.btnDoneID);
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);
        txtSelectedDate = findViewById(R.id.txtSelectedDateID);

        txtAddStrExercise = findViewById(R.id.txtAddStrExerciseID);
        txtAddCarExercise = findViewById(R.id.txtAddCarExerciseID);

        // Updating text to match selectedDate, today's date by default
        txtSelectedDate.setText(selectedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));

        txtAddStrExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStrFragRow();
            }
        });

        txtAddCarExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCarFragRow();
            }
        });

        // Clicking the Date button, directs the user to a calendar
        txtSelectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // Clicking the Done button, saving Session and directing the user to the Upcoming session view
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneClick();
            }
        });
    }

    /**
     * Method which updates selectedDate based on user input
     *
     * @param datePicker -
     * @param year       Year
     * @param month      Month
     * @param dayOfMonth Day of month
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // Updating selectedDate to the date selected by user
        selectedDate = LocalDate.of(year, month+1, dayOfMonth);
        txtSelectedDate.setText(selectedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }

    /**
     * Method called when user press Done button. Saves the information as a new Session and
     * directs the user to the Upcoming Session view
     */
    public void onDoneClick() {

        // Get the Session name from user input
        String sessionName = txtEnterSessionName.getText().toString();

        // Remove all previously added exercises
        exerciseList.clear();

        // Set boolean control true, used for seeing if
        // everything goes okay when saving the routine
        control = true;

        // Using the fragments created to save exercises in exerciseList
        fragStrToStrExList();
        fragCarToCarExList();

        // Controlling if valid user input
        //checkName(sessionName);
        checkNameLength(sessionName);

        // If no fragments returned null and name is unique
        if (control == true) {

            // Adding Session to users list
            repository.getUser().getPlanner().addSession(sessionName, selectedDate, exerciseList);

            // Give feedback that the routine has been saved
            String toastMessage = "Session: " + sessionName + " has been saved!";
            Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();

                        /*RoutineTable routineTable = new RoutineTable(getApplicationContext());
                        routineTable.insertRoutineData(routineName);
                        insertStrExInDB(routineName);
                        insertCarExInDB(routineName);*/

            // Clear Session name field
            txtEnterSessionName.setText("");
        }

        // Directing the user to Upcoming session view
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method which adds another row fragment, allowing the user to add exercises
     */
    public void createCarFragRow() {

        // Create the fragment
        Fragment fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragment = new FragCarRow();
        listCarFrag.add((FragCarRow) fragment);

        // Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayCarRowsID, fragment);

        // Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }

    /**
     * Method which adds Strength exercises row fragment, allowing the user to add exercises
     */
    public void createStrFragRow() {

        // Create the fragment
        Fragment fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragment = new FragStrRow();
        listStrFrag.add((FragStrRow) fragment);

        // Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayStrRowsID, fragment);

        // Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }

    //if the fragment had been removed remove it from the list of added exercises
    public void removeDeletedExercises() {
        if (exerciseList.get(exerciseList.size() - 1).getName() == "REMOVE ME") {
            exerciseList.remove(exerciseList.size() - 1);
        }
    }

    //if failing to save exercise
    public void failSave() {
        exerciseList.clear();
        control = false;
    }

/*    //Checks the name of all added routines to see if entered name is unique
    public void checkName(String name) {
        for (int i = 0; i < repository.getUser().getRoutineList().size(); i++) {
            if (repository.getUser().getRoutineList().get(i).getName().equals(name)) {
                String toastMessage = "Name: " + name + " is not unique";
                Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();
                control = false;
            }
        }
    }*/

    /**
     * Method to check if string is at least one character long. If not, setting control to false
     *
     * @param name String input from user
     */
    public void checkNameLength(String name) {
        if (name.length() == 0) {
            String toastMessage = "No name entered!";
            Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();
            control = false;
        }
    }

/*        public void insertStrExInDB(String name){
            for(Exercise exercise : exerciseList) {
                strExTable = new StrExTable(getApplicationContext());
                if (exercise instanceof StrengthExercise) {
                    strExTable.insertStrExData(name,
                            exercise.getName(),
                            ((StrengthExercise) exercise).getWeight(),
                            ((StrengthExercise) exercise).getSets(),
                            ((StrengthExercise) exercise).getReps());
                }
            }
        }*/

/*         public void insertCarExInDB(String name){
             for(Exercise exercise : exerciseList) {
                 carExTable = new CarExTable(getApplicationContext());
                 if (exercise instanceof CardioExercise) {
                     carExTable.insertCarExData(name,
                             exercise.getName(),
                             ((CardioExercise) exercise).getDistance(),
                             ((CardioExercise) exercise).getRunningTime());
                 }
             }

         }*/

    /**
     * Method used for creating strength Exercises based on data in strength row fragments
     */
    public void fragStrToStrExList() {

        // Go through the list of all created strength fragments
        for (FragStrRow fr : listStrFrag) {

            // If saveInfo (in FragStrRow) did not return null, add to list of exercises
            if (fr.saveInfo() != null) {
                exerciseList.add(fr.saveInfo());

                // Remove exercises of deleted fragments
                removeDeletedExercises();

                // If it had returned null
            } else {

                //run failsave and break from loop
                failSave();

                break;
            }
        }
    }

    /**
     * Method used for creating cardio Exercises based on data in cardio row fragments
     */
    public void fragCarToCarExList() {

        // Go through the list of all created cardio fragments
        for (FragCarRow fr : listCarFrag) {

            // If saveInfo (in FragCarRow) did not return null, add to list of exercises
            // and if control is still true (no strengthexercises returned null)
            if (fr.saveInfo() != null && control == true) {
                exerciseList.add(fr.saveInfo());

                // Remove exercises of deleted fragments
                removeDeletedExercises();

                // If it had returned null
            } else {

                // Run failsave and break from loop
                failSave();

                break;
            }
        }
    }


}