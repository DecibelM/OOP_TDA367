package com.traininapp.Model.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds data for a specific exercise type, this is used to save and get data specific to each exercise
 */

    // TODO Ta bort onödiga konstruktorer

public class ExerciseSpecificStatistic implements IStat{

    private String name;
    private List<Double> dataList;
    private List<Long> datesList;

    /**
     *
     * @param name Name of the exercise
     * @param dataList the data list of accomplishement in this exercise
     * @param datesList the list of dates corresponding to the data
     */
    public ExerciseSpecificStatistic(String name, List<Double> dataList, List<Long> datesList) {
        this.name = name;
        this.dataList = dataList;
        this.datesList = datesList;
    }

    /**
     * @param name Name of the exercise
     * @param initialData the first data to populate the list
     * @param initialDate the first date corresponding to the data
     */
    ExerciseSpecificStatistic(String name, Double initialData, Long initialDate){

        this.name = name;

        dataList = new ArrayList<>();
        dataList.add(initialData);

        datesList = new ArrayList<>();
        datesList.add(initialDate);
    }

    /**
     * The simplest constructor for the class where no data is needed
     * @param name the name of the exercise
     */
    public ExerciseSpecificStatistic(String name){
        this.name = name;
        dataList = new ArrayList<>();
        datesList = new ArrayList<>();
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
