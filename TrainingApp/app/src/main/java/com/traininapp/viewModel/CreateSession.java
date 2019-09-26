package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.traininapp.R;

public class CreateSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
        final EditText txtEnterExName = findViewById(R.id.txtEnterExNameID);

        togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    txtEnterExName.setText("is a shoe");
                } else {
                    // The toggle is disabled
                    txtEnterExName.setText("is strength");

                }
            }
        });

    }




}
