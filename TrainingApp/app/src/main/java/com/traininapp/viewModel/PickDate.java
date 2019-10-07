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
import com.traininapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickDate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    List<String> sessionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        Button button = findViewById(R.id.button);
        Button btnOk = findViewById(R.id.btnOkID);
        Button btnOpenCreateSession = findViewById(R.id.btnOpenCreateRoutineID);
        Spinner spnRoutine = findViewById(R.id.spnRoutineID);

        //add all cardio exercises
        sessionList.add("Running");
        sessionList.add("Swimming");
        sessionList.add("Walking");


        ArrayAdapter<CharSequence> spnRoutineAdapter = ArrayAdapter.createFromResource(this, R.array.sessions, android.R.layout.simple_spinner_item);
        spnRoutineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRoutine.setAdapter(spnRoutineAdapter);
        spnRoutine.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnOpenCreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSession();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDone();
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

        TextView textView = findViewById(R.id.textView2);
        textView.setText(currentDateString);

    }

    public void openSession(){
        Intent intent = new Intent(this, CreateSession.class);

        startActivity(intent);
    }

    public void openDone(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
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
