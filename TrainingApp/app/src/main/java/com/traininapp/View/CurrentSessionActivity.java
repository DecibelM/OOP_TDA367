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
import java.util.ArrayList;
import java.util.List;

/**
 * The Acticity for handling an already created session. Lets you see what exercises you have, add new ones and letting the app know you have finished the session
 */
public class CurrentSessionActivity extends AppCompatActivity {

    // TODO Ta bort skit som inte används längre. Done button kan göras local. Ta bort gamla kommentarer. Javadoc. Fler övriga kommentarer. Bryt ut session och sessionID till CurrentSessionViewModel.
    // TODO REMOVE SPACE! TA bort onödig import

    // Done: Onödiga imports, gammal skit, SPACE, gamla kommentarer

    private TextView sessionName;
    private TextView sessionDate;
    private List<FragStrRow> listStrFrag;
    private List<FragCarRow> listCarFrag;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcurrent_session);

        //List for all created fragments. Should probably be in the viewModel
        listStrFrag = new ArrayList<>();
        listCarFrag = new ArrayList<>();

        Intent intent = getIntent();

        Button doneBtn = findViewById(R.id.btnDoneID);
        Button saveBtn = findViewById(R.id.btnSaveID);
        Button btnDelete = findViewById(R.id.btnDeleteID);

        sessionName = findViewById(R.id.txtEnterSessionNameID);
        sessionDate = findViewById(R.id.txtSelectedDateID);
        TextView txtAddStrExercise = findViewById(R.id.txtAddStrExerciseID);
        TextView txtAddCarExercise = findViewById(R.id.txtAddCarExerciseID);
        final CurrentSessionViewModel viewModel = new CurrentSessionViewModel();
        String sessionID = intent.getStringExtra("Session");



        //Finds the session
        final Session session = viewModel.getSession(sessionID);

        loadExercises(session);
        loadSession(session);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionDone(session);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSession(session);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getModel().getSessionList().remove(session);
                SessionTable sessionTable = new SessionTable(getApplicationContext());
                StrExTable strExTable = new StrExTable(getApplicationContext());
                CarExTable carExTable = new CarExTable(getApplicationContext());

                sessionTable.clearTable();
                carExTable.clearTable();
                strExTable.clearTable();

                for(int i = 0; i < viewModel.getModel().getSessionList().size(); i++){
                    sessionTable.insertData(viewModel.getModel().getSessionList().get(i).getName(),viewModel.getModel().getSessionList().get(i).getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), viewModel.getModel().getSessionList().get(i).getSessionImage());


                    for(Exercise exercise : viewModel.getModel().getSessionList().get(i).getExerciseList()) {

                        if (exercise instanceof StrengthExercise) {

                            strExTable.insertData(sessionTable.getLatestTable(),
                                    exercise.getName(),
                                    ((StrengthExercise) exercise).getSets(),
                                    ((StrengthExercise) exercise).getReps(),
                                    ((StrengthExercise) exercise).getWeight());
                        }
                    }

                    for(Exercise exercise : viewModel.getModel().getSessionList().get(i).getExerciseList()) {

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
                addStrengthExercise(session);
            }
        });

        // Clicking on Add exercise text adds a cardio exercise row
        txtAddCarExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardioExercise(session);
            }
        });
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

    public void sessionDelete() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sessionSaved(Session session){
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
     * Creates a new row for a CardioExercise in the Cardioexercise scrollview
     * @param exercise the cardio exercise for which the row will be created
     */
    public void createCarRow(final CardioExercise exercise) {
        //create the fragment
        final FragCarRow fragment = new FragCarRow();
        //fragment.setExercise(exercise);

        fragmentCardioHandeler(listCarFrag, fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //fragment.setCardioValues(exercise, fragment);
                setCardio(fragment, exercise);
            }
        }, 1);



    }

    /**
     * Creates a new row for a StrengthExercise in the Strengthexercise scrollview
     * @param exercise the strengthexercise for which the row will be created
     */
    public void createStrRow(final StrengthExercise exercise) {
        //create the fragment
        final FragStrRow fragment = new FragStrRow();
        fragmentStrHandeler(listStrFrag, fragment);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //fragment.setStrengthValues(exercise, fragment);
                setStrength(fragment, exercise);
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
     * @param session Current session
     */
    public void addCardioExercise(Session session) {

        CardioExercise exercise = new CardioExercise("Cardio", 0, 0);
        session.getExerciseList().add(exercise);
        createCarRow(exercise);
    }

    /**
     * Adds a new StrengthExercise to the current session and creates the new row
     * @param session Current session
     */
    public void addStrengthExercise(Session session) {

        StrengthExercise exercise = new StrengthExercise("Power", 0, 0, 0);
        createStrRow(exercise);
    }

    /**
     * Saves all the exercises as they currently are and opens the main activity
     * @param session Current session
     */
    public void saveSession(Session session){
        session.getExerciseList().clear();

        for(FragCarRow cardio: listCarFrag){
            session.getExerciseList().add(cardio.saveInfo());

            removeDeletedExercises(session);
        }

        for(FragStrRow strength: listStrFrag){
            session.getExerciseList().add(strength.saveInfo());

            removeDeletedExercises(session);
        }


        SessionTable sessionTable = new SessionTable(getApplicationContext());
        StrExTable strExTable = new StrExTable(getApplicationContext());
        CarExTable carExTable = new CarExTable(getApplicationContext());

        sessionTable.clearTable();
        carExTable.clearTable();
        strExTable.clearTable();
        final CurrentSessionViewModel viewModel = new CurrentSessionViewModel();

        for(int i = 0; i < viewModel.getModel().getSessionList().size(); i++){
            sessionTable.insertData(viewModel.getModel().getSessionList().get(i).getName(),viewModel.getModel().getSessionList().get(i).getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), viewModel.getModel().getSessionList().get(i).getSessionImage());


            for(Exercise exercise : viewModel.getModel().getSessionList().get(i).getExerciseList()) {

                if (exercise instanceof StrengthExercise) {

                    strExTable.insertData(sessionTable.getLatestTable(),
                            exercise.getName(),
                            ((StrengthExercise) exercise).getSets(),
                            ((StrengthExercise) exercise).getReps(),
                            ((StrengthExercise) exercise).getWeight());
                }
            }

            for(Exercise exercise : viewModel.getModel().getSessionList().get(i).getExerciseList()) {

                if (exercise instanceof CardioExercise) {

                    carExTable.insertData(sessionTable.getLatestTable(),
                            exercise.getName(),
                            ((CardioExercise) exercise).getRunningTime(),
                            ((CardioExercise) exercise).getDistance());
                }
            }

        }

        sessionSaved(session);

    }

    /**
     * Loads all the values for the exercise into the FragCarRow
     * @param cardio A fragment for a cardiorow
     * @param exercise the exercise you want to load
     */
    public void setCardio(FragCarRow cardio, CardioExercise exercise){
        cardio.setValues(exercise);
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
        strength.setTxtEntersReps(String.valueOf(exercise.getReps()));
        strength.setTxtEnterWeight(String.valueOf(df.format(exercise.getWeight())));
    }



    //if the fragment had been removed remove it from the list of added exercises
    public void removeDeletedExercises(Session session) {
        if (session.getExerciseList().get(session.getExerciseList().size() - 1).getName() == "REMOVE ME") {
            session.getExerciseList().remove(session.getExerciseList().size() - 1);
        }
    }


    /*
    Kvar att göra:
    Fixa så du kan ta bort session. Anting för att du inte vill göra den eller för att du har gymmat klart
    Linka upp CurrentSession med Upcoming Session
    Snygga till med Done knappen
     */


}
