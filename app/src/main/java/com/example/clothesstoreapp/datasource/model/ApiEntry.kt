package com.example.clothesstoreapp.datasource.model


import com.google.gson.annotations.SerializedName

data class ApiEntry(
    @SerializedName("products")
    val products: List<Product>
)