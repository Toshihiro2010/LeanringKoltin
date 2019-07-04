package com.toshihiro.myapplication.model

data class Product(
    val message: String,
    val product_data: List<ProductData>,
    val status: String
)