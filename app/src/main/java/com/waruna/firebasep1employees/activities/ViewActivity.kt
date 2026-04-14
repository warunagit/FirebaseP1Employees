package com.waruna.firebasep1employees.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.waruna.firebasep1employees.R
import com.waruna.firebasep1employees.models.EmployeeModel

class ViewActivity : AppCompatActivity() {


    lateinit var receivedEmpId: String
    lateinit var receivedName: String
    lateinit var receivedAge: String
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

        receivedEmpId = intent.extras?.getString("empID").toString()
        receivedName = intent.extras?.getString("name").toString()
        receivedAge = intent.extras?.getString("age").toString()

        setValues(receivedEmpId,receivedName,receivedAge)
    }

    private fun setValues(empID:String, name:String,age: String) {
        empId = empID
        etName.setText(""+name)
        etAge.setText(""+age)
    }

    fun openUpdateDialog(empId: String?, empName: String?) {
        Log.d("Log: ","Starting update")
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update,null)
        mDialog.setView(mDialogView)

        Log.d("Log: ","Inflate done")

        val updateName = mDialogView.findViewById<EditText>(R.id.etUpdateName)
        val updateAge = mDialogView.findViewById<EditText>(R.id.etUpdateAge)
        val btnUpdateUpdate = mDialogView.findViewById<Button>(R.id.btnUpdateUpdate)

        updateName.setText(receivedName.toString())
        updateAge.setText(receivedAge.toString())

        Log.d("Log: ","Setting data to alert done")

        mDialog.setTitle("Updating the record of $receivedName")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateUpdate.setOnClickListener {
            updateEmployeeData(
                receivedEmpId,
                updateName.text.toString(),
                updateAge.text.toString()
            )
            Toast.makeText(this,"Update data done", Toast.LENGTH_LONG)
            setValues(receivedEmpId,updateName.text.toString(),updateAge.text.toString())

            alertDialog.dismiss()
        }

        btnDelete.setOnClickListener {
            Log.d("Log: ","Delete clicked")
            deleteRecord(receivedEmpId);
        }
    }

    fun deleteRecord(receivedEmpId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("employees").child(receivedEmpId)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this,"Deleted successfully!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener {error ->
            Toast.makeText(this,"Error! "+error.message, Toast.LENGTH_LONG).show()
        }
    }

    fun updateEmployeeData(empID: String, name: String, age: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("employees").child(empID)
        val employeeUpdateData = EmployeeModel(empID,name,age)
        dbRef.setValue(employeeUpdateData)

    }
}