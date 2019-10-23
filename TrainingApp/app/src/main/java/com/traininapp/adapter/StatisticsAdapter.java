package com.traininapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.traininapp.R;
import com.traininapp.View.GoalStatCard;
import com.traininapp.View.StatisticCard;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the adapter for the Recycler view within my pages.
 * It handles a lot of the different templates and can draw graphs or display texts depending on the data within the statistics model.
 */
public class StatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // TODO Ta bort onödig import

    private List<IStatistic> dataList;

    /**
     * This is the constructor for the class StatisticsAdapter
     * @param dataList A list of statistics and/or goals
     */
    public StatisticsAdapter(List<IStatistic> dataList) {
        this.dataList = dataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch(viewType){
            case IStatistic.TYPE_GOALSTAT:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_goal_card, parent, false);
                return new GoalViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_statistics_card, parent, false);
                return new StatisticsViewHolder(itemView);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case IStatistic.TYPE_GOALSTAT:
                ((GoalViewHolder) holder).bindView(position);
                break;
            default:
                ((StatisticsViewHolder) holder).bindView(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        } else {
            return dataList.size();
        }
    }

    /**
     * This is an alternative template to display data in each card.
     *
     * This specific one is suposed to handle statistics and draw a graph if possible.
     */
    class StatisticsViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeadLine;
        GraphView graphView;

        /**
         * The constructor for StatisticsViewHolder
         * @param itemView the view where this belongs
         */
        StatisticsViewHolder(View itemView) {
            super(itemView);
            txtHeadLine = (TextView) itemView.findViewById(R.id.headLineID);
            graphView = (GraphView) itemView.findViewById(R.id.graphViewID);
            graphView.setVisibility(View.VISIBLE);
        }

        /**
         * Binds the view to this data. (adds text to textFields)
         * @param position The position of the data in the specific list
         */
        void bindView(int position) {
            StatisticCard sCard = (StatisticCard) dataList.get(position);
            txtHeadLine.setText(sCard.getName());
            drawGraph(sCard);
            // bind data to the views
            // textView.setText()...

        }

        /**
         * Draws a graph on this card, might have to be tweaked for futher graphafe,
         * at the moment it does not display the date and the statistic looks a liitle off.
         *
         * @param statisticCard an instance of the Statistics
         */

        void drawGraph(StatisticCard statisticCard){
            List<Double> statistics = statisticCard.getStatistics();

            DataPoint[] dataPoints = new DataPoint[statistics.size()];

            for (int i = 0; i < statistics.size(); i++) {
                dataPoints[i] = new DataPoint(i, statistics.get(i));
            }

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
            graphView.setMinimumWidth(statistics.size());
            graphView.addSeries(series);
        }
    }

    /**
     * This is an alternative template to display data in each card.
     *
     * This specific one handles goals.
     */
    class GoalViewHolder extends RecyclerView.ViewHolder{

        GoalsListAdapter goalsListAdapter;

        TextView goalName;
        TextView goalTarget;
        TextView goalProgress;

        /**
         * The constructor for GoalViewHolder
         *
         * @param itemView The view connected to this instance
         */
        GoalViewHolder(View itemView) {
            super(itemView);
            goalName = (TextView) itemView.findViewById(R.id.goalHedderID);
            goalTarget = (TextView) itemView.findViewById(R.id.currenttargetID);
            goalProgress = (TextView) itemView.findViewById(R.id.currentprogressID);
            goalsListAdapter = new GoalsListAdapter();
        }

        /**
         * Binds the view to this data.
         * @param position The position of the data in the specific list
         */
        void bindView(int position) {
            GoalStatCard sCard = (GoalStatCard) dataList.get(position);

            goalName.setText(sCard.getName());
            goalTarget.setText(Double.toString(sCard.getTarget()));
            goalProgress.setText(Double.toString(sCard.getProgress()));

        }
    }
}
