package com.traininapp.Model;

import com.traininapp.Model.Planning.Exercise;

import java.util.List;

/**
 * observes sessions and waits to pass an exercise list onward.
 */
public interface ISessionObserver {

    // TODO ta bort public
    /**
     * passes an exercise list to whoever wants to aquire one when a session is done.
     *
     * @param exerciseList
     */
    public void updateSessionStats(List<Exercise> exerciseList);
}
