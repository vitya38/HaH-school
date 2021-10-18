package com.example.lesson_1_lukin

class Student(
    val id: Long,
    val name: String,
    val surname: String,
    val grade: String,
    val birthdayYear: Int
) {
    fun print(): String {
        return id.toString() + " " + name + " " + surname +
            " " + grade + " " + birthdayYear.toString()
    }
}