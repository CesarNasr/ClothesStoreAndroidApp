package com.example.clothesstoreapp.datasource.repositoryimpl

import com.example.clothesstoreapp.datasource.model.ApiEntry
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.datasource.network.api.ApiService
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.datasource.localdatasource.AppDatabase
import com.example.clothesstoreapp.datasource.utils.BasketMapper
import com.example.clothesstoreapp.datasource.utils.WishlistMapper
import com.example.clothesstoreapp.datasource.network.utils.Resource
import com.example.clothesstoreapp.datasource.network.utils.ResponseConverter
import javax.inject.Inject

/**
 *  Actual implementation of the repository that communicates with remote source (in our case) and to local database if we had one
 *  Repository acts as a single source of truth for data in our app
 */
class RepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter,
    private val db: AppDatabase,
    private val wishlistMapper: WishlistMapper,
    private val basketMapper : BasketMapper

) : Repository {


    override suspend fun fetchProducts(): Resource<ApiEntry> {
        return responseConverter.responseToResult(
            apiService.fetchProducts()
        )
    }

    override suspend fun fetchWishlistItems(): List<Product> {
        return wishlistMapper.mapFromEntityList(db.WishlistDao().getAll())
    }

    override suspend fun insertWishlistItem(item: Product): Long {
        return db.WishlistDao().insertItem(wishlistMapper.mapToEntity(item))
    }

    override suspend fun fetchBasketItems(): List<Product> {
        return basketMapper.mapFromEntityList(db.BasketDao().getAll())
    }

    override suspend fun insertBasketItem(item: Product): Long {
        return db.BasketDao().insertItem(basketMapper.mapToEntity(item))
    }

}