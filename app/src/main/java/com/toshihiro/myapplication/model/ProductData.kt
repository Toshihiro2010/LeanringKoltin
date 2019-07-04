package com.toshihiro.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductData {

    @SerializedName("product_id")
    @Expose
    var productId: String? = null
    @SerializedName("product_name")
    @Expose
    var productName: String? = null
    @SerializedName("images")
    @Expose
    var images: String? = null
    @SerializedName("sale_price")
    @Expose
    var salePrice: String? = null
    @SerializedName("complete_status")
    @Expose
    var completeStatus: String? = null
    @SerializedName("product_status")
    @Expose
    var productStatus: String? = null
    @SerializedName("is_recommend")
    @Expose
    var isRecommend: Any? = null
    @SerializedName("is_newproduct")
    @Expose
    var isNewproduct: String? = null

}