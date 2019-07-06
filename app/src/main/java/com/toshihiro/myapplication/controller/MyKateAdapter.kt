package com.toshihiro.myapplication.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.toshihiro.myapplication.R
import com.toshihiro.myapplication.model.ProductData
import kotlinx.android.synthetic.main.my_cardview.view.*
import kotlinx.android.synthetic.main.product_item.view.*


class MyKateAdapter(var items:List<String>) : RecyclerView.Adapter<MyKateAdapter.ViewHolder>(){

    var mListener: itemclickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var holder : ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.my_cardview, parent, false))

        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
//        holder.itemView.setOnClickListener({
//            if(mListener != null){
//                mListener?.onItemClick(items.get(position))
//            }
//        })

    }

    fun mySetItemListener(listener : itemclickListener){
        mListener = listener
    }

    interface itemclickListener{
        fun onItemClick(myProduct:ProductData)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(){
            var pathImg = "https://pbs.twimg.com/media/D-zgo77UIAUC__9.jpg"
            itemView.apply {
                Glide.with(itemView.context)
                    .load(pathImg)
                    .centerCrop()
                    .into(imageView2)
            }

        }
    }

}
