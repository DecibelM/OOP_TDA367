package com.traininapp.Model;

import java.util.List;

public interface ISessionObserver {

    public void updateSessionStats(List<Exercise> exerciseList);
}
