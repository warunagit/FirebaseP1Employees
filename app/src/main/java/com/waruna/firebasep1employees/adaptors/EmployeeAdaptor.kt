package com.waruna.firebasep1employees.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.waruna.firebasep1employees.R
import com.waruna.firebasep1employees.models.EmployeeModel

class EmployeeAdaptor(private val employeeList: ArrayList<EmployeeModel>):
    Adapter<EmployeeAdaptor.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeAdaptor.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.empty_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: EmployeeAdaptor.ViewHolder, position: Int) {
        val currentEmployeeName = employeeList[position]
        holder.empName.text = currentEmployeeName.name
    }

   class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val empName: TextView = itemView.findViewById(R.id.textView)
    }
}