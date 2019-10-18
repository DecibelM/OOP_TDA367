package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.CurrentSessionViewModel;
import com.traininapp.viewModel.FragCarRow;
import com.traininapp.viewModel.FragStrRow;
import com.traininapp.viewModel.UpcomingSessionsViewModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentSessionActivity extends AppCompatActivity {

    private List<Exercise> exerciseList = new ArrayList<>();

    private CurrentSessionViewModel viewModel;
    private LocalDate selectedDate;
    private Time time;
    private Button doneBtn;
    private Button addExerciseBtn;
    private TextView sessionName;
    private TextView sessionDate;
    private TextView sessionTime;
    private Intent intent;
    private Repository model;
    private SimpleDateFormat dateFormat;
    private List<FragStrRow> listStrFrag;
    private List<FragCarRow> listCarFrag;
    private EditText txtEnterExName;

    private FragmentTransaction fragmentTransaction;

    /* Some teststuff */
    private Session session;
    private LocalDate localDate;
    private List<Routine> routineList;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_session);

        //List for all created fragments. Should probably be in the viewModel
        listStrFrag = new ArrayList<>();
        listCarFrag = new ArrayList<>();

        dateFormat = new SimpleDateFormat("dd - MM - yyyy");

        intent = getIntent();

        txtEnterExName = findViewById(R.id.txtEnterExNameID);

        doneBtn = findViewById(R.id.btnDoneID);
        sessionName = findViewById(R.id.sessionName);
        sessionDate = findViewById(R.id.sessionDate);
        sessionTime = findViewById(R.id.sessionTime);
        addExerciseBtn = findViewById(R.id.addExerciseBtn);
        viewModel = new CurrentSessionViewModel();

        sessionName.setText(intent.getStringExtra("Session"));

        // Test stuff
        routineList = new ArrayList<>();
        localDate = LocalDate.now();
        session = new Session("Test 1", routineList,localDate );

        loadSession(session);
        testingStuff();


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone();
            }
        });


        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createCarRow();

            }
        });

    }

    public void sessionDone(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public LocalDate getSelectedDate() {
        return selectedDate; }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate; }

    public Time getTime() {
        return time; }

    public void setTime(Time time) {
        this.time = time; }

    public void loadSession(Session session){

        sessionName.setText(session.getName());

        String string = session.getDate().toString();

        sessionDate.setText(string);
    }

    public void loadExercises(List list, Session session){

        if(session != null){
        list.addAll(session.getExerciseList());

        } else {
            System.out.println("Null pointer bitch");
        }
    }

    //create a new cardio fragment in the row

    public void createCarRow() {
        //create the fragment
        final FragCarRow fragment;
        fragment = new FragCarRow();

        fragmentHandeler(listCarFrag,fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragment.setCardioValues((CardioExercise) session.getExerciseList().get(0), fragment);
            }
        }, 1);

    }

    public void fragmentHandeler(List list, Fragment fragment){


        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayRowsID, fragment);
        //Add it to the list of all created Cardio fragments
        list.add(fragment);
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }


    public void testingStuff(){

        session.addCardioExercise("Spring MF", 10,10);

        loadExercises(listCarFrag,session);

        createCarRow();



    }



}
