package com.waruna.firebasep1employees

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnAdd: Button
    lateinit var btnFetch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}