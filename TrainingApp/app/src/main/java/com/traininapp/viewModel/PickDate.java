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
import com.traininapp.Model.Planner;
import com.traininapp.R;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickDate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {


    Planner planner;

    // Dummy list of routines
    List<String> routineList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        Button button = findViewById(R.id.btnPickDate);
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

        button.setOnClickListener(new View.OnClickListener() {
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

        // Clicking the "Done" button, after choosing Routine and Date for the Session
        // TODO Change
        btnSaveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSaveSession();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView = findViewById(R.id.txtPickedDate);
        textView.setText(currentDateString);

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
     * TODO Change text of button
     */
    public void clickSaveSession(){



        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void addSessionToList(String name, LocalDate date){
        planner.addSession(name, date);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
