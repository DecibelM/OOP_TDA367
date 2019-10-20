package com.traininapp.viewModel;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.View.DatePickerFragment;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateSession2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    // INITIALIZING THE ELEMENTS

    // EditTexts
    private EditText txtEnterSessionName;

    // Buttons
    private Button btnAddCar, btnAddStr, btnPickDate, btnDone;

    // TextViews
    private TextView txtDate;

    // List for all created fragments
    private List<FragStrRow> listStrFrag = new ArrayList<>();
    private List<FragCarRow> listCarFrag = new ArrayList<>();

    private LocalDate selectedDate = LocalDate.now();

    private Repository repo;

    private Boolean control;

    //List for all exercises
    private List<Exercise> exerciseList = new ArrayList<>();

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session2);

        // Defining the repo
        repo = Repository.getInstance();

        // Defining Buttons
        btnAddCar = findViewById(R.id.btnAddCardioID);
        btnAddStr = findViewById(R.id.btnAddStrID);
        btnPickDate = findViewById(R.id.btnPickDateID);
        btnDone = findViewById(R.id.btnSaveSessionID);

        // Defining EditTexts
        txtEnterSessionName = findViewById(R.id.etxtSessionNameID);

        // Defining TextViews
        txtDate = findViewById(R.id.txtDateID);

        // Adding listener to Strength button, creating a new row
        btnAddStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStrRow();
            }
        });

        // Adding listener to Cardio button, creating a new row
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCarRow();
            }
        });

        // Adding listener to Save session button
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSaveSession();
            }
        });

        // Clicking the "Date" button to decide date of Routine
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    /**
     * A method to update a string to match the selected date
     * @param datePicker -
     * @param year Year
     * @param month Month
     * @param dayOfMonth Day of month
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        // Updating the string shown to the date the user selected
        txtDate.setText(currentDateString);

        // Updating selectedDate to the date selected by user
        selectedDate = LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Method called when pressing the Save session button. Directs the user to UpcomingSessionView
     */
    public void clickSaveSession() {

        // Fetching name of Session
        String sessionName = txtEnterSessionName.getText().toString();

        // Adding the created Exercises to list
        addExercises();

        Session session = new Session(sessionName, selectedDate, exerciseList);

        System.out.println("Session name: " + session.getName());
        System.out.println("Selected date: " + session.getDate().toString());
        for (Exercise exercise : session.getExerciseList()){
            System.out.println("Exercise in list: " + exercise);
        }

        // Adding the selected Session to the User's Planner's list of Sessions
        repo.getUser().getPlanner().addSession(sessionName, selectedDate, exerciseList);

        // Directing the user to UpcomingSession page again
        Intent intent = new Intent(this, MainActivity.class);

        // Starting activity
        startActivity(intent);
    }

    public void addExercises(){

        // Remove all previously added exercises
        exerciseList.clear();

        // Set boolean control true, used for seeing if
        // everything goes okay when saving the routine
        control = true;

        String sessionName = txtEnterSessionName.getText().toString();

        fragStrToStrExList();
        fragCarToCarExList();

        checkName(sessionName);
        checkNameLength(sessionName);

        // If no fragments returned null and name is unique
        if (control) {

            //Give feedback that the routine has been saved
            String toastMessage = "Session '" + sessionName + "' has been saved!";
            Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();

            txtEnterSessionName.setText("");
        }
    }

    /**
     * Method called when pressing Add Cardio button. Creates a new FragCarRow.
     */
    public void createCarRow() {
        //create the fragment
        FragCarRow fragment;
        fragment = new FragCarRow();

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayCarRowsID, fragment);
        //Add it to the list of all created Cardio fragments
        listCarFrag.add(fragment);
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();

    }

    /**
     * Method called when pressing Add Strength button. Creates a new FragStrRow.
     */
    public void createStrRow() {
        //create the fragment
        FragStrRow fragment;
        fragment = new FragStrRow();

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayStrRowsID, fragment);
        //Add it to the list of all created Strength fragments
        listStrFrag.add(fragment);
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }

    // If the fragment had been removed remove it from the list of added exercises
    public void removeDeletedExercises() {
        if (exerciseList.get(exerciseList.size() - 1).getName() == "REMOVE ME") {
            exerciseList.remove(exerciseList.size() - 1);
        }
    }

    // If failing to save exercise
    public void failSave() {
        exerciseList.clear();
        control = false;
    }

    // Checks the name of all added routines to see if entered name is unique
    public void checkName(String name){

        for (int i = 0; i < repo.getUser().getRoutineList().size(); i++){

            if (repo.getUser().getRoutineList().get(i).getName().equals(name)){
                String toastMessage = "Name: " + name + " is not unique";
                Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();
                control = false;
            }
        }
    }

    public void checkNameLength(String name){
        if(name.length() == 0){
            String toastMessage = "No name entered!";
            Toast.makeText(CreateSession2.this, toastMessage, Toast.LENGTH_SHORT).show();
            control = false;
        }
    }

    public void fragStrToStrExList(){

        // Go through the list of all created strength fragments
        for (FragStrRow fr : listStrFrag) {

            // If saveInfo (in FragStrRow) did not return null, add to list of exercises
            if (fr.saveInfo() != null) {
                exerciseList.add(fr.saveInfo());

                //remove exercises of deleted fragments
                removeDeletedExercises();

            //if it had returned null
            } else {
                //run failSave() and break from loop
                failSave();
                break;
            }
        }
    }

    public void fragCarToCarExList() {

        // Go through the list of all created cardio fragments
        for (FragCarRow fr : listCarFrag) {

            // If saveInfo (in FragCarRow) did not return null, add to list of exercises
            // and if control is still true (no strengthexercises returned null)
            if (fr.saveInfo() != null && control == true) {
                exerciseList.add(fr.saveInfo());

                //remove exercises of deleted fragments
                removeDeletedExercises();

            //if it had returned null
            } else {

                //run failSave() and break from loop
                failSave();
                break;
            }
        }
    }
}
