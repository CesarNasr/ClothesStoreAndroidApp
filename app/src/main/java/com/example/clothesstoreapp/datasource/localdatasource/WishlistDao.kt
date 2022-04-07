package com.example.clothesstoreapp.datasource.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlistitem")
    fun getAll(): List<WishlistItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(wishlistItem : WishlistItem) : Long
}