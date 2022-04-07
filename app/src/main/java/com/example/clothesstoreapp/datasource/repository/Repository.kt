package com.example.clothesstoreapp.datasource.repository

import com.example.clothesstoreapp.datasource.model.ApiEntry
import com.example.clothesstoreapp.datasource.model.Product
import com.example.tandemapp.network.utils.Resource

/**
 * Repository interface for the purpose of abstraction between data layer and another presentation layer
 */

interface Repository {

    /**
     * Fetch data from remote datasource
     */
    suspend fun fetchProducts(): Resource<ApiEntry>

    /**
     * Fetch and Insert data to local datasource of wishlist table
     */
    suspend fun fetchWishlistItems(): List<Product>
    suspend fun insertWishlistItem(item : Product): Long


    /**
     * Fetch and Insert data to local datasource of basket table
     */
    suspend fun fetchBasketItems(): List<Product>
    suspend fun insertBasketItem(item : Product): Long
}