package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateRoutine extends AppCompatActivity implements Serializable {

    // Declaring elements
    private EditText txtEnterSessionName;
    private Button btnAddNewExercise, btnDone;

    // Lists for created fragments
    private List<FragStrRow> listStrFrag = new ArrayList<>();
    private List<FragCarRow> listCarFrag = new ArrayList<>();

    // List for exercises
    private List<Exercise> exerciseList = new ArrayList<>();

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
        ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
        btnAddNewExercise = findViewById(R.id.btnAddExerciseID);
        btnDone = findViewById(R.id.btnDoneID);
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);
        final LinearLayout rowStrExerciseInfoID = findViewById(R.id.rowStrExerciseInfoID);
        final LinearLayout rowCarExerciseInfoID = findViewById(R.id.rowCarExerciseInfoID);

        //hide the titles for cardio exercises when activity starts
        rowCarExerciseInfoID.setVisibility(View.GONE);

        //depending on if toggle is enabled, hide or show relevant titles
        //also change boolean isStrength, for creating the right exercise
        togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //for cardio
                if (isChecked) {
                    selectType(rowCarExerciseInfoID, rowStrExerciseInfoID);
                    isStrength = false;
                }
                //for strength
                else {
                    // The toggle is disabled
                    selectType(rowStrExerciseInfoID, rowCarExerciseInfoID);
                    isStrength = true;
                }
            }
        });

        btnAddNewExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if strength is chosen, create strength row
                if (isStrength) {
                    createFragRow();
                } else {
                    //if strength is not chosen, create cardio row
                    createFragRow();
                }
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
            repository.getUser().getPlanner().addSession(sessionName, LocalDate.now(), exerciseList);

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
    public void createFragRow() {

        // Create the fragment
        Fragment fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (isStrength) {
            fragment = new FragStrRow();
            listStrFrag.add((FragStrRow) fragment);

        } else {
            fragment = new FragCarRow();
            listCarFrag.add((FragCarRow) fragment);
        }

        // Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayRowsID, fragment);

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