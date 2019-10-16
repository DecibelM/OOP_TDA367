package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.traininapp.Model.DatabaseHelper;
import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.Routine;
import com.traininapp.Model.Planning.StrengthExercise;
import com.traininapp.Model.Repository;
import com.traininapp.R;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class CreateRoutine extends AppCompatActivity implements Serializable {

        private EditText txtEnterRoutineName;



        //List for all created fragments
        List<FragStrRow> listStrFrag = new ArrayList<>();
        List<FragCarRow> listCarFrag = new ArrayList<>();

        Repository repository;


        private boolean control;

        DatabaseHelper myDb = new DatabaseHelper(this);

        //List for all exercises
        private List<Exercise> exerciseList = new ArrayList<>();

        private FragmentTransaction fragmentTransaction;

        //boolean to see which type of exercise is currently selected
        private boolean isStrength = true;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_routine);
            repository = Repository.getInstance();


            ToggleButton togCardioOrStrength = findViewById(R.id.togCardioOrStrengthID);
            Button btnAddnewExersice = findViewById(R.id.btnAddExerciseID);
            Button btnDone = findViewById(R.id.btnDoneID);
            Button btnSave = findViewById(R.id.btnSaveID);

            final LinearLayout rowStrExerciseInfoID = findViewById(R.id.rowStrExerciseInfoID);
            final LinearLayout rowCarExerciseInfoID = findViewById(R.id.rowCarExerciseInfoID);
            txtEnterRoutineName = findViewById(R.id.txtEnterRoutineNameID);

            //hide the titles for cardio exercises when activity starts
            rowCarExerciseInfoID.setVisibility(View.GONE);

            //depending on if toggle is enabled, hide or show relevant titles
            //also change boolean isStrength, for creating the right exercise
            togCardioOrStrength.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //for cardio
                    if (isChecked) {
                        selectType(rowCarExerciseInfoID, rowStrExerciseInfoID);
                        isStrength = false;
                    }
                    //for strength
                    else {
                        // The toggle is disabled
                        selectType(rowStrExerciseInfoID, rowCarExerciseInfoID);
                        isStrength = true;
                    }
                }
            });

            btnAddnewExersice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if strength is chosen, create strength row
                    if (isStrength) {
                        createFragRow();
                    } else {
                        //if strength is not chosen, create cardio row
                        createFragRow();
                    }
                }
            });

            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity();

                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Get the routinename that the user inputted
                    String routineName = txtEnterRoutineName.getText().toString();

                    //Remove all previously added exercises
                    exerciseList.clear();
                    //Set boolean control true, used for seeing if
                    //everything goes okay when saving the routine
                    control = true;
                    //go through the list of all created strength fragments
                    for (FragStrRow fr : listStrFrag) {

                        //if saveInfo (in FragStrRow) did not return null, add to list of exercises
                        if (fr.saveInfo() != null) {
                            exerciseList.add(fr.saveInfo());

                            //remove exercises of deleted fragments
                            removeDeletedExercises();


                            //if it had returned null
                        } else {
                            //run failsave and break from loop
                            failSave();
                            break;
                        }
                    }
                    //go through the list of all created cardio fragments
                    for (FragCarRow fr : listCarFrag) {

                        //if saveInfo (in FragCarRow) did not return null, add to list of exercises
                        //and if control is still true (no strengthexercises returned null)
                        if (fr.saveInfo() != null && control == true) {
                            exerciseList.add(fr.saveInfo());

                            //remove exercises of deleted fragments
                            removeDeletedExercises();



                            //if it had returned null
                        } else {
                            //run failsave and break from loop
                            failSave();
                            break;
                        }
                    }

                    checkName();
                    checkNameLength();

                    //if no fragments returned null and name is unique
                    if (control == true) {

                        //Give feedback that the routine has been saved
                        String toastMessage = "Routine: " + routineName + " has been saved!";
                        Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
                        repository.getUser().addRoutine(routineName, exerciseList);

                        myDb.insertRoutineData(routineName);

                        for(Exercise exercise : exerciseList) {
                            if (exercise instanceof StrengthExercise) {
                                myDb.insertStrExData(routineName,
                                        exercise.getName(),
                                        ((StrengthExercise) exercise).getWeight(),
                                        ((StrengthExercise) exercise).getSets(),
                                        ((StrengthExercise) exercise).getReps());
                                }
                            }

                        for(Exercise exercise : exerciseList) {
                            if (exercise instanceof CardioExercise) {
                                myDb.insertCarExData(routineName,
                                        exercise.getName(),
                                        ((CardioExercise) exercise).getDistance(),
                                        ((CardioExercise) exercise).getRunningTime());
                            }
                        }

                            txtEnterRoutineName.setText("");
                    }
                }
        });
    }

        //open new activity
        public void openActivity() {
            Intent intent = new Intent(this, CreateSession.class);
            startActivity(intent);
        }


    //create a new strength fragment in the row
    public void createFragRow() {
        //create the fragment
        Fragment fragment;

        //Begin the transaction, to start doing something with the fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(isStrength){
            fragment = new FragStrRow();
            listStrFrag.add((FragStrRow) fragment);


        } else{
            fragment = new FragCarRow();
            listCarFrag.add((FragCarRow) fragment);
        }
        //Add the created fragment to "displayRowsID"
        fragmentTransaction.add(R.id.displayRowsID, fragment);
        //Add it to the list of all created Strength fragments
        //Commit and finish the FragmentTransaction
        fragmentTransaction.commit();
    }


        //hide or show which type of exercise is selected, depending on toggle
        public void selectType(LinearLayout visRow, LinearLayout gonRow) {
            visRow.setVisibility(View.VISIBLE);
            gonRow.setVisibility(View.GONE);
        }

        //if the fragment had been removed remove it from the list of added exercises
        public void removeDeletedExercises() {
            if (exerciseList.get(exerciseList.size() - 1).getName() == "REMOVE ME") {
                exerciseList.remove(exerciseList.size() - 1);
            }
        }

        //if failing to save exercise
        public void failSave() {
            exerciseList.clear();
            control = false;

        }

         //Checks the name of all added routines to see if entered name is unique
         public void checkName(){
            String routineName = txtEnterRoutineName.getText().toString();
            for (int i = 0; i < repository.getUser().getRoutineList().size(); i++){
                if (repository.getUser().getRoutineList().get(i).getName().equals(routineName)){
                    String toastMessage = "Name: " + routineName + " is not unique";
                    Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
                    control = false;
                }
            }
        }

        public void checkNameLength(){
            String routineName = txtEnterRoutineName.getText().toString();
            if(routineName.length() == 0){
                String toastMessage = "No name entered!";
                Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
                control = false;
            }
        }

}