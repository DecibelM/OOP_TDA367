package com.traininapp.Model.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds data for a specific exercise type, this is used to save and get data specific to each exercise
 */
public class ExerciseSpecificStatistic implements IStat{
    private String name;
    private List<Double> dataList;
    private List<Long> datesList;

    /**
     * @param name Name of the exercise
     * @param initialData the first data to populate the list
     * @param initialDate the first date corresponding to the data
     */
    public ExerciseSpecificStatistic(String name, Double initialData, Long initialDate){
        this.name = name;

        dataList = new ArrayList<>();
        dataList.add(initialData);

        datesList = new ArrayList<>();
        datesList.add(initialDate);
    }

    /**
     * adds data to the statistics
     * @param data the data to add
     * @param date the date for the data
     */
    void addToDataList(Double data, Long date){
        dataList.add(data);
        datesList.add(date);
    }

    public String getName() {
        return name;
    }

    public List<Double> getDataList() {
        return dataList;
    }

    public List<Long> getDatesList() {
        return datesList;
    }
}
