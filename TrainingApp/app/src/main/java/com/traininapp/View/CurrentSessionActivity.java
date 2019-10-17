package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.R;
import com.traininapp.viewModel.CurrentSessionViewModel;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrentSessionActivity extends AppCompatActivity {

    private List<Exercise> exerciseList = new ArrayList<>();

    private ViewModel viewModel;
    private LocalDate selectedDate;
    private Time time;
    private Button doneBtn;
    private TextView sessionName;
    private TextView sessionDate;
    private TextView sessionTime;
    private Intent intent;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_session);


        intent = getIntent();

        doneBtn = findViewById(R.id.btnDoneID);
        sessionName = findViewById(R.id.sessionName);
        sessionDate = findViewById(R.id.sessionDate);
        sessionTime = findViewById(R.id.sessionTime);
        viewModel = new CurrentSessionViewModel();

        sessionName.setText(intent.getStringExtra("Session"));
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone();
            }
        });

    }

    public void sessionDone(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
