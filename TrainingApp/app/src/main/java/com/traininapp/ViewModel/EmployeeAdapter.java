package com.traininapp.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.traininapp.Model.Employee;
import com.traininapp.R;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private ArrayList<Employee> dataList;

    public EmployeeAdapter(ArrayList<Employee> dataList) {
        this.dataList = dataList;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.txtEmpName.setText(dataList.get(position).getEmployeeName());
        holder.txtEmpEmail.setText(dataList.get(position).getEmployeeEmail());
        holder.txtEmpPhone.setText(dataList.get(position).getEmployeePhone());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmpName, txtEmpEmail, txtEmpPhone;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtEmpName = (TextView) itemView.findViewById(R.id.txt_employee_name);
            txtEmpEmail = (TextView) itemView.findViewById(R.id.txt_employee_email);
            txtEmpPhone = (TextView) itemView.findViewById(R.id.txt_employee_phone);
        }
    }
}