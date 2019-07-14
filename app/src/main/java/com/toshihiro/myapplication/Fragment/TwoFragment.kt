package com.toshihiro.myapplication.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.toshihiro.myapplication.MyListener.MyFragmentListener

import com.toshihiro.myapplication.R
import kotlinx.android.synthetic.main.fragment_two.view.*


class TwoFragment : Fragment() {

    var listener: MyFragmentListener? = null

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
        var rootView = inflater.inflate(R.layout.fragment_two, container, false)
        var btnAdd = rootView.btnFragTwoAdd
        var btnDell = rootView.btnFragTwoDel

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnFragTwoAdd.setOnClickListener{
            listener?.onButtonOkClick("Love Pang")
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = activity as MyFragmentListener
        } catch (e: ClassCastException) {
            Log.e("bent=>", "error : " + e.message)

        }
    }
}
