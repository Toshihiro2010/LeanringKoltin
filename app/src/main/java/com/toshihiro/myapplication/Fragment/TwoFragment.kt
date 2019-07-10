package com.toshihiro.myapplication.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.toshihiro.myapplication.R


class TwoFragment : Fragment() {

    companion object{
        fun newInstance(): TwoFragment {
            var twoFragment : TwoFragment = TwoFragment()
            var bundle: Bundle = Bundle()
            return twoFragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

}
