package com.traininapp.Model.Statistics;

import java.util.List;

public interface IStat {

    /**
     * @return the name of this goal.
     */
    String getName();

    /**
     * @return Data for achieved statistics in this exercise.
     */
    List<Double> getDataList();

    /**
     * @return Dates for the datapoints.
     */
    List<Long> getDatesList();
}
