package com.traininapp.Model;

import com.traininapp.Model.Planning.CardioExercise;
import com.traininapp.Model.Planning.Exercise;
import com.traininapp.Model.Planning.StrengthExercise;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test Class for User.
 * @author Maria Fornmark
 */
public class UserTest {

    User user = new User();

    @Test
    public void addSession() {
        StrengthExercise strengthExercise = new StrengthExercise("Benpress", 1,2,10);
        CardioExercise cardioExercise = new CardioExercise("5km",2,5);
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(strengthExercise);
        exerciseList.add(cardioExercise);

        user.addSession("Session", exerciseList, LocalDate.now());

        assertEquals(1, user.getPlanner().getSessionList().size());



    }
}