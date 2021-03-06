package com.toshihiro.myapplication.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.toshihiro.myapplication.R
import com.toshihiro.myapplication.activity.TestViewPagerActivity
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_one.view.*
import java.lang.Exception

private const val KEY_STRING: String = "key_string"

class OneFragment : Fragment() {

    companion object{
        fun newInstance(str : String): OneFragment {
            var oneFragment : OneFragment = OneFragment()
            var bundle: Bundle = Bundle()
            bundle.putString(KEY_STRING , str)
            oneFragment.arguments = bundle
            return oneFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_one, container, false)
        var str = arguments!!.getString(KEY_STRING)
        var text = str.split(",")
        Log.i("check",text[0])

        var btnTest = rootView.btnFragTest
        try {
            var testView = activity as TestViewPagerActivity

            btnTest.setOnClickListener {
                var msg = testView.viewGetTest()
                Toast.makeText(activity, "hello : $msg",Toast.LENGTH_SHORT).show()
                Log.d("check", "Helo :$msg")


            }
        }catch (e : Exception){
            Log.d("error : " , e.message)
        }


        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Check", "OnDestroyView");
    }


    fun getMyText(): String {
        if (txtDataFragment1 != null) {
            return txtDataFragment1.text.toString()
        }
        return ""
    }

}
