package com.waruna.firebasep1employees.activities

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.waruna.firebasep1employees.R

class ViewActivity : AppCompatActivity() {

    lateinit var empId: String
    lateinit var etName: EditText
    lateinit var  etAge: EditText
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button
    //val intent = getIntent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view)

        initView()
        setValues()

        btnUpdate.setOnClickListener {
            openUpdateDialog(empId,intent.extras?.getString("name"))
        }
    }

    private fun initView() {
        //val intent = getIntent().getExtras()
        etName = findViewById(R.id.etEditName)
        etAge = findViewById(R.id.etEditAge)
        btnUpdate = findViewById(R.id.btnEditUpdate)
        btnDelete = findViewById(R.id.btnEditDelete)
    }

    private fun setValues() {
        empId = intent.extras?.getString("empID").toString()
        etName.setText(intent.extras?.getString("name"))
        etAge.setText(intent.extras?.getString("age"))
    }2

    fun openUpdateDialog(empId: String?, empName: String?) {}
}