package com.example.clothesstoreapp.datasource.localdatasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist")
    fun getAll(): List<Wishlist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(wishlist : Wishlist) : Long

    @Delete
    fun deleteItem(wishlist : Wishlist)

    @Query("SELECT COUNT(*) FROM wishlist")
    fun getWishListCount() : Flow<Int>
}