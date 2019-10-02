package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.traininapp.MainActivity;
import com.traininapp.R;
import com.traininapp.View.UpcomingFragment;

import java.util.ArrayList;
import java.util.List;

public class CreateSession extends AppCompatActivity {


    List<String> strActivityList = new ArrayList<>();
    List<String> carActivityList = new ArrayList<>();


    private boolean isStrength = true;
    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);


        parentLinearLayout = findViewById(R.id.tblLayStrID);

        ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
      //  final  LinearLayout rowStrExerciseInfoID = findViewById(R.id.rowStrExerciseInfoID);
      //  final LinearLayout rowCarExerciseInfoID = findViewById(R.id.RowCarExerciseInfoID);
        Button btnAddnewExersice = findViewById(R.id.btnAddExerciseID);
        Button btnDone = findViewById(R.id.btnDoneID);

        AutoCompleteTextView spnPickStrEx = findViewById(R.id.spnPickStrExID);
        AutoCompleteTextView spnPickCarEx = findViewById(R.id.spnPickCarExID);


        //add all strength activites
        strActivityList.add("Bicep Curl");
        strActivityList.add("Dumbbell Curl");
        strActivityList.add("Dumbbell Shoulder Fly");

        ArrayAdapter<String> strActivListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strActivityList);
       // spnPickStrEx.setAdapter(strActivListAdapter);

        //add all cardio activites
        carActivityList.add("Running");
        carActivityList.add("Swimming");
        carActivityList.add("Walking");



        ArrayAdapter<String> carActivListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carActivityList);
      //  spnPickCarEx.setAdapter(carActivListAdapter);

        //rowCarExerciseInfoID.setVisibility(View.GONE);


        togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
             //       rowStrExerciseInfoID.setVisibility(View.GONE);
             //       rowCarExerciseInfoID.setVisibility(View.VISIBLE);
                    isStrength = false;
                } else {
                     //The toggle is disabled
                   // rowStrExerciseInfoID.setVisibility(View.VISIBLE);
                   // rowCarExerciseInfoID.setVisibility(View.GONE);
                    isStrength = true;
                }
            }
        });

        btnAddnewExersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isStrength == true){
                    createRow(R.layout.new_strexercise_row);
               } else{
                    createRow(R.layout.new_carexercise_row);
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

    public void openActivity(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }


    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    public void createRow(int getlayout){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parentLinearLayout.addView(inflater.inflate(getlayout, null));

    }

}
