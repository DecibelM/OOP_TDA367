package com.traininapp.Model;

import java.util.List;

public interface ISessionObserver {

    public void updateCardioSessionStats(List<CardioExercise> exerciseList);
    public void updateWeightSessionStats(List<StrengthExercise> exerciseList);
}
