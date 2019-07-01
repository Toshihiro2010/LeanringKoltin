package com.toshihiro.myapplication.database

import android.arch.persistence.room.*
import android.content.Context


@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object{
        fun getAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "Sample.db").build()
    }


//    fun studentDao() : StudentDao

}