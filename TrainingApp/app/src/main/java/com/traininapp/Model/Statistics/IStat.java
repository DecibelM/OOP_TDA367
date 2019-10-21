package com.traininapp.Model.Statistics;

import java.util.List;

public interface IStat {

    /**
     * @return the name of this goal.
     */
    public String getName();

    /**
     * @return Data for achieved statistics in this exercise.
     */
    public List<Double> getDataList();

    /**
     * @return Dates for the datapoints.
     */
    public List<Long> getDatesList();
}
