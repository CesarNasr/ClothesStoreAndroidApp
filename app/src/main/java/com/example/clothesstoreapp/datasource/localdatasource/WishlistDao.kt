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

    /**
     * I have used flows and coroutines for applying reactive programming where the wishlist and basket badges
     * are always observed form the ROOM database and thus the UI will always be updated when removing/adding items to
     * the database table.
     */

    @Query("SELECT COUNT(*) FROM wishlist")
    fun getWishListCount() : Flow<Int>
}