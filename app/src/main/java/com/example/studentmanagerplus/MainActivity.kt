package com.example.studentmanagerplus

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvStudents: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = mutableListOf<Student>()

    companion object {
        const val REQUEST_ADD_STUDENT = 1
        const val REQUEST_UPDATE_STUDENT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        addSampleData()
    }

    private fun setupRecyclerView() {
        rvStudents = findViewById(R.id.rvStudents)
        
        studentAdapter = StudentAdapter(studentList) { student ->
            // Mở activity chi tiết khi click vào sinh viên
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("STUDENT", student)
            startActivityForResult(intent, REQUEST_UPDATE_STUDENT)
        }

        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = studentAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_student -> {
                // Mở activity thêm sinh viên
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD_STUDENT)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_ADD_STUDENT -> {
                    val newStudent = data.getSerializableExtra("NEW_STUDENT") as? Student
                    newStudent?.let {
                        studentAdapter.addStudent(it)
                    }
                }
                REQUEST_UPDATE_STUDENT -> {
                    val updatedStudent = data.getSerializableExtra("UPDATED_STUDENT") as? Student
                    updatedStudent?.let {
                        studentAdapter.updateStudent(it)
                    }
                }
            }
        }
    }

    private fun addSampleData() {
        val sampleStudents = listOf(
            Student("20200001", "Nguyễn Văn A", "0901234567", "Hà Nội"),
            Student("20200002", "Trần Thị B", "0902345678", "Hồ Chí Minh"),
            Student("20200003", "Lê Văn C", "0903456789", "Đà Nẵng"),
            Student("20200004", "Phạm Thị D", "0904567890", "Hải Phòng"),
            Student("20200005", "Hoàng Văn E", "0905678901", "Cần Thơ")
        )
        studentList.addAll(sampleStudents)
        studentAdapter.notifyDataSetChanged()
    }
}

