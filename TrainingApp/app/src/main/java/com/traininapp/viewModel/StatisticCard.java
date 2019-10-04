package com.traininapp.viewModel;

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

    public void deleteStatistic(Date date){
        int pos = dates.indexOf(date.getTime());
        statistics.remove(pos);
        dates.remove(pos);
    }

    public void deleteStatistic(Long date){
        int pos = dates.indexOf(date);
        statistics.remove(pos);
        dates.remove(pos);
    }

    public void addStatistics(Integer statistic, Date date){
        statistics.add(statistic);
        dates.add(date.getTime());
    }

    public ArrayList<Integer> getStatistics() {
        return new ArrayList<>(statistics);
    }

    public ArrayList<Long> getDates() {
        return new ArrayList<>(dates);
    }

    public String getName() {
        return statisticName;
    }

    public int getStatisticsCount() {
        return statistics.size();
    }

    @Override
    public int getType() {
        return IStatistic.TYPE_STATISTIC;
    }
}
