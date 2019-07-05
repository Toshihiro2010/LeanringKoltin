package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.toshihiro.myapplication.controller.MyProductAdapter
import com.toshihiro.myapplication.model.ProductData
import com.toshihiro.myapplication.service.GithubAPI
import com.toshihiro.myapplication.service.ProductAPI
import com.toshihiro.myapplication.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_recycle.*
import retrofit2.Retrofit
import java.util.*

class TestRecycleActivity : AppCompatActivity() {

    var productArrayList: List<ProductData>? = null
    var retrofitClient: Retrofit? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycle)
        retrofitClient = RetrofitClient().getRetrofitClient()
        getData()
    }

    fun getData(){
        var dataAPI = retrofitClient!!.create(ProductAPI::class.java)
        dataAPI.getProductByCode("")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if(it.isSuccessful){
                    var data = it.body()!!.product_data
                    Log.d("bent=>" , it.body()!!.message + " ")
                    productArrayList = data
                    setData()
                }
            },{
                Log.d("bent error=>", it.message)

            })

    }

    fun setData(){
        var linearLayoutManager = LinearLayoutManager(this)
        var productAdapter = MyProductAdapter(productArrayList!!)
        productAdapter.mySetItemListener(object :MyProductAdapter.itemclickListener{
            override fun onItemClick(myProduct: ProductData) {
                Toast.makeText(this@TestRecycleActivity,"id : " + myProduct.productId ,Toast.LENGTH_SHORT).show()
            }
        })
        recycleProdustList.apply {
            layoutManager = linearLayoutManager
            adapter = productAdapter

        }
    }
}
