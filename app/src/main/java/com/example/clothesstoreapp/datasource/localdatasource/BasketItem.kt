package com.example.moviesharingapp.data.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem(

    @PrimaryKey
    @ColumnInfo(name = "productId")
    val productId: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "qty")
    val qty: Int = 1
)


