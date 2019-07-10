package com.toshihiro.myapplication.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.toshihiro.myapplication.Fragment.*

class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val PAGE_NUM: Int = 3

    override fun getItem(position: Int): Fragment? {
        if (position == 0) {
            Log.i("Check", "Get Item 0")
            return OneFragment.newInstance("Android,Development")
        } else if (position == 1) {
            return TwoFragment.newInstance()
        } else if (position == 2) {
            return ThreeFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }

}