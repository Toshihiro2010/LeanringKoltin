package com.toshihiro.myapplication.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.toshihiro.myapplication.R


class ThreeFragment : Fragment() {

    companion object{
        fun newInstance(): ThreeFragment {
            var threeFragment : ThreeFragment = ThreeFragment()
            var bundle: Bundle = Bundle()
            return threeFragment
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
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

}
