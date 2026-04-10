package com.waruna.firebasep1employees.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.waruna.firebasep1employees.R

class MainActivity : AppCompatActivity() {

    lateinit var btnAdd: Button
    lateinit var btnFetch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnFetch = findViewById(R.id.btnFetch)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        btnFetch.setOnClickListener {
            startActivity(Intent(this, FetchingActivity::class.java))
        }
    }
}