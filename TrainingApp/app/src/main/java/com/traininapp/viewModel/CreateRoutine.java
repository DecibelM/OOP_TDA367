package com.traininapp.viewModel;

import androidx.appcompat.app.AppCompatActivity;
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

import com.traininapp.Model.Planning.Exercise;
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
                        createStrRow();
                    } else {
                        //if strength is not chosen, create cardio row
                        createCarRow();
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

                    //if no fragments returned null
                    if (control == true) {
                        //Get the routinename that the user inputted
                        String routineName = txtEnterRoutineName.getText().toString();


                        //Add the routine with the inputted routinename and the list of exercises
                        repository.getUser().addRoutine(routineName, exerciseList);

                        //Give feedback that the routine has been saved
                    String toastMessage = "Routine: " + routineName.toUpperCase() + " has been saved!";
                    Toast.makeText(CreateRoutine.this, toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        //open new activity
        public void openActivity() {
            Intent intent = new Intent(this, CreateSession.class);
            startActivity(intent);
        }

        //create a new cardio fragment in the row
        public void createCarRow() {
            //create the fragment
            FragCarRow fragment;
            fragment = new FragCarRow();

            //Begin the transaction, to start doing something with the fragment
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //Add the created fragment to "displayRowsID"
            fragmentTransaction.add(R.id.displayStrRowsID, fragment);
            //Add it to the list of all created Cardio fragments
            listCarFrag.add(fragment);
            //Commit and finish the FragmentTransaction
            fragmentTransaction.commit();

        }

        //create a new strength fragment in the row
        public void createStrRow() {
            //create the fragment
            FragStrRow fragment;
            fragment = new FragStrRow();

            //Begin the transaction, to start doing something with the fragment
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //Add the created fragment to "displayRowsID"
            fragmentTransaction.add(R.id.displayStrRowsID, fragment);
            //Add it to the list of all created Strength fragments
            listStrFrag.add(fragment);
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

}