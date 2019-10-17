package com.traininapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.traininapp.Model.Planning.Exercise;
import com.traininapp.R;

import java.util.List;

public class ExerciseAdapter extends BaseAdapter {

    private Context context;
    private List<Exercise> listforview;
    private LayoutInflater inflator = null;
    private View v;
    private ViewHolder vholder;

    // Constructor
    public ExerciseAdapter(Context con, List<Exercise> list) {
        super();
        context = con;
        listforview = list;
        inflator = LayoutInflater.from(con);
    }

    // Return position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Return size of list
    @Override
    public int getCount() {
        return listforview.size();
    }

    // Get Object from each position
    @Override
    public Object getItem(int position) {
        return listforview.get(position);
    }

    // ViewHolder class to contain inflated xml views
    private class ViewHolder {
        TextView title;
    }

    // Called for each view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        v = convertView;

        if (convertView == null) {

            //inflate the view for each row of listview
            v = inflator.inflate(R.layout.row_exercise, null);

            //ViewHolder object to contain row_exercise.xml elements
            vholder = new ViewHolder();
            vholder.title = (TextView) v.findViewById(R.id.txtExerciseNameID);

            //set holder to the view
            v.setTag(vholder);
        } else
            vholder = (ViewHolder) v.getTag();

        //getting MyItem Object for each position
        Exercise item = listforview.get(position);

        //set the id to editetxt important line here as it will be helpful to set text according to position
        vholder.title.setId(position);

        /*//setting the values from object to holder views for each row
        // vholder.title.setText(item.getImageheading()); vholder.image.setImageResource(item.getImageid());
        vholder.title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if (!hasFocus) {
                            final int id = v.getId();
                            Exercise item = listforview.get(id);
                            final EditText field = ((EditText) v);
                            listforview.get(id).setImageheading(field.getText().toString());
                        }
                    }
                }
        );*/
        return v;
    }
}