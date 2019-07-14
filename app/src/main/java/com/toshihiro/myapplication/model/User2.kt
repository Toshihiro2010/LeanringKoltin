package com.toshihiro.myapplication.model

class User2(var name: String = "", var job: String = "", var age: Int = 0){


    fun saveName(name: String){
        this.name = name
    }

    fun saveJob(job: String){
        this.job = job
    }
}
