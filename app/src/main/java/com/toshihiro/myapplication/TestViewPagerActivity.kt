package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.toshihiro.myapplication.controller.MyPageAdapter
import kotlinx.android.synthetic.main.activity_test_view_pager.*

class TestViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_pager)

        var myPagerAdapter = MyPageAdapter(supportFragmentManager)
        myPager.apply {
            adapter = myPagerAdapter
        }

        btnNext.setOnClickListener{
            var nextPosition = myPager.currentItem + 1
            myPager.currentItem = nextPosition
        }

        btnPrev.setOnClickListener{
            var prevPosition = myPager.currentItem - 1
            myPager.currentItem = prevPosition
        }
    }
}
