package com.toshihiro.myapplication.MyListener

import android.service.autofill.UserData


interface MyFragmentListener {
    fun onButtonOkClick(message:String)
    fun onButtonCloseClick()
}