package com.traininapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Planning.Session;
import com.traininapp.R;
import com.traininapp.View.CurrentSessionActivity;

import java.util.List;

/**
 * An adapter to handle the RecyclerView in which the Cards containing Sessions consists off
 * @author Mathias
 */
public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    //TODO Bryt ut lista med sessions till viewmodel och hämta data via metoden till den.
    private List<Session> sessionList;

    /**
     * Adapter constructor
     * @param sessionList List of Sessions
     */
    public SessionAdapter(List<Session> sessionList) {
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

        SessionViewHolder(final View itemView) {
            super(itemView);

            // Setting the ID's for the cards element
            txtSessionName = itemView.findViewById(R.id.txtSessionName);
            txtSessionDate = itemView.findViewById(R.id.txtSessionDate);
            imgSessionImage = itemView.findViewById(R.id.imgSessionImage);

            // Adding an setOnClickListener to the Card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Creating the intent
                    Intent intent = new Intent(itemView.getContext(), CurrentSessionActivity.class);

                    // Fetching the Session of Card
                    Session session = sessionList.get(getAdapterPosition());

                    // Attaching the Sessions information to intent so it can be found later
                    intent.putExtra("Session", session.toString());

                    // Starting the activity
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}