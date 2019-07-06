package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.toshihiro.myapplication.controller.MyKateAdapter
import kotlinx.android.synthetic.main.activity_test_card_view.*

class TestCardViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_card_view)
        var data: List<String> = listOf("Bent", "Toshihiro", "test", "Hello", "Jabaja")
        var kateAdapter : MyKateAdapter = MyKateAdapter(data)
        var linearLayoutManager = LinearLayoutManager(this)
        recycle_test_card.apply {
            adapter = kateAdapter
            layoutManager = linearLayoutManager
        }
    }
}
