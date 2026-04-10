package com.waruna.firebasep1employees.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waruna.firebasep1employees.R

class FetchingActivity : AppCompatActivity() {
    lateinit var employeeRecyclerView: RecyclerView
    lateinit var tvLoading: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fetching)

        employeeRecyclerView = findViewById(R.id.empRecyclerView)
        employeeRecyclerView.layoutManager = LinearLayoutManager(this)
        employeeRecyclerView.setHasFixedSize(true)
        tvLoading = findViewById(R.id.tvLoading)

    }
}