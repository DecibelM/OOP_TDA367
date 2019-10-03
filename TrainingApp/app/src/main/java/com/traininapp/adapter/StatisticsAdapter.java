package com.traininapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.R;

import java.util.ArrayList;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    private ArrayList<String> dataList;

    @NonNull
    @Override
    public StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_statistics_card, parent, false);
        return new StatisticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsViewHolder holder, int position) {

        holder.txtHeadLine.setText(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class StatisticsViewHolder extends RecyclerView.ViewHolder{

        TextView txtHeadLine;

        StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeadLine = (TextView) itemView.findViewById(R.id.headLineID);
        }
    }
}
