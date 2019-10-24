package com.traininapp.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Planning.Session;
import com.traininapp.R;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    //TODO Bryt ut lista med sessions till viewmodel och hämta data via metoden till den.
    private List<Session> sessionList;

    /**
     * Adapter constructor
     * @param sessionList List of Sessions
     */
    SessionAdapter(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    /**
     * Method which creates new views (invoked by the layout manager)
     * @param parent ViewGroup parent
     * @param viewType The type of view
     * @return A new SessionViewHolder containing the rows
     */
    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_session, parent, false);
        return new SessionViewHolder(view);
    }

    /**
     * Method which replaces the contents of the view (invoked by the layout manager)
     * @param holder Holder of the SessionViewHolder
     * @param position The position in the list
     */
    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {

        // Setting the title/name of the session
        holder.txtSessionName.setText(sessionList.get(position).getName());

        // Setting the date of the session
        holder.txtSessionDate.setText(sessionList.get(position).getDate().toString());

        // Setting the image of the session
        holder.imgSessionImage.setImageResource(sessionList.get(position).getSessionImage());
    }

    /**
     * Method to return the number of items in sessionList (invoked by the layout manager)
     * @return The size of sessionList as an int
     */
    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    /**
     * Class to provide a reference to the views for each data item
     */
    class SessionViewHolder extends RecyclerView.ViewHolder {

        TextView txtSessionName, txtSessionDate;
        ImageView imgSessionImage;

        SessionViewHolder(View itemView) {
            super(itemView);
            txtSessionName = itemView.findViewById(R.id.txtSessionName);
            txtSessionDate = itemView.findViewById(R.id.txtSessionDate);
            imgSessionImage = itemView.findViewById(R.id.imgSessionImage);
        }
    }
}