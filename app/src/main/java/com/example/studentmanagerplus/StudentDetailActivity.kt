package com.example.studentmanagerplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var tvStudentId: TextView
    private lateinit var etStudentName: EditText
    private lateinit var etStudentPhone: EditText
    private lateinit var etStudentAddress: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnCancel: Button
    
    private lateinit var currentStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        supportActionBar?.title = "Chi Tiết Sinh Viên"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeViews()
        loadStudentData()
        setupListeners()
    }

    private fun initializeViews() {
        tvStudentId = findViewById(R.id.tvStudentId)
        etStudentName = findViewById(R.id.etStudentName)
        etStudentPhone = findViewById(R.id.etStudentPhone)
        etStudentAddress = findViewById(R.id.etStudentAddress)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun loadStudentData() {
        currentStudent = intent.getSerializableExtra("STUDENT") as Student
        
        tvStudentId.text = "MSSV: ${currentStudent.id}"
        etStudentName.setText(currentStudent.name)
        etStudentPhone.setText(currentStudent.phone)
        etStudentAddress.setText(currentStudent.address)
    }

    private fun setupListeners() {
        btnUpdate.setOnClickListener {
            updateStudent()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun updateStudent() {
        val name = etStudentName.text.toString().trim()
        val phone = etStudentPhone.text.toString().trim()
        val address = etStudentAddress.text.toString().trim()

        // Validation
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
            etStudentName.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
            etStudentPhone.requestFocus()
            return
        }

        if (address.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show()
            etStudentAddress.requestFocus()
            return
        }

        // Cập nhật thông tin và trả về MainActivity
        currentStudent.name = name
        currentStudent.phone = phone
        currentStudent.address = address

        val resultIntent = Intent()
        resultIntent.putExtra("UPDATED_STUDENT", currentStudent)
        setResult(RESULT_OK, resultIntent)
        
        Toast.makeText(this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

