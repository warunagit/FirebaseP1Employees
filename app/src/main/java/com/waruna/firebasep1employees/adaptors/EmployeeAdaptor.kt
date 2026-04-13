package com.waruna.firebasep1employees.adaptors

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waruna.firebasep1employees.R
import com.waruna.firebasep1employees.models.EmployeeModel

class EmployeeAdaptor(private val employeeList: ArrayList<EmployeeModel>):
    RecyclerView.Adapter<EmployeeAdaptor.ViewHolder>(){

    lateinit var clickListner: onItemClickListner

    //1
    //List items adaptor
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): EmployeeAdaptor.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.empty_list_item,parent,false)
        return ViewHolder(itemView,clickListner)
    }

    override fun getItemCount(): Int {
        Log.d("Error:getItemCount",""+employeeList.size)
        return employeeList.size
    }

    override fun onBindViewHolder(holder: EmployeeAdaptor.ViewHolder, position: Int) {
        val currentEmployee = employeeList[position]
        Log.d("Error:onBindViewHolder",""+currentEmployee)
        holder.tvEmployeeName.text = currentEmployee.name
        Log.d("Error:currentEmployeeName",""+currentEmployee)
    }

   class ViewHolder(itemView: View, clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView){
        val tvEmployeeName: TextView = itemView.findViewById(R.id.tvEmployeeName)
       init {
           itemView.setOnClickListener {
               clickListner.onItemClick(adapterPosition)
           }
       }
    }

    //2
    //custom click listner per list item
    interface onItemClickListner{
        fun onItemClick(adapterPosition: Int) {}
    }
    fun setOnItemClickListner(onitemclicklistner: onItemClickListner){
        clickListner = onitemclicklistner
    }
}