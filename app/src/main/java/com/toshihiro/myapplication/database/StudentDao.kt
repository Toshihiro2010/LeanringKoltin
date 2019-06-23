package com.toshihiro.myapplication.database

import android.arch.persistence.room.*

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(studentEntity: StudentEntity)

    @Update
    fun updateStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

    @Query("Select * FROM student")
    fun getStudentAll():List<StudentEntity>

    @Query("Select * FROM student WHERE student.email Like:email")
    fun getStudentByEmail(email:String):StudentEntity

    @Query("DELETE FROM student")
    fun deleteTable()
}