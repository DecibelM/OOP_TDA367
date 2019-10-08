package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.traininapp.MainActivity;
import com.traininapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreateRoutine extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;

    //boolean to see which type of exercise is currently selected
    private boolean isStrength = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
        Button btnAddnewExersice = findViewById(R.id.btnAddExerciseID);
        Button btnDone = findViewById(R.id.btnDoneID);
        final  LinearLayout rowStrExerciseInfoID = findViewById(R.id.rowStrExerciseInfoID);
        final LinearLayout rowCarExerciseInfoID = findViewById(R.id.rowCarExerciseInfoID);

        //hide the titles for cardio exercises when activity starts
        rowCarExerciseInfoID.setVisibility(View.GONE);

        //depending on if toggle is enabled, hide or show relevant titles
        //also change boolean isStrength
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

        btnAddnewExersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isStrength){
                    createStrRow();
                } else{
                    createCarRow();
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
    }

    //open new activity
    public void openActivity(){
        Intent intent = new Intent (this, PickDate.class);
        startActivity(intent);
    }

    //create a new cardio fragment in the row
    public void createCarRow(){
        Fragment fragment;
        fragment = new FragCarRow();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.displayRowsID, fragment);
        fragmentTransaction.commit();
    }

    //create a new strength fragment in the row
    public void createStrRow(){
        Fragment fragment;
        fragment = new FragStrRow();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.displayRowsID, fragment);
        fragmentTransaction.commit();
    }

    //hide or show row
    public void selectType(LinearLayout visRow, LinearLayout gonRow){
        visRow.setVisibility(View.VISIBLE);
        gonRow.setVisibility(View.GONE);
    }
}