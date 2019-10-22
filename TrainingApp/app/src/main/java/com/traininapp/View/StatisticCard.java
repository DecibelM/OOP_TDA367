package com.traininapp.View;

import com.traininapp.adapter.IStatistic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class keeps hold on and gets the information for the individual statistics
 *
 * It can return two lists that consists of a set of statistics and dates which have the date for a statistic in the corresponding place in the the 'dates' list.
 */
public class StatisticCard implements IStatistic {

    String statisticName;
    private List<Integer> statistics;
    private List<Long> dates;

    /**
     *
     *
     * @param statistics
     * @param dates
     */
    public StatisticCard(String statisticName, ArrayList<Integer> statistics, ArrayList<Long> dates) {
        this.statisticName = statisticName;
        this.statistics = statistics;
        this.dates = dates;
    }

    /**
     * Delete a statistic based on a given date.
     * @param date The date as an instance of the class Date.
     */
    public void deleteStatistic(Date date){
        int pos = dates.indexOf(date.getTime());
        statistics.remove(pos);
        dates.remove(pos);
    }

    /**
     * delete a statistic based on a given date.
     * @param date The date as a 'Long' in the form of unix time.
     */
    public void deleteStatistic(Long date){
        int pos = dates.indexOf(date);
        statistics.remove(pos);
        dates.remove(pos);
    }

    /**
     * Given a statistic and a date, this will add them to the lists with statistics in the right order.
     *
     * @param statistic the statistig, like weight distance etc.
     * @param date the date of the recorded statistic.
     */
    public void addStatistics(Integer statistic, Date date){
        statistics.add(statistic);
        dates.add(date.getTime());
    }

    /**
     * @return returns a list of statistics
     */
    public ArrayList<Integer> getStatistics() {
        return new ArrayList<>(statistics);
    }

    /**
     * @return dates for the specific statistics
     */
    public ArrayList<Long> getDates() {
        return new ArrayList<>(dates);
    }

    /**
     * @return returns the name of the statistic
     */
    public String getName() {
        return statisticName;
    }

    /**
     * @return returns the size of the list of statistics
     */
    public int getStatisticsCount() {
        return statistics.size();
    }

    @Override
    public int getType() {
        return IStatistic.TYPE_STATISTIC;
    }
}
