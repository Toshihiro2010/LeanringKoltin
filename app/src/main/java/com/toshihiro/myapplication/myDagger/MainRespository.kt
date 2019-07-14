package com.toshihiro.myapplication.myDagger

import com.toshihiro.myapplication.model.Profile
import com.toshihiro.myapplication.model.User
import com.toshihiro.myapplication.model.User2

class MainRespository(var user2 : User2) {


    fun saveUser(){
        user2.saveName("Toshihiro")
        user2.saveJob("Developer")

    }
}