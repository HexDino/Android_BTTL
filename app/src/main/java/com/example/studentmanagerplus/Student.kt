package com.example.studentmanagerplus

import java.io.Serializable

data class Student(
    val id: String,
    var name: String,
    var phone: String,
    var address: String
) : Serializable

