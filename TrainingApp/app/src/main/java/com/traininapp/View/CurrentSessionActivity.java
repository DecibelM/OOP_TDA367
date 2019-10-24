package com.traininapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.traininapp.MainActivity;
import com.traininapp.Model.Database.CarExTable;
import com.traininapp.Model.Database.SessionTable;
import com.traininapp.Model.Database.StrExTable;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.R;
import com.traininapp.viewModel.CurrentSessionViewModel;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * The Activity for handling an already created session. Lets you see what exercises you have, add new ones and letting the app know you have finished the session
 *
 * Authors: Mostly Adam Törnkvist. Everything with the database is done by Isak Magnusson
 */
public class CurrentSessionActivity extends AppCompatActivity {

    // TODO Ta bort skit som inte används längre. Done button kan göras local. Ta bort gamla kommentarer. Javadoc. Fler övriga kommentarer. Bryt ut session och sessionID till CurrentSessionViewModel.
    // TODO REMOVE SPACE! TA bort onödig import

    // Done: Onödiga imports, gammal skit, SPACE, gamla kommentarer, done button

    private TextView sessionName;
    private TextView sessionDate;
    private TextView txtAddStrExercise;
    private TextView txtAddCarExercise;
    private FragmentTransaction fragmentTransaction;
    private Button doneBtn;
    private Button saveBtn;
    private Button goodJobBtn;
    private Button btnDelete;
    private CurrentSessionViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcurrent_session);

        Intent intent = getIntent();

        doneBtn = findViewById(R.id.btnDoneID);
        saveBtn = findViewById(R.id.btnSaveID);
        goodJobBtn = findViewById(R.id.btnGoodJobID);
        btnDelete = findViewById(R.id.btnDeleteID);
        sessionName = findViewById(R.id.txtEnterSessionNameID);
        sessionDate = findViewById(R.id.txtSelectedDateID);
        txtAddStrExercise = findViewById(R.id.txtAddStrExerciseID);
        txtAddCarExercise = findViewById(R.id.txtAddCarExerciseID);

        viewModel = new CurrentSessionViewModel(intent.getStringExtra("Session"),this);

        //Finds the session
        final Session session = viewModel.getCurrentSession();

        loadExercises(viewModel.getSession());
        loadSession(viewModel.getSession());

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone(viewModel.getSession());
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSession(viewModel.getSession());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getRepo().getSessionList().remove(viewModel.getSession());
                SessionTable sessionTable = new SessionTable(getApplicationContext());
                StrExTable strExTable = new StrExTable(getApplicationContext());
                CarExTable carExTable = new CarExTable(getApplicationContext());

                sessionTable.clearTable();
                carExTable.clearTable();
                strExTable.clearTable();

                for(int i = 0; i < viewModel.getRepo().getSessionList().size(); i++){
                    sessionTable.insertData(viewModel.getRepo().getSessionList().get(i).getName(),
                            viewModel.getRepo().getSessionList().get(i).getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
                            viewModel.getRepo().getSessionList().get(i).getSessionImage());


                    for(Exercise exercise : viewModel.getRepo().getSessionList().get(i).getExerciseList()) {

                        if (exercise instanceof StrengthExercise) {

                            strExTable.insertData(sessionTable.getLatestTable(),
                                    exercise.getName(),
                                    ((StrengthExercise) exercise).getSets(),
                                    ((StrengthExercise) exercise).getReps(),
                                    ((StrengthExercise) exercise).getWeight());
                        }
                    }

                    for(Exercise exercise : viewModel.getRepo().getSessionList().get(i).getExerciseList()) {

                        if (exercise instanceof CardioExercise) {

                            carExTable.insertData(sessionTable.getLatestTable(),
                                    exercise.getName(),
                                    ((CardioExercise) exercise).getRunningTime(),
                                    ((CardioExercise) exercise).getDistance());
                        }
                    }

                }
                sessionDelete();

            }
        });

        // Lets you change the name of the session
        sessionName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                session.setName(sessionName.getText().toString());
            }
        });

        // Clicking on Add exercise text adds a strength exercise row
        txtAddStrExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStrengthExercise();
            }
        });

        // Clicking on Add exercise text adds a cardio exercise row
        txtAddCarExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardioExercise();
            }
        });

        goodJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodJob();
            }
        });

        //It takes a moment to initialize the fragments. Therefore you have to wait before doing anything with them
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //fragment.setStrengthValues(exercise, fragment);
                alreadyFinished(session);
            }
        }, 1);
    }

    /**
     * Sets to session to finished. Sending the exercise info to statistics and open the main page
     * @param session selected session
     */
    public void sessionDone(Session session) {
        Intent intent = new Intent(this, MainActivity.class);
        session.finishSession();
        startActivity(intent);
    }

    /**
     * When a session is deleted it opens the main activity
     */
    public void sessionDelete() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * When a session is saved it opens the main activity
     */
    public void sessionSaved(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * Sets the name and date of the session
     * @param session selected session
     */
    public void loadSession(Session session) {
        String string = session.getDate().toString();
        sessionName.setText(session.getName());
        sessionDate.setText(string);
    }

    /**
     * Loads all the exercises currently assigned to the session
     * @param session selected session
     */
    public void loadExercises(Session session) {
        if (session != null) {
            for (Exercise exercise : session.getExerciseList()) {
                if (exercise instanceof CardioExercise)
                    createCarRow((CardioExercise) exercise);
                else {
                    createStrRow((StrengthExercise)exercise);
                }
            }
        }
    }

    /**
     * Creates a new row for a CardioExercise in the Cardioexercise scrollview and loads the values of the exercie
     * @param exercise the cardio exercise for which the row will be created
     */
    public void createCarRow(final CardioExercise exercise) {
        //create the fragment
        final FragCarRow fragment = new FragCarRow();
        //fragment.setExercise(exercise);

        fragmentCardioHandeler(viewModel.getListCarFrag(), fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //fragment.setCardioValues(exercise, fragment);
                fragment.setValues(exercise);
            }
        }, 1);
    }

    /**
     * Creates a new row for a StrengthExercise in the Strengthexercise scrollview and loads the values of the exercie
     * @param exercise the strengthexercise for which the row will be created
     */
    public void createStrRow(final StrengthExercise exercise) {
        //create the fragment
        final FragStrRow fragment = new FragStrRow();
        fragmentStrHandeler(viewModel.getListStrFrag(), fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //fragment.setStrengthValues(exercise, fragment);
                fragment.setValues(exercise);
            }
        }, 1);
    }

    /**
     * Handles and adds the strengthrows
     * @param StrengthRowlist the list of all Strengthexerciserows
     * @param fragment the new fragment for the exercise
     */
    public void fragmentStrHandeler(List StrengthRowlist, Fragment fragment) {

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayStrRowsID, fragment);
        //Add it to the list of all created Cardio fragments
        StrengthRowlist.add(fragment);
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }
    /**
     * Handles and adds the cardiorows
     * @param CardioRowList the list of all Cardioexerciserows
     * @param fragment the new fragment for the exercise
     */
    public void fragmentCardioHandeler(List CardioRowList, Fragment fragment) {

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayCarRowsID, fragment);
        //Add it to the list of all created Cardio fragments
        CardioRowList.add(fragment);
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }

    /**
     * Adds a new CardioExercise to the current Session and creates the new row
     */
    public void addCardioExercise() {

        CardioExercise exercise = new CardioExercise("Cardio", 0, 0);
        createCarRow(exercise);
    }

    /**
     * Adds a new StrengthExercise to the current session and creates the new row
     */
    public void addStrengthExercise() {

        StrengthExercise exercise = new StrengthExercise("Power", 0, 0, 0);
        createStrRow(exercise);
    }

    /**
     * Saves all the exercises as they currently are and opens the main activity
     * @param session Current session
     */
    public void saveSession(Session session){
        session.getExerciseList().clear();

        for(FragCarRow cardio: viewModel.getListCarFrag()){
            session.getExerciseList().add(cardio.saveInfo());

            removeDeletedExercises(session);
        }

        for(FragStrRow strength: viewModel.getListStrFrag()){
            session.getExerciseList().add(strength.saveInfo());

            removeDeletedExercises(session);
        }


        SessionTable sessionTable = new SessionTable(getApplicationContext());
        StrExTable strExTable = new StrExTable(getApplicationContext());
        CarExTable carExTable = new CarExTable(getApplicationContext());

        sessionTable.clearTable();
        carExTable.clearTable();
        strExTable.clearTable();

        for(int i = 0; i < viewModel.getRepo().getSessionList().size(); i++){
            sessionTable.insertData(viewModel.getRepo().getSessionList().get(i).getName(),
                    viewModel.getRepo().getSessionList().get(i).getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
                    viewModel.getRepo().getSessionList().get(i).getSessionImage());

            for(Exercise exercise : viewModel.getRepo().getSessionList().get(i).getExerciseList()) {

                if (exercise instanceof StrengthExercise) {

                    strExTable.insertData(sessionTable.getLatestTable(),
                            exercise.getName(),
                            ((StrengthExercise) exercise).getSets(),
                            ((StrengthExercise) exercise).getReps(),
                            ((StrengthExercise) exercise).getWeight());
                }
            }

            for(Exercise exercise : viewModel.getRepo().getSessionList().get(i).getExerciseList()) {

                if (exercise instanceof CardioExercise) {

                    carExTable.insertData(sessionTable.getLatestTable(),
                            exercise.getName(),
                            ((CardioExercise) exercise).getRunningTime(),
                            ((CardioExercise) exercise).getDistance());
                }
            }
        }
        sessionSaved();
    }

    /**
     * Lets you know you have done a good job and finished an exercise. Takes you back to the main activity.
     */
    public void goodJob(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This method checks if the session has been marked as finished. If it has it hides the save and done buttons and shows the "good job" button. Furthermore it makes the EditTexts uneditable.
     * @param session Current session
     */
    public void alreadyFinished(Session session){
        if (session.isFinished()){
            doneBtn.setVisibility(View.INVISIBLE);
            saveBtn.setVisibility(View.INVISIBLE);
            goodJobBtn.setVisibility(View.VISIBLE);
            txtAddStrExercise.setVisibility(View.INVISIBLE);
            txtAddCarExercise.setVisibility(View.INVISIBLE);

            for (FragStrRow fragment: viewModel.getListStrFrag()) {
                fragment.setEditable(false);
            }

            for (FragCarRow fragment: viewModel.getListCarFrag()){
                fragment.setEditable(false);
            }
        } else {
            doneBtn.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
            goodJobBtn.setVisibility(View.INVISIBLE);
            txtAddStrExercise.setVisibility(View.VISIBLE);
            txtAddCarExercise.setVisibility(View.VISIBLE);

            for (FragStrRow fragment: viewModel.getListStrFrag()) {
                fragment.setEditable(true);
            }

            for (FragCarRow fragment: viewModel.getListCarFrag()){
                fragment.setEditable(true);
            }
        }
    }

    /**
     * Loads all the values for the exercise into the FragStrRow
     * @param strength A fragment for a strengthrow
     * @param exercise the exercise you want to load
     */
    public void setStrength(FragStrRow strength, StrengthExercise exercise){

        DecimalFormat df = new DecimalFormat("###.#");

        strength.setAutPickStrEx(exercise.getName());
        strength.setTxtEnterSets(String.valueOf(exercise.getSets()));
        strength.setTxtEnterReps(String.valueOf(exercise.getReps()));
        strength.setTxtEnterWeight(String.valueOf(df.format(exercise.getWeight())));
    }

    //if the fragment had been removed remove it from the list of added exercises
    public void removeDeletedExercises(Session session) {
        if (session.getExerciseList().get(session.getExerciseList().size() - 1).getName() == "REMOVE ME") {
            session.getExerciseList().remove(session.getExerciseList().size() - 1);
        }
    }
}
