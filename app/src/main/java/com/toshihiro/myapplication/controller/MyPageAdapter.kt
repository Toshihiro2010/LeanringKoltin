package com.toshihiro.myapplication.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.toshihiro.myapplication.Fragment.*

class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val PAGE_NUM: Int = 3

    override fun getItem(position: Int): Fragment? {
        if (position == 0) {
            return OneFragment()
        } else if (position == 1) {
            return TwoFragment()
        } else if (position == 2) {
            return ThreeFragment()
        }
        return null
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }

}