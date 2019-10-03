package com.traininapp.viewModel;

public class StatisticCard implements IStatistic {

    int stat;

    public StatisticCard(int stat) {
        this.stat = stat;
    }

    public int getStat() {
        return stat;
    }

    public String getStatString() {
        return Integer.toString(stat);
    }

    @Override
    public int getType() {
        return IStatistic.TYPE_STATISTIC;
    }
}
