package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ToggleButton;

import com.traininapp.R;

public class CreateSession extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
        EditText txtEnterExName = findViewById(R.id.txtEnterExNameID);
        final TableLayout tblLayStr = findViewById(R.id.tblLayStrID);
        final TableLayout tblLayCar = findViewById(R.id.tblLayCarID);
        Button btnAddnewExersice = findViewById(R.id.btnAddExerciseID);
        TableRow rowStrengthExercise = findViewById(R.id.rowStrengthExerciseID);
        EditText txtEnterWSets = findViewById(R.id.txtEnterSetsID);
        EditText txtEnterWReps = findViewById(R.id.txtEntersRepsID);
        EditText txtEnterWWeight = findViewById(R.id.txtEnterWeightID);
        EditText strengthExercise = findViewById(R.id.strengthExerciseID);



        // tblLayStr.setColumnStretchable(0, true);
        //tblLayStr.setColumnStretchable(1, true);


        tblLayCar.setVisibility(View.GONE);


        togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    tblLayStr.setVisibility(View.GONE);
                    tblLayCar.setVisibility(View.VISIBLE);

                } else {
                     //The toggle is disabled
                    tblLayStr.setVisibility(View.VISIBLE);
                     tblLayCar.setVisibility(View.GONE);


                }
            }
        });

        btnAddnewExersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extendTable(tblLayStr);


            }
        });
    }


    private void extendTable(TableLayout table){

        TableRow row = new TableRow(this);

        EditText strEx = new EditText(this);

        EditText bla1 = new EditText(this);
        bla1.setInputType(InputType.TYPE_CLASS_NUMBER);


        EditText bla2 = new EditText(this);
        bla2.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText bla3 = new EditText(this);
        bla3.setInputType(InputType.TYPE_CLASS_NUMBER);

        bla1.setText("");
        bla2.setText("");
        bla3.setText("");

        row.addView(strEx);
        row.addView(bla1);
        row.addView(bla2);
        row.addView(bla3);

        table.addView(row);


    }


}
