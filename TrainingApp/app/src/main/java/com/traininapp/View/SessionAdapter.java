package com.traininapp.View;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Session;
import com.traininapp.R;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    private List<Session> sessionList;

    public SessionAdapter(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_session, parent, false);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {
        holder.txtSessionName.setText(sessionList.get(position).getName());
        holder.txtSessionDate.setText(sessionList.get(position).getDate().toString());
        holder.imgSessionImage.setImageResource(sessionList.get(position).getSessionImage());
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    class SessionViewHolder extends RecyclerView.ViewHolder {

        TextView txtSessionName, txtSessionDate;
        ImageView imgSessionImage;

        SessionViewHolder(View itemView) {
            super(itemView);
            txtSessionName = (TextView) itemView.findViewById(R.id.txtSessionName);
            txtSessionDate = (TextView) itemView.findViewById(R.id.txtSessionDate);
            imgSessionImage = (ImageView) itemView.findViewById(R.id.imgSessionImage);
        }
    }
}