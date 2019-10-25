package com.traininapp.View;

import com.traininapp.adapter.IStatistic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class keeps hold on and gets the information for the individual statistics
 *
 * It can return two lists that consists of a set of statistics and dates which have the date for a statistic in the corresponding place in the the 'dates' list.
 * Author: Viktor
 */
public class StatisticCard implements IStatistic {

    private String statisticName;
    private List<Double> statistics;
    private List<Long> dates;

    /**
     *
     *  @param statistics
     * @param dates
     */
    StatisticCard(String statisticName, List<Double> statistics, List<Long> dates) {
        this.statisticName = statisticName;
        this.statistics = statistics;
        this.dates = dates;
    }

    /**
     * @return returns a list of statistics
     */
    public List<Double> getStatistics() {
        return new ArrayList<>(statistics);
    }

    /**
     * @return returns the name of the statistic
     */
    public String getName() {
        return statisticName;
    }

    @Override
    public int getType() {
        return IStatistic.TYPE_STATISTIC;
    }
}
