package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.View.DatePickerFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateRoutine extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // Declaring elements
    private EditText txtEnterSessionName;
    private TextView txtSelectedDate;
    private Button btnAddStrength, btnSelectDate, btnAddCardio;
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

    //boolean to see which type of exercise is currently selected
    private boolean isStrength = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        // Initializing the repository
        repository = Repository.getInstance();

        // Initializing elements
        btnAddCardio = findViewById(R.id.btnAddCardioExerciseID);
        btnAddStrength = findViewById(R.id.btnAddExerciseID);
        btnDone = findViewById(R.id.btnDoneID);
        btnSelectDate = findViewById(R.id.btnSelectDateID);
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);
        txtSelectedDate = findViewById(R.id.txtSelectedDateID);

        // Updating text to match selectedDate, today's date by default
        txtSelectedDate.setText(selectedDate.toString());

        btnAddStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStrFragRow();
            }
        });

        btnAddCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCarFragRow();
            }
        });

        // Clicking the Date button, directs the user to a calendar
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
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
        selectedDate = LocalDate.of(year, month, dayOfMonth);
        txtSelectedDate.setText(selectedDate.toString());
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
            Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();

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

    // hide or show which type of exercise is selected, depending on toggle
    public void selectType(LinearLayout visRow, LinearLayout gonRow) {
        visRow.setVisibility(View.VISIBLE);
        gonRow.setVisibility(View.GONE);
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
                Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
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