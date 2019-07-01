package com.toshihiro.myapplication

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toshihiro.myapplication.database.AppDatabase
import com.toshihiro.myapplication.database.StudentEntity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_room.*
import java.util.*


class TestRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_room)

//        myInsert()
        mySelect()
    }

    fun myInsert(){
//        var appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "db_app").build()
//        Observable.fromCallable { AppDatabase.getAppDatabase(this) }
//        Observable.fromCallable {Log.d("Bent", "create DB") }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{
//                Log.d("Bent", "create Complete")
//            }



        val scopeStudent = StudentEntity()

        scopeStudent.code = 56122201044
        scopeStudent.email = "s56122201044@ssru.ac.th"
        scopeStudent.firstName = "Patipan"
        scopeStudent.lastName = "Ongarj"
        scopeStudent.address = "203/566 Moo 1,Teparak Road 10540"

        Observable.fromCallable{ AppDatabase.getAppDatabase(this).studentDao().insertStudent(scopeStudent)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it -> Log.d("bent","create success")
            },{error -> Log.d("bent", "Unable to update username" + error)})

//        Flowable.fromCallable{appDatabase.studentDao().insertStudent(scopeStudent)}
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{
//                Log.d("Bent", "Insert Complete")
//            }
    }

    fun mySelect(){
        Observable.fromCallable{ AppDatabase.getAppDatabase(this).studentDao().getStudentAll()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it.forEach {
                    Log.d("bent" , " " + it.id + " / " + it.firstName)
                }
            }
    }
    fun getNameList(): List<String> {
        return Arrays.asList(
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "Kitkat",
            "Lollipop",
            "Marshmallow",
            "Nugat"
        )
    }
}
