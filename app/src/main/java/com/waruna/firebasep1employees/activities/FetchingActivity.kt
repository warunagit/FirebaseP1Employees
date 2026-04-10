package com.waruna.firebasep1employees.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.waruna.firebasep1employees.R
import com.waruna.firebasep1employees.adaptors.EmployeeAdaptor
import com.waruna.firebasep1employees.models.EmployeeModel

class FetchingActivity : AppCompatActivity() {
    lateinit var employeeRecyclerView: RecyclerView
    lateinit var tvLoading: TextView
    lateinit var employeeList: ArrayList<EmployeeModel>

    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fetching)

        employeeRecyclerView = findViewById(R.id.empRecyclerView)
        employeeRecyclerView.layoutManager = LinearLayoutManager(this)
        employeeRecyclerView.setHasFixedSize(true)
        tvLoading = findViewById(R.id.tvLoading)

        employeeList = ArrayList<EmployeeModel>()

        try {
            fetchEmployeeData()

            //adapterTest()
        }catch (ex: Exception){
            Log.d("Error",ex.message.toString(),);
        }
    }

    private fun adapterTest() {
        val data = ArrayList<EmployeeModel>()
        for (i in 1..20) {
            data.add(EmployeeModel("1","Waru","35"))
        }
        // This will pass the ArrayList to our Adapter
        val adapter = EmployeeAdaptor(data)
        // Setting the Adapter with the recyclerview
        employeeRecyclerView.adapter = adapter
    }

    private fun fetchEmployeeData() {
        employeeRecyclerView.visibility = View.GONE
        tvLoading.visibility = View.VISIBLE

        //Firebase.database.getReference("company")
        dbRef = FirebaseDatabase.getInstance().getReference("employees")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                employeeList.clear()
                if (snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val employeeData = empSnap.getValue(EmployeeModel::class.java)
                        employeeList.add(employeeData!!)
                    }
                    val empAdaptor = EmployeeAdaptor(employeeList)
                    employeeRecyclerView.adapter = empAdaptor
                    employeeRecyclerView.visibility = View.VISIBLE
                    tvLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(err: DatabaseError) {
                Log.d("Error:onCancelled",""+DatabaseError.UNKNOWN_ERROR)
            }

        })
    }
}
