package com.example.clothesstoreapp.datasource.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BasketDao {

    @Query("SELECT * FROM basketitem")
    fun getAll(): List<BasketItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(basketItem : BasketItem) : Long
}