package com.toshihiro.myapplication.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.toshihiro.myapplication.R
import com.toshihiro.myapplication.database.AppDatabase
import com.toshihiro.myapplication.database.StudentEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_room.*
import java.util.*


class TestRoomActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_room)

        appDatabase = AppDatabase.getAppDatabase(this)
//        myInsert()
//        mySelect()
//        mySelect2()
        mySelect3()
    }

    fun myInsert(){
        val scopeStudent = StudentEntity()
        scopeStudent.code = 56122201044
        scopeStudent.email = "s56122201044@ssru.ac.th"
        scopeStudent.firstName = "Patipan"
        scopeStudent.lastName = "Ongarj"
        scopeStudent.address = "203/566 Moo 1,Teparak Road 10540"

        Observable.fromCallable{ appDatabase?.studentDao()?.insertStudent(scopeStudent)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it -> Log.d("bent","create success")
            },{error -> Log.d("bent", "Unable to update username" + error)})
    }

    @SuppressLint("CheckResult")
    fun mySelect(){
        Observable.fromCallable{ appDatabase!!.studentDao().getStudentAll()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                it.forEach {
                    Log.d("bent" , " " + it.id + " / " + it.firstName)
                }
            }


    }

    fun mySelect2(){
        appDatabase!!.studentDao()
            .getStudentAllLiveData()
            .observe(this,android.arch.lifecycle.Observer {
                Log.d("bent" , "LiveData")
                it?.forEach {
                    Log.d("bent" , " " + it.id + " / " + it.firstName)
                }
                textView3.text = it!!.get(0).firstName
        })
    }

    fun mySelect3(){
        appDatabase!!.studentDao().getStudentAllRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("bent" , "RxJava")
                it?.forEach {
                    Log.d("bentRX" , " " + it.id + " / " + it.firstName)
                }
                textView3.text = it!!.get(1).firstName
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
