package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.traininapp.MainActivity;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.View.DatePickerFragment;
import com.traininapp.Model.Planning.Exercise;
//import com.traininapp.Model.Model  UNCOMMENT ME WHEN MODEL ADDED!
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.Session;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateSession extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //Placeholder list of exercises
    List<Exercise> listTest1 = new ArrayList<>();
    List<Exercise> listTest2 = new ArrayList<>();
    List<Exercise> listTest3 = new ArrayList<>();

    List<Routine> listRoutine = new ArrayList<>(); //REMOVE ME WHEN MODEL ADDED!
    List<Routine> listOfAddedRoutines = new ArrayList<>();

    LocalDate selectedDate;


    private Spinner spnPickRoutine;
    private Button btnPickDate;
    private Button btnOk;
    private Button btnOpenCreateRoutine;
    private Button btnAddRoutine;
    private Button btnUndo;
    private Button btnSave;
    private TextView txtDisplayRoutines;
    private EditText txtEnterSessionName;
    //Model model = new Model(); UNCOMMENT ME WHEN MODEL ADDED!



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);


        btnPickDate = findViewById(R.id.btnPickDateID);
        btnOk = findViewById(R.id.btnOkID);
        btnUndo = findViewById(R.id.btnUndoID);
        btnAddRoutine = findViewById(R.id.btnAddRoutineID);
        btnOpenCreateRoutine = findViewById(R.id.btnOpenCreateRoutineID);
        spnPickRoutine = findViewById(R.id.spnPickRoutineID);
        txtDisplayRoutines = findViewById(R.id.txtDisplayRoutinesID);
        txtEnterSessionName = findViewById(R.id.txtEnterSessionNameID);

        isDateSelectedAlready();

        //Placeholder exercises
        StrengthExercise strEx1 = new StrengthExercise("bicep" ,1,2,3.5);
        StrengthExercise strEx2 = new StrengthExercise("pull up" ,1,2,3);

        CardioExercise carEx1 = new CardioExercise("Running" ,12,45.4);
        CardioExercise carEx2 = new CardioExercise("Swimming" ,55.5,25);

        //add mix of cardio/strength-exercises to placeholder list
        listTest1.add(strEx1);
        listTest1.add(strEx2);
        listTest1.add(carEx1);
        listTest1.add(carEx2);

        //add mix of strength-exercises to placeholder list
        listTest2.add(strEx1);
        listTest2.add(strEx2);

        //add mix of cardio-exercises to placeholder list
        listTest3.add(carEx1);
        listTest3.add(carEx2);

        //add routines to user
        /*model.getUser().addRoutine("Routine1" ,listTest);
        model.getUser().addRoutine("Routine2" ,listTest2); UNCOMMENT US WHEN MODEL ADDED!
        model.getUser().addRoutine("Routine3" ,listTest3); */

        Button btnPickDate = findViewById(R.id.btnPickDateID);
        Button btnSaveSession = findViewById(R.id.btnOkID);
        Button btnOpenCreateSession = findViewById(R.id.btnOpenCreateRoutineID);
        Spinner spnrRoutineList = findViewById(R.id.spnPickRoutineID);
        //create and setup adapter
        /* ArrayAdapter<Routine> adapter = new ArrayAdapter<Routine>(this, android.R.layout.simple_spinner_dropdown_item, model.getUser().getRoutineList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  UNCOMMENT US WHEN MODEL ADDED!
        spnPickRoutine.setAdapter(adapter);  */

        //REMOVE FROM HERE
        Routine routine1 = new Routine("Routine1" ,listTest1);
        Routine routine2 = new Routine("Routine2" ,listTest2);
        Routine routine3 = new Routine("Routine3" ,listTest3);
        listRoutine.add(routine1);
        listRoutine.add(routine2);
        listRoutine.add(routine3);

        ArrayAdapter<Routine> adapter = new ArrayAdapter<Routine>(this, android.R.layout.simple_spinner_dropdown_item, listRoutine);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPickRoutine.setAdapter(adapter);
        //TO HERE WHEN MODEL ADDED


        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        btnAddRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedRoutine();

            }
        });

        btnOpenCreateRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoutine();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Session session = new Session(txtEnterSessionName.getText().toString(), listOfAddedRoutines, )
                clickSaveSession();
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undo();
            }
        });



    }

    /**
     * A method to update a string to match the selected date
     * @param datePicker ???
     * @param year Year
     * @param month Month
     * @param dayOfMonth Day of month
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());



        // Updating selectedDate to the date selected by user
        selectedDate = LocalDate.of(year, month, dayOfMonth);
        setDate(currentDateString,selectedDate);

    }


    public void setDate(String currentDateString, LocalDate date){

        // Updating selectedDate to the date selected by user
        selectedDate = date;
        // Updating the string shown to the date the user selected
        TextView textView = findViewById(R.id.txtDisplayDateID);
        textView.setText(currentDateString);

    }

    //Open CreateRoutine
    public void openRoutine(){
        Intent intent = new Intent(this, CreateRoutine.class);
        startActivity(intent);
    }

    /**
     * Method of what happens after clicking the "Done" button. Directs the user to the
     * Upcoming sessions view
     */
    public void clickSaveSession(){

        // TODO Make this work
        // Adding the selected Session to the User's Planner's list of Sessions
        //addSessionToList(selectedRoutine,selectedDate);

        // Directing the user to UpcomingSession page again
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //Add the routine
    public void getSelectedRoutine(){


        //Get the selected routine
        Routine addedRoutine = (Routine) spnPickRoutine.getSelectedItem();

        //At the routine
        listOfAddedRoutines.add(addedRoutine);

        //Get the name of the selected routine
        String routineName = listOfAddedRoutines.get(listOfAddedRoutines.size()-1).getName();

        //refresh textview when routine has been added
        refreshTextView();

        //Give feedback to user that the routine has been added
        String toast = "Routine: " + routineName + " has been added";
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

    }

    //Remove the most recently added routine
    public void undo(){
        //Check if there is anything to remove
        if(listOfAddedRoutines.size() > 0){
            listOfAddedRoutines.remove(listOfAddedRoutines.size()-1);
            //refresh textview when routine has been removed
            refreshTextView();
        } else{
            Toast.makeText(this, "There is nothing to remove! ", Toast.LENGTH_SHORT).show();
        }

    }

    //Refreshes the textview when routine is added or removed
    public void refreshTextView(){

        //Used to remove trailing zeroes from doubles
        DecimalFormat removeZeroes = new DecimalFormat("0.#");

        //Make the textview empty before writing all info
        txtDisplayRoutines.setText("");

        //Go through each routine that has been added
        for(Routine routine : listOfAddedRoutines){
            //Show name of selected routine
            txtDisplayRoutines.append("Routine: " + routine.getName() + "\n");

            //Go through each exercise in that routine
            for(Exercise exercise : routine.getSavedExerciseList()){
                //if the exercise is a strength exercise, show the relevant information
                if(exercise instanceof StrengthExercise){
                    txtDisplayRoutines.append("Ex: "+ exercise.getName() +
                            " | Weight: " + removeZeroes.format(((StrengthExercise) exercise).getWeight()) +
                            " | Sets: " + ((StrengthExercise) exercise).getSets() +
                            " | Reps: " + ((StrengthExercise) exercise).getReps() +
                            "\n");
                }
                //if the exercise is a cardio exercise, show the relevant information
                else if(exercise instanceof CardioExercise){
                    txtDisplayRoutines.append("Ex: "+ exercise.getName() +
                            " | Dist: " + removeZeroes.format(((CardioExercise) exercise).getDistance()) +
                            " | Time: " + removeZeroes.format(((CardioExercise) exercise).getRunningTime()) +
                            "\n");
                //if the exercise neither, just show the name
                } else {
                    txtDisplayRoutines.append("Ex: "+ exercise.getName() + "\n");
                }
            }

            //Add extra line between each routine
            txtDisplayRoutines.append("\n");
        }

    }

    public void isDateSelectedAlready() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getStringExtra("FROMCALENDAR").matches("YES")) {


                String pastDate = intent.getStringExtra("DATE");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d - MM - yyyy");
                LocalDate localDate = LocalDate.parse(pastDate, formatter);
                setDate(pastDate, localDate);
            }
        }
    }
}


