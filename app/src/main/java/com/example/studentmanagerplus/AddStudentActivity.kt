package com.example.studentmanagerplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var etStudentId: EditText
    private lateinit var etStudentName: EditText
    private lateinit var etStudentPhone: EditText
    private lateinit var etStudentAddress: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        supportActionBar?.title = "Thêm Sinh Viên"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        etStudentId = findViewById(R.id.etStudentId)
        etStudentName = findViewById(R.id.etStudentName)
        etStudentPhone = findViewById(R.id.etStudentPhone)
        etStudentAddress = findViewById(R.id.etStudentAddress)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupListeners() {
        btnSave.setOnClickListener {
            saveStudent()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveStudent() {
        val id = etStudentId.text.toString().trim()
        val name = etStudentName.text.toString().trim()
        val phone = etStudentPhone.text.toString().trim()
        val address = etStudentAddress.text.toString().trim()

        // Validation
        if (id.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập MSSV", Toast.LENGTH_SHORT).show()
            etStudentId.requestFocus()
            return
        }

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

        // Tạo sinh viên mới và trả về MainActivity
        val newStudent = Student(id, name, phone, address)
        val resultIntent = Intent()
        resultIntent.putExtra("NEW_STUDENT", newStudent)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

