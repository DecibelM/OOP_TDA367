package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.View.DatePickerFragment;
import com.traininapp.R;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSession extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private String selectedRoutine;

    private LocalDate selectedDate;
    private UpcomingSessionsViewModel viewModel;

    // Adding buttons
    private Button btnPickDate;
    private Button btnSaveSession;

    // Adding EditTexts
    private EditText txtEnterSessionName;
    private EditText txtExerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        // Attaching the View model to activity
        viewModel = ViewModelProviders.of(this).get(UpcomingSessionsViewModel.class);

        // Initializing Buttons
        btnPickDate = findViewById(R.id.btnPickDateID);
        btnSaveSession = findViewById(R.id.btnSaveSessionID);

        // Initializing EditTexts
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);
        txtExerciseName = findViewById(R.id.txtExerciseNameID);

        // Clicking the "Date" button to decide date of Routine
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
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
        TextView textView = findViewById(R.id.txtDisplayDateID);
        textView.setText(currentDateString);

        // Updating selectedDate to the date selected by user
        selectedDate = LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * Method of what happens after clicking the "Done" button. Directs the user to the
     * Upcoming sessions view
     */
    public void clickSaveSession(){

        // Fetching name of Session
        String sessionName = txtEnterSessionName.getText().toString();

        // Adding the selected Session to the User's Planner's list of Sessions
        viewModel.model.getUser().getPlanner().addSession(sessionName, selectedDate, createExerciseList());

        // Directing the user to UpcomingSession page again
        Intent intent = new Intent(this, MainActivity.class);

        // Starting activity
        startActivity(intent);
    }

    private List<Exercise> createExerciseList() {

        ArrayList<Exercise> listOfExercises = new ArrayList<>();

        String nameOfExercise = txtExerciseName.getText().toString();

        Exercise exercise = new Exercise(nameOfExercise);

        listOfExercises.add(exercise);

        return listOfExercises;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // Update the name of the selected Routine when clicking on Routine from list
        selectedRoutine = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
