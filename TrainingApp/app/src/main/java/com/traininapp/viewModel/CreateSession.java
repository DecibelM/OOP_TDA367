package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
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
        tblLayCar.setVisibility(View.GONE);


        togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    tblLayStr.setVisibility(View.GONE);
                    tblLayCar.setVisibility(View.VISIBLE);

                } else {
                    // The toggle is disabled
                    tblLayStr.setVisibility(View.VISIBLE);
                    tblLayCar.setVisibility(View.GONE);


                }
            }
        });

    }




}
