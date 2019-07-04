package com.toshihiro.myapplication.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.arch.persistence.room.migration.Migration
import android.content.Context


@Database(entities = arrayOf(StudentEntity::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    companion object{

        val Migration_1_to_2 : Migration = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student ADD COLUMN age INTEGER")
            }
        }


        fun getAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "Sample.db")
                .addMigrations(Migration_1_to_2)
                .build()
    }

    abstract fun studentDao(): StudentDao

}