package com.waruna.firebasep1employees.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.waruna.firebasep1employees.R
import com.waruna.firebasep1employees.models.EmployeeModel

class AddActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        btnSave = findViewById(R.id.btnSave)



        btnSave.setOnClickListener {
            val dbRef = Firebase.database.getReference("employees")
            val nextId = dbRef.push().key!!
            val employee = EmployeeModel(nextId,etName.text.toString(),etAge.text.toString())
            //dbRef.child("employees").child(nextId).setValue(employee)
            dbRef.child(nextId).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {err->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}