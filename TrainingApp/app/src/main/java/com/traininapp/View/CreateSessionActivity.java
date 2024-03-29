package com.traininapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.traininapp.MainActivity;

import com.traininapp.Database.CarExTable;
import com.traininapp.Database.SessionTable;
import com.traininapp.Database.StrExTable;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;

import com.traininapp.R;
import com.traininapp.viewModel.CreateSessionViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The Activity class which holds the view of creating a Session
 * @author Mathias, Isak and a tiny amount of Adam.
 */
public class CreateSessionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // Declaring elements
    private EditText txtEnterSessionName;
    private TextView txtSelectedDate;
    private ImageView imgSessionIcon;
    private int image;

    // Lists for created fragments
    private List<FragStrRow> listStrFrag = new ArrayList<>();
    private List<FragCarRow> listCarFrag = new ArrayList<>();

    // List for exercises
    private List<Exercise> exerciseList = new ArrayList<>();

    // Date of session, set to today's date by default
    private LocalDate selectedDate = LocalDate.now();

    // Initializing the ViewModel
    private CreateSessionViewModel viewModel;

    private FragmentTransaction fragmentTransaction;
    private boolean control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Attaching the ViewModel to Activity
        viewModel = ViewModelProviders.of(this).get(CreateSessionViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        // Declaring the elements
        Button btnDone = findViewById(R.id.btnDoneID);
        TextView txtAddStrExercise = findViewById(R.id.txtAddStrExerciseID);
        TextView txtAddCarExercise = findViewById(R.id.txtAddCarExerciseID);
        Spinner spnrIcon = findViewById(R.id.spnrIconID);
        String[] iconsStrArray = getResources().getStringArray(R.array.iconsStringArray);
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);
        txtSelectedDate = findViewById(R.id.txtSelectedDateID);
        imgSessionIcon = findViewById(R.id.imgSessionIconID);

        // Setting image to Strength by default
        image = getResources().getIdentifier("workout_1", "drawable", getPackageName());

        //Checks if you are coming from the calendar and then sets the date accordingly
        isDateSelectedAlready();

        // Updating text to match selectedDate, today's date by default
        txtSelectedDate.setText(selectedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));

        // Setting adapter for Spinner and add Listener
        spnrIcon.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, iconsStrArray));
        spnrIcon.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        // Clicking on Add exercise text adds a strength exercise row
        txtAddStrExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStrFragRow();
            }
        });

        // Clicking on Add exercise text adds a cardio exercise row
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
        if (control) {

            SessionTable sessionTable = new SessionTable(getApplicationContext());
            sessionTable.insertData(sessionName, selectedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), image, 0);


            // Adding Session to users list
            viewModel.addSession(sessionName, exerciseList, selectedDate, image);

            for(Exercise exercise : exerciseList) {

                if (exercise instanceof StrengthExercise) {
                    StrExTable strExTable = new StrExTable(getApplicationContext());

                    strExTable.insertData(sessionTable.getLatestTable(),
                    exercise.getName(),
                            ((StrengthExercise) exercise).getSets(),
                            ((StrengthExercise) exercise).getReps(),
                            ((StrengthExercise) exercise).getWeight());
                }
            }

            for(Exercise exercise : exerciseList) {

                if (exercise instanceof CardioExercise) {
                    CarExTable carExTable = new CarExTable(getApplicationContext());

                    carExTable.insertData(sessionTable.getLatestTable(),
                            exercise.getName(),
                            ((CardioExercise) exercise).getRunningTime(),
                            ((CardioExercise) exercise).getDistance());
                }
            }

            // Give feedback that the Session has been saved
            String toastMessage = "Session: " + sessionName + " has been saved!";
            Toast.makeText(CreateSessionActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            Toast.makeText(CreateSessionActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        }

            // Directing the user to Upcoming session view
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    /**
     * Method which adds another row fragment, allowing the user to add exercises
     */
    // TODO Bryt ut det gemensamma i dessa två till en. Dumt med samma kod två gånger
    public void createCarFragRow() {

        // Create the fragment
        FragCarRow fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragment = new FragCarRow();
        listCarFrag.add(fragment);

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
        FragStrRow fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragment = new FragStrRow();
        listStrFrag.add(fragment);

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

    /**
     * Method to check if string is at least one character long. If not, setting control to false
     *
     * @param name String input from user
     */
    public void checkNameLength(String name) {
        if (name.length() == 0) {
            String toastMessage = "No name entered!";
            Toast.makeText(CreateSessionActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            control = false;
        }
    }

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
            if (fr.saveInfo() != null && control) {
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

    /**
     * Private class used by spinner for setting the Session image
     */
    private class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {

            if (position == 0){
                imgSessionIcon.setImageResource(R.drawable.workout_1);
                image = getResources().getIdentifier("workout_1", "drawable", getPackageName());
            } else {
                imgSessionIcon.setImageResource(R.drawable.workout_5);
                image = getResources().getIdentifier("workout_5", "drawable", getPackageName());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Empty necessary method
        }
    }

    /**
     * Method used when adding a Session from the CalenderView. It pre-fills the date which was
     * already selected in the CalenderView
     */
    public void isDateSelectedAlready() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getBooleanExtra("FROMCALENDAR",true)) {

                String pastDate = intent.getStringExtra("DATE");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d - MM - yyyy");

                selectedDate = LocalDate.parse(pastDate, formatter);
            }
        }
    }
}