package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;
import com.traininapp.viewModel.CurrentSessionViewModel;
import com.traininapp.View.FragCarRow;
import com.traininapp.View.FragStrRow;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private String sessionID;
    int test;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_session);

        //List for all created fragments. Should probably be in the viewModel
        listStrFrag = new ArrayList<>();
        listCarFrag = new ArrayList<>();

        dateFormat = new SimpleDateFormat("dd - MM - yyyy");

        intent = getIntent();


        doneBtn = findViewById(R.id.btnDoneID);
        sessionName = findViewById(R.id.sessionName);
        sessionDate = findViewById(R.id.sessionDate);
        sessionTime = findViewById(R.id.sessionTime);
        addExerciseBtn = findViewById(R.id.addExerciseBtn);
        viewModel = new CurrentSessionViewModel();
        sessionID = intent.getStringExtra("Session");

        //Finds the session
        session = viewModel.getSession(sessionID);

        sessionName.setText(session.getName());
        System.out.println(session);

        loadExercises(session);
        // Test stuff
        routineList = new ArrayList<>();
        localDate = LocalDate.now();

        loadSession(session);
        //testingStuff();


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone();
            }
        });


        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addCardioExercise();

            }
        });

    }

    public void sessionDone(){
        Intent intent = new Intent(this, MainActivity.class);
        //session.finishSession();
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

        //sessionName.setText(session.getName());

        String string = session.getDate().toString();

        sessionDate.setText(string);
    }

    public void loadExercises(Session session){

        if(session != null){
        for (Exercise exercise : session.getExerciseList()){
            if (exercise instanceof CardioExercise)
            createCarRow((CardioExercise) exercise);
            else {
                createStrRow(exercise);
            }
        }

        } else {
            System.out.println("Nullpointer");
        }
    }

    //create a new cardio fragment in the row

    public void createCarRow(final CardioExercise exercise) {
        //create the fragment
        final FragCarRow fragment = new FragCarRow();
        fragment.setExercise(exercise);

        fragmentHandeler(listCarFrag,fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragment.setCardioValues(exercise, fragment);
            }
        }, 1);

    }

    public void createStrRow(final Exercise exercise){
        //create the fragment
        final FragStrRow fragment = new FragStrRow();

        fragmentHandeler(listStrFrag,fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragment.setStrengthValues((StrengthExercise) exercise, fragment);
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

    public  void addCardioExercise(){

        CardioExercise exercise = new CardioExercise("Hej",0,0);
        System.out.println(session.toString());
        session.getExerciseList().add(exercise);

        createCarRow(exercise);
    }


    public void testingStuff(){

        session.addCardioExercise("Spring", 10,10);
        session.addCardioExercise("Fort", 10,10);
        session.addStrengthExercise("Lyft",10,10,10);

        loadExercises(session);





    }



}
