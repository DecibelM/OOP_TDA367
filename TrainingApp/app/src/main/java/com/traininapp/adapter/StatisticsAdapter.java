package com.traininapp.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Goal;
import com.traininapp.R;
import com.traininapp.viewModel.GoalStatCard;
import com.traininapp.viewModel.IStatistic;
import com.traininapp.viewModel.StatisticCard;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<IStatistic> dataList;
    //private ArrayList<String> dataList;


    public StatisticsAdapter(ArrayList<IStatistic> dataList) {
        this.dataList = dataList;
    }

/*
    public StatisticsAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }
*/

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

    class StatisticsViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeadLine;
        ImageView imgImageView;

        StatisticsViewHolder(View itemView) {
            super(itemView);
            txtHeadLine = (TextView) itemView.findViewById(R.id.headLineID);
            imgImageView = (ImageView) itemView.findViewById(R.id.imageViewID);
        }

        void bindView(int position) {
            StatisticCard sCard = (StatisticCard) dataList.get(position);
            txtHeadLine.setText("Statistichard");
            // bind data to the views
            // textView.setText()...

        }
    }

    class GoalViewHolder extends RecyclerView.ViewHolder{

        TextView txtgoal;
        TextView txtprogress;

        GoalViewHolder(View itemView) {
            super(itemView);
            txtgoal = (TextView) itemView.findViewById(R.id.goalID);
            txtprogress = (TextView) itemView.findViewById(R.id.progressID);
        }

        void bindView(int position) {
            GoalStatCard sCard = (GoalStatCard) dataList.get(position);
            txtgoal.setText("Goalhard");
            txtprogress.setText("Progresshard");
            // bind data to the views
            // textView.setText()...

        }
    }
}
