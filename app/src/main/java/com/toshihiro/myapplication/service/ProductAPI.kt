package com.toshihiro.myapplication.service

import com.toshihiro.myapplication.model.Product
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductAPI{

    @GET("/category/getCategoryProductBycode")
    fun getProductByCode(
        @Query("full_code") full_code:String
    ) : Observable<Response<Product>>


}