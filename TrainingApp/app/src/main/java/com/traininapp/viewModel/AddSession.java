package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.traininapp.MainActivity;
import com.traininapp.Model.DatePickerFragment;
import com.traininapp.Model.Model;
import com.traininapp.Model.Planner;
import com.traininapp.Model.Session;
import com.traininapp.R;
import com.traininapp.View.UpcomingFragment;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSession extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private String selectedRoutine;
    private LocalDate selectedDate;

    // Dummy list of routines
    List<String> routineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        Button btnPickDate = findViewById(R.id.btnPickDate);
        Button btnSaveSession = findViewById(R.id.btnSaveSession);
        Button btnOpenCreateSession = findViewById(R.id.btnOpenCreateRoutineID);
        Spinner spnrRoutineList = findViewById(R.id.spnrRoutineList);

        //Add dummy text to list of Routines
        routineList.add("Running");
        routineList.add("Swimming");
        routineList.add("Walking");

        ArrayAdapter<CharSequence> spnRoutineAdapter = ArrayAdapter.createFromResource(this, R.array.sessions, android.R.layout.simple_spinner_item);
        spnRoutineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrRoutineList.setAdapter(spnRoutineAdapter);
        spnrRoutineList.setOnItemSelectedListener(this);

        // Clicking the "Date" button to decide date of Routine
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // Clicking the "Create routine" button
        btnOpenCreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCreateSession();
            }
        });

        // Clicking the "Add session" button, after choosing Routine and Date for the Session
        btnSaveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSaveSession();
            }
        });

    }

    /**
     * A method to update a string to match the selected date
     * @param datePicker ???
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
        TextView textView = findViewById(R.id.txtPickedDate);
        textView.setText(currentDateString);

        // Updating selectedDate to the date selected by user
        selectedDate = LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Method of what happens after clicking the "Create session" button. Directs the user
     * to create a session.
     */
    public void clickCreateSession(){
        Intent intent = new Intent(this, CreateRoutine.class);

        startActivity(intent);
    }

    /**
     * Method of what happens after clicking the "Done" button. Directs the user to the
     * Upcoming sessions view
     */
    public void clickSaveSession(){

/*        // TODO Make this work
        // Adding the selected Session to the User's Planner's list of Sessions
        addSessionToList(selectedRoutine,selectedDate);*/

        // Directing the user to UpcomingSession page again
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("SELECTED_ROUTINE", selectedRoutine);
        //intent.putExtra("SELECTED_DATE", selectedDate.toString());

        startActivity(intent);
    }

/*    *//**
     * A method to add the created session to the Planners list of Sessions
     * @param
     * @param
     *//*
    public void addSessionToList(String name, LocalDate date){

        Session session = new Session(name, date);

        upcomingSessionsViewModel.getListOfSessions().add(session);

        // Printing out the sessions in the list to make sure it has been added
        for (Session s : model.getUser().getPlanner().getSessionList()){
            System.out.println(s);
        }

    }*/

    // TODO Currently updating selectedRoutin or not? Might have to return a new object
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // Update the name of the selected Routine when clicking on Routine from list
        selectedRoutine = adapterView.getItemAtPosition(i).toString();

        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}