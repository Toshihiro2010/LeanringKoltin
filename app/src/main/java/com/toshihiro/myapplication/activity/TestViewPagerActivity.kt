package com.toshihiro.myapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import com.toshihiro.myapplication.Fragment.OneFragment
import com.toshihiro.myapplication.controller.MyPageAdapter
import kotlinx.android.synthetic.main.activity_test_view_pager.*
import com.toshihiro.myapplication.R


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

        btnViewPagerRequest.setOnClickListener {
            var fragment = getActivityFragment(myPager,0)
            var oneFragment = fragment as OneFragment
            if(oneFragment != null){
                var message = oneFragment.getMyText()
                if(message != ""){
                    textView8.text = message
                    Log.i("Check", message)
                }
            }


        }
    }

    fun getActivityFragment(viewPager: ViewPager, position: Int): Fragment? {
        var name = "android:switcher:" + viewPager.id + ":" + position
        return supportFragmentManager.findFragmentByTag(name)
    }

    fun viewGetTest() : String{
        var message = textView8.text.toString()
        return message
    }
}
