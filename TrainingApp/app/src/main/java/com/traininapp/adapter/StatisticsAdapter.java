package com.traininapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.R;
import com.traininapp.viewModel.IStatistic;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    //private ArrayList<IStatistic> dataList;
    private ArrayList<String> dataList;

    /*
    public StatisticsAdapter(ArrayList<IStatistic> dataList) {
        this.dataList = dataList;
    }

     */


    public StatisticsAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }


    @Override
    public StatisticsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_statistics_card, parent, false);
        return new StatisticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder( StatisticsViewHolder holder, int position) {

        holder.txtHeadLine.setText(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class StatisticsViewHolder extends RecyclerView.ViewHolder{

        TextView txtHeadLine;

        StatisticsViewHolder(View itemView) {
            super(itemView);
            txtHeadLine = (TextView) itemView.findViewById(R.id.headLineID);
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
    }
}
